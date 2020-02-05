package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.table.TableData;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.bean.CourseExperimentOutlineTable;
import com.gemini.always.experimentmanagementsystem.presenter.CourseExperimentOutlinePresenter;
import com.gemini.always.experimentmanagementsystem.util.ExcelUtils;
import com.gemini.always.experimentmanagementsystem.util.FileUtils;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.CourseExperimentOutlineView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.orhanobut.logger.Logger;
import com.thl.filechooser.FileChooser;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.DialogAction;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
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

public class CourseExperimentOutlineFragment extends BaseFragment<CourseExperimentOutlineView, CourseExperimentOutlinePresenter> implements CourseExperimentOutlineView {

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.table)
    SmartTable table;
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

    private List<CourseExperimentOutlineTable> list = new ArrayList<>();

    private EditText edit_course_code;
    private EditText edit_course_name;
    private EditText edit_proportion_of_experimental_results;
    private EditText edit_experimental_project_name;

    private String edited_course_code = "";
    private String edited_course_name = "";
    private String edited_proportion_of_experimental_results = "";
    private String edited_experimental_project_name = "";

    private EditText edit_course;
    private String edited_course = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_base_query_table_have_title_bar, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {
        titlebar.setTitle("课程实验大纲");
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
        table.getConfig().setShowXSequence(false);
        table.getConfig().setShowYSequence(false);
        table.getConfig().setShowTableTitle(false);
        table.setZoom(true);
    }

    @Override
    public CourseExperimentOutlinePresenter createPresenter() {
        return new CourseExperimentOutlinePresenter();
    }

    @Override
    public CourseExperimentOutlineView createView() {
        return this;
    }

    private void insertData() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().insertData(edited_course_code,
                        edited_course_name,
                        edited_proportion_of_experimental_results,
                        edited_experimental_project_name);
            }
        }.start();
    }

    private void getData() {
        edited_course = edit_course.getText().toString();
        new Thread() {
            @Override
            public void run() {
                getPresenter().getData(edited_course);
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
    public void onGetDataResult(Boolean isSuccess, JSONObject responseJson) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            if (isSuccess) {
                try {
                    llStateful.showContent();
                    list.clear();
                    list.addAll(JsonUtil.stringToList(responseJson.getJSONArray("data").toString(), CourseExperimentOutlineTable.class));
                    table.setData(list);
                    table.getTableData().setOnRowClickListener(new TableData.OnRowClickListener() {
                        @Override
                        public void onClick(Column column, Object o, int col, int row) {

                            new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                                    .customView(R.layout.dialog_custom_experiment_compartment, true)
                                    .title("修改或者删除数据此条数据")
                                    .positiveText("修改")
                                    .positiveColorRes(R.color.colorPrimary)
                                    .neutralText("删除")
                                    .onNeutral(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            XToastUtils.error("删除");
                                        }
                                    })
                                    .negativeText("取消")
                                    .negativeColorRes(R.color.colorPrimary)
                                    .show();
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

    @OnClick({R.id.fab_import, R.id.fab_export, R.id.fab_add, R.id.fab_query, R.id.fab_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_query:
                MaterialDialog dialog = new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_query_condition_course_experiment_outline, true)
                        .title(R.string.title_set_query_condition)
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                getData();
                            }
                        })
                        .negativeText("取消")
                        .negativeColorRes(R.color.colorPrimary)
                        .show();

                edit_course = Objects.requireNonNull(dialog.getWindow()).findViewById(R.id.edit_course);
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
                                    List<CourseExperimentOutlineTable> list = ExcelUtils.readExcelContent(filePath, CourseExperimentOutlineTable.class);
                                    for (CourseExperimentOutlineTable courseExperimentOutlineTable : list) {
                                        getPresenter().insertData(courseExperimentOutlineTable);
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
                        ExcelUtils.createExcel(getContext(), "课程实验大纲表格", list, CourseExperimentOutlineTable.class);
                        Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast("导出成功,文件保存在:" + getActivity().getExternalFilesDir(null)));
                    }
                }.start();
                break;
            case R.id.fab_add:
                new MaterialDialog.Builder(Objects.requireNonNull(getContext()))
                        .customView(R.layout.dialog_custom_course_experiment_outline, true)
                        .title("增加")
                        .positiveText("确定")
                        .positiveColorRes(R.color.colorPrimary)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                edit_course_code = dialog.findViewById(R.id.edit_course_code);
                                edit_course_name = dialog.findViewById(R.id.edit_course_name);
                                edit_proportion_of_experimental_results = dialog.findViewById(R.id.edit_proportion_of_experimental_results);
                                edit_experimental_project_name = dialog.findViewById(R.id.edit_experimental_project_name);

                                edited_course_code = edit_course_code.getText().toString();
                                edited_course_name = edit_course_name.getText().toString();
                                edited_proportion_of_experimental_results = edit_proportion_of_experimental_results.getText().toString();
                                edited_experimental_project_name = edit_experimental_project_name.getText().toString();

                                insertData();
                            }
                        })
                        .negativeText("取消")
                        .negativeColorRes(R.color.colorPrimary)
                        .show();
                break;
            case R.id.fab_menu:

                break;
        }
    }
}
