package com.gmail.morovo1988.budjet.repositories;

import com.gmail.morovo1988.budjet.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByMonthBudget_Id(Long id);
}
