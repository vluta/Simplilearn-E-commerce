package springframework.com.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springframework.com.entity.Orders;
import springframework.com.entity.Product;
import springframework.com.repository.OrdersRepository;

@Service
public class OrdersService {

	@Autowired
	OrdersRepository ordersRepository;
	
	public String placeOrder(Orders orders) {
			// oid auto increment we need only pid as fk

			orders.setLdt(LocalDateTime.now());
		
		ordersRepository.save(orders);

		return "Order placed successfully";
	}

	@Transactional
	public String deleteOrder(int oid) {

		Optional<Orders> result = ordersRepository.findById(oid);
		System.out.println("Order to delete: " + result.get().toString());
		if (result.isPresent()) {
			Orders o = result.get();
			ordersRepository.deleteByOid(oid);
			//ordersRepository.deleteById(oid);
			//ordersRepository.delete(o);
			return "Order deleted successfully";
		} else {
			return "Order not present";
		}
	}
}
