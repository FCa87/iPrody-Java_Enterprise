package iPrody.controller;

import iPrody.DTO.OrderDTO;
import iPrody.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.closeTo;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    private final MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Autowired
    public OrderControllerTest(MockMvc mockMvc){
        this.mockMvc = mockMvc;
    }

    @Test
    public void testSearchByName() throws Exception{
        var orderDTO = new OrderDTO(1, "Perevod1", new BigDecimal("123.45"), "RUB");
        Mockito.when(orderService.getOrdersByName("Perevod1")).thenReturn(List.of(orderDTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/search")
                .param("name", "Perevod1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].ordername").value("Perevod1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].amount").value(closeTo(123.45, 0.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currency").value("RUB"));
    }

    @Test
    public void testSearchById() throws Exception{
        var orderDTO = new OrderDTO(2, "Perevod2", new BigDecimal("678.90"), "USD");
        Mockito.when(orderService.getOrderById(2L)).thenReturn(orderDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/search")
                .param("id", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("ordername").value("Perevod2"))
                .andExpect(MockMvcResultMatchers.jsonPath("amount").value(closeTo(678.90, 0.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("currency").value("USD"));
    }

    @Test
    public void testSearchAllOrders() throws Exception{
        var ordersDTO = List.of(
                new OrderDTO(1, "Perevod1", new BigDecimal("566.52"), "RUB"),
                new OrderDTO(2, "Perevod2", new BigDecimal("700"), "USD"),
                new OrderDTO(3, "Perevod3", new BigDecimal("200"), "EUR")
        );
        Mockito.when(orderService.getAllOrders()).thenReturn(ordersDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(ordersDTO)));
    }
}
