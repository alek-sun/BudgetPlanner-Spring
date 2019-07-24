package ru.ftc.android.shifttemple.features.books.presentation.planner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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
import ru.ftc.android.shifttemple.features.books.presentation.ListOfTransaction.TransactionListActivity;
import ru.ftc.android.shifttemple.features.books.presentation.Transaction.CreateTransactionActivity;
import ru.ftc.android.shifttemple.features.books.presentation.cat_name.CatNameActivity;
import ru.ftc.android.shifttemple.features.books.presentation.PresenterFactory;
import ru.ftc.android.shifttemple.features.books.presentation.fill_budget.FillBudgetActivity;

public final class PlannerActivity extends BaseActivity implements CategoryListView {

    private RecyclerView recyclerView;
    private Button addCategoryButton;
    private Button addBudgetButton;
    private Button addSpendButton;
    private PlannerAdapter adapter;
    private TextView noCategories;
    private ImageView noElemImg;


    private CategoryListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories); // xml file nam
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.category_list);
        addCategoryButton = findViewById(R.id.add_category_button);
        addBudgetButton = findViewById((R.id.add_budg_button));
        addSpendButton = findViewById((R.id.add_spend_button));
        noCategories = findViewById(R.id.no_cat_txt);
        noElemImg = findViewById(R.id.no_el_img);

        //categoryStat = findViewById(R.id.category_spended);


        //categoryStat.setVisibility(View.GONE);
        noCategories.setVisibility(View.GONE);
        noElemImg.setVisibility(View.GONE);
        addBudgetButton.setEnabled(false);
        addSpendButton.setEnabled(false);


        adapter = new PlannerAdapter(this , this::onCategorySelected);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
}

    public void onClickAddCategroy(View view) {
        Intent intent = new Intent(this, CatNameActivity.class);
        startActivity(intent);
    }

    public void onClickCreateBudget(View view) {
        Intent intent = new Intent(this, FillBudgetActivity.class);
        startActivity(intent);
    }

    public void onCategorySelected(PlannedCategory category) {
        Intent intent = new Intent(this, TransactionListActivity.class);
        StringBuilder id = new StringBuilder();
        //WARNING! govnocode : cycle dependency
        //never call this later
        presenter.setActivity(this);
        //
        presenter.loadCategoryId(category.getCategory().getName(), id, intent);
    }

    public void onClickCreateTransaction(View view) {
        Intent intent = new Intent(this, CreateTransactionActivity.class);
        startActivity(intent);
    }
    @Override
    public void showCategoryList(List<Category> list) {
        noCategories.setVisibility(View.GONE);
        noElemImg.setVisibility(View.GONE);
        addBudgetButton.setEnabled(true);
        addSpendButton.setEnabled(true);
        adapter.setCategories(createEmptyPlannedCategories(list));
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
    public void showEmptyCategories() {
        noCategories.setVisibility(View.VISIBLE);
        noElemImg.setVisibility(View.VISIBLE);
        addCategoryButton.setEnabled(true);
        addBudgetButton.setEnabled(false);
        addSpendButton.setEnabled(false);
    }

    @Override
    public void showCategoryStat(List<PlannedCategory> list) {
        //categoryStat.setVisibility(View.VISIBLE);
        addCategoryButton.setEnabled(true);
        addBudgetButton.setText("Изменить бюджет");
        addBudgetButton.setEnabled(true);
        addSpendButton.setEnabled(true);
        adapter.setCategories(list);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected MvpPresenter<CategoryListView> getPresenter() {
        presenter = PresenterFactory.createListPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }
}
