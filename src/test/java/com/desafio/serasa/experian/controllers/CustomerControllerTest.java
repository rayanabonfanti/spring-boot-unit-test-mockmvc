package com.desafio.serasa.experian.controllers;

import com.desafio.serasa.experian.domain.dtos.CustomerResponseDTO;
import com.desafio.serasa.experian.domain.entity.Customer;
import com.desafio.serasa.experian.exceptions.CustomException;
import com.desafio.serasa.experian.interfaces.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.profiles.active=test"
})
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CustomerService customerService;

    @Test
    void shouldReturnBadRequestForSaveNameNull() throws Exception {
        CustomerResponseDTO returnCustomerResponseDTO = CustomerResponseDTO.builder()
                .age(25)
                .phone("123456789")
                .build();

        mockMvc.perform(post("/customer/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(returnCustomerResponseDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"errorCode\":400,\"message\":\"name is requied.\"}"));
    }

    @Test
    void shouldReturnOkForSave() throws Exception {
        CustomerResponseDTO returnCustomerResponseDTO = CustomerResponseDTO.builder()
                .name("John Doe")
                .age(25)
                .phone("123456789")
                .build();

        Mockito.when(customerService.save(Mockito.any(Customer.class))).thenReturn(returnCustomerResponseDTO);

        mockMvc.perform(post("/customer/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(returnCustomerResponseDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnInternalServerErrorForSave() throws Exception {
        CustomerResponseDTO returnCustomerResponseDTO = CustomerResponseDTO.builder()
                .name("John Doe")
                .age(25)
                .phone("123456789")
                .build();

        Mockito.when(customerService.save(Mockito.any(Customer.class)))
                .thenThrow(new RuntimeException("Internal Error."));

        mockMvc.perform(post("/customer/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(returnCustomerResponseDTO)))
                .andExpect(status().isInternalServerError());
    }


    @Test
    void shouldReturnOKForHealthCheck() throws Exception {
        mockMvc.perform(get("/customer/healthcheck"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

    @Test
    void shouldReturnNotFoundForInvalidURL() throws Exception {
        mockMvc.perform(get("/customer/invalidurl"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnOkForDelete() throws Exception {
        Mockito.when(customerService.delete(Mockito.anyLong())).thenReturn("Customer deleted.");

        mockMvc.perform(delete("/customer/delete")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Customer deleted."));
    }

    @Test
    void shouldReturnNotFoundForDeleteNotFound() throws Exception {
        Mockito.when(customerService.delete(Mockito.anyLong())).thenThrow(new CustomException(404, "Customer not found."));

        mockMvc.perform(delete("/customer/delete")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{\"errorCode\":404,\"message\":\"Customer not found.\"}"));
    }

    @Test
    void shouldReturnInternalServerErrorForDeleteError() throws Exception {
        Mockito.when(customerService.delete(Mockito.anyLong())).thenThrow(new RuntimeException("Internal error."));

        mockMvc.perform(delete("/customer/delete")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnInternalServerErrorForUpdateError() throws Exception {
        Mockito.when(customerService.update(Mockito.anyLong(), any(Customer.class))).thenThrow(new RuntimeException("Internal error."));

        mockMvc.perform(patch("/customer/update")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnOkForGetName() throws Exception {
        CustomerResponseDTO returnCustomerResponseDTO = CustomerResponseDTO.builder()
                .name("John Doe")
                .age(25)
                .phone("123456789")
                .build();

        Mockito.when(customerService.getName(Mockito.anyString())).thenReturn(returnCustomerResponseDTO);

        mockMvc.perform(get("/customer/getName")
                        .param("name", "John Doe")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(returnCustomerResponseDTO)));
    }

    @Test
    void shouldReturnNotFoundForGetNameNotFound() throws Exception {
        Mockito.when(customerService.getName(Mockito.anyString())).thenThrow(new CustomException(404, "Customer not found."));

        mockMvc.perform(get("/customer/getName")
                        .param("name", "John Doe")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{\"errorCode\":404,\"message\":\"Customer not found.\"}"));
    }

    @Test
    void shouldReturnInternalServerErrorForGetNameError() throws Exception {
        Mockito.when(customerService.getName(Mockito.anyString())).thenThrow(new RuntimeException("Internal error."));

        mockMvc.perform(get("/customer/getName")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnOkForUpdate() throws Exception {
        CustomerResponseDTO updatedCustomer = CustomerResponseDTO.builder()
                .name("John Doe")
                .age(25)
                .phone("123456789")
                .build();

        Mockito.when(customerService.update(Mockito.anyLong(), any(Customer.class))).thenReturn(updatedCustomer);

        Customer requestDto = Customer.builder()
                .name("John Doe")
                .age(25)
                .phone("123456789")
                .build();

        mockMvc.perform(patch("/customer/update")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"John Doe\"}"));
    }

    @Test
    void shouldReturnNotFoundForUpdateNotFound() throws Exception {
        Mockito.when(customerService.update(Mockito.anyLong(), any(Customer.class)))
                .thenThrow(new CustomException(404, "Customer not found."));

        Customer requestDto = Customer.builder()
                .id(1L)
                .name("John Doe")
                .age(25)
                .phone("123456789")
                .build();

        mockMvc.perform(patch("/customer/update")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"errorCode\":404,\"message\":\"Customer not found.\"}"));
    }

}
