package ftc.shift.scheduler.models;


import java.io.Serializable;


public class TempPlannedCategory implements Serializable {

    private String idCategory;

    private Integer money;

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}