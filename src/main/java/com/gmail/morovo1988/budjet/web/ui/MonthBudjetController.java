package com.gmail.morovo1988.budjet.web.ui;

import com.gmail.morovo1988.budjet.converters.GenericConverter;
import com.gmail.morovo1988.budjet.domain.Expense;
import com.gmail.morovo1988.budjet.domain.Income;
import com.gmail.morovo1988.budjet.domain.MonthBudget;
import com.gmail.morovo1988.budjet.domain.User;
import com.gmail.morovo1988.budjet.dto.requests.ExpenseWebFormReq;
import com.gmail.morovo1988.budjet.dto.requests.IncomeWebFormReq;
import com.gmail.morovo1988.budjet.repositories.ExpenseRepository;
import com.gmail.morovo1988.budjet.repositories.IncomeRepository;
import com.gmail.morovo1988.budjet.services.IncomeService;
import com.gmail.morovo1988.budjet.services.MonthBudjetService;
import com.gmail.morovo1988.budjet.services.UserService;
import com.gmail.morovo1988.budjet.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class MonthBudjetController {

    private final MonthBudjetService budjetService;

    private final IncomeService incomeService;

    private final IncomeRepository incomeRepository;

    private final ExpenseRepository expenseRepository;

    private final UserService userService;

    private final GenericConverter<Expense, ExpenseWebFormReq> genericConverter;

    @Autowired
    public MonthBudjetController(MonthBudjetService budjetService, IncomeService incomeService, IncomeRepository incomeRepository, ExpenseRepository expenseRepository, UserService userService, GenericConverter<Expense, ExpenseWebFormReq> genericConverter) {
        this.budjetService = budjetService;
        this.incomeService = incomeService;
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.userService = userService;

        this.genericConverter = genericConverter;
    }

    @GetMapping("/monthBudget/{monthId}")
    public String displayUserDetails(
            final @PathVariable("monthId") Long id,
            final Model model

    ) {
        final MonthBudget monthBudget = this.budjetService.findBudjetById(id);
        model.addAttribute("expense", new Expense());
        model.addAttribute("income", new Income());
        model.addAttribute("monthBudget", monthBudget);
        model.addAttribute("expenses", monthBudget.getExpenses());
        model.addAttribute("incomes", monthBudget.getIncomes());
        model.addAttribute("user", this.userService.loadUserByEmail(SecurityUtils.getCurrentUserLogin()).getName());
        return "monthBudget/monthBudgetDetails";
    }


    @GetMapping("/monthBudget/{monthId}/expense")
    public String formCreateExpense(final @PathVariable("monthId") Long id,
                                    final Model model) {
        final MonthBudget monthBudget = this.budjetService.findBudjetById(id);
        model.addAttribute("expense", new Expense());
        model.addAttribute("monthBudget", monthBudget);
        model.addAttribute("user", this.userService.loadUserByEmail(SecurityUtils.getCurrentUserLogin()).getName());
        return "monthBudget/createExpense";
    }


    @PostMapping("/monthBudget/{id}/expense")
    public String addExpense(@ModelAttribute("expense") final @Valid Expense req,
                             final BindingResult bindingResult,
                             final Model model,
                             @PathVariable("id") final Long id
    ) {
        MonthBudget monthBudget = this.budjetService.findBudjetById(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("expense", req);
            model.addAttribute("monthBudget", monthBudget);
            return "monthBudget/monthBudgetDetails";
        }
//        final Expense expense = this.genericConverter.createFromDto(req);
        req.setMonthBudget(monthBudget);
//        expense.setAmount(req.getAmount());
//        expense.setDescription(req.getDescription());
        this.expenseRepository.save(req);

        return "redirect:/monthBudget/{id}";

    }

    @GetMapping("/monthBudget/{monthId}/income")
    public String formCreateIncome(final @PathVariable("monthId") Long id,
                                   final Model model) {
        final MonthBudget monthBudget = this.budjetService.findBudjetById(id);
        model.addAttribute("income", new IncomeWebFormReq());
        model.addAttribute("monthBudget", monthBudget);
        model.addAttribute("user", this.userService.loadUserByEmail(SecurityUtils.getCurrentUserLogin()).getName());
        return "monthBudget/createIncome";
    }

    @PostMapping("/monthBudget/{id}/income")
    public String addIncome(@ModelAttribute("income") final @Valid IncomeWebFormReq req,
                            final BindingResult bindingResult,
                            final Model model,
                            @PathVariable("id") final Long id
    ) {
        MonthBudget monthBudget = this.budjetService.findBudjetById(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("income", req);
            model.addAttribute("monthBudget", monthBudget);
            return "monthBudget/monthBudgetDetails";
        }

        req.setIdBudget(id);
        this.incomeService.creteIncomeFromWebForm(req);

        return "redirect:/monthBudget/{id}";

    }

    @PostMapping("/add_month_budget")
    public String addMonthBudget(@RequestParam String month) {
        if (month == "") {
            return "redirect:/";
        }

        User user = this.userService.loadUserByEmail(SecurityUtils.getCurrentUserLogin());

        String date = month + "-01";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        MonthBudget monthBudget = new MonthBudget();
        monthBudget.setUser(user);
        monthBudget.setLocalDate(LocalDate.parse(date, formatter));

        this.budjetService.createMonthBudjet(monthBudget);

        return "redirect:/";
    }

    @PostMapping("budget/{id}/delete")
    public String deleteMonthBudget(@PathVariable("id") final Long id) {

        this.budjetService.deleteBudgetById(id);
        return "redirect:/";
    }
}
