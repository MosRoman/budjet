package com.gmail.morovo1988.budjet.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
//@Data
@Getter
@Setter
//@GenericGenerator(
//        name = "seq",
//        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//        parameters = {
//                @org.hibernate.annotations.Parameter(
//                        name = "sequence_name",
//                        value = "public" + '.' + "hibernate_sequence"
//                ),
//                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
//        }
//)
public class Expense {
//
//    @Id
//    @GeneratedValue(generator = "native")
//    @SequenceGenerator(name = "native", initialValue = 5, allocationSize = 1)
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//@Id
//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq" )
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "expense_sequence", initialValue = 5,  allocationSize = 1)
    private Long id;

    private String description;

    private int amount;
    @Setter
    @ManyToOne
    @JoinColumn(name = "monthBudget_id")
    private MonthBudget monthBudget;

    public Expense() {
    }

    public Expense(String description, int amount) {
        this.description = description;
        this.amount = amount;
    }

    public Expense(String description, int amount, MonthBudget monthBudget) {
        this.description = description;
        this.amount = amount;
        this.monthBudget = monthBudget;
    }

    public Expense(Long id, String description, int amount, MonthBudget monthBudget) {
        this.id = id;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public MonthBudget getMonthBudget() {
        return monthBudget;
    }

    public void setMonthBudget(MonthBudget monthBudget) {
        this.monthBudget = monthBudget;
    }
}
