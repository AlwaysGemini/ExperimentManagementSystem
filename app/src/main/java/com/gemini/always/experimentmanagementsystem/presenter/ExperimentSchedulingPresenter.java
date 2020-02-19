package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.ExperimentSchedulingModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentSchedulingView;

import org.json.JSONObject;

public class ExperimentSchedulingPresenter extends BasePresenter<ExperimentSchedulingView> {
    private ExperimentSchedulingModel model;

    public ExperimentSchedulingPresenter() {
        this.model = new ExperimentSchedulingModel();
    }

    public void getFreeTimeData() {
        this.model.getFreeTimeData(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().getFreeTimeDataResult(isSuccess, responseJson);
            }
        });
    }
}
