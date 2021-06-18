package in.ponram.dao;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.ponram.model.Product;

@Repository
public class ProductRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * This method is used to add the product in the Database
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public boolean save(Product product) {

		String sql = "INSERT INTO stock(brand_name,product_name,product_category,arrival_date,initial_quantity,available_quantity,rate ) values ( ?,?,?,?,?,?,? )";
		Object[] params = { product.getBrandName(), product.getProductName(), product.getProductCategory(),
				Date.valueOf(product.getArrivalDate()), product.getAvailableQuantity(), product.getAvailableQuantity(),
				product.getRate() };
		int isSaved = jdbcTemplate.update(sql, params);
		return isSaved == 1 ? true : false;

	}
}
