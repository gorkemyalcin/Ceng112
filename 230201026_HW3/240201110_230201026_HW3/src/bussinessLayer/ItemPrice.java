package bussinessLayer;

/**
 * A shoppingItem class providing information about the item to be shopped.
 * @author Ben-Malik_Gorkem
 *
 */
public final class ItemPrice {
	private final String name;
	private final  double price;
	
	public ItemPrice(String name, double price) {
		this.name = name;
		this.price = price;
	} 
	
	
	
	public String getName() {
		return name;
	}
	

	public double getPrice() {
		return price;
	}
	
	public String toString() {
		return name + ", " + price;
	}
} // end ItemPrice.
