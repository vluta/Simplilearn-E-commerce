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

}
