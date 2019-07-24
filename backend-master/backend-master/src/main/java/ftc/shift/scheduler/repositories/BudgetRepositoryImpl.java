package ftc.shift.scheduler.repositories;

import ftc.shift.scheduler.db.BudgetEntity;
import ftc.shift.scheduler.db.CategoryEntity;
import ftc.shift.scheduler.db.HibernateSessionFactory;
import ftc.shift.scheduler.db.TransactionsEntity;
import ftc.shift.scheduler.models.*;
import ftc.shift.scheduler.models.Date;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BudgetRepositoryImpl implements BudgetRepository{

    private Map<String, Budget> budgets;

    private static final Map<Integer, String> months;

    static {
        months = new HashMap<>();

        months.put(Calendar.JANUARY, "Январь");
        months.put(Calendar.FEBRUARY, "Февраль");
        months.put(Calendar.MARCH, "Март");
        months.put(Calendar.APRIL, "Апрель");
        months.put(Calendar.MAY, "Май");
        months.put(Calendar.JUNE, "Июнь");
        months.put(Calendar.JULY, "Июль");
        months.put(Calendar.AUGUST, "Август");
        months.put(Calendar.SEPTEMBER, "Сентябрь");
        months.put(Calendar.OCTOBER, "Октябрь");
        months.put(Calendar.NOVEMBER, "Ноябрь");
        months.put(Calendar.DECEMBER, "Декабрь");
    }

    public BudgetRepositoryImpl() {

        budgets = new HashMap<>();
    }

    @Override
    public Budget fetchBudget(String idBudget) {

        return budgets.get(idBudget);
    }

    @Override
    public Budget updateBudget(Budget budget) {
        System.out.println("Budget updating...");
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        budget.getCategories().forEach(plannedCategory -> {
            Query query = session.createQuery(
                    "UPDATE BudgetEntity B " +
                            "SET planned =: plannedParam " +
                            "WHERE B.idCategory =: categoryId");
            //query.setParameter("param",  Integer.parseInt(budget.getIdBudget()));
            query.setParameter("plannedParam",  plannedCategory.getMoney());
            query.setParameter("categoryId", Integer.parseInt(plannedCategory.getCategory().getIdCategory()));
            query.executeUpdate();
            query = session.createQuery(
                    "UPDATE BudgetEntity B " +
                            "SET spending =: spendingParam " +
                            "WHERE B.idCategory =: categoryId");
            //query.setParameter("param",  Integer.parseInt(budget.getIdBudget()));
            query.setParameter("spendingParam",  plannedCategory.getSpending());
            query.setParameter("categoryId", Integer.parseInt(plannedCategory.getCategory().getIdCategory()));
            query.executeUpdate();
        });

        session.getTransaction().commit();

        return budget;
    }

    @Override
    public boolean deleteBudget(String idBudget) {

        budgets.remove(idBudget);

        return !budgets.containsKey(idBudget);
    }

    @Override
    public Budget createBudget(Budget budget) {
        System.out.println("Create budget");
        budget.setIdBudget(String.valueOf(budget.hashCode()));

        java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        budget.setDate(new Date(String.valueOf(now.getDate()), now.getMonth()));
        budget.setTransactions(new ArrayList<>(0));

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        budget.getCategories().forEach(category -> {
            BudgetEntity budgetEntity = new BudgetEntity();
            budgetEntity.setIdBudget(Integer.parseInt(budget.getIdBudget()));
            budgetEntity.setDate(now);
            System.out.println(budget.getIdBudget() + " " + category.getCategory().getName() +
                    " " + budget.getCategories().get(0).getSpending() + " " + budget.getCategories().get(0).getMoney());
            budgetEntity.setIdCategory(Integer.parseInt(category.getCategory().getIdCategory()));
            budgetEntity.setPlanned(category.getMoney());
            budgetEntity.setSpending(category.getSpending()); // 0
            session.save(budgetEntity);
        });

        session.getTransaction().commit();
        return budget;
    }

    @Override
    public Collection<Budget> getAllBudgets() {
        System.out.println("All budgets");
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        //
        Query queryBudgetCatId = session.createQuery("SELECT B.idCategory FROM BudgetEntity B" );//+ " WHERE TO_DAYS(CURRENT_DATE) - TO_DAYS(B.date) <= 31");
        List<Integer> budgetCategoryIds = queryBudgetCatId.list();

        Query categoriesQuery = session.createQuery("SELECT C.idCategory FROM CategoryEntity C");
        List<Integer> catEntities = categoriesQuery.list();

        for (Integer c : catEntities
             ) {
            if (!budgetCategoryIds.contains(c)){
                BudgetEntity budgetEntity = new BudgetEntity();
                budgetEntity.setIdBudget(c.hashCode());
                budgetEntity.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
                budgetEntity.setIdCategory(c);
                budgetEntity.setPlanned(0);
                budgetEntity.setSpending(0);
                session.save(budgetEntity);
            }
        }
        session.getTransaction().commit();

        //get id budgets
        Query query = session.createQuery("SELECT B.idBudget FROM BudgetEntity B" +
                " group by idBudget");
        List<Integer> budgetIds = query.list();

        ArrayList<Budget> result = new ArrayList<>();

        budgetIds.forEach(id -> {
            Budget b = new Budget();
            b.setIdBudget(String.valueOf(id));

            Query q = session.createQuery("SELECT B FROM BudgetEntity B" +
                    " WHERE idBudget =: idParam");
            q.setParameter("idParam", id);
            List<BudgetEntity> budgetEntities = q.list();
            budgetEntities.forEach(budgetEntity -> {
                PlannedCategory plannedCategory = new PlannedCategory();
                plannedCategory.setMoney(budgetEntity.getPlanned());
                plannedCategory.setSpending(budgetEntity.getSpending());
                Category category = new Category();
                Query categoryQuery = session.createQuery("SELECT C FROM CategoryEntity C" +
                        " WHERE idCategory =: idParam");
                categoryQuery.setParameter("idParam", budgetEntity.getIdCategory());
                List<CategoryEntity> categoryEntities = categoryQuery.list();
                category.setName(categoryEntities.get(0).getName());
                category.setIdCategory(String.valueOf(
                        budgetEntity.getIdCategory()));
                plannedCategory.setCategory(category);
                b.setDate(new Date(String.valueOf(budgetEntity.getDate().getDate()), budgetEntity.getDate().getMonth()));
                b.getCategories().add(plannedCategory);
            });

            q = session.createQuery("SELECT T FROM TransactionsEntity T" +
                    " WHERE idBudget =: idParam");
            q.setParameter("idParam", id);
            List<TransactionsEntity> transactionsEntities = q.list();
            transactionsEntities.forEach(transactionsEntity -> {
                Transaction t = new Transaction();
                t.setIdBudget(String.valueOf(transactionsEntity.getIdBudget()));
                t.setIdCategory(String.valueOf(transactionsEntity.getIdCategory()));
                t.setIdTransaction(String.valueOf(transactionsEntity.getIdTransactions()));
                t.setSpending(transactionsEntity.getCost());
                t.setText(transactionsEntity.getName());
                b.getTransactions().add(t);
            });
            result.add(b);
        });

        return result;
    }
}