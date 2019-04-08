package com.gmail.morovo1988.budjet.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class MonthBudget {

    @Id
    private long id;

    private LocalDate localDate;

    @OneToMany(mappedBy = "monthBudget")
    private List<Income> incomes;

    @OneToMany(mappedBy = "monthBudget")
    private List<Expense> expenses;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public MonthBudget() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
