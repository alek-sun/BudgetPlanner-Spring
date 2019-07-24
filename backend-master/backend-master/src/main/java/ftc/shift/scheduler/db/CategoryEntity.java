package ftc.shift.scheduler.db;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "category", schema = "budget_planner", catalog = "")
public class CategoryEntity {
    private int idCategory;
    private String name;
    private Collection<BudgetEntity> budgetsByIdCategory;
    private Collection<TransactionsEntity> transactionsByIdCategory;

    @Id
    @Column(name = "idCategory", nullable = false)
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return idCategory == that.idCategory &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategory, name);
    }

    /*@OneToMany(mappedBy = "categoryByIdCategory")
    public Collection<BudgetEntity> getBudgetsByIdCategory() {
        return budgetsByIdCategory;
    }

    public void setBudgetsByIdCategory(Collection<BudgetEntity> budgetsByIdCategory) {
        this.budgetsByIdCategory = budgetsByIdCategory;
    }

    @OneToMany(mappedBy = "categoryByIdCategory")
    public Collection<TransactionsEntity> getTransactionsByIdCategory() {
        return transactionsByIdCategory;
    }
*/
    public void setTransactionsByIdCategory(Collection<TransactionsEntity> transactionsByIdCategory) {
        this.transactionsByIdCategory = transactionsByIdCategory;
    }
}
