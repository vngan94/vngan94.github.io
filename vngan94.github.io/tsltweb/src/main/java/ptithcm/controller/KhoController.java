package ptithcm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.entity.ChiTietCungCap;
import ptithcm.entity.ChiTietDonDatHang;

import ptithcm.entity.CongThuc;
import ptithcm.entity.DonDatHang;
import ptithcm.entity.NguyenLieu;
import ptithcm.entity.NhaCungCap;
import ptithcm.entity.NhanVien;
import ptithcm.entity.TaiKhoan;

import ptithcm.entity.TrangThaiDonDatHang;
@Controller
@Transactional
@RequestMapping("kho/")
public class KhoController {

	@Autowired
	SessionFactory factory;
	public List<NguyenLieu> getDsnl(){
		Session session = factory.getCurrentSession();
		String hql = "FROM NguyenLieu";
		Query query = session.createQuery(hql);
		List<NguyenLieu> list = query.list();
		return list;
	}
	
	public NguyenLieu getnl(int ma){
		Session session = factory.getCurrentSession();
		String hql = "FROM NguyenLieu where manl= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		NguyenLieu nl = (NguyenLieu) query.list().get(0);
		return nl;
	}
	
	public List<NguyenLieu> getDsCongThuc(int manl){
		Session session = factory.getCurrentSession();
		String hql = "FROM NguyenLieu";
		Query query = session.createQuery(hql);
		List<NguyenLieu> list = query.list();
		session.close();
		return list;
	}
	public NguyenLieu getnguyenlieu(Integer id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NguyenLieu where manl = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		NguyenLieu nl =(NguyenLieu) query.list().get(0);
		return nl;
	}
	public int xoanguyenlieu(int manl) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM NguyenLieu where manl = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", manl);
		NguyenLieu nl =(NguyenLieu) query.list().get(0);
			try {
				session.delete(nl);
				t.commit();
				return 1;
			}
			catch (Exception ex) {
				t.rollback();
				System.out.println(ex);
				return 0;
			}
	}
	public int xoanguyenlieutrongCT(int manl) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CongThuc where manl = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", manl);
		List<CongThuc> dsct = query.list();
		for(CongThuc ct : dsct) {
			session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.delete(ct);
				t.commit();
			}
			catch (Exception ex) {
				t.rollback();
				return 0;	
			}
		}
		
		return 1;
		
	}
	public int themNguyenLieu(NguyenLieu nl) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(nl);
			t.commit();
			return 1;
		}
		catch(Exception e) {
			t.rollback();
			return 0;
		}
	}
	public int themDdh(DonDatHang ddh) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ddh);
			t.commit();
			return 1;
		}
		catch(Exception e) {
			t.rollback();
			return 0;
		}
	}
	
	@RequestMapping(value="danhsachnguyenlieu.htm")
	public String khocheck(ModelMap m, HttpSession session) {
		m.addAttribute("dsnl",this.getDsnl());
		TaiKhoan tk = (TaiKhoan) session.getAttribute("userLogin");
		return tk.getVaitro().getMavt()==1 ? "kho/danhsachnguyenlieunv":"kho/danhsachnguyenlieu";
		
	}
	
	public String kho(ModelMap m) {
		m.addAttribute("dsnl",this.getDsnl());
		
		return "kho/danhsachnguyenlieu";
	}
	@RequestMapping(value="xoanguyenlieu/{manl}.htm")
	public String xoanguyenlieu(ModelMap m,  @PathVariable("manl") int manl) {
		if(this.xoanguyenlieutrongCT(manl)==1)
			 if(this.xoanguyenlieu(manl) == 1) {
					m.addAttribute("message","xoa nguyen lieu thanh cong");
				}
			 else {
					m.addAttribute("message", "xoa nguyen lieu that bai");
			 }
		else {
			m.addAttribute("message","xoa nguyenlieutrongct that bai");
		}
		return kho(m);
	}
	@RequestMapping(value="quanli/themnguyenlieu.htm")
	public String themnguyenlieu(ModelMap m) {
		m.addAttribute("nguyenlieu",new NguyenLieu());
		return "kho/themnguyenlieu";
	}
	public String chuannHoa(String st) {
	    st=st.trim().toLowerCase();
	    st = st.replaceAll("\\s+", " ");
	    String[] temp= st.split(" ");
	            // sau khi tách xong, gán xâu st thành sâu rỗng
	    st="";
	    for(int i=0;i<temp.length;i++) {
	        st+=String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
	        if(i<temp.length-1) // nếu tempt[i] không phải từ cuối cùng
	            st+=" ";   // cộng thêm một khoảng trắng
	    }
		return st;
	}
	@RequestMapping(value="quanli/themnguyenlieu.htm", method=RequestMethod.POST)
	public String themnguyenlieu(ModelMap m, @ModelAttribute("nguyenlieu") NguyenLieu nl) {
		if(nl.getSoluong()==null) {
			m.addAttribute("message", "không để trống số lượng");
		}
		else if(nl.getTennl().trim().length()==0)
			m.addAttribute("message","khong de trong ten nguyen lieu");
		else if(nl.getDonvi().trim().length()==0)
			m.addAttribute("message","khong de trong don vi");
		else {
			nl.setTennl(this.chuannHoa(nl.getTennl()));
			if(this.themNguyenLieu(nl) == 1) {
				m.addAttribute("message","them nguyen lieu thanh cong");
				return kho(m);
			}
			else 
				m.addAttribute("message","them nhan vien that bai");
		}
		return "kho/themnguyenlieu";
	}
	public List<NhaCungCap> getDsncc() {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhaCungCap";
		Query query = session.createQuery(hql);
		List<NhaCungCap> ds = query.list();
		return ds;
	}

	public List<DonDatHang> getDsddh() {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonDatHang";
		Query query = session.createQuery(hql);
		List<DonDatHang> ds = query.list();
		return ds;
	}

	
	@RequestMapping(value="danhsachdondathang.htm")
	public String danhsachdondathang(ModelMap m) {
		m.addAttribute("dsddh", this.getDsddh());
		return "kho/danhsachdondathang";
	}
	
	public ChiTietDonDatHang getCtddh(int ma) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietDonDatHang where maddh= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		ChiTietDonDatHang ct = (ChiTietDonDatHang) query.list().get(0);
		
		return ct;
	}
	
	public int getMancc(int maddh) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonDatHang where maddh= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", maddh);
		DonDatHang ddh = (DonDatHang) query.list().get(0);
		return ddh.getNhacungcap().getMancc();
	}
	public List<Object[]> getDsCtcc(int mancc) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT c.nguyenlieu.manl, c.dongia, c.donvi FROM ChiTietCungCap c where mancc= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", mancc);
		List <Object[]> ds = query.list();
		return ds;
	}
	public ChiTietCungCap getEntityCtcc(int mancc) {
		Session session = factory.getCurrentSession();
		String hql = " FROM ChiTietCungCap where mancc= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", mancc);
		ChiTietCungCap ct = (ChiTietCungCap) query.list().get(0);
		return ct;
	}
	public List<ChiTietCungCap> getDsctcc(int mancc) {
	
		Session session = factory.getCurrentSession();
		String hql = " FROM ChiTietCungCap where mancc= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", mancc);
		List<ChiTietCungCap> ct =  query.list();
		
		return ct;
	}


	
	public List<ChiTietDonDatHang> getDsctddh(int maddh) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietDonDatHang where maddh= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", maddh);
		List <ChiTietDonDatHang> ds = query.list();
	
		return ds;
	}
	public DonDatHang getDdh(int maddh) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonDatHang where maddh= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", maddh);
		DonDatHang ddh = (DonDatHang) query.list().get(0);
		return ddh;
	}
	public void inchitietddnl(ModelMap m, int maddh) {
		float tongcong = 0;
		m.addAttribute("dsctddh",this.getDsctddh(maddh));
		int mancc = this.getMancc(maddh);
		m.addAttribute("dsctcc", this.getDsCtcc(mancc));
	
		for(Object[] c : this.getDsCtcc(mancc))
			for(ChiTietDonDatHang ct: this.getDsctddh(maddh)) 
				if(c[0].equals(ct.getNguyenlieu().getManl()))  { // kiem tra manl trong dsctcc co trung vs manl trong dsctddh 
					tongcong+=(float)c[1] *(ct.getSoluong()) ; // neu trung thi lay soluong * gia cong don vao bien tong cong 
					break;
				}
		m.addAttribute("tongcong", tongcong);
	}
 	@RequestMapping(value="chitietdondatnguyenlieu/{maddh}.htm")
	public String chitietddnl(ModelMap m, @PathVariable("maddh") int maddh) {
		this.inchitietddnl(m, maddh);
	
		return "kho/chitietdondatnguyenlieu";
	}
	public int huyddh(int maddh) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM DonDatHang where maddh= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", maddh);
		DonDatHang ddh = (DonDatHang) query.list().get(0);
		TrangThaiDonDatHang th = new TrangThaiDonDatHang();
		th.setMattddh(5);
		ddh.setTrangthai(th);
		
		try {
			session.update(ddh);
			t.commit();
			return 1;
		}
		catch(Exception e) {
			t.rollback();
			System.out.println(e);
			return 0;
		}
	}
	public int nhaphang(int maddh) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM DonDatHang where maddh= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", maddh);
		DonDatHang ddh = (DonDatHang) query.list().get(0);
		TrangThaiDonDatHang th = new TrangThaiDonDatHang();
		th.setMattddh(4);
