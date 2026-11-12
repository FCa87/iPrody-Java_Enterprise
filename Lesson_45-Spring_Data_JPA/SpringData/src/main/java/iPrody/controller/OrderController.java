package iPrody.controller;

import iPrody.DTO.OrderDTO;
import iPrody.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/search")
    public List<OrderDTO> searchOrders(@RequestParam String name){
        return orderService.getOrdersByName(name);
    }

    @GetMapping("/searchall")
    public List<OrderDTO> searchAllOrders(){
        return orderService.getAllOrders();
    }
}
