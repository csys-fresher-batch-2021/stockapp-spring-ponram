package in.ponram.dto;

import in.ponram.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

	private Product product;
	private int initialQuantity;
	private boolean status;
}
