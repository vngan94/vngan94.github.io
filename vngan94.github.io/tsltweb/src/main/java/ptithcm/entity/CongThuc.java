package ptithcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONGTHUC")
public class CongThuc {
	@Id
	@GeneratedValue
	@Column(name = "MACT")
	private int mact;
	
	@ManyToOne
	@JoinColumn(name="MATS")
	private TraSua trasua;
	
	@ManyToOne
	@JoinColumn(name="MANL")
	private NguyenLieu nguyenlieu;
	
	@Column(name = "LIEULUONG")
	private Double lieuluong;
	
	@Column(name = "DONVI")
	private String donvi;
	
	@Column(name = "GHICHU")
	private String ghichu;

	public int getMact() {
		return mact;
	}

	public void setMact(int mact) {
		this.mact = mact;
	}

	public TraSua getTrasua() {
		return trasua;
	}

	public void setTrasua(TraSua trasua) {
		this.trasua = trasua;
	}

	public NguyenLieu getNguyenlieu() {
		return nguyenlieu;
	}

	public void setNguyenlieu(NguyenLieu nguyenlieu) {
		this.nguyenlieu = nguyenlieu;
	}

	public Double getLieuluong() {
		return lieuluong;
	}

	public void setLieuluong(Double lieuluong) {
		this.lieuluong = lieuluong;
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

	public CongThuc(int mact, TraSua trasua, NguyenLieu nguyenlieu, Double lieuluong, String donvi, String ghichu) {
		super();
		this.mact = mact;
		this.trasua = trasua;
		this.nguyenlieu = nguyenlieu;
		this.lieuluong = lieuluong;
		this.donvi = donvi;
		this.ghichu = ghichu;
	}

	public CongThuc() {
		super();
	}

	
	
	
	
}
