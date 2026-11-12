package iPrody.repo;

import iPrody.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    private final OrderRepository orderRepository;

    private final List<Order> orders = new LinkedList<>();
    private final Order emptyOrder = new Order(0, "", new BigDecimal("0.0"), "");

    @Autowired
    public OrderRepositoryTest(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @BeforeEach
    public void prepareBase(){
        orders.add(orderRepository.save(new Order("Perevod1", new BigDecimal("566.52"), "RUB")));
        orders.add(orderRepository.save(new Order("Perevod2", new BigDecimal("700"), "USD")));
        orders.add(orderRepository.save(new Order("Perevod3", new BigDecimal("200"), "EUR")));
    }

    @AfterEach
    public void clearBase(){
        /*orderRepository.deleteAll();
        orderRepository.flush();*/
        orders.clear();
    }

    @Test
    public void testFindAll(){
        var result = orderRepository.findAll().stream().sorted(Comparator.comparingLong(Order::getId)).toList();
        Assertions.assertIterableEquals(orders, result);
    }

    @Test
    public void testGetById(){
        var result = orderRepository.findById(orders.get(0).getId()).orElse(emptyOrder);
        Assertions.assertEquals(orders.get(0), result);
        Assertions.assertTrue(orderRepository.findById(0L).isEmpty());
    }

    @Test
    public void testGetByName(){
        var result = orderRepository.findByOrdername("Perevod2");
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        var expected = orders.stream()
                .filter( order -> order.getOrdername().compareTo("Perevod2") == 0)
                .sorted(Comparator.comparingLong(Order::getId))
                .toList();
        result = result.stream().sorted(Comparator.comparingLong(Order::getId)).toList();
        Assertions.assertIterableEquals(expected, result);
        Assertions.assertTrue(orderRepository.findById(0L).isEmpty());
    }

}
