package in.ponram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ponram.dto.ProductDTO;
import in.ponram.model.Order;
import in.ponram.service.ReportManager;

@RestController
@RequestMapping("api/v1/report")
public class ReportController {

	@Autowired
	ReportManager manager;

	@GetMapping("stock")
	public List<ProductDTO> stockReport() {

		return manager.getFullStockReport();
	}

	@GetMapping("{id}/sales")
	public List<Order> stockReport(@PathVariable("id") int id) {

		return manager.getProductSalesReport(id);
	}
}
