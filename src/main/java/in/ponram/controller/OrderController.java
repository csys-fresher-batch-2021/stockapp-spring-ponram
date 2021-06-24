package in.ponram.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ponram.model.Order;
import in.ponram.model.OrderItem;
import in.ponram.service.OrderManager;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

	@Autowired
	private OrderManager orderManager;

	@PostMapping("{username}")
	public void purchaseProduct(@RequestBody List<OrderItem> orderItems, @PathVariable("username") String username) {
//	public void purchaseProduct(@RequestBody List<OrderItem> orderItems, HttpServletRequest request) {
		/*
		 * // TODO get username from session HttpSession session = request.getSession();
		 * String username = (String) session.getAttribute(null);
		 */
		Order order = new Order();
		order.setOrderDetails(orderItems);
		order.setUsername(username);
		order.setPurchaseDate(LocalDate.now());
		orderManager.orderProducts(order);
	}
}
