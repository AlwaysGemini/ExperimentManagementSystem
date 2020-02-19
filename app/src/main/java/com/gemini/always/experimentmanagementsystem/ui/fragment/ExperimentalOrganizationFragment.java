package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gemini.always.experimentmanagementsystem.ContentPage;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.adapter.FragmentAdapter;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.gemini.always.experimentmanagementsystem.presenter.ExperimentalOrganizationPresenter;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalOrganizationView;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.tabbar.TabSegment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentalOrganizationFragment.java
 * @Description: 教学机构模块
 * @author: 周清
 * @date: 2020-02-07 21:47
 */
public class ExperimentalOrganizationFragment extends BaseFragment<ExperimentalOrganizationView, ExperimentalOrganizationPresenter> implements ExperimentalOrganizationView {

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.tabSegment)
    TabSegment tabSegment;
    Unbinder unbinder;
    @BindView(R.id.contentViewPager)
    ViewPager contentViewPager;

    List<Fragment> fragmentList = new ArrayList<>();
    String[] pages = ContentPage.getPageNames();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_experimental_organization, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {
        FragmentAdapter<BaseFragment> adapter = new FragmentAdapter<>(getChildFragmentManager());
        for (String page : pages) {
            tabSegment.addTab(new TabSegment.Tab(page));
        }
        adapter.addFragment(new TeachingExperimentCenterFragment(), "实验教学中心");
        adapter.addFragment(new LaboratoryFragment(), "实验室");
        adapter.addFragment(new ExperimentalCompartmentFragment(), "实验分室");
        adapter.addFragment(new LaboratoryRoomFragment(), "实验房间");


        contentViewPager.setAdapter(adapter);
        contentViewPager.setCurrentItem(0);
        tabSegment.setMode(TabSegment.MODE_FIXED);
        tabSegment.setupWithViewPager(contentViewPager, false);

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
    public ExperimentalOrganizationPresenter createPresenter() {
        return new ExperimentalOrganizationPresenter();
    }

    @Override
    public ExperimentalOrganizationView createView() {
        return this;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
