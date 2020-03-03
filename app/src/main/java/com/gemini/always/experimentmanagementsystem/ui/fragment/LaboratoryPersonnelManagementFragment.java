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
import com.gemini.always.experimentmanagementsystem.bean.tableBean.LaboratoryPersonnelManagementTable;
import com.gemini.always.experimentmanagementsystem.custom.customDialog.CustomDialog;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.MyTableView;
import com.gemini.always.experimentmanagementsystem.presenter.LaboratoryPersonnelManagementPresenter;
import com.gemini.always.experimentmanagementsystem.util.ExcelUtils;
import com.gemini.always.experimentmanagementsystem.util.FileUtils;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.ListUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.LaboratoryPersonnelManagementView;
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
 * @ClassName: com.gemini.always.experimentmanagementsystem.ui.fragment.LaboratoryPersonnelManagementFragment.java
 * @Description: 实验室人员管理模块
 * @author: 周清
 * @date: 2020-02-07 21:48
 */
public class LaboratoryPersonnelManagementFragment extends BaseFragment<LaboratoryPersonnelManagementView, LaboratoryPersonnelManagementPresenter> implements LaboratoryPersonnelManagementView, View.OnClickListener {

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.table)
    MyTableView table;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    Unbinder unbinder;
    @BindView(R.id.fab_import)
    com.getbase.floatingactionbutton.FloatingActionButton fabImport;
    @BindView(R.id.fab_export)
    com.getbase.floatingactionbutton.FloatingActionButton fabExport;
    @BindView(R.id.fab_add)
    com.getbase.floatingactionbutton.FloatingActionButton fabAdd;
    @BindView(R.id.fab_query)
    com.getbase.floatingactionbutton.FloatingActionButton fabQuery;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu fabMenu;
    @BindView(R.id.fab_delete)
    FloatingActionButton fabDelete;
    List<LaboratoryPersonnelManagementTable> list = new ArrayList<>();
    private Class tableClass = LaboratoryPersonnelManagementTable.class;
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
        titlebar.setTitle("实验室人员管理");
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
    @OnClick({R.id.fab_import, R.id.fab_export, R.id.fab_add, R.id.fab_query, R.id.fab_menu, R.id.fab_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_query:
                new CustomDialog.Builder(getContext())
                        .setTitle("查询")
                        .setType(CustomDialog.TYPE_QUERY)
                        .setClazz(tableClass)
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
                                    List<LaboratoryPersonnelManagementTable> list = ExcelUtils.readExcelContent(filePath, tableClass);
                                    for (LaboratoryPersonnelManagementTable laboratoryPersonnelManagementTable : list) {
                                        getPresenter().insertData(laboratoryPersonnelManagementTable);
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
                        ExcelUtils.createExcel(getContext(), "实验室人员管理表格", list, tableClass);
                        Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast("导出成功,文件保存在:" + getActivity().getExternalFilesDir(null)));
                    }
                }.start();
                break;
            case R.id.fab_add:
                new CustomDialog.Builder(getContext())
                        .setTitle("增加")
                        .setType(CustomDialog.TYPE_ADD)
                        .setClazz(tableClass)
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

    private void insertData() {
        new Thread() {
            @Override
            public void run() {
                int count = 0;
                getPresenter().insertData(selected_and_edited_list_for_insert.get(count++),
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
                        selected_and_edited_list_for_query.get(count++),
                        selected_and_edited_list_for_query.get(count++));
            }
        }.start();
    }


    @Override
    public LaboratoryPersonnelManagementPresenter createPresenter() {
        return new LaboratoryPersonnelManagementPresenter();
    }

    @Override
    public LaboratoryPersonnelManagementView createView() {
        return this;
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
                int count = 0;
                for (int i = 0; i < jsonArray.length(); i++) {
                    spinnerDataListForQuery.add(new ArrayList<>());
                }
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(count), "teaching_experiment_center_name", spinnerDataListForQuery.get(count++));
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(count), "laboratory_name", spinnerDataListForQuery.get(count++));
                ListUtil.addAllDataIntoList(jsonArray.getJSONArray(count), "incumbency", spinnerDataListForQuery.get(count++));
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
                    table.setData(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), tableClass), tableClass);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
