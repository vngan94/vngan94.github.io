package ptithcm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="CHITIETCUNGCAP")
public class ChiTietCungCap {
	@Id
	@GeneratedValue
	@Column(name="MACTCC")
	private int mactcc;
	
	@ManyToOne
	@JoinColumn(name="MANCC")
	private NhaCungCap nhacungcap;
	
	@ManyToOne
	@JoinColumn(name="MANL")
	private NguyenLieu nguyenlieu;
	
	@Column(name = "DONGIA")
	private Float dongia;
	
	@Column(name = "DONVI")
	private String donvi;

	public int getMactcc() {
		return mactcc;
	}

	public void setMactcc(int mactcc) {
		this.mactcc = mactcc;
	}

	public NhaCungCap getNhacungcap() {
		return nhacungcap;
	}

	public void setNhacungcap(NhaCungCap nhacungcap) {
		this.nhacungcap = nhacungcap;
	}

	public NguyenLieu getNguyenlieu() {
		return nguyenlieu;
	}

	public void setNguyenlieu(NguyenLieu nguyenlieu) {
		this.nguyenlieu = nguyenlieu;
	}

	public Float getDongia() {
		return dongia;
	}

	public void setDongia(Float dongia) {
		this.dongia = dongia;
	}

	public String getDonvi() {
		return donvi;
	}

	public void setDonvi(String donvi) {
		this.donvi = donvi;
	}

	public ChiTietCungCap(int mactcc, NhaCungCap nhacungcap, NguyenLieu nguyenlieu, Float dongia, String donvi) {
		super();
		this.mactcc = mactcc;
		this.nhacungcap = nhacungcap;
		this.nguyenlieu = nguyenlieu;
		this.dongia = dongia;
		this.donvi = donvi;
	}

	public ChiTietCungCap() {
		super();
	}
	
	
	

}
