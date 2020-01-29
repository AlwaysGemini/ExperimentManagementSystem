package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.ExperimentalOrganizationModel;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalOrganizationView;

public class ExperimentalOrganizationPresenter extends BasePresenter<ExperimentalOrganizationView> {

    private ExperimentalOrganizationModel experimentalOrganizationModel;

    public ExperimentalOrganizationPresenter() {
        this.experimentalOrganizationModel = new ExperimentalOrganizationModel();
    }
}
