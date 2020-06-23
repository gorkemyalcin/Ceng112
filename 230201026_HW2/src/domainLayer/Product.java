package domainLayer;

/**
 * @author Ben-Malik_Gorkem
 *
 */
public class Product {
	private int productId;
	private String productName;
	private String productType;
	private double productPrice;
	
	public Product(int productId, String productName, String productType, double productPrice) {
		setProductId(productId);
		setProductName(productName);
		setProductType(productType);
		setProductPrice(productPrice);
	} // end constructor.
	
	private void setProductId(int productId) {
		if (productId < 0) {
			throw new IllegalArgumentException("The product of the product should be more than 0.0");
		}
		else {
			this.productId = productId;
		}
	}
	
	private void setProductName(String productName) {
		if (productName != null) {
			this.productName = productName;
		}
		else {
			throw new IllegalArgumentException("The product name cannot be null");
		}
	}
	
	private void setProductType(String productType) {
		if (productType != null) {
			this.productType = productType;
		}
		else {
			throw new IllegalArgumentException("The product type cannot be null");
		}
	}
	
	private void setProductPrice(double productPrice) {
		if (productPrice < 0) {
			throw new IllegalArgumentException("The price has to be greater than 0.0");
		}
		this.productPrice = productPrice;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public double getProductPrice() {
		return productPrice;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public String getProductType() {
		return productType;
	}
	
	public String toString() {
		return "Product [productName: " + productName + ", productId: " + productId + ", productPrice: " + productPrice + ", productType:" + productType + "]"; 
	}
} // end Product
