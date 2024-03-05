package com.desafio.serasa.experian.controllers;

import com.desafio.serasa.experian.domain.dtos.CustomerResponseDTO;
import com.desafio.serasa.experian.domain.entity.Customer;
import com.desafio.serasa.experian.exceptions.CustomException;
import com.desafio.serasa.experian.interfaces.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("customer")
@Tag(name = "Customers", description = "API to manipulate data from customers in the system.")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/healthcheck")
    public ResponseEntity<String> healthcheck() {
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/save")
    @Operation(summary = "Saves customer data.",
            description = "Endpoint responsible for saving customer data.")
    @ApiResponse(responseCode = "200", description = "Success.")
    @ApiResponse(responseCode = "400", description = "Mandatory parameter(s) were entered incorrectly.")
    @ApiResponse(responseCode = "404", description = "Customer not found.")
    @ApiResponse(responseCode = "405", description = "Poorly formatted request.")
    @ApiResponse(responseCode = "500", description = "Internal Error.")
    @ApiResponse(responseCode = "503", description = "Unavailable service.")
    public ResponseEntity<CustomerResponseDTO> save(@Valid @RequestBody Customer data, BindingResult bindingResult) throws CustomException {
        return ResponseEntity.status(201).body(customerService.save(data));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete customer data.",
            description = "Endpoint responsible for deleting customer data.")
    @ApiResponse(responseCode = "200", description = "Success.")
    @ApiResponse(responseCode = "400", description = "Mandatory parameter(s) were entered incorrectly.")
    @ApiResponse(responseCode = "404", description = "Customer not found.")
    @ApiResponse(responseCode = "405", description = "Poorly formatted request.")
    @ApiResponse(responseCode = "500", description = "Internal Error.")
    @ApiResponse(responseCode = "503", description = "Unavailable service.")
    public ResponseEntity<String> delete(@RequestParam Long id) throws CustomException {
        return ResponseEntity.ok().body(customerService.delete(id));
    }

    @PatchMapping("/update")
    @Operation(summary = "Update customer data.",
            description = "Endpoint responsible for updating customer data.")
    @ApiResponse(responseCode = "200", description = "Success.")
    @ApiResponse(responseCode = "400", description = "Mandatory parameter(s) were entered incorrectly.")
    @ApiResponse(responseCode = "404", description = "Customer not found.")
    @ApiResponse(responseCode = "405", description = "Poorly formatted request.")
    @ApiResponse(responseCode = "500", description = "Internal Error.")
    @ApiResponse(responseCode = "503", description = "Unavailable service.")
    public ResponseEntity<CustomerResponseDTO> update(@RequestParam Long id, @Valid @RequestBody Customer data) throws CustomException {
        return ResponseEntity.ok().body(customerService.update(id, data));
    }

    @GetMapping("/getName")
    @Operation(summary = "Find a customers score information.",
            description = "Endpoint responsible for finding a customers name.")
    @ApiResponse(responseCode = "200", description = "Success.")
    @ApiResponse(responseCode = "400", description = "Mandatory parameter(s) were entered incorrectly.")
    @ApiResponse(responseCode = "404", description = "Customer not found.")
    @ApiResponse(responseCode = "405", description = "Poorly formatted request.")
    @ApiResponse(responseCode = "500", description = "Internal Error.")
    @ApiResponse(responseCode = "503", description = "Unavailable service.")
    public ResponseEntity<CustomerResponseDTO> getName(@RequestParam String name) throws CustomException {
        return ResponseEntity.ok().body(customerService.getName(name));
    }

}
