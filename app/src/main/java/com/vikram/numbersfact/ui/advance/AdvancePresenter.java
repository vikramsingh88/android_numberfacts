package com.vikram.numbersfact.ui.advance;

import com.vikram.numbersfact.dataservice.RetroResponse;

/**
 * Created by M1032130 on 10/27/2017.
 */

public class AdvancePresenter implements IAdvancePresenter, IAdvanceModel.OnAdvanceRequestFinishedListener {
    private IAdvanceView advanceView;
    private IAdvanceModel advanceModel;

    public AdvancePresenter(IAdvanceView advanceView) {
        this.advanceView = advanceView;
        advanceModel = new AdvanceModel();
    }

    @Override
    public void getSpecificNumberFacts(String number, String category) {
        if (advanceView != null) {
            advanceView.showProgress();
            advanceModel.getSpecificNumberDetails(number, category, this);
        }
    }

    @Override
    public void onSuccess(RetroResponse response) {
        if (advanceView != null) {
            advanceView.hideProgress();
            advanceView.onSuccess(response);
        }
    }

    @Override
    public void onFailure(String response) {
        if (advanceView != null) {
            advanceView.hideProgress();
            advanceView.onFailure(response);
        }
    }

    @Override
    public void onDestroy() {
        advanceView = null;
    }
}
