package com.desafio.serasa.experian.database;

import com.desafio.serasa.experian.domain.entity.Customer;
import com.desafio.serasa.experian.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public DataInitializer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer = Customer.builder()
                .name("rayana")
                .age(26)
                .phone("11999780675")
                .build();

        customerRepository.save(customer);

        log.info("***CUSTOMER**** " + customer);
    }
}
