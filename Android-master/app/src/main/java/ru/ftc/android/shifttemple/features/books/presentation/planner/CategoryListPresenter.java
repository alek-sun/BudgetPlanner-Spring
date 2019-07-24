package ru.ftc.android.shifttemple.features.books.presentation.planner;

import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.books.domain.CategoryInteractor;
import ru.ftc.android.shifttemple.features.books.domain.model.Budget;
import ru.ftc.android.shifttemple.features.books.domain.model.Category;
import ru.ftc.android.shifttemple.features.books.domain.model.PlannedCategory;
import ru.ftc.android.shifttemple.network.Carry;

public final class CategoryListPresenter extends MvpPresenter<CategoryListView> {

    private final CategoryInteractor interactor;
    private PlannerActivity activity;

    public CategoryListPresenter(CategoryInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        loadCategories();
    }

    private void loadBudgets(List<Category> categories){
        interactor.loadBudgets(new Carry<List<Budget>>() {
           @Override
           public void onSuccess(List<Budget> budgets) {
               if (budgets.isEmpty()) {
                   view.showCategoryList(categories);
               } else {
                   List<Budget> b = findBudget(budgets);
                   view.showCategoryStat(concatenateLists(b));
               }
        }

           @Override
           public void onFailure(Throwable throwable) {
               view.showError(throwable.getMessage());
           }
        }
        );
    }

    /*private List<PlannedCategory> concatenateLists(List<Category> categories,
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
    }*/

    private List<PlannedCategory> concatenateLists(List<Budget> budgets) {
        List<PlannedCategory> list = new ArrayList<>();
        Log.d("LIST PLANNED ", "start");
        for (Budget b : budgets) {
            list.add(b.getCategories().get(0));
            Log.d("------------------->  ", b.getCategories().get(0).getCategory().getName());
        }
        return list;
    }

    private List<Budget> findBudget(List<Budget> result) {
        List<Budget> budgetList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);////
        Log.d("FIND BUDGET ", "start");
        for (Budget b : result) {
            if (b.getDate().getNumberOfMonth() == currentMonth) {
                budgetList.add(b);
                Log.d("------------------->  ", b.getIdBudget());
            }
        }
        return budgetList;
    }


    void setActivity(PlannerActivity activity){
        this.activity = activity;
    }

    public void loadCategoryId(String name, StringBuilder id, Intent intent){
        interactor.loadCategories(new Carry<List<Category>>() {
            @Override
            public void onSuccess(List<Category> result) {
                if (result.isEmpty()){
                    return;
                } else {
                    for (Category c : result) {
                        if (c.getName().equals(name)){
                            id.append(c.getIdCategory());
                        }
                    }
                    intent.putExtra("categoryId", id.toString());
                    activity.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }

        });
    }

    private void loadCategories() {
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
    }

    /*void onBookSelected(PlannedCategory category) {
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