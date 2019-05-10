package com.gmail.morovo1988.budjet.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data

public class Income {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "income_sequence", initialValue = 5,  allocationSize = 1)
    private Long id;

    private String description;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "monthBudget_id")
    private MonthBudget monthBudget;

    public Income() {
    }

    public Income(String description, Double amount, MonthBudget monthBudget) {
        this.description = description;
        this.amount = amount;
        this.monthBudget = monthBudget;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public MonthBudget getMonthBudget() {
        return monthBudget;
    }

    public void setMonthBudget(MonthBudget monthBudget) {
        this.monthBudget = monthBudget;
    }
}
