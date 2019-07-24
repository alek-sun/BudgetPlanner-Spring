package ftc.shift.scheduler.models;


import java.io.Serializable;

public class PlannedCategory implements Serializable {

    private Category category;

    private Integer money;

    private Integer spending;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getSpending() {
        return spending;
    }

    public void setSpending(Integer spending) {
        this.spending = spending;
    }
}