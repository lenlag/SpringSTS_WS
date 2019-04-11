package Ex5;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "item")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	@Column(name = "name", nullable = false, length = 30) // for a String-valued column, use length
	private String name;
	
	@Column(name = "code", nullable = false, columnDefinition = "varchar(30)") // to specify a Varchar type of 30																			// elements max
	private String code;
	
	@Column(name = "description", nullable = false, columnDefinition = "varchar(255)")
	private String description;
	
	@Column(name = "price", nullable = false, precision = 8, scale = 2) // for float values
	private float price;

	// ManyToMany, as 1 Item can belong to several categories, and 1 category can
	// concern several items
	
	@ManyToMany(fetch = FetchType.EAGER,cascade= {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="item__category", joinColumns=@JoinColumn(name="item_id"), inverseJoinColumns=@JoinColumn(name="category_id"))
	private Set<Category> categories = new HashSet<>();
	
	@OneToOne //one(Item) - to One(Warranty)
	@JoinColumn(name="id_warranty")
	private Warranty warranty;
		
	@OneToMany(fetch = FetchType.EAGER,cascade= {CascadeType.PERSIST,CascadeType.MERGE}) // one item - to many Images
	private Set<Image> images = new HashSet<>(); // remplacer List/ArrayList par set !!!
	
	public Item() {
		
	}

	public Item(Long id, String name, String code, String description, float price, Set<Category> categories,
			Warranty warranty, Set<Image> images) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;
		this.price = price;
		this.categories = categories;
		this.warranty = warranty;
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Warranty getWarranty() {
		return warranty;
	}

	public void setWarranty(Warranty warranty) {
		this.warranty = warranty;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() { //do not put categories.toString(), as item is contained in toString() of Category, it will generate Stack Overflow...
		String s = "";
		for (Image i : images) {
			s+=i.getAltText();
		}
		
		return "Item [id = " + id + ", name = " + name + ", code = " + code + ", description = " + description + ", price = "
				+ price + " warranty = " + warranty.getSummary() + ", image(s) = " + s + "]";
	}

	

	
}
