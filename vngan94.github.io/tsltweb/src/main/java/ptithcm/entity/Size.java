
package ptithcm.entity;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;



@Entity
@Table(name="SIZE")
public class Size {
	@Id
	@GeneratedValue
	@Column(name = "MASIZE")
	private int masize;
	
	@Column(name = "TENSIZE")
	private String tensize;
	
	@Column(name = "TILE")
	private Float tile;
	
	@OneToMany(mappedBy="size", fetch=FetchType.EAGER)
	private Collection<ChiTietSize> dscts;
	
	@OneToMany(mappedBy="size", fetch=FetchType.EAGER)
	private Collection<ChiTietHoaDon> dschd;
	
	public int getMasize() {
		return masize;
	}

	public void setMasize(int masize) {
		this.masize = masize;
	}

	public String getTensize() {
		return tensize;
	}

	public void setTensize(String tensize) {
		this.tensize = tensize;
	}

	public Float getTile() {
		return tile;
	}

	public void setTile(Float tile) {
		this.tile = tile;
	}

	public Collection<ChiTietSize> getDscts() {
		return dscts;
	}

	public void setDscts(Collection<ChiTietSize> dscts) {
		this.dscts = dscts;
	}

	

	public Size(int masize, String tensize, Float tile, Collection<ChiTietSize> dscts,
			Collection<ChiTietHoaDon> dschd) {
		super();
		this.masize = masize;
		this.tensize = tensize;
		this.tile = tile;
		this.dscts = dscts;
		this.dschd = dschd;
	}

	public Size() {
		super();
	}


	
	
	
	
}