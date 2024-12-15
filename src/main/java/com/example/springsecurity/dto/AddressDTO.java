package com.example.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private int id;
    private String addressLineOne;
    private String addressLineTwo;
    private String zipcode;
    private String country;
    private String createdAt;
    private CityDTO city;
    private String kind;
    private Boolean isActive;
}
