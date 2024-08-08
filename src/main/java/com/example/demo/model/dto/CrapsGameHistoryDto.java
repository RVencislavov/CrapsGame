package com.example.demo.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class CrapsGameHistoryDto {

    private Long id;
    private BigDecimal stake;
    private String gameType;
    private String outcome;
    private BigDecimal totalWin;
    private String details;
    private LocalDateTime createdAt;
}
