package com.gmail.morovo1988.budjet.web.api;

import com.gmail.morovo1988.budjet.domain.MonthBudget;
import com.gmail.morovo1988.budjet.services.MonthBudjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MonthBudgetResource {
    private MonthBudjetService budjetService;

    @Autowired
    public MonthBudgetResource(MonthBudjetService budjetService) {
        this.budjetService = budjetService;
    }

    @GetMapping("/month_budget/{monthId}")
    public MonthBudget displayMothBudget(
            final @PathVariable("monthId") Long id
    ) {
        final MonthBudget monthBudget = this.budjetService.findBudjetById(id);

        return monthBudget;
    }

    @GetMapping("/security/month_budget/{monthId}")
    public MonthBudget displayMothBudget2(
            final @PathVariable("monthId") Long id
    ) {
        final MonthBudget monthBudget = this.budjetService.findBudjetById(id);

        return monthBudget;
    }
}
