package ru.ftc.android.shifttemple.features.books.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.domain.model.Budget;
import ru.ftc.android.shifttemple.features.books.domain.model.Category;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.books.domain.model.TempPlannedCategory;
import ru.ftc.android.shifttemple.features.books.domain.model.Transaction;
import ru.ftc.android.shifttemple.network.Carry;
import ru.ftc.android.shifttemple.network.DefaultCallback;


public final class PlannerDataSourceImpl implements PlannerDataSource {

    private final PlannerApi plannerApi;

    public PlannerDataSourceImpl(PlannerApi plannerApi) {
        this.plannerApi = plannerApi;
    }

    @Override
    public void getCategories(Carry<List<Category>> carry) {
        plannerApi.getCategoryList().enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getCategory(String id, Carry<Category> carry) {

    }

    /*@Override
    public void getCategory(String id, Carry<Category> carry) {
        plannerApi.getCategory(id).enqueue(new DefaultCallback(carry));
    }*/

    @Override
    public void createCategory(Category category, Carry<Category> carry) {
        plannerApi.createCategory(category).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void deleteCategory(String id, Carry<Success> carry) {
        //plannerApi.deleteBook(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void confirmBudget(Budget budget, String id, Carry<Budget> carry) {
        plannerApi.changeBudget(budget, id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void loadBudgets(Carry<List<Budget>> carry) {
        plannerApi.loadBudgets().enqueue(new DefaultCallback(carry));
    }

    @Override
    public void firstBudget(List<TempPlannedCategory> categoryList, Carry<Budget> carry) {
        plannerApi.firstBudget(categoryList).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void createTransaction(Transaction transaction, Carry<Transaction> carry) {
        plannerApi.createTransaction(transaction).enqueue(new DefaultCallback(carry));
    }
}