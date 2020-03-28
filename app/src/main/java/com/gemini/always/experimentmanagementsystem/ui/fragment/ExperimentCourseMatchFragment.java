package com.gemini.always.experimentmanagementsystem.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.adapter.FragmentAdapter;
import com.gemini.always.experimentmanagementsystem.base.BaseFragment;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.tabbar.TabSegment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ExperimentCourseMatchFragment extends Fragment {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.tabSegment)
    TabSegment tabSegment;
    @BindView(R.id.contentViewPager)
    ViewPager contentViewPager;
    Unbinder unbinder;

    private String title = "生成配课";
    private String[] pages = {"待配课", "已配课"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_fragment_tab_segment, container, false);

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
        adapter.addFragment(new UnmatchedOfExperimentCourseFragment(), "待配课");
        adapter.addFragment(new MatchedOfExperimentCourseFragment(), "已配课");


        contentViewPager.setAdapter(adapter);
        contentViewPager.setCurrentItem(0);
        tabSegment.setMode(TabSegment.MODE_FIXED);
        tabSegment.setupWithViewPager(contentViewPager, false);

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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
