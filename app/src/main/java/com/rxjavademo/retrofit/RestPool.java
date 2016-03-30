package com.rxjavademo.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**
 * Created by Android Studio
 * Project：MatchLayout
 * Author：Jiafujie
 * Email：jfjie2013@163.com
 * Date：2016/1/25.
 */
public class RestPool {
    // http://www.langtianhealth.com:20081/v2/system/main?city=%E5%8C%97%E4%BA%AC
    private static String PAY_URL = "www.langtianhealth.com";


    public GitHubService init() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(logging);
      /*  client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                long t1 = System.nanoTime();
                Log.d("Retrofit", String.format("Sending request %s on %s%n%s",
                        request.url(), chain.connection(), request.headers()));

                Response response = chain.proceed(request);

                long t2 = System.nanoTime();
                Log.d("Retrofit", String.format("Received response for %s in %.1fms%n%s",
                        response.request().url(), (t2 - t1) / 1e6d, response.headers(),response.body()));

                return response;
            }
        });*/
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.langtianhealth.com:20081/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        return service;
    }
}
