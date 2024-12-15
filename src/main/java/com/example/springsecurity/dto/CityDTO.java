package com.example.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {
    private CityStateDTO cityState;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class CityStateDTO {
    private int id;
    private String slug;
    private String name;
    private StateDTO state;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class StateDTO {
    private int id;
    private String name;
    private String slug;
    private String createdAt;
    private String updatedAt;
}
