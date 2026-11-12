package iPrody.service;

import iPrody.DTO.OrderDTO;
import iPrody.model.Order;
import iPrody.repo.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.*;

public class OrderServiceTest {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public OrderServiceTest() {
        this.orderRepository = Mockito.mock(OrderRepository.class);
        this.orderService = new OrderService(orderRepository);
    }

    @Test
    public void testGetAllOrders(){
        var ordersDTO = List.of(
                new OrderDTO(1, "Perevod1", new BigDecimal("566.52"), "RUB"),
                new OrderDTO(2, "Perevod2", new BigDecimal("700"), "USD"),
                new OrderDTO(3, "Perevod3", new BigDecimal("200"), "EUR")
        );
        var orders = List.of(
                new Order(1, "Perevod1", new BigDecimal("566.52"), "RUB"),
                new Order(2, "Perevod2", new BigDecimal("700"), "USD"),
                new Order(3, "Perevod3", new BigDecimal("200"), "EUR")
        );
        Mockito.when(orderRepository.findAll()).thenReturn(orders);
        var result = orderService.getAllOrders();
        Assertions.assertIterableEquals(ordersDTO, result);
    }

    @Test
    public void testGetOrdersByName(){
        var ordersDTO = List.of(
                new OrderDTO(1, "Perevod1", new BigDecimal("566.52"), "RUB"),
                new OrderDTO(2, "Perevod1", new BigDecimal("700"), "USD")
        );
        var orders = List.of(
                new Order(1, "Perevod1", new BigDecimal("566.52"), "RUB"),
                new Order(2, "Perevod1", new BigDecimal("700"), "USD")
        );
        Mockito.when(orderRepository.findByOrdername("Perevod1")).thenReturn(orders);
        var result = orderService.getOrdersByName("Perevod1");
        Assertions.assertIterableEquals(ordersDTO, result);
    }

    @Test
    public void testGetOrderById(){
        var orderDTO = new OrderDTO(1, "Perevod1", new BigDecimal("566.52"), "RUB");
        var order = new Order(1, "Perevod1", new BigDecimal("566.52"), "RUB");
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        var result = orderService.getOrderById(1L);
        Assertions.assertEquals(orderDTO, result);
    }

    @Test
    public void testGetOrderByIdForEmpty(){
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.empty());
        var result = orderService.getOrderById(1L);
        Assertions.assertNull(result);
    }
}
