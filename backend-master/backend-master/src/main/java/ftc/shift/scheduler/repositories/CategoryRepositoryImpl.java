package ftc.shift.scheduler.repositories;

import ftc.shift.scheduler.db.CategoryEntity;
import ftc.shift.scheduler.db.HibernateSessionFactory;
import ftc.shift.scheduler.models.Category;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private Map<String, Category> categories;

    public CategoryRepositoryImpl() {
        //categories = new HashMap<>();
    }

    @Override
    public Category fetchCategory(String idCategory) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();
        Query query = session.createQuery("SELECT C FROM CategoryEntity C WHERE C.idCategory =: param");
        query.setParameter("param", Integer.parseInt(idCategory));
        List<CategoryEntity> categories = query.list();

        return new Category();
    }

    @Override
    public Category updateCategory(Category category) {

        categories.put(category.getIdCategory(), category);

        return category;
    }

    @Override
    public boolean deleteCategory(String idCategory) {

        categories.remove(idCategory);

        return !categories.containsKey(idCategory);
    }

    @Override
    public Category createCategory(Category category) {
        //System.out.println("Creating category...");
        category.setIdCategory(String.valueOf(category.hashCode()));
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setIdCategory(Integer.parseInt(category.getIdCategory()));
        categoryEntity.setName(category.getName());

        /*BudgetEntity budgetEntity = new BudgetEntity();
        budgetEntity.setIdCategory(Integer.parseInt(category.getIdCategory()));
        budgetEntity.setPlanned(0);
        budgetEntity.setSpending(0);
        java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        budgetEntity.setDate(now);
        budgetEntity.setIdBudget(budgetEntity.hashCode());
        session.save(budgetEntity);*/

        session.save(categoryEntity);

        session.getTransaction().commit();

        return category;
    }

    @Override
    public Collection<Category> getAllCategories() {
        System.out.println("Getting all categories...");
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        Query query = session.createQuery("SELECT C FROM CategoryEntity C");

        List<CategoryEntity> categories = query.list();

        Collection<Category> result = new ArrayList<>();

        categories.forEach(categoryEntity -> {
            Category c = new Category();
            c.setIdCategory(String.valueOf(categoryEntity.getIdCategory()));
            System.out.println(categoryEntity.getName());
            c.setName(categoryEntity.getName());
            result.add(c);
        });
        return result;
    }
}