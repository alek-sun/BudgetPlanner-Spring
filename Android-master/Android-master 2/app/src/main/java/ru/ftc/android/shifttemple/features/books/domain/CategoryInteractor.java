package ru.ftc.android.shifttemple.features.books.domain;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.domain.model.Budget;
import ru.ftc.android.shifttemple.features.books.domain.model.Category;
import ru.ftc.android.shifttemple.features.books.domain.model.PlannedCategory;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.books.domain.model.TempPlannedCategory;
import ru.ftc.android.shifttemple.features.books.domain.model.Transaction;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public interface CategoryInteractor {

    void loadCategories(Carry<List<Category>> carry);

    void confirmBudget(Budget budget, String id, Carry<Budget> carry);

    void loadBudgets(Carry<List<Budget>> carry);

    void createCategory(Category category, Carry<Category> carry);

    void createTransaction(Transaction transaction, Carry<Transaction> carry);

    void firstBudget(List<TempPlannedCategory> categoryList, Carry<Budget> carry);
}