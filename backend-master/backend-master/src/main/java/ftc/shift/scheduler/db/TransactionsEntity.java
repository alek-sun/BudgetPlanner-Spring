package ftc.shift.scheduler.db;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "transactions", schema = "budget_planner", catalog = "")
public class TransactionsEntity {
    private int idTransactions;
    private int idCategory;
    private String name;
    private int cost;
    private Date date;
    private int idBudget;
    private CategoryEntity categoryByIdCategory;

    @Id
    @Column(name = "idTransactions", nullable = false)
    public int getIdTransactions() {
        return idTransactions;
    }

    public void setIdTransactions(int idTransactions) {
        this.idTransactions = idTransactions;
    }

    @Basic
    @Column(name = "id_category", nullable = false)
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

    @Basic
    @Column(name = "cost", nullable = false)
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "id_budget", nullable = false)
    public int getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(int idBudget) {
        this.idBudget = idBudget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionsEntity that = (TransactionsEntity) o;
        return idTransactions == that.idTransactions &&
                idCategory == that.idCategory &&
                cost == that.cost &&
                idBudget == that.idBudget &&
                Objects.equals(name, that.name) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransactions, idCategory, name, cost, date, idBudget);
    }

    /*@ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "idCategory", nullable = false)
    public CategoryEntity getCategoryByIdCategory() {
        return categoryByIdCategory;
    }

    public void setCategoryByIdCategory(CategoryEntity categoryByIdCategory) {
        this.categoryByIdCategory = categoryByIdCategory;
    }*/
}
