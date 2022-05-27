package ptithcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CHITIETSIZE")
public class ChiTietSize {
	@Id
	@GeneratedValue
	@Column(name = "MACTS")
	private int macts;

	@ManyToOne
	@JoinColumn(name="MATS")
	private TraSua trasua;
	
	@ManyToOne
	@JoinColumn(name="MASIZE")
	private Size size;

	public int getMacts() {
		return macts;
	}

	public void setMacts(int macts) {
		this.macts = macts;
	}

	public TraSua getTrasua() {
		return trasua;
	}

	public void setTrasua(TraSua trasua) {
		this.trasua = trasua;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public ChiTietSize(int macts, TraSua trasua, Size size) {
		super();
		this.macts = macts;
		this.trasua = trasua;
		this.size = size;
	}

	public ChiTietSize() {
		super();
	}
	
	
	
}
