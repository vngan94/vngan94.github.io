package ptithcm.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ptithcm.entity.ChiTietSize;
import ptithcm.entity.CongThuc;

import ptithcm.entity.NguyenLieu;

import ptithcm.entity.Size;
import ptithcm.entity.TaiKhoan;
import ptithcm.entity.TraSua;
import ptithcm.bean.UploadFile;

@Controller
@Transactional
@RequestMapping("sanpham/")
public class TraSuaController {
	@RequestMapping("danhsachsanpham")
	public String index(ModelMap m) {
		m.addAttribute("danhsachtrasua", this.getDsTraSua());
		return "sanpham/danhsach";
	}

	@Autowired
	SessionFactory factory;

	public List<TraSua> getDsTraSua() {
		Session session = factory.getCurrentSession();
		String sql = "FROM TraSua";
		Query query = session.createQuery(sql);
		List<TraSua> dsTraSua = query.list();
		return dsTraSua;
	}

	public List<ChiTietSize> getDsctsize(int matrasua) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietSize where mats = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", matrasua);
		List<ChiTietSize> dscts = query.list();
		return dscts;
	}

	public TraSua getTraSua(int matrasua) {
		Session session = factory.getCurrentSession();
		String hql2 = "FROM TraSua where mats = :ma";
		Query query = session.createQuery(hql2);
		query.setParameter("ma", matrasua);
		TraSua ts = (TraSua) query.list().get(0);
		return ts;
	}

	public List<CongThuc> getDsct(int matrasua) {
		Session session = factory.getCurrentSession();
		String hql3 = "FROM CongThuc where mats = :ma";
		Query query = session.createQuery(hql3);
		query.setParameter("ma", matrasua);
		List<CongThuc> dsct = query.list();
		return dsct;
	}

	public List<Size> getDsSize() {
		Session session = factory.getCurrentSession();
		String hql3 = "FROM Size";
		Query query = session.createQuery(hql3);
		List<Size> dss = query.list();
		return dss;
	}

	public List<NguyenLieu> getDsNguyenLieu() {
		Session session = factory.getCurrentSession();
		String hql3 = "FROM NguyenLieu";
		Query query = session.createQuery(hql3);
		List<NguyenLieu> dsnl = query.list();
		return dsnl;
	}

	public int getSoluong(int matrasua) {
		Session session = factory.getCurrentSession();
		String hql3 = "FROM CongThuc where mats = :ma";
		Query query = session.createQuery(hql3);
		query.setParameter("ma", matrasua);
		List<CongThuc> dsct = query.list();
		return dsct.size();
	}

	public void xoaDsct(int matrasua) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CongThuc where mats = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", matrasua);
		List<CongThuc> dsct = query.list();
		for (CongThuc ct : dsct) {
			session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.delete(ct);
				
				t.commit();
				session.close();

			} catch (Exception ex) {
				t.rollback();
				session.close();

			}

		}
	}

	public void xoaDsctsize(int matrasua) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietSize where mats = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", matrasua);
		List<ChiTietSize> dscts = query.list();
		for (ChiTietSize cts : dscts) {
			session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.delete(cts);
				t.commit();
				session.close();
			} catch (Exception ex) {
				t.rollback();
				session.close();

			}

		}
	}

	public void xoaTraSua(int matrasua) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM TraSua where mats = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", matrasua);
		TraSua ts = (TraSua) query.list().get(0);
		try {
			session.delete(ts);
			t.commit();
			session.close();

		} catch (Exception ex) {
			t.rollback();
			session.close();

		}

	}

	@RequestMapping("chitetsanpham/{mats}.htm")
	public String chitietsanphamcheck(ModelMap m, @PathVariable("mats") int matrasua, HttpSession session) {
		TraSua ts = this.getTraSua(matrasua);
		m.addAttribute("trasua", ts);
		m.addAttribute("dscts", ts.getDscts());
		m.addAttribute("dsct", ts.getDsct());
		TaiKhoan tk = (TaiKhoan) session.getAttribute("userLogin");
		return tk.getVaitro().getMavt()==1 ? "sanpham/chitietsanphamnv":"sanpham/chitietsanpham";
	}
	
	public String chitietsanpham(ModelMap m, @PathVariable("mats") int matrasua) {
		TraSua ts = this.getTraSua(matrasua);
		m.addAttribute("trasua", ts);
		m.addAttribute("dscts", ts.getDscts());
		m.addAttribute("dsct", ts.getDsct());
		return "sanpham/chitietsanpham";
	}

	@RequestMapping("khoahoacmokhoa/{mats}.htm")
	public String khoahoacmokhoa(ModelMap m, @PathVariable("mats") int matrasua) {
		if (this.khoahoamokhoa(matrasua) == 1) {
			m.addAttribute("message", "Trạng thái đã được thay đổi");

		} else {
			m.addAttribute("message", "Thao tác thất bại");
		}
		return chitietsanpham(m, matrasua);
	}

	public int khoahoamokhoa(int matrasua) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM TraSua where mats = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", matrasua);
		TraSua ts = (TraSua) query.list().get(0);
		if (ts.getTrangthai() == 1) { // neu mo ban 
			ts.setTrangthai(0); // thi ngung ban
		} else {
			ts.setTrangthai(1); // thi mo ban
		}
		try {
			session.update(ts);
			t.commit();
			session.close();
			return 1;
		} catch (Exception ex) {
			t.rollback();
			session.close();
			return 0;
		}

	}
	public int kiemTraxoats(TraSua ts) {
	
		
		return ts.getDscthd().size() == 0 ? 1 : 0;
	}

	@RequestMapping("xoa/{mats}.htm")
	public String xoa(ModelMap m, @PathVariable("mats") int matrasua) {
		TraSua ts = this.getTraSua(matrasua);
		if(this.kiemTraxoats(ts) == 1) {
			this.xoaDsctsize(matrasua);
			this.xoaDsct(matrasua);
			this.xoaTraSua(matrasua);
		}
		else {
			m.addAttribute("message","không xóa được vì mất dữ liệu hóa đơn");
			return chitietsanpham(m, matrasua);
			
			
		}
		m.addAttribute("danhsachtrasua", this.getDsTraSua());
		return "sanpham/danhsach";
	}

	public int fullsize(TraSua ts) {
		return ts.getDscts().size() == this.getDsSize().size() ? 1 : 0;
	}

	public int fullnguyenlieu(TraSua ts) {
		return ts.getDsct().size() == this.getDsNguyenLieu().size() ? 1 : 0;
	}

	@RequestMapping(value = "quanli/edit/{mats}.htm", method = RequestMethod.GET)
	public String edit(ModelMap m, @PathVariable("mats") int matrasua) {
		TraSua ts = this.getTraSua(matrasua);
		m.addAttribute("trasua", ts);
		return "sanpham/edit";
	}

	@Autowired
	ServletContext context;

	@Autowired
	@Qualifier("uploadfile")
	UploadFile baseUploadfile;

	@RequestMapping("quanli/edit/{mats}.htm")
	public String update(ModelMap m, @ModelAttribute("trasua") TraSua trasua, BindingResult errors,
			@RequestParam("photo") MultipartFile photo) {
		TraSua tam = new TraSua(this.getTraSua(trasua.getMats()));
		
		if (trasua.getTents().length() == 0) {	// kiem tra du lieu dau vao
			errors.rejectValue("tents", "trasua", "Vui long nhap ten");
		}
		if (trasua.getMota().trim().length() == 0) {
			errors.rejectValue("mota", "trasua", "Vui long nhap mo ta");
		}
		if (trasua.getGia() == null) {
			errors.rejectValue("gia", "trasua", "Vui long nhap gia");
		}

		if (errors.hasErrors()) {
			trasua.setHinhanh(tam.getHinhanh());
			m.addAttribute("trasua", trasua);
			m.addAttribute("message", "chinh sua that bai");
			return "sanpham/edit";
		}
		if (photo.isEmpty()) { // du lieu dung, khong chen hinh anh -> van update
			trasua.setHinhanh(tam.getHinhanh());
			trasua.setTents(this.chuannHoa(trasua.getTents()));
			trasua.setMota(this.chuannHoa(trasua.getMota()));
			this.edittrasua(trasua);
			m.addAttribute("message", "edit thanh cong");
			m.addAttribute("trasua", trasua);
		} else {	// co chen anh - update
			try {
				String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
				String fileName = date + photo.getOriginalFilename(); // ten file moi: lay ten file goc + thoi gian up len de ma tranh trung ten voi cac file khac, neu trung thi file moi se de thay the file cu
				String photoPath = baseUploadfile.getBasePath() + File.separator + fileName; // duong dan luu file: noi luu + , file.separator la dau / + ten file
				photo.transferTo(new File(photoPath)); // chuyen file den duong dan moi
			
				trasua.setHinhanh(fileName);
				trasua.setTents(this.chuannHoa(trasua.getTents()));
				trasua.setMota(this.chuannHoa(trasua.getMota()));
				if (this.edittrasua(trasua) == 1) {
					m.addAttribute("message", "Thành công");
					m.addAttribute("trasua", trasua);
				} else {
					m.addAttribute("message", "Thất bại");
					m.addAttribute("trasua", tam);
				}
			} catch (Exception e) {
				m.addAttribute("message", "lỗi lưu file!");
				m.addAttribute("trasua", tam);
			}
		}
		return "sanpham/edit";

	}

	public int edittrasua(TraSua trasua) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(trasua);
			t.commit();
			session.close();
			return 1;

		} catch (Exception ex) {
			t.rollback();
			System.out.println(ex);
			session.close();
			return 0;
		}

	}

	public int editCongThuc(CongThuc ct) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(ct);
			t.commit();
			session.close();
			return 1;

		} catch (Exception ex) {
			t.rollback();
			System.out.println(ex);
			session.close();
			return 0;
		}
	}

	@RequestMapping(value = "quanli/editchitietsize/{mats}.htm")
	public String editchietietsize(ModelMap m, @PathVariable("mats") int matrasua) {
		m.addAttribute("cts", new ChiTietSize());
		m.addAttribute("dscts", this.getDsctsize(matrasua));
		m.addAttribute("dssize", this.getDsSize());
		return "sanpham/editchitietsize";
	}

	@RequestMapping(value = "quanli/themsize/{mats}.htm", method = RequestMethod.POST)
	public String themsize(ModelMap m, @ModelAttribute("cts") ChiTietSize cts, @PathVariable("mats") int mts) {
		TraSua ts = this.getTraSua(mts);
		if (fullsize(ts) == 1)
			m.addAttribute("message", "tra sua nay da full size");
		else if (checkSizetontai(ts, cts) == 1)
			m.addAttribute("message", "Da co size nay");
		else {
			cts.setTrasua(ts); // gán mats là được, mà có full thì dùng luon
			if (themcts(cts) == 1) {
				m.addAttribute("message", "Them size thanh cong");
			} else {
				m.addAttribute("message", "Them size that bai");
			}
		}
		return editchietietsize(m, mts);
	}

	@RequestMapping("quanli/deletects/{macts}.htm")
	public String deletects(ModelMap m, @PathVariable("macts") int macts) {
		int mats = this.getMatstumacts(macts); // can lay truoc vi lo neu xoa cts thanh cong thi khong con matcs do
												// trong bang CHITIETSIZE nua
		if (this.xoaCtsize(macts) == 1) {
			m.addAttribute("message", "xoa thanh cong");
		} else {
			m.addAttribute("message", "xoa that bai");
		}
		return editchietietsize(m, mats);
	}

	public int xoaCtsize(int macts) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM ChiTietSize where macts= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", macts);
		ChiTietSize cts = (ChiTietSize) query.list().get(0);
		try {
			session.delete(cts);
			t.commit();
			session.close();
			return 1;
		} catch (Exception ex) {
			t.rollback();
			System.out.println(ex);
			session.close();
			return 0;
		}
	}

	@RequestMapping(value = "quanli/editcongthuc/{mats}.htm")
	public String editcongthuc(ModelMap m, @PathVariable("mats") int matrasua) {
		m.addAttribute("dsct", this.getDsct(matrasua));
		m.addAttribute("mats", matrasua); // can luu lai vi truong hop tra sua do moi tao thi dsnguyen lieu trong nen ko
											// lay duoc mats cua phan tu thu nhat cua ds nguyen lieu
		return "sanpham/editcongthuc";
	}

	@RequestMapping(value = "quanli/themnguyenlieu/{mats}.htm", method = RequestMethod.GET)
	public String themnguyenlieu(ModelMap m, @PathVariable("mats") int mats) {
		if (this.fullnguyenlieu(this.getTraSua(mats)) == 1) {
			m.addAttribute("message", "trà sữa này đã full nguyên liệu");
			return editcongthuc(m, mats);
		}
		else {
			m.addAttribute("ct", new CongThuc());
			m.addAttribute("dsnl", this.getDsNguyenLieu());
			m.addAttribute("mats", mats);
			return "sanpham/themnguyenlieu";
		}
	}

	@RequestMapping(value = "quanli/themnguyenlieu/{mats}.htm", method = RequestMethod.POST)
	public String themnguyenlieu(ModelMap m, @ModelAttribute("ct") CongThuc ct, @PathVariable("mats") int mts) {
		TraSua ts = this.getTraSua(mts);
		if (this.fullnguyenlieu(ts) == 1) {
			m.addAttribute("message", "trà sữa này đã full nguyên liệu");
			return editcongthuc(m, mts);
		} else if (checkNguyenLieutontai(ts, ct) == 1)
			m.addAttribute("message", "Đã có nguyên liệu này");
		else if (ct.getDonvi().isEmpty())
			m.addAttribute("message", "Không để trống nguyên liệu");
		else if (ct.getLieuluong() == null)
			m.addAttribute("message", "Không để trống liều lượng");
		else {
			ct.setTrasua(ts); // can mats thoi, co luon thuc the thi lay luon
			if (themCongThuc(ct) == 1) {
				m.addAttribute("message", "Thêm nguyên liệu thành công");
			} else {
				m.addAttribute("message", "Thêm nguyên liệu thất bại");
			}
		}
		m.addAttribute("dsnl", this.getDsNguyenLieu());
		m.addAttribute("ct", new CongThuc());
		return "sanpham/themnguyenlieu";
	}

	public int xoaCongThuc(int mact) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM CongThuc where mact = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", mact);
		CongThuc ct = (CongThuc) query.list().get(0);
		try {
			session.delete(ct);
			t.commit();
			session.close();
			return 1;
		} catch (Exception ex) {
			t.rollback();
			System.out.println(ex);
			session.close();
			return 0;
		}
	}

	public CongThuc getCongThuc(int mact) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CongThuc where mact = :mact";
		Query query = session.createQuery(hql);
		query.setParameter("mact", mact);
		CongThuc ct = (CongThuc) query.list().get(0);
		System.out.println("in" + ct.getNguyenlieu().getTennl());
		return ct;

	}

	public int getMatstumacts(int macts) {
		System.out.println("in " + macts);
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietSize where macts= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", macts);
		ChiTietSize cts = (ChiTietSize) query.list().get(0);
		return cts.getTrasua().getMats();
	}
	public int getMatstumact(int mact) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CongThuc where mact= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", mact);
		CongThuc cts = (CongThuc) query.list().get(0);
		return cts.getTrasua().getMats();
	}

	@RequestMapping("quanli/deletecongthuc/{mact}.htm")
	public String deletecongthuc(ModelMap m, @PathVariable("mact") int mact) {
		int mats = this.getMatstumact(mact);
		if(this.xoaCongThuc(mact) == 0) {
			m.addAttribute("message","xóa thất bại");
		}
		m.addAttribute("dsct", this.getDsct(mats));
		m.addAttribute("mats", mats);
		return "sanpham/editcongthuc";
	}

	public int checkNguyenLieutontai(TraSua ts, CongThuc ct) {
		for (CongThuc c : ts.getDsct()) {
			if (c.getNguyenlieu().getManl() == ct.getNguyenlieu().getManl()) {
				return 1;
			}
		}
		return 0;
	}

	public int checkSizetontai(TraSua ts, ChiTietSize cts) {
		for (ChiTietSize c : ts.getDscts()) {
			if (c.getSize().getMasize() == cts.getSize().getMasize()) {
				return 1;
			}
		}
		return 0;
	}

	public int themcts(ChiTietSize cts) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(cts);
			t.commit();
			session.close();
			return 1;
		} 
		catch (Exception e) {
			t.rollback();
			session.close();
			return 0;
		}
	}

	public int themCongThuc(CongThuc ct) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ct);
			t.commit();
			session.close();
			return 1;
		} catch (Exception e) {
			t.rollback();
			session.close();
			return 0;
		}

	}

	public int themTraSua(TraSua ts) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.save(ts);
			t.commit();
			session.close();
			return 1;
		} catch (Exception e) {
			t.rollback();
			session.close();
			return 0;
		}
	}

	@RequestMapping(value = "quanli/editcongthuc2/{mact}.htm", method = RequestMethod.GET)
	public String editcongthuc2(ModelMap m, @PathVariable("mact") int mact) {
		m.addAttribute("ct", this.getCongThuc(mact));
		return "sanpham/editcongthuc2";
	}

	@RequestMapping(value = "quanli/editcongthuc2/{mact}.htm", method = RequestMethod.POST)
	public String editcongthuc2(ModelMap m, @PathVariable("mact") int mact, @ModelAttribute("ct") CongThuc ct) {
		System.out.println("mact" + ct.getMact());
		// full qua, lay ve chi id cua thuc the do, con lại khong co tron form thi se bao loi khi save
		if (ct.getLieuluong() == null)
			m.addAttribute("message", "khong de trong lieu luong");
		else if (ct.getDonvi().isEmpty())
			m.addAttribute("message", "Khong de trong don vi");
		else {
			if (this.editCongThuc(ct) == 1) {
				m.addAttribute("message", "Edit thành công");

			} else
				m.addAttribute("message", "Edit thất bại");
		}
		System.out.println(ct.getTrasua());
		m.addAttribute("dsct", this.getDsct(ct.getTrasua().getMats())); // thuc the ct co thuoc tinh Tra Sua, tra sua nay chi co ma
		m.addAttribute("mats", ct.getTrasua().getMats());
		return "sanpham/editcongthuc";

	}

	@RequestMapping(value = "quanli/themsanpham.htm", method = RequestMethod.GET)
	public String themsanpham(ModelMap m) {
		m.addAttribute("trasua", new TraSua());
		return "sanpham/themsanpham";
	}

	@RequestMapping(value = "quanli/themsanpham.htm")
	public String themtrasua(ModelMap m, @ModelAttribute("trasua") TraSua trasua, BindingResult errors,
			@RequestParam("photo") MultipartFile photo) {
		trasua.setTents(chuannHoa(trasua.getTents()));
		if (trasua.getTents().length() == 0) {
			m.addAttribute("message", "khong de trong ten");

		} else if (trasua.getMota().trim().length() == 0) {
			m.addAttribute("message", "khong de trong mo ta");

		} else if (trasua.getGia() == null) {
			m.addAttribute("message", "khong de trong gia");

		} else if (photo.isEmpty()) {
			m.addAttribute("message", "vui long chon hinh anh");

		} else
			try {
				System.out.println("in");
				String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
				String fileName = date + photo.getOriginalFilename();
				String photoPath = baseUploadfile.getBasePath() + File.separator + fileName;
				photo.transferTo(new File(photoPath));
				trasua.setHinhanh(fileName);
				trasua.setTrangthai(1);
				
				ChiTietSize cts = new ChiTietSize();
				Size s = new Size(2, "M", null, null, null);
				cts.setSize(s);
				cts.setTrasua(trasua);
				System.out.println("2");
				if(this.themTraSua(trasua) == 1) {
					if(this.themcts(cts) == 1) {
						m.addAttribute("message", "Thành công");
						return chitietsanpham(m, trasua.getMats());
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				m.addAttribute("message", "lỗi lưu file!");
				System.out.println(e);
				m.addAttribute("trasua", new TraSua());
				return "sanpham/themsanpham";
			}
		return "sanpham/themsanpham";

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

	public List<TraSua> search(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TraSua where tents LIKE :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", "%" + name + "%"); // % - đại diện cho 0,1, hoặc nhiều kí tự
		List<TraSua> list = query.list();
		return list;
	}

	@RequestMapping(value = "timkiem.htm")
	public String searchSinhVien(@RequestParam("searchInput") String input, ModelMap m) {
		if(input != null) {
			List<TraSua> dsts = this.search(input); //lay du lieu nguoi dung nhap vao
			m.addAttribute("danhsachtrasua", dsts);
		}
		
		return "sanpham/danhsach";
	}
}
