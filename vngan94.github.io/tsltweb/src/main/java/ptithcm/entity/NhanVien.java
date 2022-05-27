package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "NHANVIEN")
public class NhanVien {
	@Id
	@GeneratedValue
	
	@Column(name = "MANV")
	private int manv;
	
	
	@Column(name = "HO")
	@NotBlank(message = "Không để trống họ")
	private String ho;
	
	
	@Column(name = "TEN")
	@NotBlank(message = "Không để trống tên")
	private String ten;
	
	
	@Column(name = "DIACHI")
	@NotBlank(message = "Không để trống địa chỉ")
	private String diachi;
	
	@Column(name = "SDT")
	@NotBlank(message = "Không để trống số điện thoại")
	@Pattern(regexp ="[0][0-9]{9}",message = "Số điện thoại không hợp lệ")
	private String sdt;
	

	@OneToOne
	@JoinColumn(name="MATK")
	private TaiKhoan taikhoan;
	
	@OneToMany(mappedBy="nhanvien", fetch=FetchType.EAGER)
	private Collection<DonDatHang> dsddh;
	@OneToMany(mappedBy="nhanvien", fetch=FetchType.EAGER)
	private Collection<HoaDon> dshd;
	
	
	
	public Collection<DonDatHang> getDsddh() {
		return dsddh;
	}

	public void setDsddh(Collection<DonDatHang> dsddh) {
		this.dsddh = dsddh;
	}

	public Collection<HoaDon> getDshd() {
		return dshd;
	}

	public void setDshd(Collection<HoaDon> dshd) {
		this.dshd = dshd;
	}

	public int getManv() {
		return manv;
	}

	public void setManv(int manv) {
		this.manv = manv;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public TaiKhoan getTaikhoan() {
		return taikhoan;
	}

	public void setTaikhoan(TaiKhoan taikhoan) {
		this.taikhoan = taikhoan;
	}
	

	public NhanVien(int manv, String ho, String ten, String diachi, String sdt, TaiKhoan taikhoan,
			Collection<DonDatHang> dsddh, Collection<HoaDon> dshd) {
		super();
		this.manv = manv;
		this.ho = ho;
		this.ten = ten;
		this.diachi = diachi;
		this.sdt = sdt;
		this.taikhoan = taikhoan;
		this.dsddh = dsddh;
		this.dshd = dshd;
	}

	public NhanVien() {
		super();
	}
	
	
}
