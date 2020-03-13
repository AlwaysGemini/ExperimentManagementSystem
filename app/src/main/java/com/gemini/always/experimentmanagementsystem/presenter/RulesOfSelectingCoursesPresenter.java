package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.RulesOfSelectingCoursesModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.BaseCURDView;

import org.json.JSONObject;

public class RulesOfSelectingCoursesPresenter extends BasePresenter<BaseCURDView> {
    private RulesOfSelectingCoursesModel model;

    public RulesOfSelectingCoursesPresenter() {
        this.model = new RulesOfSelectingCoursesModel();
    }

    public void insertData(String year,
                           String semester,
                           String start_time,
                           String end_time,
                           String is_withdrawable,
                           String is_reelect,
                           String is_selective_capacity,
                           String is_click_to_run) {
        this.model.insertData(year,
                semester,
                start_time,
                end_time,
                is_withdrawable,
                is_reelect,
                is_selective_capacity,
                is_click_to_run,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void getQueryConditionList() {
        this.model.getQueryConditionList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetQueryConditionListResult(isSuccess, responseJson);
            }
        });
    }

    public void getData(String year,
                        String semester) {
        this.model.getData(year,
                semester,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetDataResult(isSuccess, responseJson);
                    }
                });
    }
}
