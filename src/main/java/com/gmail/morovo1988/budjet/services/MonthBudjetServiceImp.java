package com.gmail.morovo1988.budjet.services;

import com.gmail.morovo1988.budjet.domain.MonthBudget;
import com.gmail.morovo1988.budjet.repositories.MonthBudgetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MonthBudjetServiceImp implements MonthBudjetService {

    private MonthBudgetRepository monthBudgetRepository;

    public MonthBudjetServiceImp(MonthBudgetRepository monthBudgetRepository) {
        this.monthBudgetRepository = monthBudgetRepository;
    }

    @Override
    public MonthBudget createMonthBudjet(MonthBudget monthBudget) {
        return this.monthBudgetRepository.save(monthBudget);
    }

    @Override
    public List<MonthBudget> findMonthBudjets() {
        return this.monthBudgetRepository.findAll();
    }

    @Override
    public MonthBudget findBudjetById(Long id) {
        return this.monthBudgetRepository.getOne(id);
    }

    @Override
    public void deleteBudgetById(Long id) {
        this.monthBudgetRepository.delete(this.monthBudgetRepository.getOne(id));
    }
}
