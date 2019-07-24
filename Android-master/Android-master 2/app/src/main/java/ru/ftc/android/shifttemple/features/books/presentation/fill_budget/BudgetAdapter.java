package ru.ftc.android.shifttemple.features.books.presentation.fill_budget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.books.domain.model.PlannedCategory;

final class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.BudgetHolder> {

    private final List<PlannedCategory> categories = new ArrayList<>();
    private final LayoutInflater inflater;
    //private final SelectBookListener selectBookListener;

    BudgetAdapter(Context context/*, SelectBookListener selectBookListener*/) {
        inflater = LayoutInflater.from(context);
        //this.selectBookListener = selectBookListener;
    }

    @NonNull
    @Override
    public BudgetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.budget_item, parent, false);
        //for 1 cell
        return new BudgetHolder(itemView);//, selectBookListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetHolder holder, int position) {
        holder.bind(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(List<PlannedCategory> categoryList) {
        categories.clear();
        categories.addAll(categoryList);
        notifyDataSetChanged();
    }

    class BudgetHolder extends RecyclerView.ViewHolder {

        private final TextView categoryName;
        private final EditText categorySum;
        //private final SelectBookListener selectBookListener;

        BudgetHolder(View view/*, SelectBookListener selectBookListener*/) {
            super(view);
            categoryName = view.findViewById(R.id.budg_item_name);
            categorySum = view.findViewById(R.id.budg_item_sum);
        }

        void bind(final PlannedCategory category) {
            categoryName.setText(category.getCategory().getName());
            categorySum.setText(String.valueOf(category.getMoney()));
            categorySum.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.toString().equals("")){
                        return;
                    }
                    int sum = Integer.parseInt(s.toString());
                    category.setMoney(sum);
                }
            });

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectBookListener.onCategorySelect(category);
                }
            });*/

            /*itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    selectBookListener.onBookLongClick(category);
                    return true;
                }
            });*/

        }

    }

    List<PlannedCategory> getCategories(){
        return categories;
    }

    /*interface SelectBookListener {

        void onCategorySelect(Category category);
    }*/

}
