package ftc.shift.scheduler.models;

import java.io.Serializable;

public class Category implements Serializable {

    private String idCategory;

    private String name;

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