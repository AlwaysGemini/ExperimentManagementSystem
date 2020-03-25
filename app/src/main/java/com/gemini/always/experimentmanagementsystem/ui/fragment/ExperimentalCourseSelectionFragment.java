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
import com.gemini.always.experimentmanagementsystem.bean.tableBean.ExperimentalCourseSelectionTable;
import com.gemini.always.experimentmanagementsystem.custom.customTableView.MyTableView;
import com.gemini.always.experimentmanagementsystem.presenter.ExperimentalCourseSelectionPresenter;
import com.gemini.always.experimentmanagementsystem.util.JsonUtil;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalCourseSelectionView;
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

public class ExperimentalCourseSelectionFragment extends BaseFragment<ExperimentalCourseSelectionView, ExperimentalCourseSelectionPresenter> implements ExperimentalCourseSelectionView, View.OnClickListener {

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.table)
    MyTableView table;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.fab_select)
    FloatingActionButton fabSelect;
    @BindView(R.id.fab_query)
    FloatingActionButton fabQuery;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu fabMenu;
    Unbinder unbinder;

    private String title = "实验选课";
    private Class tableClass = ExperimentalCourseSelectionTable.class;
    private List<ExperimentalCourseSelectionTable> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_experimental_course_selection, container, false);

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
        fabSelect.setVisibility(View.GONE);
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

    private void getData() {
        new Thread() {
            @Override
            public void run() {
                getPresenter().getExperimentalCourseList();
            }
        }.start();
    }

    private void select(String experimental_teaching_class_id) {
        new Thread() {
            @Override
            public void run() {
                getPresenter().select(User.getCurrentUser(getContext()).getUserId(), experimental_teaching_class_id);
            }
        }.start();
    }

    @Override
    public ExperimentalCourseSelectionPresenter createPresenter() {
        return new ExperimentalCourseSelectionPresenter();
    }

    @Override
    public ExperimentalCourseSelectionView createView() {
        return this;
    }

    @Override
    public void onGetExperimentalCourseListResult(Boolean isSuccess, JSONObject responseJson) {
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
                                fabSelect.setVisibility(View.VISIBLE);
                                fabSelect.startAnimation(scaleAnimation);
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
    public void onExperimentalCourseSelectionResult(Boolean isSuccess, JSONObject responseJson) {
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

    @OnClick({R.id.fab_query, R.id.fab_select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_query:
                getData();
                break;
            case R.id.fab_select:
                if (table.getCheckedList().size() > 0)
                    select(list.get(table.getCheckedList().get(0) - 1).getExperimental_teaching_class_id());
                break;
        }
    }
}
