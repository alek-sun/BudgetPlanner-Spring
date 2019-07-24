package ru.ftc.android.shifttemple.features.books.presentation.planner;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.books.domain.model.Category;
import ru.ftc.android.shifttemple.features.books.domain.model.PlannedCategory;


interface CategoryListView extends MvpView {

    void showCategoryList(List<Category> list);

    void showEmptyCategories();

    void showCategoryStat(List<PlannedCategory> list);

    void showError(String message);

}
