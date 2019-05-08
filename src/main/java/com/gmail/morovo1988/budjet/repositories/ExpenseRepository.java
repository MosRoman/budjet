package com.gmail.morovo1988.budjet.repositories;

import com.gmail.morovo1988.budjet.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByMonthBudget_Id(Long id);
}
