package com.example.demo.controller;


import com.example.demo.model.dto.CrapsGameHistoryDto;
import com.example.demo.model.request.CrapsGameRequestDto;
import com.example.demo.model.dto.MultiCrapsGameDto;
import com.example.demo.service.CrapsGameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/craps")
public class CrapsGameController {

    private final CrapsGameService service;

    public CrapsGameController(CrapsGameService service) {
        this.service = service;
    }

    @PostMapping("/play")
    public CrapsGameHistoryDto playSingleGame(@RequestBody CrapsGameRequestDto requestDto) {
        return service.playSingleGame(requestDto);
    }

    @PostMapping("/play-multiple")
    public MultiCrapsGameDto playMultipleGames(@RequestBody CrapsGameRequestDto requestDto, @RequestParam int rounds) {
        return service.playMultipleGames(requestDto, rounds);
    }
}
