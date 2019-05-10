package com.gmail.morovo1988.budjet.converters;

import com.gmail.morovo1988.budjet.domain.Expense;
import com.gmail.morovo1988.budjet.dto.requests.ExpenseWebFormReq;
import com.gmail.morovo1988.budjet.repositories.MonthBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ExpenseWebFormViewConverter")
public class ExpenseConvetWebForm implements GenericConverter<Expense, ExpenseWebFormReq> {
    @Autowired
    private MonthBudgetRepository monthBudgetRepository;
    @Override
    public Expense createFromDto(final ExpenseWebFormReq dto) {
        final Expense expense = new Expense();

        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());
        expense.setMonthBudget(this.monthBudgetRepository.findMonthBudgetById(dto.getIdBudget()));

        return expense;
    }
}
