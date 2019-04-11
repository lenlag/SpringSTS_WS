package formation.afpa.fr.entity;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name="orderitem")
public class OrderItem {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@ManyToOne
	@JoinColumn
	private BoutiqueItem boutiqueItem;
	
	@Column(name="quantity")
	private Integer nb;

	public OrderItem(BoutiqueItem boutiqueItem, Integer nb) {
		super();
		this.boutiqueItem = boutiqueItem;
		this.nb = nb;
	}
	
	public OrderItem() {
		
	}

	public BoutiqueItem getItem() {
		return boutiqueItem;
	}

	public void setItem(BoutiqueItem boutiqueItem) {
		this.boutiqueItem = boutiqueItem;
	}

	public Integer getNb() {
		return nb;
	}

	public void setNb(Integer nb) {
		this.nb = nb;
	}

		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BoutiqueItem [item=" + boutiqueItem + ", nb=" + nb + "]";
	}

		
}
