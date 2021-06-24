package in.ponram.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Order {

	private int billNumber;
	private String username;
	private List<OrderItem> orderDetails;
	private OrderItem orderDetail;
	private int totalAmount;
	private LocalDate purchaseDate;
}
