package in.ponram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ponram.dto.Message;
import in.ponram.model.Product;
import in.ponram.service.ProductManager;

@RestController
@RequestMapping("Products")
public class ProductController {

	@Autowired
	private ProductManager manager;

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
}
