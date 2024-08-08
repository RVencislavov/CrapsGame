package com.example.demo.repository;

import com.example.demo.model.entity.CrapsGameHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrapsGameHistoryRepository extends JpaRepository<CrapsGameHistory, Long> {
}
