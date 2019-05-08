package com.gmail.morovo1988.budjet.services;

import com.gmail.morovo1988.budjet.converters.IncomeConvertWebForm;
import com.gmail.morovo1988.budjet.domain.Income;
import com.gmail.morovo1988.budjet.dto.requests.IncomeWebFormReq;
import com.gmail.morovo1988.budjet.repositories.IncomeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;

    private final IncomeConvertWebForm incomeConvertWebForm;

    public IncomeServiceImpl(IncomeRepository incomeRepository, IncomeConvertWebForm incomeConvertWebForm) {
        this.incomeRepository = incomeRepository;
        this.incomeConvertWebForm = incomeConvertWebForm;
    }

    @Override
    public Income creteIncomeFromWebForm(IncomeWebFormReq req) {

        Income income = this.incomeConvertWebForm.createFromDto(req);
        return this.incomeRepository.save(income);
    }
}
