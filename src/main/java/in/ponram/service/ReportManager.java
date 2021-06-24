package in.ponram.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ponram.dao.ReportRepository;
import in.ponram.dto.ProductDTO;
import in.ponram.model.Order;

@Service
public class ReportManager {

	@Autowired
	private ReportRepository reportRepository;

	public List<ProductDTO> getFullStockReport() {

		return reportRepository.findAll();
	}

	public List<Order> getProductSalesReport(int id) {

		return reportRepository.findByProductId(id);
	}
}
