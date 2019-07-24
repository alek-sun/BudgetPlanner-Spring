package ru.ftc.android.shifttemple.features.books.domain;

import android.util.Log;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.data.BooksRepository;
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

public final class CategoryInteractorImpl implements CategoryInteractor {

    private final BooksRepository repository;

    public CategoryInteractorImpl(BooksRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadCategories(Carry<List<Category>> carry) {
        repository.loadCategories(carry);
    }

    @Override
    public void confirmBudget(Budget budget, String id, Carry<Budget> carry) {
        repository.confirmBudget(budget, id, carry);
    }

    @Override
    public void loadBudgets(Carry<List<Budget>> carry) {
        repository.loadBudgets(carry);
    }


   /* @Override
    public void loadBook(String id, Carry<Category> carry) {
        repository.loadCategory(id, carry);
    }*/

    @Override
    public void createCategory(Category category, Carry<Category> carry) {
        repository.createCategory(category, carry);
    }

    @Override
    public void createTransaction(Transaction transaction, Carry<Transaction> carry) {
        repository.createTransaction(transaction, carry);
    }

    @Override
    public void firstBudget(List<TempPlannedCategory> categoryList, Carry<Budget> carry) {
        repository.firstBudget(categoryList, carry);
    }
}