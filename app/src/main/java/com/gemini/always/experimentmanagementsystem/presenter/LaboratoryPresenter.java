package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.LaboratoryModel;
import com.gemini.always.experimentmanagementsystem.view.LaboratoryView;

public class LaboratoryPresenter extends BasePresenter<LaboratoryView> {

    private LaboratoryModel laboratoryModel;

    public LaboratoryPresenter(){
        this.laboratoryModel = new LaboratoryModel();
    }
}
