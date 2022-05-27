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
@Table(name="NHACUNGCAP")
public class NhaCungCap {
	@Id
	@GeneratedValue
	@Column(name="MANCC")
	private int mancc;
	@Column(name="TENNCC")
	private String tenncc;
	@Column(name="SDT")
	private String sdt;
	@Column(name="DIACHI")
	private String diachi;
	
	@OneToMany(mappedBy="nhacungcap", fetch=FetchType.EAGER)
	private Collection<ChiTietCungCap> dsctcc;

	public int getMancc() {
		return mancc;
	}

	public void setMancc(int mancc) {
		this.mancc = mancc;
	}

	public String getTenncc() {
		return tenncc;
	}

	public void setTenncc(String tenncc) {
		this.tenncc = tenncc;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public Collection<ChiTietCungCap> getDsctcc() {
		return dsctcc;
	}

	public void setDsctcc(Collection<ChiTietCungCap> dsctcc) {
		this.dsctcc = dsctcc;
	}

	public NhaCungCap(int mancc, String tenncc, String sdt, String diachi, Collection<ChiTietCungCap> dsctcc) {
		super();
		this.mancc = mancc;
		this.tenncc = tenncc;
		this.sdt = sdt;
		this.diachi = diachi;
		this.dsctcc = dsctcc;
	}

	public NhaCungCap() {
		super();
	}
	
	
	
}
