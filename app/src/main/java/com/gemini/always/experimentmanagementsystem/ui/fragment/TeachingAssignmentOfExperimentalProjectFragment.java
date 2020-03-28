package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.queryBean.QueryTeachingAssignmentOfExperimentalProject;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.TeachingAssignmentOfExperimentalProjectTable;
import com.gemini.always.experimentmanagementsystem.custom.customDialog.CustomDialog;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.MyTableView;
import com.gemini.always.experimentmanagementsystem.presenter.TeachingAssignmentOfExperimentalProjectPresenter;
import com.gemini.always.experimentmanagementsystem.util.ExcelUtils;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.ListUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.BaseCURDView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.orhanobut.logger.Logger;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.DialogAction;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TeachingAssignmentOfExperimentalProjectFragment extends BaseFragment<BaseCURDView, TeachingAssignmentOfExperimentalProjectPresenter> implements BaseCURDView, View.OnClickListener {

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.table)
    MyTableView table;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.fab_delete)
    FloatingActionButton fabDelete;
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
    Unbinder unbinder;

    private String title = "实验项目教学任务书";
    private Class tableClass = TeachingAssignmentOfExperimentalProjectTable.class;
    private Class queryClass = QueryTeachingAssignmentOfExperimentalProject.class;
    private List<TeachingAssignmentOfExperimentalProjectTable> list = new ArrayList<>();
    private List<List<String>> spinnerDataListForQuery = new ArrayList<>();
    private List<String> selected_and_edited_list_for_insert = new ArrayList<>();
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
        getQueryConditionList();
    }

    private void initTable() {
        table.setData(list, tableClass);
        table.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (table.getIsShowCheckBox()) {
                    table.setCheckedPosition(position, !table.getIsCheckPosition(position));
                }
            }
        });
        table.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                if (!table.getIsShowCheckBox()) {
                    table.setIsShowCheckBox(true);
                    table.setCheckedPosition(position, true);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1.0f, 0, 1.0f, 100, 100);
                    scaleAnimation.setDuration(500);
                    fabDelete.setVisibility(View.VISIBLE);
                    fabDelete.startAnimation(scaleAnimation);
                }
                return false;
            }
        });
    }

    private void initView() {
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
        fabAdd.setVisibility(View.GONE);
        fabImport.setVisibility(View.GONE);
        fabDelete.setVisibility(View.GONE);
    }

    @Override
    public TeachingAssignmentOfExperimentalProjectPresenter createPresenter() {
        return new TeachingAssignmentOfExperimentalProjectPresenter();
    }

    @Override
    public BaseCURDView createView() {
        return this;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    @OnClick({R.id.fab_query, R.id.fab_import, R.id.fab_export, R.id.fab_add, R.id.fab_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_query:
                new CustomDialog.Builder(getContext())
                        .setTitle("查询")
                        .setClazz(queryClass)
                        .setSpinnerDataList(spinnerDataListForQuery)
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
            case R.id.fab_import:

                break;
            case R.id.fab_export:
                new Thread() {
                    @Override
                    public void run() {
                        ExcelUtils.createExcel(getContext(), title + "表格", list, tableClass);
                        Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast("导出成功,文件保存在:" + getActivity().getExternalFilesDir(null)));
                    }
                }.start();
                break;
            case R.id.fab_add:

                break;
            case R.id.fab_delete:
                if (table.getCheckedList().size() > 0)
                    new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                            .content("确定要删除这" + table.getCheckedList().size() + "项吗？")
                            .positiveText("确定")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    table.setIsShowCheckBox(false);
                                    table.notifyDataSetChanged();
                                    fabDelete.setVisibility(View.GONE);
                                }
                            })
                            .negativeText("取消")
                            .show();
                break;
        }
    }

    private void getQueryConditionList() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().getQueryConditionList();
            }
        }.start();
    }

    private void getData() {
        new Thread() {
            @Override
            public void run() {
                int count = 0;
                getPresenter().getData(selected_and_edited_list_for_query.get(count++),
                        selected_and_edited_list_for_query.get(count++),
                        selected_and_edited_list_for_query.get(count++),
                        selected_and_edited_list_for_query.get(count++));
            }
        }.start();
    }

    @Override
    public void onInsertDataResult(Boolean isSuccess, JSONObject responseJson) {

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
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(count), "year", spinnerDataListForQuery.get(count++));
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(count), "semester", spinnerDataListForQuery.get(count++));
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(count), "college", spinnerDataListForQuery.get(count++));
            } catch (JSONException e) {
                XToastUtils.toast(e.getMessage());
            }
        }
    }

    @Override
    public void onGetDataResult(Boolean isSuccess, JSONObject responseJson) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            if (isSuccess) {
                try {
                    llStateful.showContent();
                    list.clear();
                    list.addAll(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), tableClass));
                    initTable();
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
    public void onUpdateResult(Boolean isSuccess, JSONObject responseJson) {

    }

    @Override
    public void onDeleteResult(Boolean isSuccess, JSONObject responseJson) {

    }
}
