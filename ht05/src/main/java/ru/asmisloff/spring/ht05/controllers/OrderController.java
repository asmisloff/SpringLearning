package ru.asmisloff.spring.ht05.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.asmisloff.spring.ht05.entities.Order;
import ru.asmisloff.spring.ht05.services.OrderService;
import ru.asmisloff.spring.ht05.utils.OrderFilter;

import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String showAll(Model model,
                          @RequestParam(defaultValue = "1", name = "p") int page,
                          @RequestParam Map<String, String> params
    ) {
        if (page < 1) {
            page = 1;
        }

        OrderFilter orderFilter = new OrderFilter(params);
        Page<Order> orders = orderService.findAll(orderFilter.getSpec(), page - 1, 5);

        model.addAttribute("orders", orders);
        model.addAttribute("filterDefinition", orderFilter.getFilterDefinition());

        return "orders";
    }

}
