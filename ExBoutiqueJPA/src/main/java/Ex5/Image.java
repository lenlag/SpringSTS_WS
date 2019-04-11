package Ex5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "image")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	@Column(name = "path", nullable = false, length = 255)
	private String path;
	@Column(name = "alt_text", nullable = false, length = 255)
	private String altText;

		
	public Image() {

	}


	public Image(Long id, String path, String altText) {
		super();
		this.id = id;
		this.path = path;
		this.altText = altText;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getAltText() {
		return altText;
	}


	public void setAltText(String altText) {
		this.altText = altText;
	}


	@Override
	public String toString() {
		return "Image [id = " + id + ", path = " + path + ", Alternative Text = " + altText + "]";
	}



}
