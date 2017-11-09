package com.vikram.numbersfact.ui.advance;

import com.vikram.numbersfact.dataservice.RetroResponse;

/**
 * Created by M1032130 on 10/27/2017.
 */

public interface IAdvanceView {
    void showProgress();
    void hideProgress();
    void onSuccess(RetroResponse response);
    void onFailure(String error);
}
