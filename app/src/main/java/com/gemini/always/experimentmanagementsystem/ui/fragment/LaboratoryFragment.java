package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bin.david.form.core.SmartTable;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.LaboratoryTable;
import com.gemini.always.experimentmanagementsystem.custom.CustomDialog;
import com.gemini.always.experimentmanagementsystem.presenter.LaboratoryPresenter;
import com.gemini.always.experimentmanagementsystem.util.ExcelUtils;
import com.gemini.always.experimentmanagementsystem.util.FileUtils;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.ListUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.LaboratoryView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.orhanobut.logger.Logger;
import com.thl.filechooser.FileChooser;
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
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.ui.fragment.LaboratoryFragment.java
 * @Description:实验室模块
 * @author: 周清
 * @date: 2020-02-07 21:47
 */
@RuntimePermissions
public class LaboratoryFragment extends BaseFragment<LaboratoryView, LaboratoryPresenter> implements LaboratoryView, View.OnClickListener {

    @BindView(R.id.table)
    SmartTable table;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    Unbinder unbinder;
    @BindView(R.id.fab_import)
    FloatingActionButton fabImport;
    @BindView(R.id.fab_export)
    FloatingActionButton fabExport;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;
    @BindView(R.id.fab_query)
    FloatingActionButton fabQuery;

    private List<LaboratoryTable> list = new ArrayList<>();
    private List<List<String>> spinnerDataListForQuery = new ArrayList<>();
    private List<String> selected_and_edited_list_for_insert = new ArrayList<>();
    private List<String> selected_and_edited_list_for_query = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_base_query_table_have_no_toolbar, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        getQueryConditionList();
    }

    @Override
    public void onResume() {
        super.onResume();
        table.setData(list);
    }

    private void initView() {
        table.getConfig().setShowXSequence(false);
        table.getConfig().setShowYSequence(false);
        table.getConfig().setShowTableTitle(false);
        table.setZoom(true);
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

    @Override
    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    @OnClick({R.id.fab_query, R.id.fab_import, R.id.fab_export, R.id.fab_add, R.id.fab_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_query:
                new CustomDialog.Builder(getContext())
                        .setTitle("查询")
                        .setSpinnerTextList(Arrays.asList(getResources().getStringArray(R.array.laboratorySpinnerTextListForQuery)))
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
                                    List<LaboratoryTable> list = ExcelUtils.readExcelContent(filePath, LaboratoryTable.class);
                                    for (LaboratoryTable laboratoryTable : list) {
                                        getPresenter().insertData(laboratoryTable);
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
                        ExcelUtils.createExcel(getContext(), "实验室表格", list, LaboratoryTable.class);
                        Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast("导出成功,文件保存在:" + getActivity().getExternalFilesDir(null)));
                    }
                }.start();
                break;
            case R.id.fab_add:
                new CustomDialog.Builder(getContext())
                        .setTitle("增加")
                        .setEditList(Arrays.asList(getResources().getStringArray(R.array.laboratoryTextListForInsert)))
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
        }
    }

    private void insertData() {
        new Thread() {
            @Override
            public void run() {
                int count = 0;
                getPresenter().insertData(selected_and_edited_list_for_insert.get(count++),
                        selected_and_edited_list_for_insert.get(count++),
                        selected_and_edited_list_for_insert.get(count++),
                        selected_and_edited_list_for_insert.get(count++),
                        selected_and_edited_list_for_insert.get(count++),
                        selected_and_edited_list_for_insert.get(count++),
                        selected_and_edited_list_for_insert.get(count++));
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
        new Thread() {
            @Override
            public void run() {
                int count = 0;
                getPresenter().getData(selected_and_edited_list_for_query.get(count++),
                        selected_and_edited_list_for_query.get(count++),
                        selected_and_edited_list_for_query.get(count++));
            }
        }.start();
    }

    @Override
    public void onInsertDataResult(Boolean isSuccess, JSONObject responseJson) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            try {
                XToastUtils.toast(responseJson.getString("msg"));
            } catch (JSONException e) {
                Logger.e(e, "JSONException:");
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
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(0), "affiliated_teaching_experiment_center", spinnerDataListForQuery.get(0));
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(1), "laboratory_name", spinnerDataListForQuery.get(1));
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(2), "enable_flag", spinnerDataListForQuery.get(2));
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
                    list.addAll(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), LaboratoryTable.class));
                    table.setData(list);
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
}
