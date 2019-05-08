package com.gmail.morovo1988.budjet.services;

import com.gmail.morovo1988.budjet.domain.Expense;
import com.gmail.morovo1988.budjet.dto.requests.ExpenseWebFormReq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    @Override
    public Expense createExpenseFromWeb(ExpenseWebFormReq expenseWebFormReq) {
        return null;
    }
}
