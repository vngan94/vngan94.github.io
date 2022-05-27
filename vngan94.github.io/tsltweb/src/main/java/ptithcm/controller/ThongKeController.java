package ptithcm.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.entity.ChiTietHoaDon;
import ptithcm.entity.HoaDon;

@Controller
@Transactional
@RequestMapping("thongke/")
public class ThongKeController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("doanhthu")
	public String doanhthu() {
		return "thongke/doanhthu";
	}
	@RequestMapping("doanhthuthang")
	public String doanhthuthang() {
		return "thongke/doanhthuthang";
	}

	@RequestMapping("thongketdoanhthutheonam")
	public String thongketheonam2(@RequestParam("nam") int nam, ModelMap m) {
		Session session = factory.getCurrentSession();
		String hql = "Select SUM(thanhtien) from HoaDon where year(ngaythem) = :nam AND matthd=1";
		Query query = session.createQuery(hql);
		query.setParameter("nam", nam);
		m.addAttribute("nam", nam);
		m.addAttribute("tongcong", (query.list().get(0))); 
		return"thongke/ketquadoanhthu";
	}
	@RequestMapping("thongketdoanhthutheothang")
	public String thongketheocacthang(@RequestParam("nam") int nam, ModelMap m) {
		
		Session session = factory.getCurrentSession();
		String hql = "Select month(ngaythem), SUM(thanhtien) from HoaDon where year(ngaythem) = :nam  AND matthd=1 GROUP BY month(ngaythem)";
		Query query = session.createQuery(hql);
		query.setParameter("nam", nam);
		List<Object[]> ds = query.list(); 
		m.addAttribute("ds", ds); 
		return "thongke/ketquadoanhthuthang";
	}
}
