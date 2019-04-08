package com.gmail.morovo1988.budjet.repositories;

import com.gmail.morovo1988.budjet.domain.MonthBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthBudgetRepository extends JpaRepository<MonthBudget, Long> {
    List<MonthBudget> findMonthBudgetByUser_Email(String login);
}
