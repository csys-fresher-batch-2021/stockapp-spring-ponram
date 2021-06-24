package in.ponram.model;

import lombok.Data;

@Data
public class OrderItem {

	private int billNumber;
	private int productId;
	private String brandName;
	private String productCategory;
	private String productName;
	private int rate;
	private int quantity;
	private int totalAmount;
}
