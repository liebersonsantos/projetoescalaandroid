package com.example.lieberson.projetoescalaandroid.network;

import android.content.Context;

import com.example.lieberson.projetoescalaandroid.helper.Constantes;
import com.example.lieberson.projetoescalaandroid.helper.Preferencias;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static Retrofit retrofit;

    public static Api getInstance() {


        if (retrofit == null) {
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor()) //vejo o que mando e recebo
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constantes.URL_BASE)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }


        return retrofit.create(Api.class);
    }

    public static Api getInstanceLogin(Context context) {


        if (retrofit == null) {
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor()) //vejo o que mando e recebo
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(chain -> {
                        Request request = chain.request();

                        Preferencias preferencias = new Preferencias(context);
                        Request.Builder builder = request.newBuilder()
                                .addHeader("Accept", "application/json;versions=1")
                                .header("Authorization", Credentials.basic(preferencias.getEmail(), preferencias.getSenha()));

                        return chain.proceed(builder.build());
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constantes.URL_BASE)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }


        return retrofit.create(Api.class);
    }
}
