package springframework.com.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springframework.com.entity.Orders;
import springframework.com.entity.Product;
import springframework.com.service.OrdersService;
import springframework.com.service.ProductService;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    ProductService productService;

    @Autowired
    OrdersService ordersService;

    @RequestMapping(value = "/orderPlace",method = RequestMethod.GET)
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
    }
}
