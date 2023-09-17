package com.example.lab2_iot_20200839.services;

import com.example.lab2_iot_20200839.dto.Profile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TypicodeServices {

    @GET("/api/")
        Call<Profile> getResult();
}
