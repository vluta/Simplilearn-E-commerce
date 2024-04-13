package springframework.com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jakarta.servlet.http.HttpServletRequest;
import springframework.com.entity.Orders;
import springframework.com.entity.Product;
import springframework.com.service.OrdersService;
import springframework.com.service.ProductService;
@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	OrdersService ordersService;
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String open(Model model, Product product) {
	
	String name1="Store Product";
	String name2="Update Product";
	
	List<Product> listOfProduct = productService.findAllProducts();
	List<Object[]> orderdetails = productService.orderDetails();
	
	model.addAttribute("products", listOfProduct);
	model.addAttribute("buttonValue1", name1);
	model.addAttribute("buttonValue2", name2);
	model.addAttribute("product", product);
	model.addAttribute("orderdetails", orderdetails);
	
	System.out.println(listOfProduct);
	return "index";
	}
	
	@RequestMapping(value = "/addProduct",method = RequestMethod.POST)
	public String addProductDetails(Model model, Product product,HttpServletRequest req) {
		String b1 = req.getParameter("b1");
		String b2 = req.getParameter("b2");
		String result="";
		//String name="";
		String name1 = "Store Product";
		String name2 = "Update Product";

		if(b1 != null && b1.equals("Store Product")) {
			result = productService.storeProduct(product);
		} else if (b1 == null & b2.equals("Update Product")) {
			result = productService.updateProduct(product);
		}

		product.setPid(0);
		product.setPname("");
		product.setPrice(0);
		model.addAttribute("product", product);
		List<Product> listOfProduct = productService.findAllProducts();
		List<Object[]> orderdetails = productService.orderDetails();
		model.addAttribute("orderdetails", orderdetails);
		model.addAttribute("products", listOfProduct);
		model.addAttribute("msg", result);
		model.addAttribute("buttonValue1", name1);
		model.addAttribute("buttonValue2", name2);

		return "index";
	}
	
	@RequestMapping(value = "/deleteProduct",method = RequestMethod.GET)
	public String deleteProductById(Model model, Product product,HttpServletRequest req) {
		int pid = Integer.parseInt(req.getParameter("pid"));
		System.out.println("pid is "+pid);
		String name = "Store Product";
		String result = productService.deleteProduct(pid);
		List<Product> listOfProduct = productService.findAllProducts();
		model.addAttribute("products", listOfProduct);
		model.addAttribute("product", product);
		model.addAttribute("msg", result);
		model.addAttribute("buttonValue", name);
		List<Object[]> orderdetails = productService.orderDetails();
		model.addAttribute("orderdetails", orderdetails);
	return "index";
	}

	@RequestMapping(value = "/updateProduct",method = RequestMethod.GET)
	public String searchProductById(Model model, Product product, HttpServletRequest req) {
		String b2 = req.getParameter("b2");
		String result="";
		String name1 = "Store Product";
		String name2 = "Update Product";

		if(b2.equals("Update Product")) {
			result = productService.storeProduct(product);
		}

		product.setPid(0);
		product.setPname("");
		product.setPrice(0);
		model.addAttribute("product", product);
		List<Product> listOfProduct = productService.findAllProducts();
		List<Object[]> orderdetails = productService.orderDetails();
		model.addAttribute("orderdetails", orderdetails);
		model.addAttribute("products", listOfProduct);
		model.addAttribute("msg", result);
		model.addAttribute("buttonValue1", name1);
		model.addAttribute("buttonValue2", name2);
		return "index";
	}
	
	
	/*@RequestMapping(value = "/orderPlace",method = RequestMethod.GET)
	public String placeOrder(Model model, HttpServletRequest req, Orders order, Product product) {
		int pid = Integer.parseInt(req.getParameter("pid"));
		order.setPid(pid);
		String name="Store Product"; 
		String result = ordersService.placeOrder(order);
		List<Product> listOfProduct = productService.findAllProducts();
		model.addAttribute("products", listOfProduct);
		model.addAttribute("product", product);
		model.addAttribute("msg", result);
		model.addAttribute("buttonValue", name);
		//model.addAttribute("msg", result);
		List<Object[]> orderdetails = productService.orderDetails();
		model.addAttribute("orderdetails", orderdetails);
	return "index";
	}*/
}
