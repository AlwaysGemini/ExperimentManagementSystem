package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

public interface ExperimentProjectInstructionCheckView extends MVPView {
    void onGetExperimentProjectInstructionStateResult(Boolean isSuccess, JSONObject responseJson);

    void onDownloadExperimentProjectInstructionResult(Boolean isSuccess, JSONObject responseJson);
}
