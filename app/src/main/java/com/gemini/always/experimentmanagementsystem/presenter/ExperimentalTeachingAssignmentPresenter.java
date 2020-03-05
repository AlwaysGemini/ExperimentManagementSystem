package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.ExperimentTeachingAssignmentTable;
import com.gemini.always.experimentmanagementsystem.model.ExperimentTeachingAssignmentModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalTeachingAssignmentView;

import org.json.JSONObject;

/**
 * @Author: 周清
 * @Description:
 * @Date: Created in 9:42 2020/2/8
 */
public class ExperimentalTeachingAssignmentPresenter extends BasePresenter<ExperimentalTeachingAssignmentView> {

    private ExperimentTeachingAssignmentModel model;

    public ExperimentalTeachingAssignmentPresenter() {
        this.model = new ExperimentTeachingAssignmentModel();
    }

    public void insertData(String experimental_teaching_class_id,
                           String teacher_id,
                           String experiment_item_id) {
        this.model.insertData(experimental_teaching_class_id,
                teacher_id,
                experiment_item_id,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void insertData(ExperimentTeachingAssignmentTable table) {
        this.model.insertData(table.getExperimental_teaching_class_name(),
                table.getTeacher(),
                table.getExperiment_item_name(),
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
                        String semester,
                        String college,
                        String course) {
        this.model.getData(year,
                semester,
                college,
                course, new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetDataResult(isSuccess, responseJson);
                    }
                });
    }
}
