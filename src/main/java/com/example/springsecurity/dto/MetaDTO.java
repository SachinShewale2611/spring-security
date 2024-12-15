package com.example.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaDTO {
    private int page;
    private int perPage;
    private int totalCount;
    private int currentCount;
}
