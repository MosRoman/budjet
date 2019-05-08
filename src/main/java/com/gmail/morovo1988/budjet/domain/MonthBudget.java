package com.gmail.morovo1988.budjet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
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
public class MonthBudget {
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @SequenceGenerator(name = "native", initialValue = 5, allocationSize = 1)
//    @Id

    //@Id
//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq" )
//@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "month_budget_sequence", initialValue = 20, allocationSize = 1)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    @OneToMany(mappedBy = "monthBudget")
    @JsonIgnore
    private List<Income> incomes;

    @OneToMany(mappedBy = "monthBudget")
    @JsonIgnore
    private List<Expense> expenses;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public MonthBudget() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
