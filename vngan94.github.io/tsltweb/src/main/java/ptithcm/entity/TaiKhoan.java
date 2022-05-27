package ptithcm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "TAIKHOAN")
public class TaiKhoan {
	@Id
	@GeneratedValue
	@Column(name = "MATK")
	private int matk;
	
	@Column(name = "TENTAIKHOAN")
	private String tentaikhoan;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "TRANGTHAI")
	private int trangthai;
	
	@Column(name = "NGAYTHEM")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date ngaythem;
	
	@ManyToOne
	@JoinColumn(name="MAVT")
	private VaiTro vaitro;
	
	@OneToOne
	@JoinColumn(name="MANV")
	private NhanVien nhanvien;

	public int getMatk() {
		return matk;
	}

	public void setMatk(int matk) {
		this.matk = matk;
	}

	public String getTentaikhoan() {
		return tentaikhoan;
	}

	public void setTentaikhoan(String tentaikhoan) {
		this.tentaikhoan = tentaikhoan;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	public Date getNgaythem() {
		return ngaythem;
	}

	public void setNgaythem(Date ngaythem) {
		this.ngaythem = ngaythem;
	}

	public VaiTro getVaitro() {
		return vaitro;
	}

	public void setVaitro(VaiTro vaitro) {
		this.vaitro = vaitro;
	}

	public NhanVien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(NhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}

	public TaiKhoan(int matk, String tentaikhoan, String password, int trangthai, Date ngaythem, VaiTro vaitro,
			NhanVien nhanvien) {
		super();
		this.matk = matk;
		this.tentaikhoan = tentaikhoan;
		this.password = password;
		this.trangthai = trangthai;
		this.ngaythem = ngaythem;
		this.vaitro = vaitro;
		this.nhanvien = nhanvien;
	}

	public TaiKhoan() {
		super();
	}
	
	
}
