package com.example.demo.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MultiCrapsGameDto {
    private List<CrapsGameHistoryDto> games;
    private BigDecimal totalStake;
    private BigDecimal totalWin;
    private BigDecimal winPercentage;
}