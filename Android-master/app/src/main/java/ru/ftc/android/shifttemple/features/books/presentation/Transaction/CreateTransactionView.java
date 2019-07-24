package ru.ftc.android.shifttemple.features.books.presentation.Transaction;

import ru.ftc.android.shifttemple.features.MvpView;

public interface CreateTransactionView extends MvpView {
    void onTransactionCreated();

    void showError(String message);
}
