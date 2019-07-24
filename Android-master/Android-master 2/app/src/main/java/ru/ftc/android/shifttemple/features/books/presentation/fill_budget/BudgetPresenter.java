package ru.ftc.android.shifttemple.features.books.presentation.fill_budget;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.books.domain.CategoryInteractor;
import ru.ftc.android.shifttemple.features.books.domain.model.Budget;
import ru.ftc.android.shifttemple.features.books.domain.model.Category;
import ru.ftc.android.shifttemple.features.books.domain.model.PlannedCategory;
import ru.ftc.android.shifttemple.features.books.domain.model.TempPlannedCategory;
import ru.ftc.android.shifttemple.network.Carry;

public class BudgetPresenter extends MvpPresenter<BudgetView> {
    private final CategoryInteractor interactor;
    private String curBudgetId;

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
                       Budget budget = findBudget(budgets);
                       curBudgetId = budget.getIdBudget();
                       view.showBudget(concatenateLists(categoryList, budget));
                   }
               }

               @Override
               public void onFailure(Throwable throwable) {
                   view.showError(throwable.getMessage());
               }
           }
        );
    }

    private void isExsistBudget(List<PlannedCategory> categoryList) {
        interactor.loadBudgets(new Carry<List<Budget>>() {
            @Override
            public void onSuccess(List<Budget> budgets) {
                Budget budget;
                if ((budget = containsBudgId(curBudgetId, budgets))!=null) {
                    budget.setCategories(categoryList);
                    interactor.confirmBudget(budget, curBudgetId, new Carry<Budget>() {
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
        for(PlannedCategory c : categoryList){
            list.add(new TempPlannedCategory(c.getCategory().getIdCategory(), c.getMoney()));
        }
        return list;
    }

    private Budget containsBudgId(String curBudgetId, List<Budget> budgets) {
        for (Budget b :
                budgets) {
            if (b.getIdBudget().equals(curBudgetId)){
                return b;
            }
        }
        return null;
    }

    /*private List<TempPlannedCategory> concatenateLists(List<Category> categories,
                                                       Budget budget) {
        Log.d("conc", "----------- СТAРТ ");
        List<TempPlannedCategory> list = new ArrayList<>();
        PlannedCategory plCat;
        TempPlannedCategory tempPlannedCategory;
        Log.d("conc", categories.toString());
        for (Category c : categories) {
            if ((plCat = myContains(budget.getCategories(), c)) == null) {
                tempPlannedCategory = new TempPlannedCategory(c.getIdCategory(), 0);
            } else {
                tempPlannedCategory = new TempPlannedCategory(plCat.getCategory().getIdCategory(), plCat.getMoney());
            }
            list.add(tempPlannedCategory);
        }
        Log.d("conc", String.valueOf(list.isEmpty()));
        return list;
    }*/

    private List<PlannedCategory> concatenateLists(List<Category> categories,
                                                       Budget budget) {
        List<PlannedCategory> list = new ArrayList<>();
        PlannedCategory plCat;
        for (Category c : categories) {
            if ((plCat = myContains(budget.getCategories(), c)) == null) {
                plCat = new PlannedCategory(c, 0,0);
            }
            list.add(plCat);
        }
        return list;
    }

    private Budget findBudget(List<Budget> result) {
        Integer maxMonth = -1;
        Budget lastBudget = new Budget();
        Integer curNumMonth;
        for (Budget b : result) {
            if ((curNumMonth = b.getDate().getNumberOfMonth()) > maxMonth) {
                maxMonth = curNumMonth;
                lastBudget = b;
            }
        }
        return lastBudget;
    }

    private void loadCategories() {
        Log.d("CATEG ", "start");
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
        for (PlannedCategory c : categories) {
            if (c.getCategory().getName().equals(category.getName())) {
                return c;
            }
        }
        return null;
    }

    //==========================================================

    public void onConfirmBudget(List<PlannedCategory> categoryList) {
        isExsistBudget(categoryList);
    }


    /*private void loadBudgets() {
        interactor.loadBudgets(new Carry<List<Budget>>() {
            @Override
            public void onSuccess(List<Budget> result) {
                if (result.isEmpty()) {
                    Log.d("BUDGET", "----------> empty");
                    loadCategories();
                } else {
                    Budget budget = findBudget(result);
                    curBudgetId = budget.getIdBudget();
                    view.showBudget(budget.getCategories());
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d("BUDGET", "----------> failure");
                Log.d("BUDGET", "----------> " + throwable.getMessage());
                view.showError(throwable.getMessage());
            }
        });
    }*/

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
