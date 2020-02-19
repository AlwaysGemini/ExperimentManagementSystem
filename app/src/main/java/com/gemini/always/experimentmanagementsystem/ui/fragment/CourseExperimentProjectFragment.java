package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.CourseExperimentProjectTable;
import com.gemini.always.experimentmanagementsystem.custom.customDialog.CustomDialog;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.MyTableView;
import com.gemini.always.experimentmanagementsystem.presenter.CourseExperimentProjectPresenter;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.ListUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.CourseExperimentProjectView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.orhanobut.logger.Logger;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.ui.fragment.CourseExperimentProjectFragment.java
 * @Description: 教学实验项目模块
 * @author: 周清
 * @date: 2020-02-07 21:47
 */
public class CourseExperimentProjectFragment extends BaseFragment<CourseExperimentProjectView, CourseExperimentProjectPresenter> implements CourseExperimentProjectView, View.OnClickListener {

    @BindView(R.id.table)
    MyTableView table;
    Unbinder unbinder;
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.fab_import)
    FloatingActionButton fabImport;
    @BindView(R.id.fab_export)
    FloatingActionButton fabExport;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;
    @BindView(R.id.fab_query)
    FloatingActionButton fabQuery;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu fabMenu;
    @BindView(R.id.fab_delete)
    FloatingActionButton fabDelete;

    private Class tableClass = CourseExperimentProjectTable.class;
    private List<CourseExperimentProjectTable> list = new ArrayList<>();
    private List<List<String>> spinnerDataListForQuery = new ArrayList<>();
    private List<String> selected_and_edited_list_for_query = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_base_my_table, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        getListData();
    }

    private void initView() {
        titlebar.setTitle("教学实验项目");
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
        fabMenu.removeButton(fabAdd);
        fabMenu.removeButton(fabExport);
        fabMenu.removeButton(fabImport);
        fabDelete.setVisibility(View.GONE);
    }

    private void getData() {
        new Thread() {
            @Override
            public void run() {
                int count = 0;
                getPresenter().getData(selected_and_edited_list_for_query.get(count++),
                        selected_and_edited_list_for_query.get(count++),
                        selected_and_edited_list_for_query.get(count++),
                        selected_and_edited_list_for_query.get(count++),
                        selected_and_edited_list_for_query.get(count++));
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
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            if (isSuccess) {
                try {
                    llStateful.showContent();
                    list.clear();
                    list.addAll(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), tableClass));
                    table.setData(list, CourseExperimentProjectTable.class);
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
    public void onGetQueryConditionListResult(Boolean isSuccess, JSONObject responseJson) {
        if (isSuccess) {
            try {
                JSONArray jsonArray = responseJson.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    spinnerDataListForQuery.add(new ArrayList<>());
                }
                int count = 0;
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(count), "instructional_school", spinnerDataListForQuery.get(count++));
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(count), "course_category", spinnerDataListForQuery.get(count++));
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(count), "course_assignment", spinnerDataListForQuery.get(count++));
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(count), "course_enabling_grade", spinnerDataListForQuery.get(count++));
            } catch (JSONException e) {
                XToastUtils.toast(e.getMessage());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getListData() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().getQueryConditionList();
            }
        }.start();
    }

    @OnClick(R.id.fab_query)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_query:
                new CustomDialog.Builder(getContext())
                        .setTitle("查询")
                        .setSpinnerTextList(Arrays.asList(getResources().getStringArray(R.array.courseExperimentProjectTextListForInsert)))
                        .setSpinnerDataList(spinnerDataListForQuery)
                        .setEditList(Arrays.asList(getResources().getStringArray(R.array.courseExperimentProjectTextListForQuery)))
                        .serOnPositive("确定", new CustomDialog.DialogIF() {
                            @Override
                            public void onPositive(CustomDialog dialog, List<String> list) {
                                selected_and_edited_list_for_query = list;
                                getData();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消", (dialogInterface, i) -> dialogInterface.dismiss())
                        .create()
                        .show();
                break;
        }
    }
}