//		th.setTentt("Đã giao");;
		ddh.setTrangthai(th);
		
		try {
			session.update(ddh);
			t.commit();
			return 1;
		}
		catch(Exception e) {
			t.rollback();
			System.out.println(e);
			return 0;
		}
	}
	
	
	public int CapNhatSoLuong(List<ChiTietDonDatHang> dsct) {
	
		for(ChiTietDonDatHang ct : dsct) {
			Session session = factory.openSession(); // phải open lại và ko phải getcurrent vi no xay ra loi 2 session cung 1 luc nên ko xu li duoc
			String hql = "FROM NguyenLieu where manl= :ma";
			Query query = session.createQuery(hql);
			query.setParameter("ma", ct.getNguyenlieu().getManl());
			NguyenLieu nl = (NguyenLieu) query.list().get(0);
			session.close(); // ket thuc 1 session
		
			nl.setSoluong(nl.getSoluong() + ct.getSoluong());
				session = factory.openSession(); // mo 1 sesion moi duy nhat
				Transaction t = session.beginTransaction();
				try {
					
					session.update(nl);
					t.commit();
				}
				catch (Exception e) {
					t.rollback();
					System.out.println(e);
					return 0;
				}
			}
		return 1;
		
	}
	

	@RequestMapping("huyddh/{maddh}.htm") 
	public String huyddh(ModelMap m, @PathVariable("maddh") int maddh) {
		if(this.huyddh(maddh) == 1) 
			m.addAttribute("message","hủy đơn đặt hàng thành công");
		else 
			m.addAttribute("message","hủy đơn đặt hàng thất bại");
		return danhsachdondathang(m);
	}
	@RequestMapping("nhaphang/{maddh}.htm")
	public String nhaphang(ModelMap m, @PathVariable("maddh") int maddh) {
		
			if(	this.nhaphang(maddh)==1)
				if(	this.CapNhatSoLuong(this.getDsctddh(maddh)) == 1) {
				m.addAttribute("message", "nhap hang thanh cong");
				}
			else {
				m.addAttribute("message","nhap hang that bai");
			}
		
		
		return danhsachdondathang(m);
	}
	public List<NhanVien> getDsnv() {
		Session session = factory.getCurrentSession();
		String sql = "FROM NhanVien";
		Query query = session.createQuery(sql);
		List<NhanVien> dsnv = query.list();
		return dsnv;
	}
	public String getDonvi(int manl) {
		Session session = factory.getCurrentSession();
		String sql = "FROM NguyenLieu where manl= :ma";
		Query query = session.createQuery(sql);
		query.setParameter("ma", manl);
		NguyenLieu dv = (NguyenLieu) query.list().get(0);
		return dv.getDonvi();
	}
	@RequestMapping(value="datnguyenlieu.htm")
	public String datnguyenlieu(ModelMap m) {
		m.addAttribute("dondathang", new DonDatHang());
		m.addAttribute("dsncc", this.getDsncc());
//		m.addAttribute("dsnv", this.getDsnv());
		return "kho/datnguyenlieu";
	}
	
	@RequestMapping(value="datnguyenlieu.htm", method=RequestMethod.POST)
	public String datnguyenlieu(ModelMap m, @ModelAttribute("dondathang") DonDatHang ddh, HttpSession session) {
		ddh.setNgaylap(new Date());
		TrangThaiDonDatHang t = new TrangThaiDonDatHang();
		t.setMattddh(1);
		ddh.setTrangthai(t);
		TaiKhoan tk = (TaiKhoan) session.getAttribute("userLogin");
		ddh.setNhanvien(tk.getNhanvien());
		if(this.themDdh(ddh) == 1) {
			ChiTietDonDatHang ct = new ChiTietDonDatHang();
			m.addAttribute("maddh", ddh.getMaddh());
			m.addAttribute("ctddh", ct);	
			m.addAttribute("dsctcc", this.getDsctcc(ddh.getNhacungcap().getMancc()));	
		}
		return "kho/chonnguyenlieudedat";
	}
	
	public String inDsnldedat(ModelMap m, DonDatHang ddh) {
		ChiTietDonDatHang ct = new ChiTietDonDatHang();
		m.addAttribute("maddh", ddh.getMaddh());
		m.addAttribute("ctddh", ct);	
		m.addAttribute("dsctcc", this.getDsctcc(ddh.getNhacungcap().getMancc()));	
		return "kho/chonnguyenlieudedat";
	}
	
	@RequestMapping(value="insertnguyenlieu/{maddh}.htm", method=RequestMethod.POST)
	public String insertnguyenlieu(ModelMap m,  @ModelAttribute("ctddh") ChiTietDonDatHang ctddh, @PathVariable("maddh") int maddh) {
		DonDatHang ddh = this.getDdh(maddh);
		ctddh.setDonvi(this.getDonvi(ctddh.getNguyenlieu().getManl()));
		ctddh.setDondathang(ddh);
		if(this.kiemTraChonFull(maddh, ddh.getNhacungcap().getMancc()) == 1) {
				m.addAttribute("message","ban da chon full nguyen lieu");
				this.inchitietddnl(m, maddh);
				return "kho/inlaidondatnguyenlieu";
		 }
		else if(this.kiemTraTonTaiCtddh(maddh, ctddh.getNguyenlieu().getManl()) == 1) {
			m.addAttribute("message","nguyen lieu nay da duoc dat");
		}
		else  {
			if(this.themCtddh(ctddh) == 1) {
				this.inchitietddnl(m, maddh);
				return "kho/inlaidondatnguyenlieu";
			}	
			else 
				m.addAttribute("message","them ctddh that bai");
		}
		return inDsnldedat(m, ddh);
		
	}
	
	@RequestMapping("datxong.htm") 
	public String datxong(ModelMap m) {
		return danhsachdondathang(m);
	}
	
	@RequestMapping("chontiep/{maddh}.htm") 
	public String chontiep(ModelMap m, @PathVariable("maddh") int maddh) {
		DonDatHang ddh = this.getDdh(maddh);
		 if(this.kiemTraChonFull(maddh, ddh.getNhacungcap().getMancc()) == 1) {
				m.addAttribute("message","ban da chon full nguyen lieu");
				this.inchitietddnl(m, maddh);
				return "kho/inlaidondatnguyenlieu";
		 }
		return inDsnldedat(m, ddh);
	}
	
	public int kiemTraTonTaiCtddh(int maddh, int manl) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietDonDatHang where maddh= :ma and manl = :manl";
		Query query = session.createQuery(hql);
		query.setParameter("ma", maddh);
		query.setParameter("manl", manl);
		List<ChiTietDonDatHang> dsct = query.list();
		return dsct.size()>0 ? 1 : 0;
	}
	
	public int kiemTraChonFull(int maddh, int mancc) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietDonDatHang where maddh= :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", maddh);
		List<ChiTietDonDatHang> dsct = query.list();
		return dsct.size()==this.getDsctcc(mancc).size() ? 1 : 0;
	}

	
	
	public int themCtddh(ChiTietDonDatHang c) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(c);
			t.commit();
			return 1;
		}
		catch(Exception e) {
			t.rollback();
			System.out.println(e);
			return 0;
		}
	}
	
	public int editnguyenlieu(NguyenLieu nl) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(nl);
			t.commit();
			return 1;
		}
		catch(Exception e) {
			t.rollback();
			System.out.println(e);
			return 0;
		}
	}
	
	@RequestMapping("editnguyenlieu/{manl}.htm")
	public String editnguyenlieu(ModelMap m, @PathVariable("manl") int manl) {
		m.addAttribute("nl", this.getnl(manl));
		return"kho/editnguyenlieu";
	}
	
	@RequestMapping(value="editnguyenlieu/{manl}.htm", method=RequestMethod.POST)
	public String editnguyenlieu(ModelMap m, @ModelAttribute("nl") NguyenLieu nl) {
		if(nl.getSoluong() == null) {
			m.addAttribute("message","không để trống số lượng");
		}
		else if(this.editnguyenlieu(nl) == 1) {
			m.addAttribute("message","cap nhat so luong thanh cong");
			return kho(m);
		}
		else {
			m.addAttribute("message","cap nhat that bai");
		}
		return editnguyenlieu(m, nl.getManl());
	}
}
