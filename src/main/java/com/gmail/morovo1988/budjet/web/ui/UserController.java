package com.gmail.morovo1988.budjet.web.ui;

import com.gmail.morovo1988.budjet.domain.User;
import com.gmail.morovo1988.budjet.repositories.MonthBudgetRepository;
import com.gmail.morovo1988.budjet.repositories.RoleRepository;
import com.gmail.morovo1988.budjet.services.UserService;
import com.gmail.morovo1988.budjet.utils.SecurityUtils;
import com.gmail.morovo1988.budjet.validations.UniqueEmailForNewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller

public class UserController {

    private UserService userService;

    private RoleRepository roleRepository;

    private MonthBudgetRepository monthBudgetRepository;

    private final UniqueEmailForNewUser emailForNewUser;

    @Autowired
    public UserController(UserService userService, RoleRepository roleRepository, UniqueEmailForNewUser emailForNewUser, MonthBudgetRepository monthBudgetRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.emailForNewUser = emailForNewUser;
        this.monthBudgetRepository = monthBudgetRepository;
    }

    @InitBinder("newUser")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(this.emailForNewUser);
    }

    @GetMapping(value = "/")
    public String wellcome(Model model) {
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
        return "/security/registration";
    }

    @PostMapping("/register")
    public String processFormCreateUser(
            @ModelAttribute("newUser") final @Valid User req,
            final BindingResult bindingResult,
            final Model model

    ) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("newUser", req);
            model.addAttribute("roles", this.roleRepository.findAll());

            return "security/registration";
        }

        final User user = this.userService.createUser(req);

        if (user == null) {
//            throw new InternalServerException("We cannot create user");
        }

        return "redirect:/";
    }

    @GetMapping("users")
    public String listUsers(final Model model) {
        model.addAttribute("users", this.userService.findUsers());
        model.addAttribute("roles", this.roleRepository.findAll());
        return "users/listUsers";
    }
}
