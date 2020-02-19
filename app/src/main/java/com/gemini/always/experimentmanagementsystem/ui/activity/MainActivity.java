package com.gemini.always.experimentmanagementsystem.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.gemini.always.experimentmanagementsystem.MyApplication;
import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.adapter.DrawerAdapter;
import com.gemini.always.experimentmanagementsystem.adapter.ExpandableItemAdapter;
import com.gemini.always.experimentmanagementsystem.bean.DrawerItem;
import com.gemini.always.experimentmanagementsystem.bean.Item;
import com.gemini.always.experimentmanagementsystem.bean.Level0Item;
import com.gemini.always.experimentmanagementsystem.bean.SimpleItem;
import com.gemini.always.experimentmanagementsystem.bean.SpaceItem;
import com.gemini.always.experimentmanagementsystem.bean.User;
import com.gemini.always.experimentmanagementsystem.util.OtherUtils;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.githang.statusbar.StatusBarCompat;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.DialogAction;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.callback.DragStateListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.chad.library.adapter.base.BaseQuickAdapter.SCALEIN;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.ui.activity.MainActivity.java
 * @Description: MainActivity，显示主界面，在没有登陆的情况下会自动跳转到登录界面
 * @author: 周清
 * @date: 2020-02-07 21:46
 */
public class MainActivity extends AppCompatActivity {

    private static final int POS_CHANGE_ROLE = 0;
    private static final int POS_SETTING = 1;
    private static final int POS_LOGOUT = 3;
    private static SlidingRootNav mSlidingRootNav;
    Level0Item[] level0Item = new Level0Item[8];
    private TitleBar titleBar;
    private RelativeLayout container;
    private String[] mMenuTitles;
    private Drawable[] mMenuIcons;
    private DrawerAdapter mAdapter;
    private ExpandableItemAdapter adapter;
    private List<MultiItemEntity> data = new ArrayList<>();

    /**
     * 启动MainActivity
     *
     * @param context
     */
    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static SlidingRootNav getSlidingRootNav() {
        return mSlidingRootNav;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#FF108EE9"));
        if (!User.isLogin()) {
            FragmentSelectActivity.startFragmentSelecter(this, "LoginFragment");
            finish();
            return;
        }
        initSlidingMenu(savedInstanceState);

        initView();
        initData();
    }

