package ptithcm.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="TRASUA")
public class TraSua {
	@Id
	@GeneratedValue
	@Column(name = "MATS")
	private int mats;
	
	@Column(name = "TENTS")
	private String tents;
	
	@Column(name = "MOTA")
	private String mota;
	
	@Column(name = "HINHANH")
	private String hinhanh;
	
	@Column(name = "TRANGTHAI")
	private int trangthai;

	
	@Column(name = "GIA")
	private Float gia;

	@OneToMany(mappedBy="trasua", fetch=FetchType.EAGER)
	private Collection<ChiTietSize> dscts;
	
	@OneToMany(mappedBy="trasua", fetch=FetchType.EAGER)
	private Collection<CongThuc> dsct;
	
	@OneToMany(mappedBy="trasua", fetch=FetchType.EAGER)
	private Collection<ChiTietHoaDon> dscthd;
	

	
	
	


	public Collection<ChiTietHoaDon> getDscthd() {
		return dscthd;
	}

	public void setDscthd(Collection<ChiTietHoaDon> dscthd) {
		this.dscthd = dscthd;
	}

	public int getMats() {
		return mats;
	}

	public void setMats(int mats) {
		this.mats = mats;
	}

	public String getTents() {
		return tents;
	}

	public void setTents(String tents) {
		this.tents = tents;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public String getHinhanh() {
		return hinhanh;
	}

	public void setHinhanh(String string) {
		this.hinhanh = string;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}



	public Float getGia() {
		return gia;
	}

	public void setGia(Float gia) {
		this.gia = gia;
	}

	public Collection<ChiTietSize> getDscts() {
		return dscts;
	}

	public void setDscts(Collection<ChiTietSize> dscts) {
		this.dscts = dscts;
	}

	
	

	public TraSua(int mats, String tents, String mota, String hinhanh, int trangthai, Float gia,
			Collection<ChiTietSize> dscts, Collection<CongThuc> dsct, Collection<ChiTietHoaDon> dscthd) {
		super();
		this.mats = mats;
		this.tents = tents;
		this.mota = mota;
		this.hinhanh = hinhanh;
		this.trangthai = trangthai;
		this.gia = gia;
		this.dscts = dscts;
		this.dsct = dsct;
		this.dscthd = dscthd;
	}

	public TraSua() {
		super();
	}

	public Collection<CongThuc> getDsct() {
		return dsct;
	}

	public void setDsct(Collection<CongThuc> dsct) {
		this.dsct = dsct;
	}

	
	public TraSua(TraSua ts) {
		
		this.mats = ts.getMats();
		this.tents = ts.getTents();
		this.mota = ts.getMota();
		this.hinhanh = ts.getHinhanh();
		this.trangthai = ts.getTrangthai();
	
		this.gia = ts.getGia();
		this.dscts = ts.getDscts();
		this.dsct = ts.getDsct();
	}
	
	
	
	
	
}
