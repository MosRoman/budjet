package com.gmail.morovo1988.budjet.web.ui;

import com.gmail.morovo1988.budjet.domain.MonthBudget;
import com.gmail.morovo1988.budjet.domain.User;
import com.gmail.morovo1988.budjet.dto.requests.UpdateUserReq;
import com.gmail.morovo1988.budjet.exceptions.InternalServerException;
import com.gmail.morovo1988.budjet.exceptions.UserNotFoundException;
import com.gmail.morovo1988.budjet.repositories.MonthBudgetRepository;
import com.gmail.morovo1988.budjet.repositories.RoleRepository;
import com.gmail.morovo1988.budjet.services.ExpenseService;
import com.gmail.morovo1988.budjet.services.IncomeService;
import com.gmail.morovo1988.budjet.services.UserService;
import com.gmail.morovo1988.budjet.utils.SecurityUtils;
import com.gmail.morovo1988.budjet.validations.UniqueEmailForNewUser;
import com.gmail.morovo1988.budjet.validations.UniqueEmailForUpdateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller

public class UserController {

    private UserService userService;

    private RoleRepository roleRepository;

    private MonthBudgetRepository monthBudgetRepository;

    private final IncomeService incomeService;

    private final ExpenseService expenseService;

    private final UniqueEmailForNewUser emailForNewUser;

    private final UniqueEmailForUpdateUser uniqueEmailForUpdateUser;

    @Autowired
    public UserController(UserService userService,
                          RoleRepository roleRepository,
                          IncomeService incomeService,
                          ExpenseService expenseService,
                          UniqueEmailForNewUser emailForNewUser,
                          MonthBudgetRepository monthBudgetRepository,
                          UniqueEmailForUpdateUser uniqueEmailForUpdateUser) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.incomeService = incomeService;
        this.expenseService = expenseService;
        this.emailForNewUser = emailForNewUser;
        this.monthBudgetRepository = monthBudgetRepository;
        this.uniqueEmailForUpdateUser = uniqueEmailForUpdateUser;
    }

    @InitBinder("newUser")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(this.emailForNewUser);
    }

    @InitBinder("userUpdate")
    protected void initBinderUpdate(WebDataBinder binder) {
        binder.addValidators(this.uniqueEmailForUpdateUser);
    }

    @GetMapping(value = "/")
    public String wellcome(Model model) {

        model.addAttribute("monthBudget", new MonthBudget());
        model.addAttribute("monthBudgets", this.monthBudgetRepository.findMonthBudgetByUser_Email(SecurityUtils.getCurrentUserLogin()));
        model.addAttribute("user", this.userService.loadUserByEmail(SecurityUtils.getCurrentUserLogin()).getName());
        return "/home";
    }

    @GetMapping(value = "/admin")
    public String admin(Model model) {
        return "/admin";
    }


    @GetMapping("/register")
    public String displayFormCreateUser(final Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", this.roleRepository.findAll());
        return "/users/formCreateUser";
    }

    @PostMapping("/register")
    public String processFormCreateUser(
            @ModelAttribute("newUser") final @Valid User req,
            final BindingResult bindingResult,
            final Model model

    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("newUser", req);
            model.addAttribute("roles", this.roleRepository.findAll());

            return "/users/formCreateUser";
        }

        final User user = this.userService.createUser(req);

        if (user == null) {
            throw new InternalServerException("We cannot create user");
        }

        return "redirect:/users/";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public User findOne(Long id){
        return this.userService.findUser(id);
    }

    @GetMapping("users")
    public String listUsers(final Model model) {
        model.addAttribute("users", this.userService.findUsers());
        model.addAttribute("roles", this.roleRepository.findAll());
        model.addAttribute("user", this.userService.loadUserByEmail(SecurityUtils.getCurrentUserLogin()).getName());
        return "users/listUsers";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") final Long id) {

        this.userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("users/{id}/update")
    public String updateUser(final @PathVariable Long id,
                             final Model model
    ) {
        final UpdateUserReq user =
                this.userService.loadUpdateUserById(id)
                        .orElseThrow(UserNotFoundException::new);

        model.addAttribute("userUpdate", user);
        model.addAttribute("user", this.userService.loadUserByEmail(SecurityUtils.getCurrentUserLogin()).getName());
        return "users/formUpdateUser";
    }

    @PostMapping("/users/*/update")
    public String processFormUpdateUser(
            @ModelAttribute("userUpdate") final @Valid UpdateUserReq updatedUser,
            final BindingResult bindingResult,
            final Model model
    ) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userUpdate", updatedUser);
            return "users/formUpdateUser";
        }
        System.out.println("Heloo");
        System.out.println(updatedUser);
        final User user = this.userService.updateUser(updatedUser);

        if (user == null) {
            throw new InternalServerException("We cannot update user");
        }
        return "redirect:/users/";
    }
}