package com.example.ladmusiciankim.searchimage.data.remote;

import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class ImageService {
    private static String END_POINT = "https://apis.daum.net";

    private static RestApiClient restApiClient;

    public static RestApiClient getRestApiClient() {
        if (restApiClient == null) {
            restApiClient = createRetrofit().create(RestApiClient.class);
        }
        return restApiClient;
    }

    @NonNull
    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build();
    }

    @NonNull
    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000, TimeUnit.MILLISECONDS);


        if (BuildConfig.DEBUG) {
            builder.addInterceptor(createHttpLoggingInterceptor());
            builder.addNetworkInterceptor(createStethoNetworkInterceptor());
        }

        return builder.build();
    }

    @NonNull
    private static Interceptor createHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message ->
                Log.i("OkHttpLogging", "message: " + message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @NonNull
    private static StethoInterceptor createStethoNetworkInterceptor() {
        return new StethoInterceptor();
    }
}
