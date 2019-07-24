package ru.ftc.android.shifttemple.features.books.domain.model;

import java.io.Serializable;

public class Category implements Serializable {

    private String idCategory;

    private String name;

    public Category(String name) {
        this.name = name;
        idCategory = "-1";
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

