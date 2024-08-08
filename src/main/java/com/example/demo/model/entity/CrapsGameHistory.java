package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "craps_game_history")
@Data
public class CrapsGameHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stake", nullable = false)
    private BigDecimal stake;

    @Column(name = "game_type", nullable = false)
    private String gameType;

    @Column(name = "outcome", nullable = false)
    private String outcome;

    @Column(name = "total_win", nullable = false)
    private BigDecimal totalWin;

    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
