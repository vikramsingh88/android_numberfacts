package com.vikram.numbersfact.request;

import com.vikram.numbersfact.dataservice.AppDataClient;
import com.vikram.numbersfact.dataservice.IAppDataAPIs;
import com.vikram.numbersfact.dataservice.RetroResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by M1032130 on 10/27/2017.
 */

public class AdvanceRequest<T> {

    private final AppDataClient.OnDataReceived mCallBack;
    IAppDataAPIs mClient;
    AppDataClient mService;
    String category;
    String number;

    public AdvanceRequest(String number, String category, AppDataClient.OnDataReceived<T> callback) {
        mClient = AppDataClient.getClient();
        mService = AppDataClient.getService();
        this.number = number;
        this.category = category;
        mCallBack = callback;
    }

    public void callService() {
        Call<RetroResponse> call = null;
        if (number.contains("/")) {
            call = mClient.getSpecificDateFacts(number.split("/")[0], number.split("/")[1], category);
        } else {
            call = mClient.getSpecificNumberFacts(number, category);
        }

        call.enqueue(new Callback<RetroResponse>() {
            @Override
            public void onResponse(Call<RetroResponse> call,
                                   Response<RetroResponse> response) {
                mService.onResponse(response, mCallBack);
            }

            @Override
            public void onFailure(Call<RetroResponse> call, Throwable t) {
                mCallBack.onDataFailure(t.getMessage());
            }
        });
    }


}