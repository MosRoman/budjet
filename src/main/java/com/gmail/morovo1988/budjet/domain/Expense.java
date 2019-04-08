package com.gmail.morovo1988.budjet.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Expense {

    @Id
    private long id;

    private String description;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "monthBudget_id")
    private MonthBudget monthBudget;

    public Expense() {
    }

    public Expense(long id, String descrioption, int count) {
        this.id = id;
        this.description = descrioption;
        this.amount = count;
    }
}
