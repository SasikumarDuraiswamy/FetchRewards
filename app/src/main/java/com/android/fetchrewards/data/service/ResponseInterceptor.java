package com.android.fetchrewards.data.service;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * ResponseInterceptor class to intercept the user API call
 */
public class ResponseInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        Response response = chain.proceed(request);
        String rawData = response.body().string();
        if(isJson(rawData, JsonArray.class)) {
            //Receiving response data is array to avoid the gson parser to convert from array to object
            rawData = "{ \"user\":" + rawData + "}";
        }
        // Re-create the response before returning it because body can be read only once
        return response.newBuilder()
                .body( ResponseBody.create(response.body().contentType(), rawData)).build();
    }

    private boolean isJson(String data, Class jsonElement){
        try{
            (new Gson()).fromJson(data, jsonElement);
            return true;
        }catch (Exception exception){}
        return false;
    }

}
