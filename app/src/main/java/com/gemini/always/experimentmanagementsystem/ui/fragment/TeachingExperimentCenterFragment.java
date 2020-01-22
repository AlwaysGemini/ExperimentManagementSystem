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
import com.gemini.always.experimentmanagementsystem.presenter.TeachingExperimentCenterPresenter;
import com.gemini.always.experimentmanagementsystem.view.TeachingExperimentCenterView;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TeachingExperimentCenterFragment extends BaseFragment<TeachingExperimentCenterView, TeachingExperimentCenterPresenter> implements TeachingExperimentCenterView, View.OnClickListener {

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

        initView();
    }

    private void initView() {
        table.getConfig().setShowXSequence(false);
        table.getConfig().setShowYSequence(false);
        table.getConfig().setShowTableTitle(false);
        table.setZoom(true);

        buttonSettingQueryCondition.setOnClickListener(this);
        buttonQuery.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
    }

    @Override
    public TeachingExperimentCenterPresenter createPresenter() {
        return new TeachingExperimentCenterPresenter();
    }

    @Override
    public TeachingExperimentCenterView createView() {
        return this;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_setting_query_condition:

                break;
            case R.id.button_query:

                break;
            case R.id.button_add:

                break;
        }
    }
}
