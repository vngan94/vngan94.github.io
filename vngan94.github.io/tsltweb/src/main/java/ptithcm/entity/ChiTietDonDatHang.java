package ptithcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name="CHITIETDONDATHANG")
public class ChiTietDonDatHang {
	@Id
	@GeneratedValue
	@Column(name="MACTDDH")
	private int mactddh;
	
	@ManyToOne
	@JoinColumn(name="MADDH")
	private DonDatHang dondathang;
	
	@ManyToOne
	@JoinColumn(name="MANL")
	private NguyenLieu nguyenlieu;
	
	@Column(name="SOLUONG")
	private int soluong;
	
	@Column(name="DONVI")
	private String donvi;
	
	
	
	@Column(name="GHICHU")
	private String ghichu;

	public int getMactddh() {
		return mactddh;
	}

	public void setMactddh(int mactddh) {
		this.mactddh = mactddh;
	}

	public DonDatHang getDondathang() {
		return dondathang;
	}

	public void setDondathang(DonDatHang dondathang) {
		this.dondathang = dondathang;
	}

	public NguyenLieu getNguyenlieu() {
		return nguyenlieu;
	}

	public void setNguyenlieu(NguyenLieu nguyenlieu) {
		this.nguyenlieu = nguyenlieu;
	}

	public String getDonvi() {
		return donvi;
	}

	public void setDonvi(String donvi) {
		this.donvi = donvi;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public ChiTietDonDatHang() {
		super();
	}

	public ChiTietDonDatHang(int mactddh, DonDatHang dondathang, NguyenLieu nguyenlieu, int soluong, String donvi,
			String ghichu) {
		super();
		this.mactddh = mactddh;
		this.dondathang = dondathang;
		this.nguyenlieu = nguyenlieu;
		this.soluong = soluong;
		this.donvi = donvi;
		this.ghichu = ghichu;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	
	
	
	
	
}
