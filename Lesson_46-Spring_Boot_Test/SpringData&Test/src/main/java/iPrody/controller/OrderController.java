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
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDTO> searchAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(path = "/search", params = "name")
    public List<OrderDTO> searchByName(@RequestParam String name){
        return orderService.getOrdersByName(name);
    }

    @GetMapping(path = "/search", params = "id")
    public OrderDTO searchById(@RequestParam Long id){
        return orderService.getOrderById(id);
    }
}
