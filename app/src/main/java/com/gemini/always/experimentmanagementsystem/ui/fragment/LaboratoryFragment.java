package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.bin.david.form.core.SmartTable;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.presenter.LaboratoryPresenter;
import com.gemini.always.experimentmanagementsystem.view.LaboratoryView;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LaboratoryFragment extends BaseFragment<LaboratoryView, LaboratoryPresenter> implements LaboratoryView {

    @BindView(R.id.button_setting_query_condition)
    RoundButton buttonSettingQueryCondition;
    @BindView(R.id.edit_course)
    EditText editCourse;
    @BindView(R.id.button_query)
    RoundButton buttonQuery;
    @BindView(R.id.line_query)
    RelativeLayout lineQuery;
    @BindView(R.id.table)
    SmartTable table;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    Unbinder unbinder;
    @BindView(R.id.button_add)
    FloatingActionButton buttonAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_base_query_table, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public LaboratoryPresenter createPresenter() {
        return new LaboratoryPresenter();
    }

    @Override
    public LaboratoryView createView() {
        return this;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
