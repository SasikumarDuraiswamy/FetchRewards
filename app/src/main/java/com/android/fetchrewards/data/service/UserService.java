package com.android.fetchrewards.data.service;

import com.android.fetchrewards.data.model.BaseResponse;
import com.android.fetchrewards.data.model.UserResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * UserService class is the instance of the Retrofit and it Single Instance class
 */
public class UserService {

    /**
     * Variable to hold the  application base URL
     */
    private static final String URL = "https://fetch-hiring.s3.amazonaws.com/";

    /**
     * Variable to hold the retrofit interface instance
     */
    private HiringApi mHiringApi;

    /**
     * Variable to hold the static userservice instane
     */
    private static UserService instance;


    /**
     * Method to create the user service single instance
     * @return - UserService
     */
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    /**
     * Constructor to build the Retrofit with the factory and intercept.
     */
    private UserService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ResponseInterceptor()).build();
        Retrofit mRetrofit = new Retrofit.Builder()
                .addConverterFactory(createGsonConverter(BaseResponse.class, new GsonDeserializer()))
                .baseUrl(URL)
                .client(client)
                .build();
        mHiringApi = mRetrofit.create(HiringApi.class);
    }

    /**
     * Mehtod to create the custom gson converter factory
     * @param type - Fetch Rewards API Base response
     * @param typeAdapter - Custom Converter
     * @return - Converter factory
     */
    private static Converter.Factory createGsonConverter(Class type, Object typeAdapter) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeHierarchyAdapter( type, typeAdapter );
        Gson gson = gsonBuilder.create();
        return GsonConverterFactory.create(gson);
    }

    /**
     * Method to get the hiring api retrofit instance
     * @return - Retrofit
     */
    public HiringApi getHiringApi() {
        return mHiringApi;
    }

    /**
     * Interface to set the retrofit api calls
     */
    public interface HiringApi {
        @GET("hiring.json") Call<UserResponse> getAllUser();
    }
}
