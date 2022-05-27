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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="DONDATHANG")
public class DonDatHang {
	@Id
	@GeneratedValue
	@Column(name="MADDH")
	private int maddh;
	
	@Column(name = "THOIGIANLAP")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date ngaylap;
	
	@ManyToOne
	@JoinColumn(name="MANV")
	private NhanVien nhanvien;
	
	@ManyToOne
	@JoinColumn(name="MATTDDH")
	private TrangThaiDonDatHang trangthai;
	
	
	@ManyToOne
	@JoinColumn(name="MANCC")
	private NhaCungCap nhacungcap;

	@OneToMany(mappedBy="dondathang", fetch=FetchType.EAGER)
	private Collection<ChiTietDonDatHang> dsctddh;
	
	
	public Collection<ChiTietDonDatHang> getDsctddh() {
		return dsctddh;
	}


	public void setDsctddh(Collection<ChiTietDonDatHang> dsctddh) {
		this.dsctddh = dsctddh;
	}


	public int getMaddh() {
		return maddh;
	}


	public void setMaddh(int maddh) {
		this.maddh = maddh;
	}


	public Date getNgaylap() {
		return ngaylap;
	}


	public void setNgaylap(Date ngaylap) {
		this.ngaylap = ngaylap;
	}


	public NhanVien getNhanvien() {
		return nhanvien;
	}


	public void setNhanvien(NhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}


	public TrangThaiDonDatHang getTrangthai() {
		return trangthai;
	}


	public void setTrangthai(TrangThaiDonDatHang trangthai) {
		this.trangthai = trangthai;
	}


	


	public DonDatHang(int maddh, Date ngaylap, NhanVien nhanvien, TrangThaiDonDatHang trangthai, NhaCungCap nhacungcap,
			Collection<ChiTietDonDatHang> dsctddh) {
		super();
		this.maddh = maddh;
		this.ngaylap = ngaylap;
		this.nhanvien = nhanvien;
		this.trangthai = trangthai;
		this.nhacungcap = nhacungcap;
		this.dsctddh = dsctddh;
	}


	public NhaCungCap getNhacungcap() {
		return nhacungcap;
	}


	public void setNhacungcap(NhaCungCap nhacungcap) {
		this.nhacungcap = nhacungcap;
	}


	public DonDatHang() {
		super();
	}
	
	
}
