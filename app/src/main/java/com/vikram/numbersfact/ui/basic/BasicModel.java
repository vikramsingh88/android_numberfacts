package com.vikram.numbersfact.ui.basic;

import android.util.Log;

import com.vikram.numbersfact.dataservice.AppDataClient;
import com.vikram.numbersfact.dataservice.RetroResponse;
import com.vikram.numbersfact.request.BasicRequest;

/**
 * Created by M1032130 on 10/26/2017.
 */

public class BasicModel implements IBasicModel {

    @Override
    public void getNumberDetails(final String category, final OnBasicRequestFinishedListener listener) {
        new BasicRequest(category, new AppDataClient
                .OnDataReceived<RetroResponse>() {

            @Override
            public void onDataSuccess(RetroResponse response) {
                Log.d("getNumberDetails", "onDataSuccess " + response.getText());
                listener.onSuccess(response);
            }

            @Override
            public void onDataFailure(String error) {
                Log.d("getNumberDetails", "onDataFailure " + error.toString());
                listener.onFailure(error);
            }
        }).callService();
    }
}
