package com.example.demo.service;

import com.example.demo.model.dto.CrapsGameHistoryDto;
import com.example.demo.model.request.CrapsGameRequestDto;
import com.example.demo.model.dto.MultiCrapsGameDto;

public interface CrapsGameService {
    public CrapsGameHistoryDto playSingleGame(CrapsGameRequestDto requestDto);
    public MultiCrapsGameDto playMultipleGames(CrapsGameRequestDto requestDto, int rounds);
}
