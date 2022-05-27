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
@Table(name="TRANGTHAIDONDATHANG")
public class TrangThaiDonDatHang {
	@Id
	@GeneratedValue
	@Column(name="MATTDDH")
	private int mattddh;
	
	@Column(name="TENTT")
	private String tentt;
	
	@OneToMany(mappedBy="trangthai", fetch=FetchType.LAZY)
	private Collection<DonDatHang> dsddh;

	public int getMattddh() {
		return mattddh;
	}

	public void setMattddh(int mattddh) {
		this.mattddh = mattddh;
	}

	public String getTentt() {
		return tentt;
	}

	public void setTentt(String tentt) {
		this.tentt = tentt;
	}

	public Collection<DonDatHang> getDsddh() {
		return dsddh;
	}

	public void setDsddh(Collection<DonDatHang> dsddh) {
		this.dsddh = dsddh;
	}

	public TrangThaiDonDatHang(int mattddh, String tentt, Collection<DonDatHang> dsddh) {
		super();
		this.mattddh = mattddh;
		this.tentt = tentt;
		this.dsddh = dsddh;
	}

	public TrangThaiDonDatHang() {
		super();
	}
	
	
	
}
