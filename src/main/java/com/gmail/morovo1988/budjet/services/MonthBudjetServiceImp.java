package com.gmail.morovo1988.budjet.services;

import com.gmail.morovo1988.budjet.domain.Expense;
import com.gmail.morovo1988.budjet.domain.Income;
import com.gmail.morovo1988.budjet.domain.MonthBudget;
import com.gmail.morovo1988.budjet.repositories.MonthBudgetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MonthBudjetServiceImp implements MonthBudjetService {

    private final MonthBudgetRepository monthBudgetRepository;

    private final ExpenseService expenseService;

    private final IncomeService incomeService;

    public MonthBudjetServiceImp(MonthBudgetRepository monthBudgetRepository,
                                 ExpenseService expenseService,
                                 IncomeService incomeService) {
        this.monthBudgetRepository = monthBudgetRepository;
        this.expenseService = expenseService;
        this.incomeService = incomeService;
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
        MonthBudget monthBudget = this.monthBudgetRepository.findMonthBudgetById(id);
        deleteListExpense(monthBudget.getExpenses());
        deleteListIncome(monthBudget.getIncomes());

        this.monthBudgetRepository.delete(this.monthBudgetRepository.getOne(id));
    }

    public void deleteListExpense(List<Expense> expenses){
        for (Expense expense:expenses) {
            this.expenseService.deleteExpenseById(expense.getId());
        }
    }

    public void deleteListIncome(List<Income> incomes){
        for (Income income:incomes) {
            this.incomeService.deleteIncomeById(income.getId());
        }
    }

}
