CREATE TABLE craps_game_history (
                                    id BIGINT PRIMARY KEY IDENTITY,
                                    stake DECIMAL(10, 2) NOT NULL,
                                    game_type VARCHAR(50) NOT NULL,
                                    outcome VARCHAR(50) NOT NULL,
                                    total_win DECIMAL(10, 2) NOT NULL,
                                    details TEXT NOT NULL,
                                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);