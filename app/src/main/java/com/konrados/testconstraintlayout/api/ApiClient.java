package com.konrados.testconstraintlayout.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;

    public static AuthApi getAuthApi()
    {
        if (retrofit == null)
        {
            HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
            logger.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/") // emulator; na fizycznym telefonie wpisz IP kompa
                    .addConverterFactory(ScalarsConverterFactory.create()) // <- najpierw scalars
                    .addConverterFactory(GsonConverterFactory.create())    // potem JSON
                    .client(client)
                    .build();
        }
        return retrofit.create(AuthApi.class);
    }

}
