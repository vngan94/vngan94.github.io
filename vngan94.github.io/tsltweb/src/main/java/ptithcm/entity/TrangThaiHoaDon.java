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
@Table(name="TRANGTHAIHOADON")
public class TrangThaiHoaDon {
	@Id 
	@GeneratedValue
	@Column(name="MATTHD")
	private int matthd;
	
	@Column(name="TENTT")
	private String tentt;
	
	@OneToMany(mappedBy="trangthai", fetch=FetchType.EAGER)
	private Collection<HoaDon> dshd;

	public int getMatthd() {
		return matthd;
	}

	public void setMatthd(int matthd) {
		this.matthd = matthd;
	}

	public String getTentt() {
		return tentt;
	}

	public void setTentt(String tentt) {
		this.tentt = tentt;
	}

	
	public Collection<HoaDon> getDshd() {
		return dshd;
	}

	public void setDshd(Collection<HoaDon> dshd) {
		this.dshd = dshd;
	}

	public TrangThaiHoaDon() {
		super();
	}

	public TrangThaiHoaDon(int matthd, String tentt, Collection<HoaDon> dshd) {
		super();
		this.matthd = matthd;
		this.tentt = tentt;
		this.dshd = dshd;
	}
	
	
	
	
	
}
