package com.gmail.morovo1988.budjet.services;

import com.gmail.morovo1988.budjet.converters.IncomeConvertWebForm;
import com.gmail.morovo1988.budjet.domain.Income;
import com.gmail.morovo1988.budjet.domain.MonthBudget;
import com.gmail.morovo1988.budjet.dto.requests.IncomeWebFormReq;
import com.gmail.morovo1988.budjet.repositories.IncomeRepository;
import com.gmail.morovo1988.budjet.repositories.MonthBudgetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;

    private final IncomeConvertWebForm incomeConvertWebForm;

    private final MonthBudgetRepository monthBudgetRepository;

    public IncomeServiceImpl(IncomeRepository incomeRepository, IncomeConvertWebForm incomeConvertWebForm, MonthBudgetRepository monthBudgetRepository) {
        this.incomeRepository = incomeRepository;
        this.incomeConvertWebForm = incomeConvertWebForm;
        this.monthBudgetRepository = monthBudgetRepository;
    }

    @Override
    public Income creteIncomeFromWebForm(IncomeWebFormReq req) {

        Income income = this.incomeConvertWebForm.createFromDto(req);
        MonthBudget monthBudget = income.getMonthBudget();
        monthBudget.setResult(monthBudget.getResult()+income.getAmount());
        this.monthBudgetRepository.save(monthBudget);
        return this.incomeRepository.save(income);
    }

    @Override
    public Long sumExpenses(MonthBudget monthBudget) {
        return this.incomeRepository.totalSumExpense(monthBudget);
    }

    @Override
    public Income findOne(Long id) {
        return this.incomeRepository.getOne(id);
    }

    @Override
    public void deleteIncomeById(Long id) {
        this.incomeRepository.delete(this.incomeRepository.getOne(id));
    }
}
