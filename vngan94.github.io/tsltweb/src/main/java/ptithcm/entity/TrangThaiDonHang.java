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
@Table(name="TRANGTHAIDONHANG")
public class TrangThaiDonHang {
	@Id
	@GeneratedValue
	@Column(name="MATTDH")
	private int mattdh;
	
	@Column(name="TENTT")
	private String tentt;
	
	@OneToMany(mappedBy="trangthai", fetch=FetchType.LAZY)
	private Collection<DonHang> dsdh;

	public int getMattdh() {
		return mattdh;
	}

	public void setMattdh(int mattdh) {
		this.mattdh = mattdh;
	}

	public String getTentt() {
		return tentt;
	}

	public void setTentt(String tentt) {
		this.tentt = tentt;
	}

	public Collection<DonHang> getDsdh() {
		return dsdh;
	}

	public void setDsdh(Collection<DonHang> dsdh) {
		this.dsdh = dsdh;
	}

	public TrangThaiDonHang(int mattdh, String tentt, Collection<DonHang> dsdh) {
		super();
		this.mattdh = mattdh;
		this.tentt = tentt;
		this.dsdh = dsdh;
	}

	public TrangThaiDonHang() {
		super();
	}
	
	
	
}
