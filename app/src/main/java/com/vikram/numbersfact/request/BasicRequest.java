package com.vikram.numbersfact.request;

import com.vikram.numbersfact.dataservice.AppDataClient;
import com.vikram.numbersfact.dataservice.IAppDataAPIs;
import com.vikram.numbersfact.dataservice.RetroResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by M1032130 on 10/26/2017.
 */

public class BasicRequest<T> {

    private final AppDataClient.OnDataReceived mCallBack;
    IAppDataAPIs mClient;
    AppDataClient mService;
    String category;

    public BasicRequest(String category, AppDataClient.OnDataReceived<T> callback) {
        mClient = AppDataClient.getClient();
        mService = AppDataClient.getService();
        this.category = category;
        mCallBack = callback;
    }

    public void callService() {
        Call<RetroResponse> call = mClient.getNumberFacts(category);

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