package ru.ftc.android.shifttemple.features.books.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.domain.model.Budget;
import ru.ftc.android.shifttemple.features.books.domain.model.Category;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.books.domain.model.TempPlannedCategory;
import ru.ftc.android.shifttemple.features.books.domain.model.Transaction;
import ru.ftc.android.shifttemple.network.Carry;


public final class BooksRepositoryImpl implements BooksRepository {

    private final PlannerDataSource dataSource;

    public BooksRepositoryImpl(PlannerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadCategories(Carry<List<Category>> carry) {
        dataSource.getCategories(carry);
    }

    @Override
    public void loadCategory(String id, Carry<Category> carry) {
        dataSource.getCategory(id, carry);
    }

    @Override
    public void createCategory(Category category, Carry<Category> carry) {
        dataSource.createCategory(category, carry);
    }

    @Override
    public void deleteCategory(String id, Carry<Success> carry) {
        dataSource.deleteCategory(id, carry);
    }

    @Override
    public void confirmBudget(Budget budget, String id, Carry<Budget> carry) {
        dataSource.confirmBudget(budget, id, carry);
    }

    @Override
    public void loadBudgets(Carry<List<Budget>> carry) {
        dataSource.loadBudgets(carry);
    }

    @Override
    public void firstBudget(List<TempPlannedCategory> categoryList, Carry<Budget> carry) {
        dataSource.firstBudget(categoryList, carry);
    }

    @Override
    public void createTransaction(Transaction transaction, Carry<Transaction> carry) {
        dataSource.createTransaction(transaction, carry);
    }
}