package com.desafio.serasa.experian.interfaces;

import com.desafio.serasa.experian.domain.dtos.CustomerResponseDTO;
import com.desafio.serasa.experian.domain.entity.Customer;
import com.desafio.serasa.experian.exceptions.CustomException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public interface CustomerService {

    CustomerResponseDTO save(@Valid Customer data) throws CustomException;

    String delete(Long id) throws CustomException;

    CustomerResponseDTO update(Long id, @Valid Customer data) throws CustomException;

    CustomerResponseDTO getName(String name) throws CustomException;
}
