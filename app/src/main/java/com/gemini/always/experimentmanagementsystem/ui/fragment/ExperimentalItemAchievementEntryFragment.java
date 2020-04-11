package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.insertBean.InsertExperimentalEquipment;
import com.gemini.always.experimentmanagementsystem.bean.queryBean.QueryExperimentalEquipment;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.ExperimentalEquipmentTable;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.MyTableView;
import com.gemini.always.experimentmanagementsystem.presenter.ExperimentalItemAchievementEntryPresenter;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalItemAchievementEntryView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ExperimentalItemAchievementEntryFragment extends BaseFragment<ExperimentalItemAchievementEntryView, ExperimentalItemAchievementEntryPresenter> implements ExperimentalItemAchievementEntryView, View.OnClickListener {

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.table)
    MyTableView table;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.fab_get_template)
    FloatingActionButton fabGetTemplate;
    @BindView(R.id.fab_import)
    FloatingActionButton fabImport;
    @BindView(R.id.fab_query)
    FloatingActionButton fabQuery;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu fabMenu;
    Unbinder unbinder;

    private String title = "实验项目成绩录入";
    private Class tableClass = ExperimentalEquipmentTable.class;
    private Class queryClass = QueryExperimentalEquipment.class;
    private Class insertClass = InsertExperimentalEquipment.class;
    private List<ExperimentalEquipmentTable> list = new ArrayList<>();
    private List<List<String>> spinnerDataListForQuery = new ArrayList<>();
    private List<String> selected_and_edited_list_for_insert = new ArrayList<>();
    private List<String> selected_and_edited_list_for_query = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_experimental_item_achievement_entry, container, false);

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

    @Override
    public ExperimentalItemAchievementEntryPresenter createPresenter() {
        return new ExperimentalItemAchievementEntryPresenter();
    }

    @Override
    public ExperimentalItemAchievementEntryView createView() {
        return this;
    }

    private void initView() {
        fabGetTemplate.setVisibility(View.GONE);
        titlebar.setTitle(title);
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

    @Override
    public void onGetTemplateResult(Boolean isSuccess, JSONObject responseJson) {

    }

    @Override
    public void onImportExperimentalItemAchievementResult(Boolean isSuccess, JSONObject responseJson) {

    }

    @OnClick({R.id.fab_get_template, R.id.fab_import, R.id.fab_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_get_template:
                break;
            case R.id.fab_import:
                break;
            case R.id.fab_query:
                break;
        }
    }
}
