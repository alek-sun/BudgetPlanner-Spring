package ru.ftc.android.shifttemple.features.books.presentation.cat_name;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.books.presentation.PresenterFactory;

public class CatNameActivity extends BaseActivity implements CatNameView {
    private CategoryNamePresenter presenter;
    private EditText nameField;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_name); // xml file name
        initView();
    }

    public void onClickCategoryCreated(View view) {
        presenter.onCreateCategory(nameField.getText().toString());
    }

    private void initView() {
        nameField = findViewById(R.id.cat_name);
        createButton = findViewById(R.id.create_cat_but);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCreateCategory(nameField.getText().toString());
            }
        });
    }

    @Override
    protected MvpPresenter<CatNameView> getPresenter() {
        presenter = PresenterFactory.createCatNamePresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    public void onCategoryCreated() {
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
