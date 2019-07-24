package ru.ftc.android.shifttemple.features.books.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Budget implements Serializable {

    private String idBudget;

    private List<PlannedCategory> categories;

    private List<Transaction> transactions;

    private Date date;

    public Budget(String idBudget, List<PlannedCategory> categories) {
        this.idBudget = idBudget;
        this.categories = categories;
        //this.transactions = transactions;
        //this.date = date;
    }

    public Budget() {
        categories = new ArrayList<>();
    }

    public String getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(String idBudget) {
        this.idBudget = idBudget;
    }

    public List<PlannedCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<PlannedCategory> categories) {
        this.categories = categories;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}


