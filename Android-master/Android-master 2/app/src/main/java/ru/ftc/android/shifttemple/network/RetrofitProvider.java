package ru.ftc.android.shifttemple.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitProvider {

    //private static final String BASE_URL = "http://35.243.248.118:9998/api/";
    //private static final String BASE_URL = "http://87bb7212.ngrok.io/api/";
    private static final String BASE_URL = "http://192.168.31.172:10000/api/";

    private final Retrofit retrofit;

    public RetrofitProvider() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(createClient())
                .build();
    }

    private OkHttpClient createClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.addInterceptor(logInterceptor);

        return builder.build();

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}