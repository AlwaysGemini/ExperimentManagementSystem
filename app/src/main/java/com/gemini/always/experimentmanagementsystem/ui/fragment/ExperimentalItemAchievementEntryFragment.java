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
import com.gemini.always.experimentmanagementsystem.bean.User;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.ExperimentalItemAchievementEntryTable;
import com.gemini.always.experimentmanagementsystem.bean.tableTemplateBean.ExperimentalItemAchievementTableTemplate;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.MyTableView;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableAdapter;
import com.gemini.always.experimentmanagementsystem.presenter.ExperimentalItemAchievementEntryPresenter;
import com.gemini.always.experimentmanagementsystem.util.ExcelUtils;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalItemAchievementEntryView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.thl.filechooser.FileChooser;
import com.thl.filechooser.FileInfo;
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
    private Class tableClass = ExperimentalItemAchievementEntryTable.class;
    private List<ExperimentalItemAchievementEntryTable> list = new ArrayList<>();

    private Class templateClass = ExperimentalItemAchievementTableTemplate.class;

    private String toBeGetTemplate;

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

    private void getExperimentItemAchievementTableSummary() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().getExperimentItemAchievementTableSummary(User.getCurrentUser(getContext()).getUserId());
            }
        }.start();
    }

    private void getTemplate(String experiment_course_match_id, String experiment_item_id) {
        new Thread() {
            @Override
            public void run() {
                getPresenter().getTemplate(experiment_course_match_id, experiment_item_id);
            }
        }.start();
    }

    private void importExperimentalItemAchievement(String filePath,
                                                   String fileName,
                                                   String experiment_item_id,
                                                   String experiment_course_match_id) {
        new Thread() {
            @Override
            public void run() {
                getPresenter().importExperimentalItemAchievement(filePath,
                        fileName,
                        experiment_item_id,
                        experiment_course_match_id);
            }
        }.start();
    }

    @Override
    public void onGetExperimentItemAchievementTableSummaryResult(Boolean isSuccess, JSONObject responseJson) {
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
                                table.setSelectMode(TableAdapter.SingleSelection);
                                table.setCheckedPosition(position, true);
                                ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1.0f, 0, 1.0f, 100, 100);
                                scaleAnimation.setDuration(500);
                                fabGetTemplate.setVisibility(View.VISIBLE);
                                fabGetTemplate.startAnimation(scaleAnimation);
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
    public void onGetTemplateResult(Boolean isSuccess, JSONObject responseJson) {
        if (isSuccess) {
            new Thread() {
                @Override
                public void run() {
                    List<ExperimentalItemAchievementTableTemplate> data = new ArrayList<>();
                    try {
                        data = new Gson().fromJson(responseJson.getJSONArray("data").toString(), new TypeToken<List<ExperimentalItemAchievementTableTemplate>>() {
                        }.getType());
                    } catch (JSONException e) {
                        Logger.e(e, "JSONException:");
                    }
                    ExcelUtils.createExcel(getContext(), toBeGetTemplate + "表格", data, templateClass);
                    Objects.requireNonNull(getActivity()).runOnUiThread(() -> XToastUtils.toast("导出成功,文件保存在:" + getActivity().getExternalFilesDir(null)));
                }
            }.start();
        }
    }

    @Override
    public void onImportExperimentalItemAchievementResult(Boolean isSuccess, JSONObject responseJson) {
        if (isSuccess) {
            try {
                XToastUtils.toast(responseJson.getString("msg"));
            } catch (JSONException e) {
                Logger.e(e, "JSONException");
            }
        }
    }

    @OnClick({R.id.fab_get_template, R.id.fab_import, R.id.fab_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_get_template:
                toBeGetTemplate = list.get(table.getCheckedList().get(0) - 1).getExperimental_teaching_class_name() + " - " + list.get(table.getCheckedList().get(0) - 1).getExperiment_item_name() + " - ";
                getTemplate(list.get(table.getCheckedList().get(0) - 1).getExperiment_course_match_id(),
                        list.get(table.getCheckedList().get(0) - 1).getExperiment_item_id());
                break;
            case R.id.fab_import:
                if (table.getCheckedList().size() != 1) {
                    XToastUtils.error("只能单个上传");
                } else {
                    FileChooser fileChooser = new FileChooser(this, new FileChooser.FileChoosenListener() {
                        @Override
                        public void onFileChoosen(String filePath) {
                            new Thread() {
                                @Override
                                public void run() {
                                    ExperimentalItemAchievementEntryTable toBeUpload = list.get(table.getCheckedList().get(0) - 1);
                                    importExperimentalItemAchievement(filePath,
                                            toBeUpload.getExperiment_item_id() + " - " + toBeUpload.getExperiment_course_match_id() + " - " + "实验项目成绩表格.xlsx",
                                            toBeUpload.getExperiment_item_id(),
                                            toBeUpload.getExperiment_course_match_id());
                                }
                            }.start();
                        }
                    });
                    fileChooser.setTitle("选择需要提交的文件");
                    fileChooser.setDoneText("确定");
                    fileChooser.setChooseType(FileInfo.FILE_TYPE_FILE);
                    fileChooser.showFile(true);
                    fileChooser.setThemeColor(R.color.colorAccent);
                    fileChooser.open();
                }
                break;
            case R.id.fab_query:
                getExperimentItemAchievementTableSummary();
                break;
        }
    }
}
