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
@Table(name="TOPPING")
public class Topping {
	@Id
	@GeneratedValue
	@Column(name = "MATOPPING")
	private int matopping;
	
	@Column(name = "TOPPING")
	private String topping;
	
	@Column(name = "GIA")
	private Float gia;
	
	@OneToMany(mappedBy="topping", fetch=FetchType.LAZY)
	private Collection<ChiTietHoaDon> dscthd;

	public int getMatopping() {
		return matopping;
	}

	public void setMatopping(int matopping) {
		this.matopping = matopping;
	}

	public String getTopping() {
		return topping;
	}

	public void setTopping(String topping) {
		this.topping = topping;
	}

	public Collection<ChiTietHoaDon> getDscthd() {
		return dscthd;
	}

	public void setDscthd(Collection<ChiTietHoaDon> dscthd) {
		this.dscthd = dscthd;
	}

	
	public Float getGia() {
		return gia;
	}

	public void setGia(Float gia) {
		this.gia = gia;
	}
	
	

	public Topping(int matopping, String topping, Float gia, Collection<ChiTietHoaDon> dscthd) {
		super();
		this.matopping = matopping;
		this.topping = topping;
		this.gia = gia;
		this.dscthd = dscthd;
	}

	public Topping() {
		super();
	}
	
	
}
