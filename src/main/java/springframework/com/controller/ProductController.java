package springframework.com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jakarta.servlet.http.HttpServletRequest;
import springframework.com.entity.Login;
import springframework.com.entity.Product;
import springframework.com.service.LoginService;
import springframework.com.service.OrdersService;
import springframework.com.service.ProductService;
@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	OrdersService ordersService;

	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/admin",method = RequestMethod.GET)
	public String open(Model model, Product product, Login login, HttpServletRequest req) {
	
	String name1="Store Product";
	String name2="Update Product";
	String name3="Change Password";
	String result="";
	
	List<Product> listOfProduct = productService.findAllProducts();
	List<Object[]> orderdetails = productService.orderDetails();
	List<Login> userdetails = loginService.userDetails();
	System.out.println("Users" + userdetails.toString());
	
	model.addAttribute("products", listOfProduct);
	model.addAttribute("buttonValue1", name1);
	model.addAttribute("buttonValue2", name2);
	model.addAttribute("buttonValue3", name3);
	model.addAttribute("product", product);
	model.addAttribute("login", login);
	model.addAttribute("orderdetails", orderdetails);
	model.addAttribute("userdetails", userdetails);

	return "admin";
	}

	@RequestMapping(value = "/admin",method = RequestMethod.POST)
	public String updateAdmin(Model model, Product product, Login login, HttpServletRequest req) {

		String name1="Store Product";
		String name2="Update Product";
		String name3="Change Password";
		String result="";

		List<Product> listOfProduct = productService.findAllProducts();
		List<Object[]> orderdetails = productService.orderDetails();
		List<Login> userdetails = loginService.userDetails();
		System.out.println("Users" + userdetails.toString());

		model.addAttribute("products", listOfProduct);
		model.addAttribute("buttonValue1", name1);
		model.addAttribute("buttonValue2", name2);
		model.addAttribute("buttonValue3", name3);
		model.addAttribute("product", product);
		model.addAttribute("login", login);
		model.addAttribute("orderdetails", orderdetails);
		model.addAttribute("userdetails", userdetails);

		String b3 = req.getParameter("b3");

		if (b3 != null && b3.equals("Change Password")) {
			result = loginService.changePassword(login);
		}

		return "admin";
	}
	
	@RequestMapping(value = "/addProduct",method = RequestMethod.POST)
	public String addProductDetails(Model model, Product product, Login login, HttpServletRequest req) {
		String b1 = req.getParameter("b1");
		String b2 = req.getParameter("b2");
		String b3 = req.getParameter("b3");
		String result="";
		String name1 = "Store Product";
		String name2 = "Update Product";
		String name3="Change Password";

		if(b1 != null && b1.equals("Store Product")) {
			result = productService.storeProduct(product);
		} else if (b1 == null & b2.equals("Update Product")) {
			result = productService.updateProduct(product);
		}

		if (b3 != null && b3.equals("Change Password")) {
			result = loginService.changePassword(login);
		}

		product.setPid(0);
		product.setPname("");
		product.setPrice(0);
		model.addAttribute("product", product);
		List<Product> listOfProduct = productService.findAllProducts();
		List<Object[]> orderdetails = productService.orderDetails();
		List<Login> userdetails = loginService.userDetails();

		model.addAttribute("products", listOfProduct);
		model.addAttribute("msg", result);
		model.addAttribute("buttonValue1", name1);
		model.addAttribute("buttonValue2", name2);
		model.addAttribute("buttonValue3", name3);
		model.addAttribute("login", login);
		model.addAttribute("orderdetails", orderdetails);
		model.addAttribute("userdetails", userdetails);
		return "admin";
	}
	
	@RequestMapping(value = "/deleteProduct",method = RequestMethod.GET)
	public String deleteProductById(Model model, Product product, Login login, HttpServletRequest req) {
		int pid = Integer.parseInt(req.getParameter("pid"));

		String name1 = "Store Product";
		String name2 = "Update Product";
		String name3="Change Password";

		String result = productService.deleteProduct(pid);
		List<Product> listOfProduct = productService.findAllProducts();
		List<Object[]> orderdetails = productService.orderDetails();
		List<Login> userdetails = loginService.userDetails();
		model.addAttribute("products", listOfProduct);
		model.addAttribute("product", product);
		model.addAttribute("msg", result);
		model.addAttribute("buttonValue1", name1);
		model.addAttribute("buttonValue2", name2);
		model.addAttribute("buttonValue3", name3);
		model.addAttribute("login", login);
		model.addAttribute("orderdetails", orderdetails);
		model.addAttribute("userdetails", userdetails);

		return "admin";
	}
}
