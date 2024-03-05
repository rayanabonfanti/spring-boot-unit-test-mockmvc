package com.desafio.serasa.experian.utils;

import com.desafio.serasa.experian.domain.dtos.CustomerResponseDTO;
import com.desafio.serasa.experian.domain.entity.Customer;

public class CustomerUtils {

    private CustomerUtils() {
    }

    public static void updateCustomerWithDates(Customer customer, Customer update) {
        customer.setName(update.getName());
        customer.setAge(update.getAge());
        customer.setPhone(update.getPhone());
    }

    public static CustomerResponseDTO createCustomerResponseDTO(Customer customer) {
        return new CustomerResponseDTO(
                customer.getName(),
                customer.getAge(),
                customer.getPhone()
        );
    }

}
