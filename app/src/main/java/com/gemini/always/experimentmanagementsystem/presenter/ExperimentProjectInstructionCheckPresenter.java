package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.ExperimentProjectInstructionCheckModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentProjectInstructionCheckView;

import org.json.JSONObject;

public class ExperimentProjectInstructionCheckPresenter extends BasePresenter<ExperimentProjectInstructionCheckView> {
    private ExperimentProjectInstructionCheckModel model;

    public ExperimentProjectInstructionCheckPresenter() {
        this.model = new ExperimentProjectInstructionCheckModel();
    }

    public void getExperimentProjectInstructionState() {
        this.model.getExperimentProjectInstructionState(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetExperimentProjectInstructionStateResult(isSuccess, responseJson);
            }
        });
    }

    public void download(final String url, final String destFileDir, final String destFileName) {
        this.model.download(url, destFileDir, destFileName,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onDownloadExperimentProjectInstructionResult(isSuccess, responseJson);
                    }
                });
    }
}
