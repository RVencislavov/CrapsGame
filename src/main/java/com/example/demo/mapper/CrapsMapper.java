package com.example.demo.mapper;

import com.example.demo.model.dto.CrapsGameHistoryDto;
import com.example.demo.model.entity.CrapsGameHistory;
import com.example.demo.model.request.CrapsGameRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CrapsMapper {
    CrapsGameHistory toEntity(CrapsGameHistoryDto crapsGameHistoryDto);
    CrapsGameHistoryDto fromRequestDto(CrapsGameRequestDto requestDto);
    CrapsGameHistoryDto toDto(CrapsGameHistory crapsGameHistory);
}
