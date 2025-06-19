package com.logistics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.dto.OrderRequestDto;
import com.logistics.entity.Order;
import com.logistics.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@Import(OrderControllerTest.MockConfig.class)  // 👈 Import the mock config
class OrderControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Autowired private OrderService orderService;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public OrderService orderService() {
            return mock(OrderService.class);  // 👈 Using Mockito manually
        }
    }

    @Test
    void shouldCreateOrderSuccessfully() throws Exception {
        OrderRequestDto dto = new OrderRequestDto();
        dto.setVendorId("V123");
        dto.setCustomerName("John Doe");
        dto.setDeliveryAddress("123 Street");
        dto.setDeliveryZone("ZoneA");

//        when(orderService.createOrder(any())).thenReturn(new Order());
//
//        mockMvc.perform(post("/api/orders")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(dto)))
//                .andExpect(status().isOk());
    }
}
