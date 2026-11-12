package iPrody.service;

import iPrody.DTO.OrderDTO;
import iPrody.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> getOrdersByName(String name) {
        var orders = orderRepository.findByOrdername(name);
        return orders.stream()
                .map(order -> new OrderDTO(order.getId(), order.getOrdername(), order.getAmount(), order.getCurrency()))
                .toList();

    }

    public List<OrderDTO> getAllOrders(){
        var orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> new OrderDTO(order.getId(), order.getOrdername(), order.getAmount(), order.getCurrency()))
                .toList();
    }
}
