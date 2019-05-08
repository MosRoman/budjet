package com.gmail.morovo1988.budjet.converters;

import com.gmail.morovo1988.budjet.domain.Expense;
import com.gmail.morovo1988.budjet.dto.requests.ExpenseWebFormReq;
import org.springframework.stereotype.Component;

@Component("ExpenseWebFormViewConverter")
public class ExpenseConvetWebForm implements GenericConverter<Expense, ExpenseWebFormReq> {
    @Override
    public Expense createFromDto(final ExpenseWebFormReq dto) {
        final Expense expense = new Expense();

        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());


        return expense;
    }
}
