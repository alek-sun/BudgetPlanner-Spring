package ru.ftc.android.shifttemple.features.books.presentation.cat_name;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.books.domain.CategoryInteractor;
import ru.ftc.android.shifttemple.features.books.domain.model.Category;
import ru.ftc.android.shifttemple.network.Carry;

public class CategoryNamePresenter extends MvpPresenter<CatNameView> {
    private final CategoryInteractor interactor;

    public CategoryNamePresenter(CategoryInteractor interactor) {
        this.interactor = interactor;
    }


    public void onCreateCategory(String name) {

        Category category = new Category(name);
        interactor.createCategory(category, new Carry<Category>() {
            @Override
            public void onSuccess(Category result) {
                view.onCategoryCreated();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }
}
