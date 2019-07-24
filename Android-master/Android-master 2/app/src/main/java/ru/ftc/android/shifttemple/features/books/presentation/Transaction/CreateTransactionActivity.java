package ru.ftc.android.shifttemple.features.books.presentation.Transaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.books.presentation.PresenterFactory;

public class CreateTransactionActivity extends BaseActivity implements CreateTransactionView {
    private CreateTransactionPresenter presenter;
    private List<String> catNames;

    private EditText cost;
    private EditText description;
    private Spinner spinner;

    public void onClickTransactionCreated(View view) {
        presenter.onCreateTransaction(description.getText().toString(),
                Integer.valueOf(cost.getText().toString()),
                spinner.getSelectedItem().toString());
    }

    @Override
    protected MvpPresenter<CreateTransactionView> getPresenter() {
        presenter = PresenterFactory.createTransactionPresenter(this, this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    public void onTransactionCreated() {
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    void setCatName(List<String> list){
        catNames = list;
    }

    void showList(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, catNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = findViewById(R.id.spinner_of_category_list);
        spinner.setAdapter(adapter);

        spinner.setPrompt("Категория");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_transaction);

        cost = findViewById(R.id.cost);
        description = findViewById(R.id.description_of_buy);
    }


}