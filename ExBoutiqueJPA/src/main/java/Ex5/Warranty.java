package Ex5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="warranty")
public class Warranty {
	
	@Id
	@GeneratedValue(
			strategy= GenerationType.AUTO,
			generator="native"
			)
			@GenericGenerator(
			name = "native",
			strategy = "native"
			)
	private Long id;
	
	@Column(name="summary", nullable=false, length=255)
		private String summary;
	@Column(name="full_text", nullable=false, length=255)
		private String fullText;
	
	public Warranty() {
		
	}

	public Warranty(Long id, String summary, String fullText) {
		super();
		this.id = id;
		this.summary = summary;
		this.fullText = fullText;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getFullText() {
		return fullText;
	}

	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	@Override
	public String toString() {
		return "Warranty [id = " + id + ", summary = " + summary + ", Full Text = " + fullText + "]";
	}
	
	

}
