package springframework.com.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springframework.com.entity.Login;
import springframework.com.entity.Orders;
import springframework.com.entity.Product;
import springframework.com.repository.LoginRepository;
import springframework.com.service.OrdersService;
import springframework.com.service.ProductService;
import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("user")
public class UserController {

	@Autowired
	ProductService productService;

	@Autowired
	OrdersService ordersService;

	@Autowired
	LoginRepository loginRepository;

	//@RequestMapping(value = "/deleteProductFromOrder",method = RequestMethod.GET)
	//public String deleteProductById(Model model, Product product,HttpServletRequest req) {
	//}

	@RequestMapping(value = "/user",method = RequestMethod.GET)
	public String open(Model model, Orders order) {

		String name1="Place Order";
		String name2="Delete Order";

		List<Product> listOfProduct = productService.findAllProducts();
		List<Object[]> orderdetails = productService.orderDetails();

		model.addAttribute("products", listOfProduct);
		model.addAttribute("buttonValue1", name1);
		model.addAttribute("buttonValue2", name2);
		model.addAttribute("order", order);
		model.addAttribute("orderdetails", orderdetails);

		return "user";
	}

	/*@RequestMapping(value = "/addToOrder",method = RequestMethod.POST)
	public String addProductDetails(Model model, Product product,HttpServletRequest req) {
		int pid = Integer.parseInt(req.getParameter("pid"));
		order.setPid(pid);
		String name="create Order";
		String result = ordersService.placeOrder(order);
		List<Product> listOfProduct = productService.findAllProducts();
		model.addAttribute("products", listOfProduct);
		model.addAttribute("product", product);
		model.addAttribute("msg", result);
		model.addAttribute("buttonValue", name);
		//model.addAttribute("msg", result);
		List<Object[]> orderdetails = productService.orderDetails();
		model.addAttribute("orderdetails", orderdetails);


		return "user";
	}*/

	@RequestMapping(value = "/orderPlace",method = RequestMethod.POST)
	public String placeOrder(Model model, HttpServletRequest req, Orders order, Product product) {
		String result="";
		String b1 = req.getParameter("b1");
		String b2 = req.getParameter("b2");
		int oid = Integer.parseInt(req.getParameter("oid"));
		System.out.println("Oid from request: " + oid);
		String username = req.getParameter("username");
		System.out.println("Username from request:" + username);
		order.setPid(null);

		Login login = loginRepository.findByUsername(username);
		System.out.println("User:" + login);
		int uid = loginRepository.findByUsername(username).getUid();
		order.setUid(uid);

		String name1="Place Order";
		String name2="Delete Order";

		if(b1 != null && b1.equals("Place Order")) {
			result = ordersService.placeOrder(order);
		} else if (b1 == null & b2.equals("Delete Order")) {
			ordersService.deleteOrderById(oid);
		}

		List<Product> listOfProduct = productService.findAllProducts();
		model.addAttribute("products", listOfProduct);
		//model.addAttribute("product", product);
		model.addAttribute("order", order);
		model.addAttribute("msg", result);
		model.addAttribute("buttonValue1", name1);
		model.addAttribute("buttonValue2", name2);
		//model.addAttribute("msg", result);
		List<Object[]> orderdetails = productService.orderDetailsByUsername(username);
		System.out.println("Your Orders details: " + orderdetails.toString());
		model.addAttribute("orderdetails", orderdetails);
		return "user";
	}

}
