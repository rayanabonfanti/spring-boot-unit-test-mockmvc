package com.desafio.serasa.experian.services;

import com.desafio.serasa.experian.domain.dtos.*;
import com.desafio.serasa.experian.domain.entity.Customer;
import com.desafio.serasa.experian.exceptions.CustomException;
import com.desafio.serasa.experian.interfaces.CustomerService;
import com.desafio.serasa.experian.repositories.CustomerRepository;
import com.desafio.serasa.experian.utils.CustomerUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String OBJECT_NOT_FOUND = "Object not found.";
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponseDTO save(@Valid Customer save) throws CustomException {
        if (Optional.ofNullable(this.customerRepository.findByName(save.getName())).isPresent())
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Object exist.");

        this.customerRepository.save(save);
        return CustomerUtils.createCustomerResponseDTO(save);
    }

    @Override
    public String delete(Long id) throws CustomException {
        Optional<Customer> findCustomer = customerRepository.findById(id);

        if (findCustomer.isPresent()) {
            customerRepository.delete(findCustomer.get());
            return "Customer deleted.";
        }

        throw new CustomException(HttpStatus.BAD_REQUEST.value(), OBJECT_NOT_FOUND);
    }

    @Override
    public CustomerResponseDTO update(Long id, @Valid Customer update) throws CustomException {
        Optional<Customer> findCustomer = customerRepository.findById(id);

        if (findCustomer.isPresent()) {
            Customer customer = findCustomer.get();
            CustomerUtils.updateCustomerWithDates(customer, update);
            customerRepository.save(customer);
            return CustomerUtils.createCustomerResponseDTO(update);
        }

        throw new CustomException(HttpStatus.BAD_REQUEST.value(), OBJECT_NOT_FOUND);
    }

    @Override
    public CustomerResponseDTO getName(String name) throws CustomException {
        Optional<Customer> customerOptional = Optional.ofNullable(customerRepository.findByName(name));

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return CustomerUtils.createCustomerResponseDTO(customer);
        }

        throw new CustomException(HttpStatus.BAD_REQUEST.value(), OBJECT_NOT_FOUND);
    }

}
