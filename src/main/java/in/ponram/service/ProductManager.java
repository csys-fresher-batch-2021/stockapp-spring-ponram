package in.ponram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ponram.dao.ProductRepository;
import in.ponram.model.Product;
import in.ponram.validator.ProductValidation;

@Service
public class ProductManager {

	@Autowired
	private ProductRepository productRepository;

	/**
	 * This method is used to add the product in the ArrayList
	 * 
	 * @param product
	 * @throws Exception
	 */
	public boolean addStock(Product product) {

		boolean added = false;
		if (ProductValidation.isValidProduct(product)) {

			added = productRepository.save(product);
		}
		return added;
	}
}
