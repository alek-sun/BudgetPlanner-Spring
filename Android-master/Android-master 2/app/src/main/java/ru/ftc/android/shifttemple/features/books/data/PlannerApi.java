package ru.ftc.android.shifttemple.features.books.data;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.ftc.android.shifttemple.features.books.domain.model.Budget;
import ru.ftc.android.shifttemple.features.books.domain.model.Category;
import ru.ftc.android.shifttemple.features.books.domain.model.PlannedCategory;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.books.domain.model.TempPlannedCategory;
import ru.ftc.android.shifttemple.features.books.domain.model.Transaction;
import ru.ftc.android.shifttemple.features.books.domain.model.Wrapper;

public interface PlannerApi {

    //0
    @GET("category")
    Call<Wrapper<List<Category>>> getCategoryList();
    //arg for onSuccess

    //1
    @POST("category")
    Call<Wrapper<Category>> createCategory(@Body Category category);

    //WRAPPER!!!!!!!!!!!!!!!!!!!!!! <--------------------------------------------
    //2 new budget (planning category)
    @POST("budget") // change
    Call<Wrapper<Budget>> firstBudget(@Body List<TempPlannedCategory> budget);

    //@GET("budget") // change
    //Call<Category> createCategory(@Body Category category);

    @GET("budget") // change
    Call<Wrapper<List<Budget>>> loadBudgets ();

    //@DELETE("budget/{id_budget}") // change
    //Call<Category> createCategory(@Body Category category);

    @DELETE("category/{id_category}")
    Call<Success> deleteCategory(@Path("id") String id);

    //@POST("category/{id_category}") // change
    //Call<Category> createCategory(@Body Category category);

    //7 change budget
    @POST("budget/{id_budget}")
    Call<Wrapper<Budget>> changeBudget(@Body Budget budget, @Path("id_budget") String id);

    //8
    //@POST("budget/{id_budget}/transaction") // change
    //Call<Wrapper<Category>> addTransaction(@Body Transaction transaction, @Path("id_budget") String id);

    @POST("transaction")
    Call<Wrapper<Transaction>> createTransaction(@Body Transaction transaction);

    //@GET("budget/{id_budget}/transaction") // change
    //Call<Category> createCategory(@Body Category category);

    //@DELETE("transaction/{id_transaction}")
    //Call<Success> deleteCategory(@Path("id") String id);
}