    private void initSlidingMenu(Bundle savedInstanceState) {
        mMenuTitles = ResUtils.getStringArray(R.array.menu_titles);
        mMenuIcons = OtherUtils.getDrawableArray(this, R.array.menu_icons);

        mSlidingRootNav = new SlidingRootNavBuilder(this)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        mAdapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CHANGE_ROLE).setChecked(true),
                createItemFor(POS_SETTING),
                new SpaceItem(48),
                createItemFor(POS_LOGOUT)));
        mAdapter.setListener(new DrawerAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position) {
                switch (position) {
                    case POS_CHANGE_ROLE:

                        break;
                    case POS_SETTING:

                        break;
                    case POS_LOGOUT:
                        /*new AlertDialog.Builder(MainActivity.this)
                                .setTitle("确定退出登录吗？")
                                .setPositiveButton(R.string.btn_confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        User.logout();
                                        XToastUtils.toast("成功退出登录");
                                        if (!User.isLogin()) {
                                            FragmentSelectActivity.startFragmentSelecter(MyApplication.getContext(), "LoginFragment");
                                            finish();
                                            return;
                                        }
                                    }
                                })
                                .setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).show();*/
                        new MaterialDialog.Builder(MainActivity.this)
                                .content("确定退出登录吗？")
                                .positiveText("确定")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        User.logout();
                                        XToastUtils.toast("成功退出登录");
                                        if (!User.isLogin()) {
                                            FragmentSelectActivity.startFragmentSelecter(MyApplication.getContext(), "LoginFragment");
                                            finish();
                                            return;
                                        }
                                    }
                                })
                                .negativeText("取消")
                                .show();
                        break;
                }
            }
        });

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(mAdapter);

        mAdapter.setSelected(POS_CHANGE_ROLE);
        mSlidingRootNav.setMenuLocked(false);
        mSlidingRootNav.getLayout().addDragStateListener(new DragStateListener() {
            @Override
            public void onDragStart() {

            }

            @Override
            public void onDragEnd(boolean isMenuOpened) {
                if (isMenuOpened) {
                    /*if (GuideCaseView.isShowOnce(MainActivity.this, "guide_key_sliding_root_navigation")) {
                        final GuideCaseView guideStep1 = new GuideCaseView.Builder(MainActivity.this)
                                .title("点击进入，可切换主题样式哦～～")
                                .titleSize(18, TypedValue.COMPLEX_UNIT_SP)
                                .focusOn(ivSetting)
                                .build();

                        final GuideCaseView guideStep2 = new GuideCaseView.Builder(MainActivity.this)
                                .title("点击进入，扫码关注哦～～")
                                .titleSize(18, TypedValue.COMPLEX_UNIT_SP)
                                .focusOn(ivQrcode)
                                .build();

                        new GuideCaseQueue()
                                .add(guideStep1)
                                .add(guideStep2)
                                .show();
                        GuideCaseView.setShowOnce(MainActivity.this, "guide_key_sliding_root_navigation");
                    }*/
                }
            }
        });
    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(mMenuIcons[position], mMenuTitles[position])
                .withIconTint(ThemeUtils.resolveColor(this, R.attr.xui_config_color_content_text))
                .withTextTint(ThemeUtils.resolveColor(this, R.attr.xui_config_color_content_text))
                .withSelectedIconTint(ThemeUtils.resolveColor(this, R.attr.colorAccent))
                .withSelectedTextTint(ThemeUtils.resolveColor(this, R.attr.colorAccent));
    }

    /**
     * 初始化布局
     */
    private void initView() {
        titleBar = findViewById(R.id.titlebar);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSlidingRootNav.isMenuClosed()) {
                    mSlidingRootNav.openMenu();
                } else if (mSlidingRootNav.isMenuOpened()) {
                    mSlidingRootNav.closeMenu();
                }
            }
        });

        container = findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSlidingRootNav.isMenuOpened()) {
                    mSlidingRootNav.closeMenu();
                }
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //创建布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //创建适配器
        adapter = new ExpandableItemAdapter(this, data);
        //开启动画（默认为渐显效果）
        adapter.openLoadAnimation(SCALEIN);
        //设置不重复执行动画
        adapter.isFirstOnly(true);
        adapter.setUpFetchEnable(true);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {//为列表设置点击事件，通过Fragment选择器跳转对应的Fragment
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClick(final BaseQuickAdapter adapter, View view, final int position) {
                mSlidingRootNav.closeMenu();
                switch (((Item) (Objects.requireNonNull(adapter.getItem(position)))).getItemName()) {
                    case "实验机构":
                        FragmentSelectActivity.startFragmentSelecter(getApplicationContext(), "ExperimentalOrganizationFragment");
                        break;
                    case "实验室人员管理":
                        FragmentSelectActivity.startFragmentSelecter(getApplicationContext(), "LaboratoryPersonnelManagementFragment");
                        break;
                    case "实验仪器设备管理":
                        FragmentSelectActivity.startFragmentSelecter(getApplicationContext(), "ExperimentalEquipmentFragment");
                        break;
                    case "实验耗材管理":
                        FragmentSelectActivity.startFragmentSelecter(getApplicationContext(), "ExperimentalConsumablesManagementFragment");
                        break;
                    case "实验项目管理":
                        FragmentSelectActivity.startFragmentSelecter(getApplicationContext(), "ExperimentalProjectManagementFragment");
                        break;
                    case "课程实验大纲":
                        FragmentSelectActivity.startFragmentSelecter(getApplicationContext(), "CourseExperimentOutlineFragment");
                        break;
                    case "实验教学班维护":
                        FragmentSelectActivity.startFragmentSelecter(getApplicationContext(), "MaintenanceOfTeachingExperimentalClassFragment");
                        break;
                    case "实验教学任务书":
                        FragmentSelectActivity.startFragmentSelecter(getApplicationContext(), "ExperimentalTeachingAssignmentFragment");
                        break;
                    case "实验项目教学任务书":
                        FragmentSelectActivity.startFragmentSelecter(getApplicationContext(), "TeachingAssignmentOfExperimentalProjectFragment");
                        break;
                    case "实验排课":
                        FragmentSelectActivity.startFragmentSelecter(getApplicationContext(), "ExperimentSchedulingFragment");
                        break;
                }
            }
        });

        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化主界面的列表数据
     */
    private void initData() {
        level0Item[0] = new Level0Item("实验课程项目");
        data.add(level0Item[0]);

        level0Item[1] = new Level0Item("实验资源管理");
        level0Item[1].addSubItem(new Item("实验机构"));
        level0Item[1].addSubItem(new Item("实验室人员管理"));
        level0Item[1].addSubItem(new Item("实验仪器设备管理"));
        level0Item[1].addSubItem(new Item("实验耗材管理"));
        level0Item[1].addSubItem(new Item("代码维护"));
        data.add(level0Item[1]);

        level0Item[2] = new Level0Item("实验项目管理");
        level0Item[2].addSubItem(new Item("实验项目管理"));
        level0Item[2].addSubItem(new Item("课程实验大纲"));
        data.add(level0Item[2]);

        level0Item[3] = new Level0Item("实验开课排课");
        level0Item[3].addSubItem(new Item("实验教学班维护"));
        level0Item[3].addSubItem(new Item("实验教学任务书"));
        level0Item[3].addSubItem(new Item("实验项目教学任务书"));
        level0Item[3].addSubItem(new Item("实验排课"));
        level0Item[3].addSubItem(new Item("实验项目指导书审核"));
        level0Item[3].addSubItem(new Item("实验项目指导书查看"));
        data.add(level0Item[3]);

        level0Item[4] = new Level0Item("实验选课管理");
        level0Item[4].addSubItem(new Item("选课规则设置"));
        level0Item[4].addSubItem(new Item("选课名单调整"));
        level0Item[4].addSubItem(new Item("生成配课"));
        data.add(level0Item[4]);

        level0Item[5] = new Level0Item("实验成绩管理");
        level0Item[5].addSubItem(new Item("实验项目成绩录入"));
        level0Item[5].addSubItem(new Item("实验成绩汇总入"));
        level0Item[5].addSubItem(new Item("实验成绩审核"));
        level0Item[5].addSubItem(new Item("实验考勤成绩"));
        data.add(level0Item[5]);

        level0Item[6] = new Level0Item("开放性实验管理");
        level0Item[6].addSubItem(new Item("项目申请"));
        level0Item[6].addSubItem(new Item("项目审核"));
        level0Item[6].addSubItem(new Item("实验安排"));
        level0Item[6].addSubItem(new Item("预约控制"));
        level0Item[6].addSubItem(new Item("名单调整"));
        level0Item[6].addSubItem(new Item("成绩管理"));
        data.add(level0Item[6]);

        level0Item[7] = new Level0Item("实验课表打印");
        level0Item[7].addSubItem(new Item("课程课表打印"));
        level0Item[7].addSubItem(new Item("教师课表打印"));
        level0Item[7].addSubItem(new Item("场地课表打印"));
        data.add(level0Item[7]);

        adapter.notifyDataSetChanged();
    }
}
