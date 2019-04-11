package Ex5;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue(
			strategy= GenerationType.AUTO,
			generator="native"
			)
			@GenericGenerator(
			name = "native",
			strategy = "native"
			)
	private	Long id;
	
	@Column(name="name", nullable=false, length=30)//for a String-valued column, use length
	private String name;
	@Column(name="code", nullable=false, columnDefinition="varchar(30)") //to specify a Varchar type of 30 elements max
	private String code;
	@Column(name="description", nullable=false, columnDefinition="varchar(255)")
	private	String description;
	
	@ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER) //Many To Many to declare in both classes. This is a dependent class, so we put mappedBy="name of the FK object in the main class mapping this dependent class"
	private Set<Item> items = new HashSet<>(); // !! put EAGER, otherwise the list is not uploaded upon findAll() function request;
	
	
	public Category() {
		
	}


	public Category(Long id, String name, String code, String description) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;
	
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


	public Set<Item> getItems() {
		return items;
	}


	public void setItems(Set<Item> items) {
		this.items = items;
	}


	@Override
	public String toString() {
		String s = "";
		for (Item item : items) {
			s+= item.getName() +"| ";
		}
		
		return "Category [id = " + id + ", name = " + name + ", code = " + code + ", description = " + description + ", item(s) = "
				+ s +  "]";
	}

	
	
	
}