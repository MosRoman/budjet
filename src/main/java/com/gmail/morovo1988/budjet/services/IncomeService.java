package com.gmail.morovo1988.budjet.services;

import com.gmail.morovo1988.budjet.domain.Income;
import com.gmail.morovo1988.budjet.dto.requests.IncomeWebFormReq;

public interface IncomeService {
    Income creteIncomeFromWebForm(IncomeWebFormReq req);
}
