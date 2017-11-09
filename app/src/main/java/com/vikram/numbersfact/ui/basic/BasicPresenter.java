package com.vikram.numbersfact.ui.basic;

import com.vikram.numbersfact.dataservice.RetroResponse;

public class BasicPresenter implements IBasicPresenter, IBasicModel.OnBasicRequestFinishedListener{
    private IBasicView basicView;
    private IBasicModel basicModel;

    public BasicPresenter(IBasicView basicView) {
        this.basicView = basicView;
        basicModel = new BasicModel();
    }


    @Override
    public void getNumberFacts(String category) {
        if (basicView != null) {
            basicView.showProgressBar();
            basicModel.getNumberDetails(category, this);
        }
    }

    @Override
    public void onSuccess(RetroResponse response) {
        if (basicView != null) {
            basicView.hideProgressBar();
            basicView.onSuccess(response);
        }
    }

    @Override
    public void onFailure(String response) {
        if (basicView != null) {
            basicView.hideProgressBar();
            basicView.onFailure(response);
        }
    }

    @Override
    public void onDestroy() {
        basicView = null;
    }
}
