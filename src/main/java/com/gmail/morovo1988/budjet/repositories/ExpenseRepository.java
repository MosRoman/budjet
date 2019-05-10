package com.gmail.morovo1988.budjet.repositories;

import com.gmail.morovo1988.budjet.domain.Expense;
import com.gmail.morovo1988.budjet.domain.MonthBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByMonthBudget_Id(Long id);

    @Query("SELECT  sum(amount) FROM Expense e WHERE e.monthBudget = :MonthBudget")
    Long totalSumExpense(@Param("MonthBudget") MonthBudget monthBudget);
}
