package ptithcm.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.Recaptra.XacMinhRecaptcha;
import ptithcm.bean.Mailer;
import ptithcm.entity.CongThuc;
import ptithcm.entity.NhanVien;
import ptithcm.entity.TaiKhoan;
import ptithcm.entity.TraSua;

import ptithcm.entity.VaiTro;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;


@Controller
@Transactional
@RequestMapping("taikhoan/")
public class TaiKhoanController {
	
	@RequestMapping("index")
	public String index(ModelMap m) {
		return "home/nhanvienview";
	}

	// login
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(ModelMap m) {
		m.addAttribute("taikhoan", new TaiKhoan());
		return "home/login";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		// remove all session
		
		session.removeAttribute("userLogin");

		return "redirect:/";
	}

	@Autowired
	SessionFactory factory;

	public List<TaiKhoan> getDstaikhoan() {
		Session session = factory.getCurrentSession();
		String sql = "FROM TaiKhoan";
		Query query = session.createQuery(sql);
		List<TaiKhoan> dsTaiKhoan = query.list();
		return dsTaiKhoan;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginValidation(ModelMap m, @ModelAttribute("taikhoan") TaiKhoan tk, BindingResult errors,
			HttpSession session2, HttpServletRequest request) {
		String gRecaptcha = request.getParameter("g-recaptcha-response"); // recapcha của google có cái param ten vay
		boolean check = XacMinhRecaptcha.xacminh(gRecaptcha);
		if (tk.getTentaikhoan().trim().length() == 0) {
			errors.rejectValue("tentaikhoan", "tk", "Username cannot be blank");
		}
		if (tk.getPassword().trim().length() == 0) {
			errors.rejectValue("password", "tk", "Password cannot be blank");
		}
		if (errors.hasErrors()) {
			return "home/login";
		}
		else {
			if(check == false) {
				m.addAttribute("reCaptra","Vui lòng nhập capcha");
				return "home/login";
			}
			String tentaikhoan = tk.getTentaikhoan();
			String password = tk.getPassword();
			String pass = this.encryptPassword(password);
			Session session = factory.getCurrentSession();
			String hql = "FROM TaiKhoan t WHERE t.tentaikhoan = :tentaikhoan AND t.password = :password";
			Query query = session.createQuery(hql);
			query.setParameter("tentaikhoan", tentaikhoan);
			query.setParameter("password", pass);
			List<TaiKhoan> list = query.list();

			if (list.size() > 0) {
				if (list.get(0).getTrangthai() == 0) {
					m.addAttribute("message", "Your account is blocked. Please contact with the admin to open");
					return "home/login";
				} else {
					session2.setAttribute("userLogin", list.get(0));
					if (list.get(0).getVaitro().getMavt() == 1) { // nhan vien
						return "home/nhanvienview";
					}
					return "home/quanliview";
				}
			} else {
				m.addAttribute("message", "Invalid email or password");
				return "home/login";
			}
		}
	}

	public String encryptPassword(String password) {
		try {
			// su dung class MessageDigest
			MessageDigest md = MessageDigest.getInstance("MD5"); // tao 1 ham ban su dung thuat toan MD5
			md.update(password.getBytes()); // no nhan dau vao la 1 mang byte, nhung password cua ta la dang chu so nen ta chuyen password sang  dang byte
			byte[] digest = md.digest(); // thuc hien bam, ket qua la 1 mang byte da duoc bam
			String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase(); // chuyen mang da duoc bam sang dang hexa in hoa
			return myHash;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// logout
	@RequestMapping("quenmatkhau")
	public String quenmatkhau() {

		return "home/quenmatkhau";
	}

	public String taoMatKhau() { // tu 100000 - 999999
		Random generator = new Random();
		int value = generator.nextInt((999999 - 100000) + 1) + 100000;
		return value + "";
	}

	@Autowired
	Mailer mailer;

	@RequestMapping(value = "quenmatkhau", method = RequestMethod.POST)
	public String datLaiMK(@RequestParam("tentaikhoan") String tentaikhoan, ModelMap m) {
		if (tentaikhoan.trim().length() == 0) {
			m.addAttribute("message", "Email cannot blank");
			return "home/quenmatkhau";
		}

		else {

			Session session = factory.getCurrentSession();
			String hql = "FROM TaiKhoan WHERE tentaikhoan = :tentaikhoan";
			Query query = session.createQuery(hql);
			query.setParameter("tentaikhoan", tentaikhoan);
			List<TaiKhoan> list = query.list();
			if (list.size() == 0) {
				m.addAttribute("message", "Invalid Email!");
			} else {
				String newpass = this.taoMatKhau();
				String mk = this.encryptPassword(newpass);
				// Cập nhật mật khẩu trong csdl
				session = factory.openSession();
				Transaction t = session.beginTransaction();
				try {
					TaiKhoan tk = list.get(0);
					tk.setPassword(mk);
					session.update(tk);
					t.commit();

				} catch (Exception e) {
					t.rollback();
					System.out.println("Update k thành công");
				} finally {
					session.close();
				}

				// gửi mail

				try {
					mailer.send("vngan240005@gmail.com", tentaikhoan, "Mật khẩu mới của bạn là: ", newpass);
					m.addAttribute("message", "Check mail to get your new password");
				} catch (Exception e) {
					m.addAttribute("message", "Gửi mail thất bại.");
				}
			}
			return "home/quenmatkhau";
		}
	}

	public List<TaiKhoan> getDstk() {
		Session session = factory.getCurrentSession();
		String sql = "FROM TaiKhoan";
		Query query = session.createQuery(sql);
		List<TaiKhoan> dstk = query.list();
		return dstk;
	}

	public List<NhanVien> getDsnv() {
		Session session = factory.getCurrentSession();
		String sql = "FROM NhanVien";
		Query query = session.createQuery(sql);
		List<NhanVien> dsnv = query.list();
		return dsnv;
	}

	@RequestMapping("danhsachtaikhoan.htm")
	public String danhsachtaikhoan(ModelMap m) {
		m.addAttribute("dstk", this.getDstaikhoan());
		return "home/danhsachtaikhoan";
	}

//	@RequestMapping("danhsachnhanvien.htm")
//	public String danhsachnhanvien(ModelMap m) {
//		m.addAttribute("dsnv", this.getDsnv());
//
//		return "home/danhsachnhanvien";
//	}
	@RequestMapping("danhsachnhanvien.htm")
	public String danhsachnhanvien(ModelMap m,  HttpServletRequest request) {
		List<NhanVien> ds = this.getDsnv();
		PagedListHolder pagedListHolder = new PagedListHolder(ds);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(2);// So trang hien len
		pagedListHolder.setPageSize(5); // so dong
		m.addAttribute("pagedListHolder", pagedListHolder);

		return "home/danhsachnhanvien";
	}
	

	public int khoaHoacMoKhoa(int matk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM TaiKhoan where matk = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", matk);
		TaiKhoan ts = (TaiKhoan) query.list().get(0);
		if (ts.getTrangthai() == 1) {
			ts.setTrangthai(0);
		} else {
			ts.setTrangthai(1);
		}
		try {
			session.update(ts);
			t.commit();
			System.out.println("in");
			return 1;
		} catch (Exception ex) {
			t.rollback();
			return 0;
		}
	}

	public int xoaTaiKhoanNhanVien(int matk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM NhanVien where matk = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", matk);
		NhanVien nv = (NhanVien) query.list().get(0);
		nv.setTaikhoan(null);
		try {
			session.update(nv);
			t.commit();
			System.out.println("ok");
			return 1;

		} catch (Exception ex) {
			t.rollback();
			System.out.println("no");
			return 0;
		}
	}

	public int themTaiKhoanNhanVien(int manv, int matk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM NhanVien where manv = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", manv);
		NhanVien nv = (NhanVien) query.list().get(0);
		TaiKhoan tk = new TaiKhoan();
		tk.setMatk(matk);
		nv.setTaikhoan(tk);
		try {
			session.update(nv);
			t.commit();

			return 1;

		} catch (Exception ex) {
			t.rollback();

			return 0;
		}
	}

	public int xoaTaiKhoan(int matk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM TaiKhoan where matk = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", matk);
		TaiKhoan tk = (TaiKhoan) query.list().get(0);
		try {
			session.delete(tk);
			t.commit();
			return 1;
		} catch (Exception ex) {
			t.rollback();
			return 0;
		}
	}

	@RequestMapping("khoahoacmokhoataikhoan/{matk}.htm")
	public String khoataikhoan(ModelMap m, @PathVariable("matk") int matk) {
		if (this.khoaHoacMoKhoa(matk) == 1) {
			m.addAttribute("message", "Thao tác thành công");
		} else {
			m.addAttribute("message", "Thao tác thất bại");
		}
		return danhsachtaikhoan(m);
	}

	@RequestMapping("xoataikhoan/{matk}.htm")
	public String xoataikhoan(ModelMap m, @PathVariable("matk") int matk) {
		if (this.xoaTaiKhoanNhanVien(matk) == 1)
			if (this.xoaTaiKhoan(matk) == 1)
				m.addAttribute("message", "Xóa thành công");
			else {

				m.addAttribute("message", "Xóa thất bại");
			}
		else {
			m.addAttribute("message", "Xóa tai khoan nhan vien thất bại");
		}
		return danhsachtaikhoan(m);
	}

	@RequestMapping("themnhanvien.htm")
	public String themnhanvien(ModelMap m) {
		m.addAttribute("nv", new NhanVien());
		return "home/themnhanvien";
	}

	public String chuannHoa(String st) {
		st = st.trim().toLowerCase();
		st = st.replaceAll("\\s+", " ");
		String[] temp = st.split(" ");
		// sau khi tách xong, gán xâu st thành sâu rỗng
		st = "";
		for (int i = 0; i < temp.length; i++) {
			st += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
			if (i < temp.length - 1) // nếu tempt[i] không phải từ cuối cùng
				st += " "; // cộng thêm một khoảng trắng
		}
		return st;
	}

	public int themNhanVien(NhanVien nv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(nv);
			t.commit();
			return 1;
		} catch (Exception e) {
			t.rollback();
			return 0;
		}
	}

	@RequestMapping(value = "themnhanvien.htm", method = RequestMethod.POST)
	public String themnhanvien( @ModelAttribute("nv") NhanVien nv, HttpServletRequest request, BindingResult errors, ModelMap m) {
		if (nv.getHo().trim().length() == 0)
			m.addAttribute("message", "khong de trong ho");
		else if (nv.getTen().trim().length() == 0)
			m.addAttribute("message", "khong de trong ten");
		else if (nv.getDiachi().trim().length() == 0)
			m.addAttribute("message", "khong de trong diachi");
		else if (nv.getSdt().trim().length() == 0)
			m.addAttribute("message", "khong de trong sdt");
		else if(checkSdtTonTai(nv.getSdt()) == 1) {
			m.addAttribute("message", "Số điện thoại này đã được đăng kí");
		}
		else {
			nv.setHo(this.chuannHoa(nv.getHo()));
			nv.setTen(this.chuannHoa(nv.getTen()));
			nv.setDiachi(this.chuannHoa(nv.getDiachi()));
			nv.setSdt(this.chuannHoa(nv.getSdt()));
			if (this.themNhanVien(nv) == 1) {
				m.addAttribute("message", "them nhan vien thanh cong");
				return danhsachnhanvien(m, request);
			} else
				m.addAttribute("message", "them nhan vien that bai");
		}
		return "home/themnhanvien";
	}

	public List<VaiTro> getDsvt() {
		Session session = factory.getCurrentSession();
		String hql = "FROM VaiTro";
		Query query = session.createQuery(hql);
		List<VaiTro> ds = query.list();
		return ds;
	}

	public List<TaiKhoan> getTaiKhoan(String tentaikhoan) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TaiKhoan where tentaikhoan = :tentaikhoan";
		Query query = session.createQuery(hql);
		query.setParameter("tentaikhoan", tentaikhoan);
		List<TaiKhoan> dstk = query.list();
		return dstk;
	}

	public TaiKhoan getTaiKhoan(int matk) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TaiKhoan where matk = :matk";
		Query query = session.createQuery(hql);
		query.setParameter("matk", matk);
		TaiKhoan tk = (TaiKhoan) query.list().get(0);
		return tk;
	}

