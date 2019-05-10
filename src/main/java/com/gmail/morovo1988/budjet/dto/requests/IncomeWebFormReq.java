package com.gmail.morovo1988.budjet.dto.requests;

import java.util.Objects;

public class IncomeWebFormReq {
    private String description;

    private Double amount;

    private Long idBudget;

    public IncomeWebFormReq() {
    }

    public IncomeWebFormReq(String description, Double amount) {
        this.description = description;
        this.amount = amount;
    }

    public IncomeWebFormReq(String description, Double amount, Long idBudget) {
        this.description = description;
        this.amount = amount;
        this.idBudget = idBudget;
    }

    public Long getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(Long idBudget) {
        this.idBudget = idBudget;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomeWebFormReq that = (IncomeWebFormReq) o;
        return amount == that.amount &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, amount);
    }
}
