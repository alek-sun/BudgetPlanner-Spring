package ftc.shift.scheduler.db;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BudgetEntityPK implements Serializable {
    private int idBudget;
    private int idCategory;

    @Column(name = "id_budget", nullable = false)
    @Id
    public int getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(int idBudget) {
        this.idBudget = idBudget;
    }

    @Column(name = "id_category", nullable = false)
    @Id
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetEntityPK that = (BudgetEntityPK) o;
        return idBudget == that.idBudget &&
                idCategory == that.idCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBudget, idCategory);
    }
}
