package ftc.shift.scheduler.repositories;

import ftc.shift.scheduler.db.HibernateSessionFactory;
import ftc.shift.scheduler.db.TransactionsEntity;
import ftc.shift.scheduler.models.Transaction;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private Map<String, Transaction> transactions;

    public TransactionRepositoryImpl() {

        transactions = new HashMap<>();
    }

    @Override
    public Transaction fetchTransaction(String idTransaction) {

        return transactions.get(idTransaction);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {

        transactions.put(transaction.getIdTransaction(), transaction);

        return transaction;
    }

    @Override
    public boolean deleteTransaction(String idTransaction) {

        transactions.remove(idTransaction);

        return !transactions.containsKey(idTransaction);
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        System.out.println("Creating transaction...");
        transaction.setIdTransaction(String.valueOf(transaction.hashCode()));
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        TransactionsEntity t = new TransactionsEntity();
        t.setIdTransactions(Integer.parseInt(transaction.getIdTransaction()));
        t.setIdCategory(Integer.parseInt(transaction.getIdCategory()));
        t.setCost(transaction.getSpending());
        t.setName(transaction.getText());
        t.setIdBudget(Integer.parseInt(transaction.getIdBudget()));
        t.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        session.save(t);

        session.getTransaction().commit();
        //HibernateSessionFactory.shutdown();

        return transaction;
    }

    @Override
    public Collection<Transaction> getAllTransactions() {

        return transactions.values();
    }
}