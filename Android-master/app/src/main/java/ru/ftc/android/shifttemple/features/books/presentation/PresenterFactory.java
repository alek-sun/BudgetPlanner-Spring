package ru.ftc.android.shifttemple.features.books.presentation;

import android.content.Context;

import ru.ftc.android.shifttemple.App;
import ru.ftc.android.shifttemple.features.books.data.PlannerApi;
import ru.ftc.android.shifttemple.features.books.data.PlannerDataSource;
import ru.ftc.android.shifttemple.features.books.data.PlannerDataSourceImpl;
import ru.ftc.android.shifttemple.features.books.data.BooksRepository;
import ru.ftc.android.shifttemple.features.books.data.BooksRepositoryImpl;
import ru.ftc.android.shifttemple.features.books.domain.CategoryInteractor;
import ru.ftc.android.shifttemple.features.books.domain.CategoryInteractorImpl;
import ru.ftc.android.shifttemple.features.books.presentation.ListOfTransaction.TransactionListActivity;
import ru.ftc.android.shifttemple.features.books.presentation.ListOfTransaction.TransactionListPresenter;
import ru.ftc.android.shifttemple.features.books.presentation.Transaction.CreateTransactionActivity;
import ru.ftc.android.shifttemple.features.books.presentation.Transaction.CreateTransactionPresenter;
import ru.ftc.android.shifttemple.features.books.presentation.cat_name.CategoryNamePresenter;
import ru.ftc.android.shifttemple.features.books.presentation.fill_budget.BudgetPresenter;
import ru.ftc.android.shifttemple.features.books.presentation.planner.CategoryListPresenter;

public final class PresenterFactory {

    public static CategoryListPresenter createListPresenter(Context context) {
        final PlannerApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(PlannerApi.class);

        final PlannerDataSource dataSource = new PlannerDataSourceImpl(api);
        final BooksRepository repository = new BooksRepositoryImpl(dataSource);
        final CategoryInteractor interactor = new CategoryInteractorImpl(repository);

        return new CategoryListPresenter(interactor);
    }

    public static CategoryNamePresenter createCatNamePresenter(Context context) {
        final PlannerApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(PlannerApi.class);

        final PlannerDataSource dataSource = new PlannerDataSourceImpl(api);
        final BooksRepository repository = new BooksRepositoryImpl(dataSource);
        final CategoryInteractor interactor = new CategoryInteractorImpl(repository);

        return new CategoryNamePresenter(interactor);
    }

    public static BudgetPresenter createBudgetPresenter(Context context) {
        final PlannerApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(PlannerApi.class);

        final PlannerDataSource dataSource = new PlannerDataSourceImpl(api);
        final BooksRepository repository = new BooksRepositoryImpl(dataSource);
        final CategoryInteractor interactor = new CategoryInteractorImpl(repository);

        return new BudgetPresenter(interactor);
    }

    public static CreateTransactionPresenter createTransactionPresenter(Context context, CreateTransactionActivity activity) {
        final PlannerApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(PlannerApi.class);

        final PlannerDataSource dataSource = new PlannerDataSourceImpl(api);
        final BooksRepository repository = new BooksRepositoryImpl(dataSource);
        final CategoryInteractor interactor = new CategoryInteractorImpl(repository);

        return new CreateTransactionPresenter(interactor, activity);
    }

    public static TransactionListPresenter createTransactionListPresenter(TransactionListActivity context) {
        final PlannerApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(PlannerApi.class);

        final PlannerDataSource dataSource = new PlannerDataSourceImpl(api);
        final BooksRepository repository = new BooksRepositoryImpl(dataSource);
        final CategoryInteractor interactor = new CategoryInteractorImpl(repository);

        return new TransactionListPresenter(interactor);
    }
}