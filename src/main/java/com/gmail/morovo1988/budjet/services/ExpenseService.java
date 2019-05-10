package com.gmail.morovo1988.budjet.services;

import com.gmail.morovo1988.budjet.domain.Expense;
import com.gmail.morovo1988.budjet.domain.MonthBudget;
import com.gmail.morovo1988.budjet.dto.requests.ExpenseWebFormReq;

public interface ExpenseService {
    Expense createExpenseFromWeb(ExpenseWebFormReq expenseWebFormReq);

    Long sumExpenses(MonthBudget monthBudget);
}
