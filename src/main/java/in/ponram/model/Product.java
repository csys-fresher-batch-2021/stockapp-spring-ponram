package in.ponram.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

	private int productId;
	private String brandName;
	private String productName;
	private String productCategory;
//	@Past(message="Date of birth should not be greater than current date")
	private LocalDate arrivalDate;
	private int rate;
	private int availableQuantity;

}
