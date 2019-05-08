package com.gmail.morovo1988.budjet.services;

import com.gmail.morovo1988.budjet.domain.MonthBudget;

import java.util.List;

public interface MonthBudjetService {
    MonthBudget createMonthBudjet(MonthBudget monthBudget);

    List<MonthBudget> findMonthBudjets();

    MonthBudget findBudjetById(Long id);

    void deleteBudgetById(Long id);

    }
