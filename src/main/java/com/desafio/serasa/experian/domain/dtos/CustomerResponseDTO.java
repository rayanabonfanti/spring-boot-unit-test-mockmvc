package com.desafio.serasa.experian.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {
    private String name;
    private Integer age;
    private String phone;
}
