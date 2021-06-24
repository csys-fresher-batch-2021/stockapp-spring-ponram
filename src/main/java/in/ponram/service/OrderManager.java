package in.ponram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ponram.dao.OrderItemRepository;
import in.ponram.dao.OrderRepository;
import in.ponram.dao.ProductRepository;
import in.ponram.model.Order;
import in.ponram.model.OrderItem;
import in.ponram.validator.OrderValidation;

@Service
public class OrderManager {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderItemRepository itemRepository;
	@Autowired
	private OrderValidation orderValidation;

	public void orderProducts(Order order) {

		int total = 0;

		if (orderValidation.isValidIdAndQuantity(order)) {

			Integer orderId = orderRepository.save(order);
			for (OrderItem details : order.getOrderDetails()) {

				int rate = orderValidation.getRate(details.getProductId());
				details.setBillNumber(orderId);
				details.setRate(rate);
				details.setTotalAmount(rate * details.getQuantity());
				total = total + details.getTotalAmount();
				itemRepository.save(details);
				productRepository.updateReduceQuantity(details.getProductId(), details.getQuantity());
			}

			orderRepository.updateBillAmount(orderId, total);
		}

	}

}
