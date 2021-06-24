package in.ponram.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ponram.dao.ProductRepository;
import in.ponram.exception.ValidatorException;
import in.ponram.model.Order;
import in.ponram.model.OrderItem;
import in.ponram.model.Product;
import in.ponram.util.NumberValidator;

@Component
public class OrderValidation {

	private OrderValidation() {
		// default constructor
	}

	@Autowired
	private ProductRepository productRepository;

	/**
	 * This method is used to validate product id and check quantity is valid
	 * 
	 * @param order
	 * @return
	 */
	public boolean isValidIdAndQuantity(Order order) {

		// validation
		boolean isValid = false;
		for (OrderItem details : order.getOrderDetails()) {
			boolean isValidId = NumberValidator.isValidNumber(details.getProductId(), "Invalid Id");
			if (isValidId) {
				boolean isValidQuantity = checkQuantity(details.getQuantity(), details.getProductId());
				if (isValidQuantity) {
					isValid = true;
				} else {
					throw new ValidatorException("Qunatity out of stock");
				}
			}
		}
		return isValid;
	}

	/**
	 * This method is to validate quantity and user enter quantity is present in
	 * stock
	 * 
	 * @param quantity
	 * @param productId
	 * @return
	 */
	public boolean checkQuantity(int quantity, int productId) {

		boolean isValid = false;
		if (NumberValidator.isValidNumber(quantity, "Invalid Qunatity")) {
			List<Product> products = productRepository.findAll();
			for (Product product : products) {
				if (product.getProductId() == productId && product.getAvailableQuantity() >= quantity) {
					isValid = true;
					break;
				}
			}
		}
		return isValid;
	}

	/**
	 * This method is used to get the rate from the stock using product id
	 * 
	 * @param productId
	 * @return
	 */
	public int getRate(int productId) {
		int rate = 0;

		List<Product> products = productRepository.findAll();
		for (Product product : products) {
			if (product.getProductId() == productId) {
				rate = product.getRate();
				break;
			}
		}
		return rate;

	}

}
