package ru.ftc.android.shifttemple.features.books.presentation.Transaction;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.books.domain.CategoryInteractor;
import ru.ftc.android.shifttemple.features.books.domain.model.Budget;
import ru.ftc.android.shifttemple.features.books.domain.model.Category;
import ru.ftc.android.shifttemple.features.books.domain.model.PlannedCategory;
import ru.ftc.android.shifttemple.features.books.domain.model.Transaction;
import ru.ftc.android.shifttemple.network.Carry;

public class CreateTransactionPresenter extends MvpPresenter<CreateTransactionView> {
    private final CategoryInteractor interactor;
    private CreateTransactionActivity activity;

    public CreateTransactionPresenter(CategoryInteractor interactor, CreateTransactionActivity activity) {
        this.interactor = interactor;
        this.activity = activity;
    }

    @Override
    protected void onViewReady() {
        loadCategories();
    }

    void loadCategories() {
        interactor.loadCategories(new Carry<List<Category>>() {
            @Override
            public void onSuccess(List<Category> result) {
                activity.setCatName(getStrings(result));
                activity.showList();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }

        });
    }

    private List<String> getStrings(List<Category> result) {
        List<String> strs = new ArrayList<>();
        for (Category c : result
             ) {
            strs.add(c.getName());
        }
        return strs;
    }

    private Budget findBudget(List<Budget> result, String category) {
        Integer maxMonth = -1;
        Budget lastBudget = new Budget();
        Integer curNumMonth;
        for (Budget b : result) {
            if (b.getCategories().get(0).getCategory().getName().equals(category) &&
                    (curNumMonth = b.getDate().getNumberOfMonth()) > maxMonth){
                maxMonth = curNumMonth;
                lastBudget = b;
            }
        }
        return lastBudget;
    }

    private void sendTransaction(Transaction transaction){
        interactor.createTransaction(transaction, new Carry<Transaction>() {
            @Override
            public void onSuccess(Transaction result) {
                view.onTransactionCreated();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

    public void onCreateTransaction(String descr, Integer cost, String category) {
        interactor.loadBudgets(new Carry<List<Budget>>() {
            @Override
            public void onSuccess(List<Budget> budgets) {
                Budget budget = findBudget(budgets, category);
                String lastBudgetId = budget.getIdBudget();
                //System.out.println(budget.getCategories());
                //String categoryId = findCatIdByName(budget.getCategories(), category);
                //String categoryId = findCatIdByName(budgets, category);
                Transaction transaction = new Transaction(
                        budget.getCategories().get(0).getCategory().getIdCategory(),
                        lastBudgetId, descr, cost);
                sendTransaction(transaction);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

    private String findCatIdByName(List<Budget> budgets, String name) {
        for (Budget b : budgets
             ) {
            for (PlannedCategory c :
                    b.getCategories()) {
                System.out.println(c.getCategory().getName() + "========================= " + name);
                if (c.getCategory().getName().equals(name)){
                    return c.getCategory().getIdCategory();
                }
            }
        }

        return null;
    }
}
