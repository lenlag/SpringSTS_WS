package formation.afpa.fr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "boutiqueitem")
public class BoutiqueItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@Column(name="code", nullable = false, length = 30)
	private String code;
	
	@Column(name="label", nullable = false, length = 30)
	private String label;
	
	@Column(name="price", nullable = false, precision = 8, scale = 2)
	private double price;
	
	
	public BoutiqueItem(Long id, String code, String label, double price) {
		super();
		this.id = id;
		this.code = code;
		this.label = label;
		this.price = price;
	}
	
	public BoutiqueItem() {
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double d) {
		this.price = d;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", code=" + code + ", label=" + label + ", price=" + price + "]";
	}
	
	
}
