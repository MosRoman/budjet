package com.gmail.morovo1988.budjet.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Income {
    @Id
    private long id;

    private String description;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "monthBudget_id")
    private MonthBudget monthBudget;

    public Income() {
    }

    public Income(long id, String description, int count) {
        this.id = id;
        this.description = description;
        this.amount = count;
    }
}
