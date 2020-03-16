package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.ExperimentProjectInstructionUploadModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentProjectInstructionUploadView;

import org.json.JSONObject;

public class ExperimentProjectInstructionUploadPresenter extends BasePresenter<ExperimentProjectInstructionUploadView> {
    private ExperimentProjectInstructionUploadModel model;

    public ExperimentProjectInstructionUploadPresenter() {
        this.model = new ExperimentProjectInstructionUploadModel();
    }

    public void getExperimentProjectInstructionState() {
        this.model.getExperimentProjectInstructionState(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetExperimentProjectInstructionStateResult(isSuccess, responseJson);
            }
        });
    }

    public void uploadExperimentProjectInstruction(String filePath,
                                                   String fileName,
                                                   String experiment_project_instruction_id) {
        this.model.uploadExperimentProjectInstruction(filePath,
                fileName,
                experiment_project_instruction_id,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onUploadExperimentProjectInstructionResult(isSuccess, responseJson);
                    }
                });
    }
}
