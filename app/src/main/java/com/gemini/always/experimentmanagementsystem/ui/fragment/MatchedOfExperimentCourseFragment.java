package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.MatchedOfExperimentCourseTable;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.MyTableView;
import com.gemini.always.experimentmanagementsystem.presenter.MatchedOfExperimentCoursePresenter;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.MatchedOfExperimentCourseView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.orhanobut.logger.Logger;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MatchedOfExperimentCourseFragment extends BaseFragment<MatchedOfExperimentCourseView, MatchedOfExperimentCoursePresenter> implements MatchedOfExperimentCourseView, View.OnClickListener {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.table)
    MyTableView table;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.fab_match)
    FloatingActionButton fabMatch;
    @BindView(R.id.fab_query)
    FloatingActionButton fabQuery;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu fabMenu;
    Unbinder unbinder;

    private Class tableClass = MatchedOfExperimentCourseTable.class;
    private List<MatchedOfExperimentCourseTable> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_unmatched_of_experiment_course, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initView() {
        fabMatch.setVisibility(View.GONE);
        titlebar.setVisibility(View.GONE);
    }

    @Override
    public MatchedOfExperimentCoursePresenter createPresenter() {
        return new MatchedOfExperimentCoursePresenter();
    }

    @Override
    public MatchedOfExperimentCourseView createView() {
        return this;
    }

    private void getMatchedList() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().getMatchedList();
            }
        }.start();
    }

    @Override
    public void onGetMatchedListResult(Boolean isSuccess, JSONObject responseJson) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            if (isSuccess) {
                try {
                    llStateful.showContent();
                    list.clear();
                    list.addAll(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), tableClass));
                    table.setData(list, tableClass);
                } catch (JSONException e) {
                    Logger.e(e, "JSONException");
                }
            } else {
                try {
                    XToastUtils.toast(responseJson.getString("msg"));
                } catch (JSONException e) {
                    Logger.e(e, "JSONException:");
                }
                llStateful.showEmpty();
            }
        });
    }

    @Override
    @OnClick({R.id.fab_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_query:
                getMatchedList();
                break;
        }
    }
}
