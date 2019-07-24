package ru.ftc.android.shifttemple.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitProvider {

    private static final String BASE_URL = "http://192.168.43.149:10000/api/";
    //private static final String BASE_URL = "http://87bb7212.ngrok.io/api/";
    //private static final String BASE_URL = "http://192.168.31.172:10000/api/";

    private final Retrofit retrofit;

    public RetrofitProvider() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(createClient())
                .build();
    }

    private OkHttpClient createClient() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient builder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logInterceptor)
                .build();

        return builder;

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}