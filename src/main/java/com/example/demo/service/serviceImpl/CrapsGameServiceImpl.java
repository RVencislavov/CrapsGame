package com.example.demo.service.serviceImpl;


import com.example.demo.mapper.CrapsMapper;
import com.example.demo.model.dto.CrapsGameHistoryDto;
import com.example.demo.model.entity.CrapsGameHistory;
import com.example.demo.model.request.CrapsGameRequestDto;
import com.example.demo.model.dto.MultiCrapsGameDto;
import com.example.demo.repository.CrapsGameHistoryRepository;
import com.example.demo.service.CrapsGameService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CrapsGameServiceImpl implements CrapsGameService {

    private final CrapsGameHistoryRepository repository;
    private final CrapsMapper mapper;

    public CrapsGameServiceImpl(CrapsGameHistoryRepository repository, CrapsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public CrapsGameHistoryDto playSingleGame(CrapsGameRequestDto requestDto) {
        CrapsGameHistoryDto gameHistoryDto = mapper.fromRequestDto(requestDto);

        BigDecimal stake = gameHistoryDto.getStake();
        Random random = new Random();

        int roll1 = random.nextInt(6) + 1;
        int roll2 = random.nextInt(6) + 1;
        int total = roll1 + roll2;

        String outcome;
        BigDecimal totalWin = BigDecimal.ZERO;
        StringBuilder details = new StringBuilder("Roll: " + roll1 + " + " + roll2 + " = " + total);

        if (total == 7 || total == 11) {
            outcome = "Win";
            totalWin = stake.add(stake);
        } else if (total == 2 || total == 3 || total == 12) {
            outcome = "Lose";
        } else {
            int point = total;
            details.append(". Point is ").append(point);

            while (true) {
                roll1 = random.nextInt(6) + 1;
                roll2 = random.nextInt(6) + 1;
                total = roll1 + roll2;

                details.append(". Roll: ").append(roll1).append(" + ").append(roll2).append(" = ").append(total);

                if (total == point) {
                    outcome = "Win";
                    totalWin = stake.add(stake);
                    break;
                } else if (total == 7) {
                    outcome = "Lose";
                    break;
                }
            }
        }

        gameHistoryDto.setOutcome(outcome);
        gameHistoryDto.setTotalWin(totalWin);
        gameHistoryDto.setDetails(details.toString());

        CrapsGameHistory gameHistory = mapper.toEntity(gameHistoryDto);
        gameHistory = repository.save(gameHistory);


        return mapper.toDto(gameHistory);
    }

    @Transactional
    public MultiCrapsGameDto playMultipleGames(CrapsGameRequestDto requestDto, int rounds) {
        List<CrapsGameHistoryDto> gameDtos = new ArrayList<>();
        BigDecimal totalStake = BigDecimal.ZERO;
        BigDecimal totalWin = BigDecimal.ZERO;

        for (int i = 0; i < rounds; i++) {
            CrapsGameHistoryDto response = playSingleGame(requestDto);
            gameDtos.add(response);
            totalStake = totalStake.add(response.getStake());
            totalWin = totalWin.add(response.getTotalWin());
        }

        BigDecimal winPercentage = totalStake.compareTo(BigDecimal.ZERO) > 0
                ? totalWin.divide(totalStake, BigDecimal.ROUND_HALF_UP)
                : BigDecimal.ZERO;

        MultiCrapsGameDto multiResponse = new MultiCrapsGameDto();
        multiResponse.setGames(gameDtos);
        multiResponse.setTotalStake(totalStake);
        multiResponse.setTotalWin(totalWin);
        multiResponse.setWinPercentage(winPercentage);

        return multiResponse;
    }
}

