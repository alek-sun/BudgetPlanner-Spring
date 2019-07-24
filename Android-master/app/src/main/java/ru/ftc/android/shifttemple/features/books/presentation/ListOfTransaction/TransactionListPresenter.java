package ru.ftc.android.shifttemple.features.books.presentation.ListOfTransaction;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.books.domain.CategoryInteractor;
import ru.ftc.android.shifttemple.features.books.domain.model.Budget;
import ru.ftc.android.shifttemple.features.books.domain.model.Category;
import ru.ftc.android.shifttemple.features.books.domain.model.PlannedCategory;
import ru.ftc.android.shifttemple.features.books.domain.model.Transaction;
import ru.ftc.android.shifttemple.network.Carry;

public final class TransactionListPresenter extends MvpPresenter<TransactionListView> {

    private final CategoryInteractor interactor;

    public TransactionListPresenter(CategoryInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        loadTransactions();
    }

    private void loadTransactions() {
        interactor.loadBudgets(new Carry<List<Budget>>() {
            @Override
            public void onSuccess(List<Budget> budgets) {
                if (budgets.isEmpty()) {
                    view.showEmptyTransactions();
                } else {
                    Budget b = findBudget(budgets, view.getCategoryId());
                    view.showTranactionList(b.getTransactions());
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

    private List<Transaction> getCatTransactions(List<Transaction> transactions, String categoryId) {
        List<Transaction> list = new ArrayList<>();
        for (Transaction t : transactions
             ) {
            if (t.getIdCategory() != null) {
                if (t.getIdCategory().equals(categoryId)) {
                    list.add(t);
                }
            }
        }
        return list;
    }

    /*private void loadBudgets(List<Category> categories){
        interactor.loadBudgets(new Carry<List<Budget>>() {
           @Override
           public void onSuccess(List<Budget> budgets) {
               if (budgets.isEmpty()) {
                   view.showCategoryList(categories);
                   //Log.d("BUDGET", "----------> empty");
               } else {
                   Budget budget = findBudget(budgets);
                   view.showCategoryStat(concatenateLists(categories, budget));
               }
        }

           @Override
           public void onFailure(Throwable throwable) {
               view.showError(throwable.getMessage());
           }
        }
        );
    }*/

    private List<PlannedCategory> concatenateLists(List<Category> categories,
                                                   Budget budget) {
        List<PlannedCategory> list = new ArrayList<>();
        PlannedCategory plCat;
        for (Category c : categories) {
            if ((plCat = myContains(budget.getCategories(), c)) == null) {
                plCat = new PlannedCategory(c, 0, 0);
            }
            list.add(plCat);
        }
        return list;
    }

    private Budget findBudget(List<Budget> result, String catId) {
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);////
        Log.d("FIND BUDGET ", "start");
        for (Budget b : result) {
            if (b.getDate().getNumberOfMonth() == currentMonth &&
                    b.getCategories().get(0).getCategory().getIdCategory().equals(catId)) {
                return b;
            }
        }
        return null;
    }

   /* private void loadCategories() {
        interactor.loadCategories(new Carry<List<Category>>() {

            @Override
            public void onSuccess(List<Category> result) {
                if (result.isEmpty()){
                    view.showEmptyCategories();
                } else {
                    loadBudgets(result);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }

        });
    }*/
/*
    void onCategorySelected(PlannedCategory category) {
        interactor.loadBook(category.getIdCategory(), new Carry<Category>() {

            @Override
            public void onSuccess(Category result) {
                // do something
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }

        });
    }

    void onBookLongClicked(PlannedCategory category) {
        interactor.deleteBook(category.getIdCategory(), new Carry<Success>() {

            @Override
            public void onSuccess(Success result) {
                loadCategories();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }
*/
    private PlannedCategory myContains(List<PlannedCategory> categories, Category category) {
        for (PlannedCategory c : categories){
            if (c.getCategory().getName().equals(category.getName())){
                return c;
            }
        }
        return null;
    }
}