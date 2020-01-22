package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.LaboratoryRoomModel;
import com.gemini.always.experimentmanagementsystem.view.LaboratoryRoomView;

public class LaboratoryRoomPresenter extends BasePresenter<LaboratoryRoomView> {

    private LaboratoryRoomModel laboratoryRoomModel;

    public LaboratoryRoomPresenter(){
        this.laboratoryRoomModel = new LaboratoryRoomModel();
    }
}
