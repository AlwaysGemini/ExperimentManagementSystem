package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.TeachingExperimentCenterModel;
import com.gemini.always.experimentmanagementsystem.view.TeachingExperimentCenterView;

public class TeachingExperimentCenterPresenter extends BasePresenter<TeachingExperimentCenterView> {

    private TeachingExperimentCenterModel teachingExperimentCenterModel;

    public TeachingExperimentCenterPresenter(){
        this.teachingExperimentCenterModel = new TeachingExperimentCenterModel();
    }
}