	public int checkTaiKhoanTonTai(String tentaikhoan) {
		return this.getTaiKhoan(tentaikhoan).size() == 0 ? 0 : 1;
	}
	
	public int checkSdtTonTai( String sdt) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVien where sdt = :sdt";
		Query query = session.createQuery(hql);
		query.setParameter("sdt", sdt);
		List<NhanVien> l =  query.list();
		return l.size() > 0 ? 1:0;
	}
	
	

	public int themTaiKhoan(TaiKhoan tk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(tk);
			t.commit();
			return 1;
		} catch (Exception e) {
			t.rollback();
			return 0;
		}
	}

	@RequestMapping(value = "themtaikhoan/{manv}.htm")
	public String themtaikhoan(ModelMap m, @PathVariable("manv") int manv) {
		m.addAttribute("manv", manv);
		m.addAttribute("tk", new TaiKhoan());
		m.addAttribute("dsvt", this.getDsvt());

		return "home/themtaikhoan";
	}

	@RequestMapping(value = "themtaikhoan/{manv}.htm", method = RequestMethod.POST)
	public String themtaikhoan(ModelMap m, @PathVariable("manv") int manv, @ModelAttribute("tk") TaiKhoan tk) {

		if (tk.getTentaikhoan().trim().length() == 0)
			m.addAttribute("message", "khong de trong ten tai khoan");
		else if (checkTaiKhoanTonTai(tk.getTentaikhoan()) == 1)
			m.addAttribute("message", "tai khoan nay da ton tai");
	
		else {
			String newpass = this.taoMatKhau();
			String mk = this.encryptPassword(newpass);
			tk.setPassword(mk);
			NhanVien nv = new NhanVien();
			nv.setManv(manv);
			tk.setNhanvien(nv);
			tk.setNgaythem(new Date());
			tk.setTrangthai(1);

			if (this.themTaiKhoan(tk) == 1) {
				if (this.themTaiKhoanNhanVien(manv, tk.getMatk()) == 1) {
					m.addAttribute("message", "them tai khoan thanh cong. Check mail để biết mật khẩu");
					m.addAttribute("dsnv", this.getDsnv());
					return "home/danhsachnhanvien";
				}
			} else
				m.addAttribute("message", "them tai khoan that bai");
		}
		return themtaikhoan(m, manv);
	}

	public int edittaikhoan(TaiKhoan tk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(tk);
			t.commit();
			return 1;

		} catch (Exception ex) {
			t.rollback();
			return 0;
		}
	}

	@RequestMapping(value = "edittaikhoan/{matk}.htm")
	public String edittaikhoan(ModelMap m, @PathVariable("matk") int matk) {
		m.addAttribute("tk", this.getTaiKhoan(matk));
		m.addAttribute("dsvt", this.getDsvt());
		return "home/edittaikhoan";
	}

	@RequestMapping(value = "edittaikhoan/{matk}.htm", method = RequestMethod.POST) // load lai 2 lan de thay vai tro
																					// thay doi
	public String edittaikhoan(ModelMap m, @ModelAttribute("tk") TaiKhoan tk, @PathVariable("matk") int matk) {
		tk.setPassword(this.getTaiKhoan(matk).getPassword());

		if (this.edittaikhoan(tk) == 1) {
			m.addAttribute("message", "Edit thanh cong");
			return danhsachtaikhoan(m);
		} else {
			m.addAttribute("message", "Edit that bai");
		}

		m.addAttribute("dsvt", this.getDsvt());
		return "home/edittaikhoan";

	}
}
