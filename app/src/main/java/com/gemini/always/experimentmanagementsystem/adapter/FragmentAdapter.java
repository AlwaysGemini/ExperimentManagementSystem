package com.gemini.always.experimentmanagementsystem.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.adapter.FragmentAdapter.java
 * @Description: Fragment适配器
 * @author: 周清
 * @date: 2020-02-07 21:40
 */
public class FragmentAdapter<T extends Fragment> extends FragmentPagerAdapter {

    private List<T> mFragmentList = new ArrayList<>();

    private List<String> mTitleList = new ArrayList<>();

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentAdapter(FragmentManager fm, List<T> fragments) {
        super(fm);
        setFragments(fragments);
    }

    public FragmentAdapter(FragmentManager fm, T[] fragments) {
        super(fm);
        setFragments(Arrays.asList(fragments));
    }

    public FragmentAdapter<T> setFragments(List<T> fragments) {
        if (fragments != null && fragments.size() > 0) {
            mFragmentList.clear();
            mFragmentList.addAll(fragments);
        }
        return this;
    }

    public FragmentAdapter<T> addFragments(List<T> fragments) {
        if (fragments != null && fragments.size() > 0) {
            mFragmentList.addAll(fragments);
        }
        return this;
    }

    public FragmentAdapter<T> setTitles(List<String> titles) {
        if (titles != null && titles.size() > 0) {
            mTitleList.clear();
            mTitleList.addAll(titles);
        }
        return this;
    }

    public FragmentAdapter<T> addTitles(List<String> titles) {
        if (titles != null && titles.size() > 0) {
            mTitleList.addAll(titles);
        }
        return this;
    }

    public FragmentAdapter<T> addFragment(T fragment, String title) {
        if (fragment != null) {
            mFragmentList.add(fragment);
            mTitleList.add(title);
        }
        return this;
    }

    @Override
    public T getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    public List<T> getFragmentList() {
        return mFragmentList;
    }

    public List<String> getTitleList() {
        return mTitleList;
    }
}