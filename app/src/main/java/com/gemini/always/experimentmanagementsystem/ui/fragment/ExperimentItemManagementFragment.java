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
import com.gemini.always.experimentmanagementsystem.bean.insertBean.InsertExperimentItem;
import com.gemini.always.experimentmanagementsystem.bean.queryBean.QueryExperimentItem;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.ExperimentItemTable;
import com.gemini.always.experimentmanagementsystem.custom.customDialog.CustomDialog;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.MyTableView;
import com.gemini.always.experimentmanagementsystem.presenter.ExperimentItemManagementPresenter;
import com.gemini.always.experimentmanagementsystem.util.ExcelUtils;
import com.gemini.always.experimentmanagementsystem.util.FileUtils;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.ListUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalProjectManagementView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.orhanobut.logger.Logger;
import com.thl.filechooser.FileChooser;
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

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentalProjectManagementFragment.java
 * @Description: 教学项目管理模块
 * @author: 周清
 * @date: 2020-02-07 21:47
 */
public class ExperimentItemManagementFragment extends BaseFragment<ExperimentalProjectManagementView, ExperimentItemManagementPresenter> implements ExperimentalProjectManagementView {

    private static String TAG = "ExperimentItemManagementFragment";

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.table)
    MyTableView table;
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
    Unbinder unbinder;
    @BindView(R.id.fab_delete)
    FloatingActionButton fabDelete;

    private Class tableClass = ExperimentItemTable.class;
    private Class queryClass = QueryExperimentItem.class;
    private Class insertClass = InsertExperimentItem.class;
    private List<ExperimentItemTable> list = new ArrayList<>();
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

    private void initView() {
        titlebar.setTitle("实验项目管理");
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
        fabDelete.setVisibility(View.GONE);
    }

    @Override
    public ExperimentItemManagementPresenter createPresenter() {
        return new ExperimentItemManagementPresenter();
    }

    @Override
    public ExperimentalProjectManagementView createView() {
        return this;
    }

    private void insertData() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().insertData(selected_and_edited_list_for_insert.get(0),
                        selected_and_edited_list_for_insert.get(1),
                        selected_and_edited_list_for_insert.get(2),
                        selected_and_edited_list_for_insert.get(3),
                        selected_and_edited_list_for_insert.get(4),
                        selected_and_edited_list_for_insert.get(5),
                        selected_and_edited_list_for_insert.get(6),
                        selected_and_edited_list_for_insert.get(7),
                        selected_and_edited_list_for_insert.get(8),
                        selected_and_edited_list_for_insert.get(9),
                        selected_and_edited_list_for_insert.get(10));
            }
        }.start();
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
        //edited_experimental_project_name_for_query = edit_experimental_project_name_for_query.getText().toString();
        new Thread() {
            @Override
            public void run() {
                getPresenter().getData(selected_and_edited_list_for_query.get(0),
                        selected_and_edited_list_for_query.get(1),
                        selected_and_edited_list_for_query.get(2),
                        selected_and_edited_list_for_query.get(3));
            }
        }.start();
    }

    @Override
    public void onInsertDataResult(Boolean isSuccess, JSONObject responseJson) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            try {
                XToastUtils.toast(responseJson.getString("msg"));
            } catch (JSONException e) {
                Logger.e(e, TAG);
            }
        });
    }

    @Override
    public void onGetQueryConditionListResult(Boolean isSuccess, JSONObject responseJson) {
        if (isSuccess) {
            try {
                JSONArray jsonArray = responseJson.getJSONArray("data");
                int count = 0;
                for (int i = 0; i < jsonArray.length(); i++) {
                    spinnerDataListForQuery.add(new ArrayList<>());
                }
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(count), "experiment_attribute", spinnerDataListForQuery.get(count++));
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(count), "experiment_type", spinnerDataListForQuery.get(count++));
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(count), "experiment_category", spinnerDataListForQuery.get(count++));
            } catch (JSONException e) {
                Logger.e(e, TAG);
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
                } catch (JSONException e) {
                    Logger.e(e, TAG);
                }
            } else {
                try {
                    XToastUtils.toast(responseJson.getString("msg"));
                } catch (JSONException e) {
                    Logger.e(e, TAG);
                }
                llStateful.showEmpty();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fab_import, R.id.fab_export, R.id.fab_add, R.id.fab_query, R.id.fab_menu, R.id.fab_delete})
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
                FileChooser fileChooser = new FileChooser(this, new FileChooser.FileChoosenListener() {
                    @Override
                    public void onFileChoosen(String filePath) {
                        new Thread() {
                            @Override
                            public void run() {
                                if (FileUtils.getFormatName(filePath).equals("xlsx")) {
                                    Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast(filePath));
                                    List<ExperimentItemTable> list = ExcelUtils.readExcelContent(filePath, tableClass);
                                    for (ExperimentItemTable experimentalProjectTable : list) {
                                        getPresenter().insertData(experimentalProjectTable);
                                    }
                                } else {
                                    Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.error("请选择.xlsx格式的表格文件"));
                                }
                            }
                        }.start();
                    }
                });
                FileUtils.initFileChooser(fileChooser);
                break;
            case R.id.fab_export:
                new Thread() {
                    @Override
                    public void run() {
                        ExcelUtils.createExcel(getContext(), "实验分室表格", list, tableClass);
                        Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast("导出成功,文件保存在:" + getActivity().getExternalFilesDir(null)));
                    }
                }.start();
                break;
            case R.id.fab_add:
                new CustomDialog.Builder(getContext())
                        .setTitle("增加")
                        .setClazz(insertClass)
                        .serOnPositive("确定", new CustomDialog.DialogIF() {
                            @Override
                            public void onPositive(CustomDialog dialog, List<String> list) {
                                selected_and_edited_list_for_insert = list;
                                insertData();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消", (dialogInterface, i) -> dialogInterface.dismiss())
                        .create()
                        .show();
                break;
            case R.id.fab_menu:

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
                                    table.setHeadIsChecked(false);
                                    table.notifyDataSetChanged();
                                    fabDelete.setVisibility(View.GONE);
                                }
                            })
                            .negativeText("取消")
                            .show();
                break;
        }
    }
}
