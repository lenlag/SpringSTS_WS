package formation.afpa.fr.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "myorder")
public class MyOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
		
	
	@OneToMany(fetch = FetchType.LAZY, cascade= (CascadeType.ALL))
	@JoinTable
	private Set<OrderItem> oItem = new HashSet<>();
	
	@Column(name="date", nullable = false)
	private Date dateAchat;
	
	
	public MyOrder() {
		
	}


	public MyOrder(Set<OrderItem> oItem, Date dateAchat) {
		super();
		this.oItem = oItem;
		this.dateAchat = dateAchat;
	}


	public Set<OrderItem> getoItem() {
		return oItem;
	}


	public void setoItem(Set<OrderItem> oItem) {
		this.oItem = oItem;
	}


	public Date getDateAchat() {
		return dateAchat;
	}


	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "MyOrder [oItem=" + oItem + ", dateAchat=" + dateAchat + "]";
	}
	
	


	
	

}
