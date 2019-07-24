package ru.ftc.android.shifttemple.features.books.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.domain.model.Budget;
import ru.ftc.android.shifttemple.features.books.domain.model.Category;
import ru.ftc.android.shifttemple.features.books.domain.model.PlannedCategory;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.books.domain.model.TempPlannedCategory;
import ru.ftc.android.shifttemple.features.books.domain.model.Transaction;
import ru.ftc.android.shifttemple.network.Carry;

public interface PlannerDataSource {

    void getCategories(Carry<List<Category>> carry);

    void getCategory(String id, Carry<Category> carry);

    void createCategory(Category category, Carry<Category> carry);

    void deleteCategory(String id, Carry<Success> carry);

    void confirmBudget(Budget budget, String id, Carry<Budget> carry);

    void loadBudgets(Carry<List<Budget>> carry);

    void firstBudget(List<TempPlannedCategory> categoryList, Carry<Budget> carry);

    void createTransaction(Transaction transaction, Carry<Transaction> carry);
}
