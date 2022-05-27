package ptithcm.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sf.ehcache.hibernate.HibernateUtil;
import ptithcm.entity.ChiTietDonDatHang;
import ptithcm.entity.ChiTietHoaDon;
import ptithcm.entity.ChiTietSize;
import ptithcm.entity.DonDatHang;
import ptithcm.entity.DonHang;
import ptithcm.entity.HoaDon;
import ptithcm.entity.NguyenLieu;
import ptithcm.entity.NhanVien;
import ptithcm.entity.TaiKhoan;
import ptithcm.entity.Topping;
import ptithcm.entity.TraSua;
import ptithcm.entity.TrangThaiDonDatHang;
import ptithcm.entity.TrangThaiDonHang;
import ptithcm.entity.TrangThaiHoaDon;

@Controller
@Transactional
@RequestMapping("giaodich/")
public class GiaoDichController {
	@Autowired
	SessionFactory factory;

	public List<HoaDon> getDshd() {
		Session session = factory.getCurrentSession();
		String hql = "FROM HoaDon";
		Query query = session.createQuery(hql);
		List<HoaDon> list = query.list();
		return list;
	}

	public HoaDon getHd(int mahd) {
		Session session = factory.getCurrentSession();
		String hql = "FROM HoaDon where mahd= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", mahd);
		HoaDon hd = (HoaDon) query.list().get(0);
		return hd;
	}

	public HoaDon getHdlast() {
		Session session = factory.getCurrentSession();
		String hql = "FROM HoaDon";
		Query query = session.createQuery(hql);
		List<HoaDon> l = query.list();
		HoaDon hd = l.get(l.size() - 1);
		return hd;
	}

	public List<ChiTietHoaDon> getDscthd(int ma) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietHoaDon where mahd= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		List<ChiTietHoaDon> list = query.list();
		return list;
	}

	@RequestMapping("quanli/danhsachhoadon.htm")
	public String danhsachhoadon(ModelMap m) {
		m.addAttribute("dshd", this.getDshd());
		return "giaodich/danhsachhoadon";
	}

	@RequestMapping("quanli/chitiethoadon/{mahd}.htm")
	public String chitiethoadon(ModelMap m, @PathVariable("mahd") int mahd) {
		this.inchitiethoadon(m, mahd);
		return "giaodich/inhoadon";
	}

	@RequestMapping("quanli/quaylai/{mahd}.htm")
	public String quaylai(ModelMap m) {
		return danhsachhoadon(m);
	}

	public List<DonHang> getDsdh() {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang";
		Query query = session.createQuery(hql);
		List<DonHang> list = query.list();
		return list;
	}

	public HoaDon getHoadonTumadh(int madh) {
		Session session = factory.openSession(); // get current -> illegal
		String hql = "FROM HoaDon where madh= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", madh);
		HoaDon dh = (HoaDon) query.list().get(0);
		session.close();
		return dh;
	}

	public int getMaHoadonTumacthd(int macthd) {
		Session session = factory.getCurrentSession(); // get current -> illegal
		String hql = "FROM ChiTietHoaDon where macthd= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", macthd);
		ChiTietHoaDon ct = (ChiTietHoaDon) query.list().get(0);
		return ct.getHoadon().getMahd();
	}

	public int capnhatTrangthaiHoadon(HoaDon hd) {

		TrangThaiHoaDon th = new TrangThaiHoaDon();
		th.setMatthd(2);
		hd.setTrangthai(th);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(hd);
			t.commit();
			session.close();
			return 1;
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			session.close();
			return 0;
		}
	}

	public int updatecthd(int mahd, int matp, int soluong) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietHoaDon where mahd= :ma and matopping= :matp";
		Query query = session.createQuery(hql);
		query.setParameter("ma", mahd);
		query.setParameter("matp", matp);
		ChiTietHoaDon ct = (ChiTietHoaDon) query.list().get(0);
		ct.setSoluong(ct.getSoluong() + soluong);
		session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(ct);
			t.commit();
			session.close();
			return 1;
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			session.close();
			return 0;
		}
	}

	public int updatecthd(int mahd, int masize, int mats, int soluong) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietHoaDon where mahd= :ma and mats = :mats and masize = :masize";
		Query query = session.createQuery(hql);
		query.setParameter("ma", mahd);
		query.setParameter("mats", mats);
		query.setParameter("masize", masize);
		ChiTietHoaDon ct = (ChiTietHoaDon) query.list().get(0);
		ct.setSoluong(ct.getSoluong() + soluong);
		session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(ct);
			t.commit();
			session.close();
			return 1;
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			session.close();
			return 0;
		}
	}

	public int updatecthd(ChiTietHoaDon c) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(c);
			t.commit();
			session.close();
			return 1;
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			session.close();
			return 0;
		}
	}

	public int capnhatTrangthaiDonhang(int madh) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang where mahd= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", madh);
		DonHang dh = (DonHang) query.list().get(0);
		session = factory.openSession();
		Transaction t = session.beginTransaction();
		TrangThaiDonHang th = new TrangThaiDonHang(5, "Đã hủy", null); // set luon tentt luon vi luc load lai neu k set
																		// thi no khong hien tentt, phai chay lai 1 lan
																		// nua moi thay

		dh.setTrangthai(th);
		try {
			session.update(dh);
			t.commit();
			session.close();
			return 1;
		} catch (Exception e) {
			t.rollback();
			session.close();
			System.out.println(e);
			return 0;
		}
	}

	@RequestMapping("danhsachdonhang.htm")
	public String danhsachdonhang(ModelMap m) {
		m.addAttribute("dsdh", this.getDsdh());
		return "giaodich/danhsachdonhang";
	}

	@RequestMapping("huydonhang/{madh}.htm")
	public String huydonhang(ModelMap m, @PathVariable("madh") int madh) {
		// cap nhat trangthai hoa don: da huy
		// cap nhat trong thai don hang: da huy
		HoaDon hd = this.getHoadonTumadh(madh);
		if (this.capnhatTrangthaiHoadon(hd) == 1) {
			if (this.capnhatTrangthaiDonhang(madh) == 1) {
				m.addAttribute("message", "hủy đơn hàng thành công");
			} else {
				m.addAttribute("message", "hủy đơn hàng thất bại");
			}
		} else {
			m.addAttribute("hủy hóa đơn thất bại");
		}
		return danhsachdonhang(m);
	}

	public int capnhattientrinh(int madh) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang where madh= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", madh);
		DonHang dh = (DonHang) query.list().get(0);
		session = factory.openSession();
		Transaction t = session.beginTransaction();

		TrangThaiDonHang th = new TrangThaiDonHang(); // set luon tentt luon vi luc load lai neu k set thi no khong hien
														// tentt, phai chay lai 1 lan nua moi thay
		if (dh.getTrangthai().getMattdh() == 1) {
			th.setMattdh(2);
			th.setTentt("Đang pha chế");
		} else if (dh.getTrangthai().getMattdh() == 2) {
			th.setMattdh(3);
			th.setTentt("Đang giao");
		} else if (dh.getTrangthai().getMattdh() == 3) {
			th.setMattdh(4);
			th.setTentt("Đã giao");
		}
		dh.setTrangthai(th);
		try {
			session.update(dh);
			t.commit();
			session.close();
			return 1;
		} catch (Exception e) {
			t.rollback();
			session.close();
			System.out.println(e);
			return 0;
		}
	}

	@RequestMapping("capnhattientrinh/{madh}.htm")
	public String capnhattientrinh(ModelMap m, @PathVariable("madh") int madh) {
		if (this.capnhattientrinh(madh) == 1) {
			m.addAttribute("cập nhật thành công");
		} else {
			m.addAttribute("hủy hóa đơn thất bại");
		}
		m.addAttribute("dsdh", this.getDsdh());
		return "giaodich/danhsachdonhang";
	}

	public List<NhanVien> getDsnv() {
		Session session = factory.getCurrentSession();
		String sql = "FROM NhanVien";
		Query query = session.createQuery(sql);
		List<NhanVien> dsnv = query.list();
		return dsnv;
	}

	public int themHoaDon(HoaDon hd) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(hd);
			t.commit();

			session.close();
			return 1;
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			session.close();
			return 0;
		}
	}

	public int themDonhang(DonHang dh) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(dh);
			t.commit();
			session.close();
			return 1;
		} catch (Exception e) {
			t.rollback();
			session.close();
			e.printStackTrace();
			return 0;
		}
	}

	public int themcthd(ChiTietHoaDon ctdh) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ctdh);
			t.commit();
			session.close();
			return 1;
		} catch (Exception e) {
			t.rollback();
			session.close();
			return 0;
		}
	}

	public int edithd(HoaDon hd) {

		Session session = factory.getCurrentSession();
		session.clear(); // khong co 2 dong nay xay ra loi illgal to open 2 session
		session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(hd);
			t.commit();
			session.close();
			return 1;
		} catch (Exception e) {
			t.rollback();
			session.close();
			System.out.println("day");
			e.printStackTrace();

			return 0;
		}
	}

	public List<TraSua> getDsts() {
		Session session = factory.getCurrentSession();
		String sql = "FROM TraSua where trangthai=1";
		Query query = session.createQuery(sql);
		List<TraSua> dsTraSua = query.list();
		return dsTraSua;
	}

	@RequestMapping("laphoadon.htm")
	public String chontrasua(ModelMap m) {
		m.addAttribute("cthd", new ChiTietHoaDon());
		m.addAttribute("dsts", this.getDsts());
		return "giaodich/chontrasua";
	}

	public void inchitiethoadon(ModelMap m, int mahd) {
		List<ChiTietHoaDon> l = this.getDscthd(mahd);
		m.addAttribute("dscthd", l);
		// tinh thanh tien
		int tongcong = 0;
		for (ChiTietHoaDon ct : l) {
			if (ct.getTrasua() != null) { // co order tra sua
				tongcong += ct.getSoluong() * ct.getTrasua().getGia() * ct.getSize().getTile(); // tinh tien trasua
			} else // co order topping
				tongcong += ct.getSoluong() * ct.getTopping().getGia(); // tinh tien topping
		}
		m.addAttribute("tongcong", tongcong);
	}
	
	public int tinhtien( int mahd) {
		List<ChiTietHoaDon> l = this.getDscthd(mahd);
		
		// tinh thanh tien
		int tongcong = 0;
		for (ChiTietHoaDon ct : l) {
			if (ct.getTrasua() != null) { // co order tra sua
				tongcong += ct.getSoluong() * ct.getTrasua().getGia() * ct.getSize().getTile(); // tinh tien trasua
			} else // co order topping
				tongcong += ct.getSoluong() * ct.getTopping().getGia(); // tinh tien topping
		}
		return tongcong;
	}
		
	

	@RequestMapping(value = "xong.htm", method = RequestMethod.POST)
	public String xong(ModelMap m, @ModelAttribute("cthd") ChiTietHoaDon cthd, HttpSession session) {

		// kiem tra chon trung:
//			+ trung: - chi update - in lai hoa don
//			+ khong trung: them cthd - in lai hoa don 
		// lan dau: them hoa don - them cthd - in lai hoa don

		if (cthd.getHoadon() != null) {
			System.out.println("in");
			int mahd = cthd.getHoadon().getMahd();
			if (this.kiemTraTonTaiCthd(mahd, cthd.getTrasua().getMats(), cthd.getSize().getMasize()) == 1) {
				if (this.updatecthd(mahd, cthd.getTrasua().getMats(), cthd.getSize().getMasize(),
						cthd.getSoluong()) == 1) {
					m.addAttribute("message", "cap nhat so luong thanh cong");
				}
			} else {
				if (this.themcthd(cthd) == 1) {
					m.addAttribute("message", "thêm thành công");
				}
			}
			this.inchitiethoadon(m, mahd);
			return "giaodich/inlaihoadon";
		}

		else {
			System.out.println("in2");
			// lan dau
			HoaDon hd = new HoaDon();
			TaiKhoan tk = (TaiKhoan) session.getAttribute("userLogin");
			hd.setNhanvien(tk.getNhanvien());
			hd.setTrangthai(new TrangThaiHoaDon(1, null, null));
			hd.setNgaythem(new Date());
			hd.setThanhtien(0);
			System.out.println("in3 lan dau");
			if (this.themHoaDon(hd) == 1) {
				hd = this.getHdlast();
				cthd.setHoadon(hd);
				if (this.themcthd(cthd) == 1) {
					m.addAttribute("message", "thêm thành công");
				}
			}
			this.inchitiethoadon(m, hd.getMahd());
			return "giaodich/inlaihoadon";
		}
	}

	public List<ChiTietSize> getDsctsize(int matrasua) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietSize where mats = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", matrasua);
		List<ChiTietSize> dscts = query.list();
		return dscts;
	}

	// in hoa don cho khach hang thi nhan vien moi bat dau lam => tao don
	// hang
	@RequestMapping("inhoadon/{mahd}.htm")
	public String inhoadon(ModelMap m, @PathVariable("mahd") int mahd) {
		HoaDon hd = this.getHd(mahd);

		DonHang dh = new DonHang();
		dh.setHoadon(hd);
		dh.setTrangthai(new TrangThaiDonHang(1, null, null));
		if (this.themDonhang(dh) == 0) {

			m.addAttribute("in hoa don that bai");
		} 
		else {

			hd.setDonhang(dh);
			hd.setThanhtien(this.tinhtien(mahd));
			this.inchitiethoadon(m, mahd);
			if (this.edithd(hd) == 1) {
				System.out.println("ok");
			}
		}

		return "giaodich/inhoadon";
	}

	public int kiemTraTonTaiCthd(int mahd, int mats, int masize) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietHoaDon where mahd= :ma and mats = :mats and masize = :masize";
		Query query = session.createQuery(hql);
		query.setParameter("ma", mahd);
		query.setParameter("mats", mats);
		query.setParameter("masize", masize);
		List<ChiTietHoaDon> dsct = query.list();
		return dsct.size() > 0 ? 1 : 0;
	}

	public int kiemTraclcthd(int mahd) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietHoaDon where mahd= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", mahd);
		List<ChiTietHoaDon> dsct = query.list();
		System.out.println("in2" + dsct.size());
		return dsct.size() > 0 ? 1 : 0;
	}

	public ChiTietHoaDon getCthd(int macthd) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietHoaDon where macthd= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", macthd);
		ChiTietHoaDon ct = (ChiTietHoaDon) query.list().get(0);
		return ct;
	}

	public int kiemTraTonTaiCthd(int mahd, int matopping) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietHoaDon where mahd= :ma and matopping = :matp";
		Query query = session.createQuery(hql);
		query.setParameter("ma", mahd);
		query.setParameter("matp", matopping);
		List<ChiTietHoaDon> dsct = query.list();
		return dsct.size() > 0 ? 1 : 0;
	}

	public List<Topping> getDstp() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Topping";
		Query query = session.createQuery(hql);
		List<Topping> dstp = query.list();
		return dstp;
	}

	@RequestMapping(value = "chontieptrasua/{mahd}.htm")
	public String chontieptrasua(ModelMap m, @PathVariable("mahd") int mahd) {
		m.addAttribute("dsts", this.getDsts());
		ChiTietHoaDon cthd = new ChiTietHoaDon();
		cthd.setHoadon(this.getHd(mahd));
		System.out.println("chontiep" + cthd.getHoadon().getMahd());
		m.addAttribute("cthd", cthd);
		return "giaodich/chontrasua";
	}

	@RequestMapping(value = "chontieptopping/{mahd}.htm")
	public String chontieptopping(ModelMap m, @PathVariable("mahd") int mahd) {
		m.addAttribute("dstp", this.getDstp());
		ChiTietHoaDon cthd = new ChiTietHoaDon();
		cthd.setHoadon(this.getHd(mahd));
		m.addAttribute("cthd", cthd);
		return "giaodich/chontieptopping";
	}

	@RequestMapping(value = "getts.htm", method = RequestMethod.POST)
	public String getts(ModelMap m, @ModelAttribute("cthd") ChiTietHoaDon cthd) {
		m.addAttribute("dssize", this.getDsctsize(cthd.getTrasua().getMats()));
		m.addAttribute("cthd", cthd);
		return "giaodich/chonsizeVasoluong";

	}

	@RequestMapping(value = "xongtopping.htm", method = RequestMethod.POST)
	public String xongtopping(ModelMap m, @ModelAttribute("cthd") ChiTietHoaDon cthd) {

		int mahd = cthd.getHoadon().getMahd();
		System.out.println("mahd" + mahd);
		// kiem tra trung:
		// + trung: update cthd - in lai hoa don
		// + khong trung: them cthd - in lai hoa don
		System.out.println("trung");
		if (this.kiemTraTonTaiCthd(mahd, cthd.getTopping().getMatopping()) == 1) {
			if (this.updatecthd(mahd, cthd.getTopping().getMatopping(), cthd.getSoluong()) == 1) {
				m.addAttribute("message", "cap nhat so luong thanh cong");
			}
		} else {
			if (this.themcthd(cthd) == 1) {
				m.addAttribute("message", "thêm thành công");
			}
		}
		this.inchitiethoadon(m, mahd);
		return "giaodich/inlaihoadon";
	}

	public int xoaDscthd(int ma) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietHoaDon where mahd = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		List<ChiTietHoaDon> dscthd = query.list();
		for (ChiTietHoaDon ct : dscthd) {
			session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.delete(ct);
				t.commit();
				session.close();
			} catch (Exception ex) {
				t.rollback();
				session.close();
				return 0;
			}
		}
		return 1;
	}

	public int xoahoadon(int ma) {
		Session session = factory.getCurrentSession();
		String hql = "FROM HoaDon where mahd = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		HoaDon hd = (HoaDon) query.list().get(0);
		session.clear();
		session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(hd);
			t.commit();
			System.out.println("ok");
			session.close();
			return 1;
		} catch (Exception ex) {
			t.rollback();
			session.close();
			System.out.println(ex);
			return 0;

		}

	}

	public int xoacthd(int ma) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietHoaDon where macthd = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		ChiTietHoaDon cthd = (ChiTietHoaDon) query.list().get(0);
		session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(cthd);
			t.commit();
			session.close();
			return 1;
		} catch (Exception ex) {
			t.rollback();
			session.close();
			return 0;
		}
	}

	@RequestMapping("xoacthd/{macthd}.htm")
	public String xoacthd(ModelMap m, @PathVariable("macthd") int macthd) {
		int mahd = getMaHoadonTumacthd(macthd);
		if (this.xoacthd(macthd) == 1) {
			if (this.kiemTraclcthd(mahd) == 1) { // con
				this.inchitiethoadon(m, mahd);
			} else {
				System.out.println("inmhd" + mahd);
				if (this.xoahoadon(mahd) == 1) { // khong con
					return this.chontrasua(m);
				}
			}

		}
		return "giaodich/inlaihoadon";
	}

	@RequestMapping("xoatatca/{mahd}.htm")
	public String xoatatca(ModelMap m, @PathVariable("mahd") int mahd) {

		if (this.xoaDscthd(mahd) == 1) {
			if (this.xoahoadon(mahd) == 1) {

				m.addAttribute("message", "xoa thanh cong");
			}
		}
		return this.chontrasua(m);
	}

	@RequestMapping("editcthd/{macthd}.htm")
	public String editcthd(ModelMap m, @PathVariable("macthd") int macthd) {
		m.addAttribute("cthd", this.getCthd(macthd));
		return "giaodich/editchitiethoadon";
	}

	@RequestMapping(value = "editcthd/{macthd}.htm", method = RequestMethod.POST)
	public String editcthd(ModelMap m, @PathVariable("macthd") int macthd, @ModelAttribute("cthd") ChiTietHoaDon cthd) {
		ChiTietHoaDon c = this.getCthd(macthd);
		c.setSoluong(cthd.getSoluong());
		if (this.updatecthd(c) == 1) {
			m.addAttribute("message", "cap nhat so luong thanh cong");

		}
		this.inchitiethoadon(m, c.getHoadon().getMahd());
		return "giaodich/inlaihoadon";

	}

}
