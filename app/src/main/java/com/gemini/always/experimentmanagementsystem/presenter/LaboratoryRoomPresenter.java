package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.LaboratoryRoomModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.LaboratoryRoomView;

import org.json.JSONObject;

public class LaboratoryRoomPresenter extends BasePresenter<LaboratoryRoomView> {

    private LaboratoryRoomModel laboratoryRoomModel;

    public LaboratoryRoomPresenter() {
        this.laboratoryRoomModel = new LaboratoryRoomModel();
    }

    public void insertData(String laboratory_room_code,
                           String laboratory_room_name,
                           String affiliated_experimental_compartment,
                           String nature_of_experimental_site,
                           String category_of_scientific_research_base,
                           String person_in_charge_of_the_experimental_room,
                           String status_of_joint_construction,
                           String campus,
                           String capacity,
                           String remarks,
                           String enable_flag) {
        this.laboratoryRoomModel.insertData(laboratory_room_code,
                laboratory_room_name,
                affiliated_experimental_compartment,
                nature_of_experimental_site,
                category_of_scientific_research_base,
                person_in_charge_of_the_experimental_room,
                status_of_joint_construction,
                campus,
                capacity,
                remarks,
                enable_flag,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void getQueryConditionList() {
        this.laboratoryRoomModel.getQueryConditionList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetQueryConditionListResult(isSuccess, responseJson);
            }
        });
    }

    public void getData(String affiliated_teaching_experiment_center,
                        String affiliated_laboratory,
                        String affiliated_experimental_compartment,
                        String nature_of_experimental_site,
                        String category_of_scientific_research_base,
                        String status_of_joint_construction,
                        String campus,
                        String enable_flag) {
        this.laboratoryRoomModel.getData(affiliated_teaching_experiment_center,
                affiliated_laboratory,
                affiliated_experimental_compartment,
                nature_of_experimental_site,
                category_of_scientific_research_base,
                status_of_joint_construction,
                campus,
                enable_flag,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetDataResult(isSuccess, responseJson);
                    }
                });
    }
}
