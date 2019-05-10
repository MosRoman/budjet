package com.gmail.morovo1988.budjet.repositories;

import com.gmail.morovo1988.budjet.domain.Income;
import com.gmail.morovo1988.budjet.domain.MonthBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByMonthBudget_Id(Long id);

    @Query("SELECT  sum(amount) FROM Income e WHERE e.monthBudget = :MonthBudget")
    Long totalSumExpense(@Param("MonthBudget") MonthBudget monthBudget);
}
