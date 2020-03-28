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
import com.gemini.always.experimentmanagementsystem.bean.insertBean.InsertUnmatchedOfExperimentCourse;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.UnmatchedOfExperimentCourseTable;
import com.gemini.always.experimentmanagementsystem.custom.customDialog.CustomDialog;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.MyTableView;
import com.gemini.always.experimentmanagementsystem.presenter.UnmatchedOfExperimentCoursePresenter;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.UnmatchedOfExperimentCourseView;
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

public class UnmatchedOfExperimentCourseFragment extends BaseFragment<UnmatchedOfExperimentCourseView, UnmatchedOfExperimentCoursePresenter> implements UnmatchedOfExperimentCourseView, View.OnClickListener {

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

    private Class tableClass = UnmatchedOfExperimentCourseTable.class;
    private Class insertClass = InsertUnmatchedOfExperimentCourse.class;
    private List<UnmatchedOfExperimentCourseTable> list = new ArrayList<>();

    private List<String> selected_and_edited_list_for_insert = new ArrayList<>();

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
    public UnmatchedOfExperimentCoursePresenter createPresenter() {
        return new UnmatchedOfExperimentCoursePresenter();
    }

    @Override
    public UnmatchedOfExperimentCourseView createView() {
        return this;
    }

    private void getUnmatchedList() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().getUnmatchedList();
            }
        }.start();
    }

    private void match(String experimental_teaching_class_id,
                       String capacity) {
        new Thread() {
            @Override
            public void run() {
                getPresenter().match(experimental_teaching_class_id,
                        capacity);
            }
        }.start();
    }

    @Override
    public void onGetUnmatchedListResult(Boolean isSuccess, JSONObject responseJson) {
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
                                fabMatch.setVisibility(View.VISIBLE);
                                fabMatch.startAnimation(scaleAnimation);
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
    public void onMatchResult(Boolean isSuccess, JSONObject responseJson) {
        if (isSuccess) {
            Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                try {
                    XToastUtils.toast(responseJson.getString("msg"));
                } catch (JSONException e) {
                    Logger.e(e, "JSONException:");
                }
            });
        }
    }

    @Override
    @OnClick({R.id.fab_query, R.id.fab_match})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_query:
                getUnmatchedList();
                break;
            case R.id.fab_match:
                new CustomDialog.Builder(getContext())
                        .setTitle("生成配课")
                        .setType(CustomDialog.TYPE_QUERY)
                        .setClazz(insertClass)
                        .serOnPositive("确定", new CustomDialog.DialogIF() {
                            @Override
                            public void onPositive(CustomDialog dialog, List<String> selected_list_for_insert) {
                                selected_and_edited_list_for_insert = selected_list_for_insert;
                                for (int selectPosition : table.getCheckedList()) {
                                    match(list.get(selectPosition - 1).getExperimental_teaching_class_id(), selected_list_for_insert.get(0));
                                }
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
