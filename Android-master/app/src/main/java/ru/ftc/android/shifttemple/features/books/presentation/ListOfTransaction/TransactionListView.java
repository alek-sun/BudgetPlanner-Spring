package ru.ftc.android.shifttemple.features.books.presentation.ListOfTransaction;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.books.domain.model.Category;
import ru.ftc.android.shifttemple.features.books.domain.model.PlannedCategory;
import ru.ftc.android.shifttemple.features.books.domain.model.Transaction;


interface TransactionListView extends MvpView {

    void showTranactionList(List<Transaction> list);

    void showEmptyTransactions();

    String getCategoryId();

    //void showCategoryStat(List<PlannedCategory> list);

    void showError(String message);

}
