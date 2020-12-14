package ru.asmisloff.spring.ht08.controllers.rest;

import com.fasterxml.jackson.annotation.JsonView;
import ru.asmisloff.spring.ht08.entities.Order;
import ru.asmisloff.spring.ht08.entities.views.OrderView;
import ru.asmisloff.spring.ht08.services.OrderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/api/v1")
public class OrderRestController {

    OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/xml",produces= MediaType.APPLICATION_XML_VALUE)
    public List<Order> ordersToXml(){
        return orderService.findAll();
    }


    @GetMapping(value = "/id", produces= MediaType.APPLICATION_JSON_VALUE)
    @JsonView(OrderView.Id.class)
    public List<Order> ordersIdToJson(){
        return orderService.findAll();
    }

    @GetMapping(value = "/idCode")
    @JsonView(OrderView.IdCode.class)
    public List<Order> ordersIdCodeToJson(){
        return orderService.findAll();
    }

    @GetMapping(value = "/idCodeUser")
    @JsonView(OrderView.IdCodePriceUser.class)
    public List<Order> ordersIdCodeUserToJson(){
        return orderService.findAll();
    }

    @GetMapping(value = "/idCodeUserOrderEntry")
    @JsonView(OrderView.IdCodeUserOrderEntry.class)
    public List<Order> ordersIdCodeUserOrderEntryToJson(){
        return orderService.findAll();
    }

}
