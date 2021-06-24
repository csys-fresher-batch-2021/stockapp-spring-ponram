package in.ponram.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.ponram.model.OrderItem;

@Repository
public class OrderItemRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean save(OrderItem orderItem) {
		
		String sql = "INSERT INTO order_item (bill_id,product_id,product_quantity,total_amount) values(?,?,?,?)";
		Object[] params = { orderItem.getBillNumber(), orderItem.getProductId(), orderItem.getQuantity(),
				orderItem.getTotalAmount() };
		int isSaved = jdbcTemplate.update(sql, params);
		return isSaved == 1 ? true : false;
		
	}
}
