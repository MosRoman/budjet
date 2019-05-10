package com.gmail.morovo1988.budjet.services;

import com.gmail.morovo1988.budjet.converters.ExpenseConvetWebForm;
import com.gmail.morovo1988.budjet.domain.Expense;
import com.gmail.morovo1988.budjet.domain.MonthBudget;
import com.gmail.morovo1988.budjet.dto.requests.ExpenseWebFormReq;
import com.gmail.morovo1988.budjet.repositories.ExpenseRepository;
import com.gmail.morovo1988.budjet.repositories.MonthBudgetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    private final MonthBudgetRepository monthBudgetRepository;

    private final ExpenseConvetWebForm expenseConvetWebForm;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, MonthBudgetRepository monthBudgetRepository, ExpenseConvetWebForm expenseConvetWebForm) {
        this.expenseRepository = expenseRepository;
        this.monthBudgetRepository = monthBudgetRepository;
        this.expenseConvetWebForm = expenseConvetWebForm;
    }

    @Override
    public Expense createExpenseFromWeb(ExpenseWebFormReq expenseWebFormReq) {
        Expense expense = this.expenseConvetWebForm.createFromDto(expenseWebFormReq);

        MonthBudget monthBudget = expense.getMonthBudget();
        monthBudget.setResult(monthBudget.getResult()-expense.getAmount());
        this.monthBudgetRepository.save(monthBudget);
        return this.expenseRepository.save(expense);
    }

    @Override
    public Long sumExpenses(MonthBudget monthBudget) {
        return this.expenseRepository.totalSumExpense(monthBudget);
    }
}
