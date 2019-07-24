package ru.ftc.android.shifttemple.features.books.presentation.fill_budget;

import java.util.Collection;
import java.util.List;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.books.domain.model.PlannedCategory;
import ru.ftc.android.shifttemple.features.books.domain.model.TempPlannedCategory;

interface BudgetView extends MvpView {
    void onBudgetConfirmed();

    void showError(String message);

    void showBudget(List<PlannedCategory> budget);
}
