package formation.afpa.fr.session;

public class CartItem {
	
	private String code;
	private String label;
	private int quantity;
	private double price;
	
	public CartItem() {
		// TODO Auto-generated constructor stub
	}


	public CartItem(String code, String label, int quantity, double price) {
		super();
		this.code = code;
		this.label = label;
		this.quantity = quantity;
		this.price = price;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
