package in.ponram.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.ponram.dto.ProductDTO;
import in.ponram.model.Order;
import in.ponram.model.OrderItem;
import in.ponram.model.Product;

@Repository
public class ReportRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Get all the products from the stock
	 * 
	 * @return List of product's
	 */
	public List<ProductDTO> findAll() {

		String sql = "SELECT product_id,brand_name,product_name,product_category,arrival_date,initial_quantity,available_quantity,rate,active  FROM stock ORDER BY product_id ASC";

		List<ProductDTO> reportList = null;

		reportList = jdbcTemplate.query(sql, (rs, rowNum) -> {
			ProductDTO report = new ProductDTO();
			Product list = new Product();
			list.setProductId(rs.getInt("product_id"));
			list.setBrandName(rs.getString("brand_name"));
			list.setProductName(rs.getString("product_name"));
			list.setProductCategory(rs.getString("product_category"));
			list.setArrivalDate((rs.getDate("arrival_date")).toLocalDate());
			list.setAvailableQuantity(rs.getInt("available_quantity"));
			list.setRate(rs.getInt("rate"));
			report.setProduct(list);
			report.setInitialQuantity(rs.getInt("initial_quantity"));
			report.setStatus(rs.getBoolean("active"));
			return report;
		});

		return reportList;
	}

	/**
	 * Get the sales report details using product id
	 * 
	 * @param id
	 * @return
	 */
	public List<Order> findByProductId(int id) {

		List<Order> salesReportList = null;
		String sql = "select o.bill_id as billId,o.user_name as username,s.brand_name as brandName,s.product_name as productName,s.rate as rate,it.product_quantity as quantity,it.total_amount as totalAmount,o.purchase_date as purchaseDate from orders o, order_item it, stock s where o.bill_id = it.bill_id and it.product_id = s.product_id and it.product_id = ?";

		salesReportList = jdbcTemplate.query(sql, (rs, rowNum) -> {
			Order order = new Order();
			OrderItem orderItem = new OrderItem();
			order.setBillNumber(rs.getInt("billId"));
			order.setUsername(rs.getString("username"));
			orderItem.setBrandName(rs.getString("brandName"));
			orderItem.setProductName(rs.getString("productName"));
			orderItem.setRate(rs.getInt("rate"));
			orderItem.setQuantity(rs.getInt("quantity"));
			orderItem.setTotalAmount(rs.getInt("totalAmount"));
			order.setPurchaseDate((rs.getDate("purchaseDate")).toLocalDate());
			order.setOrderDetail(orderItem);
			return order;
		}, id);
		return salesReportList;
	}
}
