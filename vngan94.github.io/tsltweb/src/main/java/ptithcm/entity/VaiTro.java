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
@Table(name = "VAITRO")
public class VaiTro {
	@Id
	@GeneratedValue
	@Column(name="MAVT")
	private int mavt;
	
	@Column(name="TENVAITRO")
	private String tenvaitro;
	
	@OneToMany(mappedBy="vaitro", fetch=FetchType.EAGER)
	private Collection<TaiKhoan> dstaikhoan;

	public int getMavt() {
		return mavt;
	}

	public void setMavt(int mavt) {
		this.mavt = mavt;
	}

	public String getTenvaitro() {
		return tenvaitro;
	}

	public void setTenvaitro(String tenvaitro) {
		this.tenvaitro = tenvaitro;
	}

	public Collection<TaiKhoan> getDstaikhoan() {
		return dstaikhoan;
	}

	public void setDstaikhoan(Collection<TaiKhoan> dstaikhoan) {
		this.dstaikhoan = dstaikhoan;
	}

	public VaiTro(int mavt, String tenvaitro, Collection<TaiKhoan> dstaikhoan) {
		super();
		this.mavt = mavt;
		this.tenvaitro = tenvaitro;
		this.dstaikhoan = dstaikhoan;
	}

	public VaiTro() {
		super();
	}
	
	
}
