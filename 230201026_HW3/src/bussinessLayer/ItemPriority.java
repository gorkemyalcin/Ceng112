package bussinessLayer;

/**An ItemPriotity class defining the properties of an item.
 * @author Ben-Malik_Gorkem
 *
 */
public class ItemPriority {
	
	private String name;
	private int priority;
	private  double price;
	
	public ItemPriority(String name, int priority) {
		this.name = name;
		this.priority = priority;
		this.price = 0.0;
	}
	
	public String getName() {
		return name;
	}
	public int getPriority() {
		return priority;
	} 
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString() {
		return "Name: " + name + ", Price: " + price;
	}

} // end ItemPriority.
