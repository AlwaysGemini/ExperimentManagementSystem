package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bin.david.form.core.SmartTable;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.CourseExperimentProjectTable;
import com.gemini.always.experimentmanagementsystem.presenter.CourseExperimentProjectPresenter;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.view.CourseExperimentProjectView;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CourseExperimentProjectFragment extends BaseFragment<CourseExperimentProjectView, CourseExperimentProjectPresenter> implements CourseExperimentProjectView {

    @BindView(R.id.table_course_experiment_project)
    SmartTable tableCourseExperimentProject;
    Unbinder unbinder;
    @BindView(R.id.titlebar)
    TitleBar titlebar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_course_experiment_project, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();
    }

    private void initView() {
        tableCourseExperimentProject.getConfig().setShowXSequence(false);
        tableCourseExperimentProject.getConfig().setShowYSequence(false);
        tableCourseExperimentProject.setZoom(true);

        titlebar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.requireNonNull(getActivity()).getSupportFragmentManager().getBackStackEntryCount() >= 1) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    getActivity().finish();
                }
            }
        });
    }

    private void initData() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().getData();
            }
        }.start();
    }

    @Override
    public CourseExperimentProjectPresenter createPresenter() {
        return new CourseExperimentProjectPresenter();
    }

    @Override
    public CourseExperimentProjectView createView() {
        return this;
    }

    @Override
    public void onGetDataResult(Boolean isSuccess, JSONObject responseJson) {
        if (isSuccess) {
            try {
                tableCourseExperimentProject.setData(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), CourseExperimentProjectTable.class));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
