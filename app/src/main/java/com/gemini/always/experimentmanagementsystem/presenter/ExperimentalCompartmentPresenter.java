package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.ExperimentalOrganizationModel;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalCompartmentView;

public class ExperimentalCompartmentPresenter extends BasePresenter<ExperimentalCompartmentView> {

    private ExperimentalOrganizationModel experimentalOrganizationModel;

    public ExperimentalCompartmentPresenter(){
        this.experimentalOrganizationModel = new ExperimentalOrganizationModel();
    }
}
