package in.ponram.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
	 */
	public boolean save(Product product) {

		String sql = "INSERT INTO stock(brand_name,product_name,product_category,arrival_date,initial_quantity,available_quantity,rate ) VALUES ( ?,?,?,?,?,?,? )";
		Object[] params = { product.getBrandName(), product.getProductName(), product.getProductCategory(),
				Date.valueOf(product.getArrivalDate()), product.getAvailableQuantity(), product.getAvailableQuantity(),
				product.getRate() };
		int isSaved = jdbcTemplate.update(sql, params);
		return isSaved == 1 ? true : false;

	}

	/**
	 * This method is used to get all the product from database
	 * 
	 * @return product ArrayList
	 */
	public List<Product> findAll() {

		String sql = "SELECT product_id,brand_name,product_name,product_category,arrival_date,available_quantity,rate  FROM stock WHERE active = true ORDER BY product_id ASC";

		List<Product> productList = null;
		productList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));

		return productList;
	}

	/**
	 * This method is used to remove the product from the database
	 * 
	 * @return
	 */
	public boolean remove(Integer id) {

		String sql = "UPDATE stock SET active = false WHERE product_id = ?;";

		int isUpdated = jdbcTemplate.update(sql, id);
		return isUpdated == 1 ? true : false;
	}
}
