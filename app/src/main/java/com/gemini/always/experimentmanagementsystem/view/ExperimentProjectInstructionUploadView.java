package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

public interface ExperimentProjectInstructionUploadView extends MVPView {
    void onGetExperimentProjectInstructionStateResult(Boolean isSuccess, JSONObject responseJson);

    void onUploadExperimentProjectInstructionResult(Boolean isSuccess, JSONObject responseJson);
}
