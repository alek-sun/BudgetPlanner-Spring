package ru.ftc.android.shifttemple.features.books.presentation.fill_budget;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.books.domain.model.PlannedCategory;
import ru.ftc.android.shifttemple.features.books.presentation.PresenterFactory;

public class FillBudgetActivity extends BaseActivity implements BudgetView {
    private BudgetPresenter presenter;
    private RecyclerView recyclerView;
    private BudgetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_budget); // xml file nam
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.fill_budget_recycle_view);
        adapter = new BudgetAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onClickConfirmBudget(View view) {
        //call presenter's method(send request to server)
        List<PlannedCategory> categoryList = adapter.getCategories();
        presenter.onConfirmBudget(categoryList);
    }

    @Override
    public void onBudgetConfirmed() {
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showBudget(List<PlannedCategory> budget) {
        adapter.setCategories(new ArrayList(budget));
    }

    @Override
    protected MvpPresenter<BudgetView> getPresenter() {
        presenter = PresenterFactory.createBudgetPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

}
