package ru.ftc.android.shifttemple.features.books.presentation.fill_budget;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.books.domain.CategoryInteractor;
import ru.ftc.android.shifttemple.features.books.domain.model.Budget;
import ru.ftc.android.shifttemple.features.books.domain.model.Category;
import ru.ftc.android.shifttemple.features.books.domain.model.PlannedCategory;
import ru.ftc.android.shifttemple.features.books.domain.model.TempPlannedCategory;
import ru.ftc.android.shifttemple.network.Carry;

public class BudgetPresenter extends MvpPresenter<BudgetView> {
    private final CategoryInteractor interactor;

    public BudgetPresenter(CategoryInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        Log.d("VIEW ", "READY");
        loadCategories();
    }

    private void loadBudgets(List<Category> categoryList) {
        Log.d("BUDGET ", "start-------------");
        interactor.loadBudgets(new Carry<List<Budget>>() {
                                   @Override
                                   public void onSuccess(List<Budget> budgets) {
                                       if (budgets.isEmpty()) {
                                           Log.d("BUDGET ", "EMPTY");
                                           view.showBudget(createEmptyPlannedCategories(categoryList));
                                       } else {
                                           Log.d("BUDGET ", "SHOW...");
                                           view.showBudget(concatenateLists(findBudget(budgets)));
                                       }
                                   }

                                   @Override
                                   public void onFailure(Throwable throwable) {
                                       view.showError(throwable.getMessage());
                                   }
                               }
        );
    }

    private void isExistBudget(List<PlannedCategory> categoryList) {
        interactor.loadBudgets(new Carry<List<Budget>>() {
                                   @Override
                                   public void onSuccess(List<Budget> budgets) {
                                       List<Budget> b = findBudget(budgets);
                                       if (!b.isEmpty()) {
                                           Budget budget = new Budget();
                                           String id = String.valueOf(new Random().nextInt());
                                           budget.setCategories(categoryList);
                                           budget.setIdBudget(id);
                                           interactor.confirmBudget(budget, id, new Carry<Budget>() {
                                               @Override
                                               public void onSuccess(Budget result) {
                                                   view.onBudgetConfirmed();
                                               }
                                               @Override
                                               public void onFailure(Throwable throwable) {
                                                   view.showError(throwable.getMessage());
                                               }
                                           });
                                       } else {
                                           interactor.firstBudget(createTmpCategoryList(categoryList), new Carry<Budget>() {
                                               @Override
                                               public void onSuccess(Budget result) {
                                                   view.onBudgetConfirmed();
                                               }

                                               @Override
                                               public void onFailure(Throwable throwable) {
                                                   view.showError(throwable.getMessage());
                                               }
                                           });
                                       }
                                   }

                                   @Override
                                   public void onFailure(Throwable throwable) {
                                       view.showError(throwable.getMessage());
                                   }
                               }
        );
    }

    private List<TempPlannedCategory> createTmpCategoryList(List<PlannedCategory> categoryList) {
        List<TempPlannedCategory> list = new ArrayList<>();
        for (PlannedCategory c : categoryList) {
            list.add(new TempPlannedCategory(c.getCategory().getIdCategory(), c.getMoney()));
        }
        return list;
    }

    private Budget containsBudgId(String curBudgetId, List<Budget> budgets) {
        for (Budget b :
                budgets) {
            if (b.getIdBudget().equals(curBudgetId)) {
                return b;
            }
        }
        return null;
    }

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

    private void loadCategories() {
        interactor.loadCategories(new Carry<List<Category>>() {

            @Override
            public void onSuccess(List<Category> result) {
                loadBudgets(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }

        });
    }

    private PlannedCategory myContains(List<PlannedCategory> categories, Category category) {
        for (PlannedCategory c : categories) {
            if (c.getCategory().getName().equals(category.getName())) {
                return c;
            }
        }
        return null;
    }

    //==========================================================

    public void onConfirmBudget(List<PlannedCategory> categoryList) {
        isExistBudget(categoryList);
    }

    private List<PlannedCategory> createEmptyPlannedCategories(List<Category> result) {
        final ArrayList<PlannedCategory> categories = new ArrayList<>();
        for (Category cat : result
                ) {
            PlannedCategory c = new PlannedCategory(cat, 0, 0);
            categories.add(c);
        }
        return categories;
    }


}
