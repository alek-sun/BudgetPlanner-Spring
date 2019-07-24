package ftc.shift.scheduler.db;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "budget", schema = "budget_planner", catalog = "")
@IdClass(BudgetEntityPK.class)
public class BudgetEntity {
    private int idBudget;
    private int idCategory;
    private int spending;
    private int planned;
    private Date date;
    private CategoryEntity categoryByIdCategory;
    private TransactionsEntity transactionsByIdBudget;

    @Id
    @Column(name = "id_budget", nullable = false)
    public int getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(int idBudget) {
        this.idBudget = idBudget;
    }

    @Id
    @Column(name = "id_category", nullable = false)
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Basic
    @Column(name = "spending", nullable = false)
    public int getSpending() {
        return spending;
    }

    public void setSpending(int spending) {
        this.spending = spending;
    }

    @Basic
    @Column(name = "planned", nullable = false)
    public int getPlanned() {
        return planned;
    }

    public void setPlanned(int planned) {
        this.planned = planned;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetEntity that = (BudgetEntity) o;
        return idBudget == that.idBudget &&
                idCategory == that.idCategory &&
                spending == that.spending &&
                planned == that.planned &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBudget, idCategory, spending, planned, date);
    }

    /*@ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "idCategory", nullable = false)
    public CategoryEntity getCategoryByIdCategory() {
        return categoryByIdCategory;
    }

    public void setCategoryByIdCategory(CategoryEntity categoryByIdCategory) {
        this.categoryByIdCategory = categoryByIdCategory;
    }*/

    /*@ManyToOne
    @JoinColumn(name = "id_budget", referencedColumnName = "id_budget", nullable = false)
    public TransactionsEntity getTransactionsByIdBudget() {
        return transactionsByIdBudget;
    }

    public void setTransactionsByIdBudget(TransactionsEntity transactionsByIdBudget) {
        this.transactionsByIdBudget = transactionsByIdBudget;
    }*/
}
