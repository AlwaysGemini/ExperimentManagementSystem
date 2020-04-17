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
import com.gemini.always.experimentmanagementsystem.bean.tableBean.ExperimentAchievementEntryTable;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.MyTableView;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableAdapter;
import com.gemini.always.experimentmanagementsystem.presenter.ExperimentAchievementExaminingPresenter;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentAchievementExaminingView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.orhanobut.logger.Logger;
import com.xuexiang.xui.widget.actionbar.TitleBar;
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

public class ExperimentAchievementExaminingFragment extends BaseFragment<ExperimentAchievementExaminingView, ExperimentAchievementExaminingPresenter> implements ExperimentAchievementExaminingView, View.OnClickListener {

    private static String TAG = "ExperimentAchievementExaminingFragment";

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.table)
    MyTableView table;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.fab_examining)
    FloatingActionButton fabExamining;
    @BindView(R.id.fab_query)
    FloatingActionButton fabQuery;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu fabMenu;
    Unbinder unbinder;

    private String title = "实验成绩审核";
    private Class tableClass = ExperimentAchievementEntryTable.class;
    private List<ExperimentAchievementEntryTable> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_experiment_achievement_examining, container, false);

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
        fabExamining.setVisibility(View.GONE);

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
    public ExperimentAchievementExaminingPresenter createPresenter() {
        return new ExperimentAchievementExaminingPresenter();
    }

    @Override
    public ExperimentAchievementExaminingView createView() {
        return this;
    }

    private void getExperimentItemAchievementTableSummary() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().getExperimentItemAchievementTableSummary(User.getCurrentUser(getContext()).getUserId());
            }
        }.start();
    }

    private void examining(String experiment_course_match_id,
                           String experiment_achievement_table_state) {
        new Thread() {
            @Override
            public void run() {
                getPresenter().examining(experiment_course_match_id,
                        experiment_achievement_table_state);
            }
        }.start();
    }

    @Override
    public void onGetExperimentAchievementTableSummaryResult(Boolean isSuccess, JSONObject responseJson) {
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
                                fabExamining.setVisibility(View.VISIBLE);
                                fabExamining.startAnimation(scaleAnimation);
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
    public void onExaminingResult(Boolean isSuccess, JSONObject responseJson) {
        if (isSuccess) {
            try {
                XToastUtils.toast(responseJson.getString("msg"));
            } catch (JSONException e) {
                Logger.e(e, TAG);
            }
        }
    }

    @OnClick({R.id.fab_examining, R.id.fab_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_examining:
                ArrayList<String> items = new ArrayList<>();
                items.add("审核通过");
                items.add("审核不通过");
                new MaterialDialog.Builder(Objects.requireNonNull(getActivity()))
                        .title("审核")
                        .items(items)
                        .itemsCallbackSingleChoice(
                                0,
                                new MaterialDialog.ListCallbackSingleChoice() {
                                    @Override
                                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                        examining(list.get(table.getCheckedList().get(0) - 1).getExperiment_course_match_id(),
                                                text.toString());
                                        return true;
                                    }
                                })
                        .positiveText(R.string.btn_confirm)
                        .negativeText(R.string.btn_cancel)
                        .show();
                break;
            case R.id.fab_query:
                getExperimentItemAchievementTableSummary();
                break;
        }
    }
}
