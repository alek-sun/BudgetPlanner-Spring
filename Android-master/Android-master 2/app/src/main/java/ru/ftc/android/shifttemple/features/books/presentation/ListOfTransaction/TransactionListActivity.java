package ru.ftc.android.shifttemple.features.books.presentation.ListOfTransaction;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.books.domain.model.Category;
import ru.ftc.android.shifttemple.features.books.domain.model.PlannedCategory;
import ru.ftc.android.shifttemple.features.books.domain.model.Transaction;
import ru.ftc.android.shifttemple.features.books.presentation.PresenterFactory;

public final class TransactionListActivity extends BaseActivity implements TransactionListView {

    private RecyclerView recyclerView;
    private TransactionListAdapter adapter;
    private ImageView noElemImg;

    public String getCategoryId() {
        return categoryId;
    }

    private String categoryId;

    private TransactionListPresenter presenter;
    private TextView noTransactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_transaction);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.transaction_list);
        noTransactions = findViewById(R.id.no_transactions);
        noElemImg = findViewById(R.id.no_el_img);

        noElemImg.setVisibility(View.GONE);
        noTransactions.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);

        categoryId = getIntent().getStringExtra("categoryId");

        adapter = new TransactionListAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<PlannedCategory> createEmptyPlannedCategories(List<Category> result) {
        final ArrayList<PlannedCategory> categories = new ArrayList<>();
        for (Category cat : result
        ) {
            categories.add(new PlannedCategory(cat, 0, 0));
        }
        return categories;
    }

   /* private List<Category> getCategories(Collection<PlannedCategory> categories) {
        ArrayList<Category> c = new ArrayList<>();
        for (PlannedCategory plCat : categories
        ) {
            c.add(plCat.getCategory());
        }
        return c;
    }*/


    @Override
    public void showTranactionList(List<Transaction> list) {
        noElemImg.setVisibility(View.GONE);
        noTransactions.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.setTransactions(list);
    }

    @Override
    public void showEmptyTransactions() {
        Log.d("AAAAAAAAAAAAAAAAAAAAA", "jgj");
        recyclerView.setVisibility(View.GONE);
        noTransactions.setVisibility(View.VISIBLE);
        noElemImg.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected MvpPresenter<TransactionListView> getPresenter() {
        presenter = PresenterFactory.createTransactionListPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }
}
