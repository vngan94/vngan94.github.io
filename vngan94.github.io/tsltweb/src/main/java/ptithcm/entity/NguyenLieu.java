package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "NGUYENLIEU")
public class NguyenLieu {
	@Id
	@GeneratedValue
	@Column(name = "MANL")
	private int manl;
	
	@Column(name = "TENNL")
	private String tennl;
	
	@Column(name = "SOLUONG")
	private Double soluong;
	
	@Column(name = "DONVI")
	private String donvi;
	
	
	@OneToMany(mappedBy="nguyenlieu", fetch=FetchType.EAGER)
	private Collection<CongThuc> dsct;

	@OneToMany(mappedBy="nguyenlieu", fetch=FetchType.EAGER)
	private Collection<ChiTietCungCap> dsctcc;
	
	@OneToMany(mappedBy="nguyenlieu", fetch=FetchType.EAGER)
	private Collection<ChiTietDonDatHang> dctddh;
	
	
	public Collection<ChiTietDonDatHang> getDctddh() {
		return dctddh;
	}

	public void setDctddh(Collection<ChiTietDonDatHang> dctddh) {
		this.dctddh = dctddh;
	}

	public Collection<ChiTietCungCap> getDsctcc() {
		return dsctcc;
	}

	public void setDsctcc(Collection<ChiTietCungCap> dsctcc) {
		this.dsctcc = dsctcc;
	}

	public int getManl() {
		return manl;
	}

	public void setManl(int manl) {
		this.manl = manl;
	}

	public String getTennl() {
		return tennl;
	}

	public void setTennl(String tennl) {
		this.tennl = tennl;
	}

	public Double getSoluong() {
		return soluong;
	}

	public void setSoluong(Double soluong) {
		this.soluong = soluong;
	}

	public String getDonvi() {
		return donvi;
	}

	public void setDonvi(String donvi) {
		this.donvi = donvi;
	}

	public Collection<CongThuc> getDsct() {
		return dsct;
	}

	public void setDsct(Collection<CongThuc> dsct) {
		this.dsct = dsct;
	}
	

	public NguyenLieu() {
		super();
	}

	public NguyenLieu(int manl, String tennl, Double soluong, String donvi, Collection<CongThuc> dsct,
			Collection<ChiTietCungCap> dsctcc, Collection<ChiTietDonDatHang> dctddh) {
		super();
		this.manl = manl;
		this.tennl = tennl;
		this.soluong = soluong;
		this.donvi = donvi;
		this.dsct = dsct;
		this.dsctcc = dsctcc;
		this.dctddh = dctddh;
	}

	
	
	
	

	
	
	
}
