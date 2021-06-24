package in.ponram.dao;

import java.sql.Date;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import in.ponram.model.Order;

@Repository
public class OrderRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int save(Order order) {

		String sql = "INSERT INTO orders (user_name,purchase_date) values(?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement pst = connection.prepareStatement(sql, new String[] { "bill_id" });
			pst.setString(1, order.getUsername());
			pst.setDate(2, Date.valueOf(order.getPurchaseDate()));
			return pst;
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	/**
	 * This method is used to update the total bill amount in the database
	 */
	public boolean updateBillAmount(int billId, int total) {

		String sql = "UPDATE orders SET total_bill_amount = ? WHERE bill_id = ?;";
		int isUpdated = jdbcTemplate.update(sql, total, billId);
		return isUpdated == 1 ? true : false;
	}
}
