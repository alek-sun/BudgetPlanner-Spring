package ru.ftc.android.shifttemple.features.books.presentation.cat_name;

import ru.ftc.android.shifttemple.features.MvpView;

public interface CatNameView extends MvpView {
    void onCategoryCreated();
    void showError(String message);
}
