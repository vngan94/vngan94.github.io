package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="DONHANG")
public class DonHang {
	@Id 
	@GeneratedValue
	@Column(name="MADH")
	private int madh;
	
	@ManyToOne
	@JoinColumn(name="MATTDH")
	private TrangThaiDonHang trangthai;
	
	@OneToOne
	@JoinColumn(name="MAHD")
	private HoaDon hoadon;

	public int getMadh() {
		return madh;
	}

	public void setMadh(int madh) {
		this.madh = madh;
	}

	public TrangThaiDonHang trangthai() {
		return trangthai;
	}

	public void setTentt(TrangThaiDonHang trangthai) {
		this.trangthai = trangthai;
	}
	

	public TrangThaiDonHang getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(TrangThaiDonHang trangthai) {
		this.trangthai = trangthai;
	}

	public HoaDon getHoadon() {
		return hoadon;
	}

	public void setHoadon(HoaDon hoadon) {
		this.hoadon = hoadon;
	}
	
	

	public DonHang(int madh, TrangThaiDonHang trangthai, HoaDon hoadon) {
		super();
		this.madh = madh;
		this.trangthai = trangthai;
		this.hoadon = hoadon;
	}

	public DonHang() {
		super();
	}


	
	
	
}

