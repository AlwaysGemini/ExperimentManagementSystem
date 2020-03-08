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

    public void getUnAllocationData() {
        this.model.getUnAllocationData(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetUnAllocationDataResult(isSuccess, responseJson);
            }
        });
    }

    public void insertData(String experiment_scheduling_id,
                           String instructor_id,
                           String laboratory_room_id,
                           String week,
                           String day,
                           String start_time,
                           String length) {
        this.model.insertData(experiment_scheduling_id,
                instructor_id,
                laboratory_room_id,
                week,
                day,
                start_time,
                length,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });

    }
}
