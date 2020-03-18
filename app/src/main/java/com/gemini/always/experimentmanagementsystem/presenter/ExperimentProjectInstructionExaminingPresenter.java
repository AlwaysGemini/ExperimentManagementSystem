package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.ExperimentProjectInstructionExaminingModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentProjectInstructionExaminingView;

import org.json.JSONObject;

public class ExperimentProjectInstructionExaminingPresenter extends BasePresenter<ExperimentProjectInstructionExaminingView> {
    private ExperimentProjectInstructionExaminingModel model;

    public ExperimentProjectInstructionExaminingPresenter() {
        this.model = new ExperimentProjectInstructionExaminingModel();
    }

    public void getExperimentProjectInstructionState() {
        this.model.getExperimentProjectInstructionState(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetExperimentProjectInstructionStateResult(isSuccess, responseJson);
            }
        });
    }

    public void examining(String experiment_project_instruction_id,
                          String state) {
        this.model.examining(experiment_project_instruction_id,
                state,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onExaminingExperimentProjectInstructionResult(isSuccess, responseJson);
                    }
                });
    }
}
