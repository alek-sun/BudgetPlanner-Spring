package ru.ftc.android.shifttemple.features.books.presentation.planner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.books.domain.model.PlannedCategory;

final class StatAdapter extends RecyclerView.Adapter<StatAdapter.CategoryListHolder> {

    private final List<PlannedCategory> categories = new ArrayList<>();
    private final LayoutInflater inflater;

    StatAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoryListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.book_item, parent, false);
        //for 1 cell
        return new CategoryListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListHolder holder, int position) {
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

    class CategoryListHolder extends RecyclerView.ViewHolder {

        private final TextView categoryName;
        private final TextView spendedMoney;

        CategoryListHolder(View view) {
            super(view);
            categoryName = view.findViewById(R.id.category_name);
            spendedMoney = view.findViewById(R.id.category_spended);
        }

        void bind(final PlannedCategory category) {
            categoryName.setText(category.getCategory().getName());
            String str = category.getSpending() + " / " + category.getMoney();
            spendedMoney.setText(str);
        }
    }

}
