package com.vikram.numbersfact.dataservice;

/**
 * Created by M1032130 on 10/26/2017.
 */

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IAppDataAPIs {
    String CONTENT_TYPE = "Content-Type";
    String JSON_TYPE = "application/json";
    String BASE_URL = "http://numbersapi.com/"; // "http://numbersapi.com/random/{category}";

    @GET("random/{category}")
    Call<RetroResponse> getNumberFacts(@Path("category") String category);

    @GET("{random}/{category}")
    Call<RetroResponse> getSpecificNumberFacts(@Path("random") String random, @Path("category") String category);

    @GET("{month}/{date}/{category}")
    Call<RetroResponse> getSpecificDateFacts(@Path("month") String month,@Path("date") String date, @Path("category") String category);
}
