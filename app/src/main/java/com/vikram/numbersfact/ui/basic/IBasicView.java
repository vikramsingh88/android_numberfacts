package com.vikram.numbersfact.ui.basic;

import com.vikram.numbersfact.dataservice.RetroResponse;

/**
 * Created by M1032130 on 10/26/2017.
 */

public interface IBasicView {
    void showProgressBar();
    void hideProgressBar();
    void onSuccess(RetroResponse response);
    void onFailure(String error);
}
