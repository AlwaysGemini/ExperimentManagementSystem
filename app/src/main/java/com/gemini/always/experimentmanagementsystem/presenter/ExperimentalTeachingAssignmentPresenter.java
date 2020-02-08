package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.bean.ExperimentalTeachingAssignmentTable;
import com.gemini.always.experimentmanagementsystem.model.ExperimentalTeachingAssignmentModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalTeachingAssignmentView;

import org.json.JSONObject;

/**
 * @Author: 周清
 * @Description:
 * @Date: Created in 9:42 2020/2/8
 */
public class ExperimentalTeachingAssignmentPresenter extends BasePresenter<ExperimentalTeachingAssignmentView> {

    private ExperimentalTeachingAssignmentModel model;

    public ExperimentalTeachingAssignmentPresenter() {
        this.model = new ExperimentalTeachingAssignmentModel();
    }

    public void insertData(String name_of_teaching_class,
                           String teacher,
                           String including_experimental_items,
                           String number_of_electives) {
        this.model.insertData(name_of_teaching_class,
                teacher,
                including_experimental_items,
                number_of_electives,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void insertData(ExperimentalTeachingAssignmentTable table) {
        this.model.insertData(table.getName_of_teaching_class(),
                table.getTeacher(),
                table.getIncluding_experimental_items(),
                table.getNumber_of_electives(),
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

    public void getData(String school_year,
                        String semester,
                        String school_of_commencement,
                        String course) {
        this.model.getData(school_year,
                semester,
                school_of_commencement,
                course, new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetDataResult(isSuccess, responseJson);
                    }
                });
    }
}
