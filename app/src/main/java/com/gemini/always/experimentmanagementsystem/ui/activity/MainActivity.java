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

    private static String TAG = "MainActivity";

    private static final int POS_CHANGE_ROLE = 0;
    private static final int POS_SETTING = 1;
    private static final int POS_HELP = 2;
    private static final int POS_LOGOUT = 4;
    private static SlidingRootNav mSlidingRootNav;
    private Level0Item[] level0Item = new Level0Item[8];
    private TitleBar titleBar;
    private RelativeLayout container;
    private String[] mMenuTitles;
    private Drawable[] mMenuIcons;
    private DrawerAdapter mAdapter;
    private ExpandableItemAdapter adapter;
    private List<MultiItemEntity> data = new ArrayList<>();

    /**
     * 启动MainActivity
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
        if (!User.isLogin(MainActivity.this)) {
            FragmentSelectActivity.startFragmentSelector(this, "LoginFragment");
            finish();
            return;
        }
        initSlidingMenu(savedInstanceState);

        initView();
        initData();
    }

    /**
     * 初始化侧滑界面
     *
     * @param savedInstanceState
     */
    private void initSlidingMenu(Bundle savedInstanceState) {
        mMenuTitles = ResUtils.getStringArray(R.array.menu_titles);
        mMenuIcons = OtherUtils.getDrawableArray(this, R.array.menu_icons);

        mSlidingRootNav = new SlidingRootNavBuilder(this)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        if (User.getCurrentUserType(MainActivity.this).equals("管理员+教师") || User.getCurrentUserType(MainActivity.this).equals("选择为管理员") || User.getCurrentUserType(MainActivity.this).equals("选择为教师")) {
            mAdapter = new DrawerAdapter(Arrays.asList(
                    createItemFor(POS_CHANGE_ROLE).setChecked(false),
                    createItemFor(POS_SETTING),
                    createItemFor(POS_HELP),
                    new SpaceItem(48).setChecked(true),
                    createItemFor(POS_LOGOUT)));
            mAdapter.setSelected(2);
        } else {
            mAdapter = new DrawerAdapter(Arrays.asList(
                    createItemFor(POS_SETTING),
                    createItemFor(POS_HELP),
                    new SpaceItem(48),
                    createItemFor(POS_LOGOUT)));
        }

        mAdapter.setListener(new DrawerAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position) {
                if (mSlidingRootNav.isMenuClosed()) {
                    return;
                }
                int realPosition = position;
                if (!(User.getCurrentUserType(MainActivity.this).equals("管理员+教师") || User.getCurrentUserType(MainActivity.this).equals("选择为管理员") || User.getCurrentUserType(MainActivity.this).equals("选择为教师"))) {
                    realPosition = position + 1;
                }
                switch (realPosition) {
                    case POS_CHANGE_ROLE:
                        ArrayList<String> items = new ArrayList<>();
                        items.add("管理员");
                        items.add("教师");
                        new MaterialDialog.Builder(MainActivity.this)
                                .title("切换身份")
                                .items(items)
                                .itemsCallbackSingleChoice(
                                        0,
                                        new MaterialDialog.ListCallbackSingleChoice() {
                                            @Override
                                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                                if (text.equals("管理员")) {
                                                    User.setCurrentUserType(getApplicationContext(), "选择为管理员");
                                                } else {
                                                    User.setCurrentUserType(getApplicationContext(), "选择为教师");
                                                }
                                                initData();
                                                return true;
                                            }
                                        })
                                .positiveText(R.string.btn_confirm)
                                .negativeText(R.string.btn_cancel)
                                .show();
                        break;
                    case POS_SETTING:

                        break;
                    case POS_HELP:
                        FragmentSelectActivity.startFragmentSelector(MainActivity.this, "HelpFragment");
                        break;
                    case POS_LOGOUT:
                        new MaterialDialog.Builder(MainActivity.this)
                                .content("确定退出登录吗？")
                                .positiveText("确定")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        User.logout(getApplicationContext());
                                        XToastUtils.toast("成功退出登录");
                                        if (!User.isLogin(getApplicationContext())) {
                                            FragmentSelectActivity.startFragmentSelector(MyApplication.getContext(), "LoginFragment");
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
                String fragmentName = getFragmentNameByItemTitle(((Item) (Objects.requireNonNull(adapter.getItem(position)))).getItemName());
                if (!fragmentName.equals("")) {
                    FragmentSelectActivity.startFragmentSelector(getApplicationContext(), fragmentName);
                } else {
                    XToastUtils.error("暂未开发");
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化主界面的列表数据
     */
    private void initData() {
        int count = 0;
        data.clear();
        switch (User.getCurrentUserType(MainActivity.this)) {
            case "":
            case "管理员+教师":
            case "选择为管理员":
            case "管理员":
                level0Item[count] = new Level0Item("实验课程项目");
                level0Item[count].addSubItem(new Item("实验课程项目"));
                data.add(level0Item[count++]);

                level0Item[count] = new Level0Item("实验资源管理");
                level0Item[count].addSubItem(new Item("实验机构"));
                level0Item[count].addSubItem(new Item("实验室人员管理"));
                level0Item[count].addSubItem(new Item("实验仪器设备管理"));
                level0Item[count].addSubItem(new Item("实验耗材管理"));
                data.add(level0Item[count++]);

                level0Item[count] = new Level0Item("实验项目管理");
                level0Item[count].addSubItem(new Item("实验项目管理"));
                level0Item[count].addSubItem(new Item("课程实验大纲"));
                data.add(level0Item[count++]);

                level0Item[count] = new Level0Item("实验开课排课");
                level0Item[count].addSubItem(new Item("实验教学班维护"));
                level0Item[count].addSubItem(new Item("实验教学任务书"));
                level0Item[count].addSubItem(new Item("实验项目教学任务书"));
                level0Item[count].addSubItem(new Item("实验排课"));
                level0Item[count].addSubItem(new Item("实验项目指导书审核"));
                level0Item[count].addSubItem(new Item("实验项目指导书查看"));
                data.add(level0Item[count++]);

                level0Item[count] = new Level0Item("实验选课管理");
                level0Item[count].addSubItem(new Item("选课规则设置"));
                //level0Item[count].addSubItem(new Item("选课名单调整"));
                level0Item[count].addSubItem(new Item("生成配课"));
                data.add(level0Item[count++]);

                level0Item[count] = new Level0Item("实验成绩管理");
                level0Item[count].addSubItem(new Item("实验成绩录入"));
                level0Item[count].addSubItem(new Item("实验成绩审核"));
                data.add(level0Item[count++]);

                level0Item[count] = new Level0Item("开放性实验管理");
                level0Item[count].addSubItem(new Item("项目申请"));
                level0Item[count].addSubItem(new Item("项目审核"));
                level0Item[count].addSubItem(new Item("实验安排"));
                level0Item[count].addSubItem(new Item("预约控制"));
                level0Item[count].addSubItem(new Item("名单调整"));
                level0Item[count].addSubItem(new Item("成绩管理"));
                //data.add(level0Item[count++]);
                break;
            case "选择为教师":
            case "教师":
                level0Item[count] = new Level0Item("实验课程项目");
                level0Item[count].addSubItem(new Item("实验课程项目"));
                data.add(level0Item[count++]);

                level0Item[count] = new Level0Item("实验项目管理");
                level0Item[count].addSubItem(new Item("实验项目管理"));
                level0Item[count].addSubItem(new Item("课程实验大纲"));
                data.add(level0Item[count++]);

                level0Item[count] = new Level0Item("实验开课排课");
                level0Item[count].addSubItem(new Item("实验教学班维护"));
                level0Item[count].addSubItem(new Item("实验教学任务书"));
                level0Item[count].addSubItem(new Item("实验项目教学任务书"));
                level0Item[count].addSubItem(new Item("实验排课"));
                level0Item[count].addSubItem(new Item("实验项目指导书提交"));
                level0Item[count].addSubItem(new Item("实验项目指导书查看"));
                data.add(level0Item[count++]);

                level0Item[count] = new Level0Item("实验选课管理");
                //level0Item[count].addSubItem(new Item("选课名单调整"));
                level0Item[count].addSubItem(new Item("生成配课"));
                data.add(level0Item[count++]);

                level0Item[count] = new Level0Item("实验成绩管理");
                level0Item[count].addSubItem(new Item("实验成绩录入"));
                data.add(level0Item[count++]);

                level0Item[count] = new Level0Item("开放性实验管理");
                level0Item[count].addSubItem(new Item("项目申请"));
                level0Item[count].addSubItem(new Item("实验安排"));
                level0Item[count].addSubItem(new Item("预约控制"));
                level0Item[count].addSubItem(new Item("名单调整"));
                level0Item[count].addSubItem(new Item("成绩管理"));
                //data.add(level0Item[count++]);
                break;
            case "学生":
                level0Item[count] = new Level0Item("实验选课管理");
                level0Item[count].addSubItem(new Item("实验选课"));
                data.add(level0Item[count++]);

                level0Item[count] = new Level0Item("实验成绩管理");
                level0Item[count].addSubItem(new Item("实验成绩查看"));
                data.add(level0Item[count++]);
                break;
        }
        adapter.notifyDataSetChanged();
    }

    private String getFragmentNameByItemTitle(String itemTitle) {
        String fragmentName = "";
        switch (itemTitle) {
            case "实验课程项目":
                fragmentName = "CourseExperimentProjectFragment";
                break;
            case "实验机构":
                fragmentName = "ExperimentalOrganizationFragment";
                break;
            case "实验室人员管理":
                fragmentName = "LaboratoryPersonnelManagementFragment";
                break;
            case "实验仪器设备管理":
                fragmentName = "ExperimentalEquipmentFragment";
                break;
            case "实验耗材管理":
                fragmentName = "ExperimentalConsumablesManagementFragment";
                break;
            case "实验项目管理":
                fragmentName = "ExperimentalProjectManagementFragment";
                break;
            case "课程实验大纲":
                fragmentName = "CourseExperimentOutlineFragment";
                break;
            case "实验教学班维护":
                fragmentName = "MaintenanceOfTeachingExperimentalClassFragment";
                break;
            case "实验教学任务书":
                fragmentName = "ExperimentalTeachingAssignmentFragment";
                break;
            case "实验项目教学任务书":
                fragmentName = "TeachingAssignmentOfExperimentalProjectFragment";
                break;
            case "实验排课":
                fragmentName = "ExperimentSchedulingFragment";
                break;
            case "选课规则设置":
                fragmentName = "RulesOfSelectingCoursesFragment";
                break;
            case "实验项目指导书提交":
                fragmentName = "ExperimentProjectInstructionUploadFragment";
                break;
            case "实验项目指导书审核":
                fragmentName = "ExperimentProjectInstructionExaminingFragment";
                break;
            case "实验项目指导书查看":
                fragmentName = "ExperimentProjectInstructionCheckFragment";
                break;
            case "实验选课":
                fragmentName = "ExperimentalCourseSelectionFragment";
                break;
            case "生成配课":
                fragmentName = "ExperimentCourseMatchFragment";
                break;
            case "实验成绩录入":
                fragmentName = "ExperimentalItemAchievementEntryFragment";
                break;
            case "实验成绩审核":
                fragmentName = "ExperimentAchievementExaminingFragment";
                break;
            case "实验成绩查看":
                fragmentName = "CheckOutExperimentAchievementFragment";
                break;
        }
        return fragmentName;
    }
}
