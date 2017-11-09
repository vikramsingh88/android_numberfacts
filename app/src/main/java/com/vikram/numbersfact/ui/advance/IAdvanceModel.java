package com.vikram.numbersfact.ui.advance;

import com.vikram.numbersfact.dataservice.RetroResponse;

/**
 * Created by M1032130 on 10/27/2017.
 */

public interface IAdvanceModel {
    interface OnAdvanceRequestFinishedListener {
        void onSuccess(RetroResponse response);
        void onFailure(String response);
    }

    void getSpecificNumberDetails(String number, String category, OnAdvanceRequestFinishedListener listener);
}
