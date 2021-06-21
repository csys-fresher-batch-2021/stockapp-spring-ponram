package in.ponram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ponram.dto.Message;
import in.ponram.model.Product;
import in.ponram.service.ProductManager;

@RestController
@RequestMapping("Products")
public class ProductController {

	@Autowired
	private ProductManager manager;

	/**
	 * Post method http://localhost:9090/Products
	 * 
	 * @param product
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Message> saveProduct(@RequestBody Product product) {

		boolean isSaved = manager.addStock(product);
		Message message = new Message();
		HttpStatus httpStatus;
		if (isSaved) {
			httpStatus = HttpStatus.OK;
		} else {
			message.setErrorMessage("Can't able to add");
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(message, httpStatus);
	}

	/**
	 * Get method http://localhost:9090/Products
	 * 
	 * @param product
	 * @return
	 */
	@GetMapping
	public List<Product> displayProducts() {
		return manager.getAllStock();
	}

	/**
	 * http://localhost:9090/Products/{id}/Remove
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}/Remove")
	public ResponseEntity<Message> removeProduct(@PathVariable("id") int id) {

		boolean isDeleted = manager.deleteProduct(id);
		Message message = new Message();
		HttpStatus httpStatus;
		if (isDeleted) {
			message.setInfoMessage("Removed successfuly");
			httpStatus = HttpStatus.OK;
		} else {
			message.setErrorMessage("Can't able to remove");
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(message, httpStatus);
	}
	
	/**
	 * http://localhost:9090/Products/Add?id=1&quantity=10
	 * 
	 * @param id
	 * @return
	 */
	@PatchMapping("Add")
	public ResponseEntity<Message> updateProductQuantity(@RequestParam("id") int id, @RequestParam("quantity") int quantity) {
		
		boolean isUpdated = manager.increaseQuantity(id, quantity); 
		Message message = new Message();
		HttpStatus httpStatus;
		if(isUpdated) {
			message.setInfoMessage("Stock added successfuly");
			httpStatus = HttpStatus.OK;
		}
		else {
			message.setErrorMessage("Can't able to add stock");
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(message, httpStatus);
	}

}
