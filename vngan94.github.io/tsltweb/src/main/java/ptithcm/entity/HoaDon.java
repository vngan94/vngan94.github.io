package ptithcm.entity;

import java.util.Collection;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="HOADON")
public class HoaDon {
	@Id 
	@GeneratedValue
	@Column(name="MAHD")
	private int mahd;
	
	@Column(name="THANHTIEN")
	private int thanhtien;
	
	@Column(name="THOIGIANLAP")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
	private Date ngaythem;
	
	@ManyToOne
	@JoinColumn(name="MANV")
	private NhanVien nhanvien;
	
	@ManyToOne
	@JoinColumn(name="MATTHD")
	private TrangThaiHoaDon trangthai;
	
	@OneToOne
	@JoinColumn(name="MADH")
	private DonHang donhang;
	
	@OneToMany(mappedBy="hoadon", fetch=FetchType.EAGER)
	private Collection<ChiTietHoaDon> dscthd;

	public int getMahd() {
		return mahd;
	}

	public void setMahd(int mahd) {
		this.mahd = mahd;
	}

	public Date getNgaythem() {
		return ngaythem;
	}

	public void setNgaythem(Date ngaythem) {
		this.ngaythem = ngaythem;
	}

	public NhanVien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(NhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}

	public TrangThaiHoaDon getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(TrangThaiHoaDon trangthai) {
		this.trangthai = trangthai;
	}

	public DonHang getDonhang() {
		return donhang;
	}

	public void setDonhang(DonHang donhang) {
		this.donhang = donhang;
	}

	public Collection<ChiTietHoaDon> getDscthd() {
		return dscthd;
	}

	public void setDscthd(Collection<ChiTietHoaDon> dscthd) {
		this.dscthd = dscthd;
	}

	

	public HoaDon(int mahd, int thanhtien, Date ngaythem, NhanVien nhanvien, TrangThaiHoaDon trangthai, DonHang donhang,
			Collection<ChiTietHoaDon> dscthd) {
		super();
		this.mahd = mahd;
		this.thanhtien = thanhtien;
		this.ngaythem = ngaythem;
		this.nhanvien = nhanvien;
		this.trangthai = trangthai;
		this.donhang = donhang;
		this.dscthd = dscthd;
	}

	public int getThanhtien() {
		return thanhtien;
	}

	public void setThanhtien(int thanhtien) {
		this.thanhtien = thanhtien;
	}

	public HoaDon() {
		super();
	}
	
	
	
	
}
