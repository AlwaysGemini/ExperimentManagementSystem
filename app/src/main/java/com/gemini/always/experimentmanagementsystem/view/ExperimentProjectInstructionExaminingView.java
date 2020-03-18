package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

public interface ExperimentProjectInstructionExaminingView extends MVPView {
    void onGetExperimentProjectInstructionStateResult(Boolean isSuccess, JSONObject responseJson);

    void onExaminingExperimentProjectInstructionResult(Boolean isSuccess, JSONObject responseJson);
}
