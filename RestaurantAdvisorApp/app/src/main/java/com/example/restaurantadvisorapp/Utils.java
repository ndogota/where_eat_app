package com.example.restaurantadvisorapp;

import com.example.restaurantadvisorapp.entities.ApiError;
import com.example.restaurantadvisorapp.network.RetrofitBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;

class Utils {
    static ApiError convertErrors(ResponseBody response) {
        Converter<ResponseBody, ApiError> converter = RetrofitBuilder.getRetrofit().responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError apiError = null;

        try {
            apiError = converter.convert(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return apiError;
    }
}
