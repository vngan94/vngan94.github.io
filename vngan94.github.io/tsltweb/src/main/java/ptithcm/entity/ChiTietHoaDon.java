package ptithcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CHITIETHOADON")
public class ChiTietHoaDon {
	@Id
	@GeneratedValue
	@Column(name = "MACTHD")
	private int macthd;
	
	@ManyToOne
	@JoinColumn(name="MAHD")
	private HoaDon hoadon;
	
	@ManyToOne
	@JoinColumn(name="MASIZE")
	private Size size;
	
	@ManyToOne
	@JoinColumn(name="MATS")
	private TraSua trasua;
	
	@Column(name = "SOLUONG")
	private int soluong;
	

	@ManyToOne
	@JoinColumn(name="MATOPPING")
	private Topping topping;
	
	@Column(name = "GHICHU")
	private String ghichu;

	public int getMacthd() {
		return macthd;
	}

	public void setMacthd(int macthd) {
		this.macthd = macthd;
	}

	public HoaDon getHoadon() {
		return hoadon;
	}

	public void setHoadon(HoaDon hoadon) {
		this.hoadon = hoadon;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public TraSua getTrasua() {
		return trasua;
	}

	public void setTrasua(TraSua trasua) {
		this.trasua = trasua;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public Topping getTopping() {
		return topping;
	}

	public void setTopping(Topping topping) {
		this.topping = topping;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public ChiTietHoaDon(int macthd, HoaDon hoadon, Size size, TraSua trasua, int soluong, Topping topping,
			String ghichu) {
		super();
		this.macthd = macthd;
		this.hoadon = hoadon;
		this.size = size;
		this.trasua = trasua;
		this.soluong = soluong;
		this.topping = topping;
		this.ghichu = ghichu;
	}

	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
	