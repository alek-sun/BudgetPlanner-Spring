package ru.ftc.android.shifttemple.features.books.presentation.ListOfTransaction;

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
import ru.ftc.android.shifttemple.features.books.domain.model.Transaction;

final class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.CategoryListHolder> {

    private final List<Transaction> transactions = new ArrayList<>();
    private final LayoutInflater inflater;

    TransactionListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoryListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.transaction_item, parent, false);
        //for 1 cell
        return new CategoryListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListHolder holder, int position) {
        holder.bind(transactions.get(position));
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions.clear();
        this.transactions.addAll(transactions);
        notifyDataSetChanged();
    }

    class CategoryListHolder extends RecyclerView.ViewHolder {

        private final TextView transactionName;
        private final TextView spendedMoney;

        CategoryListHolder(View view) {
            super(view);
            transactionName = view.findViewById(R.id.transaction_name);
            spendedMoney = view.findViewById(R.id.transaction_spended);
        }

        void bind(final Transaction transaction) {
            if (transaction!=null) {
                transactionName.setText(transaction.getText());
                String str = String.valueOf(transaction.getSpending());
                spendedMoney.setText(str);
            }
        }
    }

}
