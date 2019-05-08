package com.gmail.morovo1988.budjet.converters;

import com.gmail.morovo1988.budjet.domain.Income;
import com.gmail.morovo1988.budjet.dto.requests.IncomeWebFormReq;
import com.gmail.morovo1988.budjet.repositories.MonthBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ImcomeWebFormViewConverter")
public class IncomeConvertWebForm implements GenericConverter<Income, IncomeWebFormReq> {
    @Autowired
    private MonthBudgetRepository monthBudgetRepository;

    @Override
    public Income createFromDto(final IncomeWebFormReq dto) {
        final Income income = new Income();

        income.setDescription(dto.getDescription());
        income.setAmount(dto.getAmount());
        income.setMonthBudget(this.monthBudgetRepository.findMonthBudgetById(dto.getIdBudget()));

        return income;
    }
}
