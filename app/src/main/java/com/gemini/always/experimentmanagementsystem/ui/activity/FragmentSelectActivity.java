package com.gemini.always.experimentmanagementsystem.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.ui.fragment.CheckOutExperimentAchievementFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.CourseExperimentOutlineFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.CourseExperimentProjectFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentAchievementEntryFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentAchievementExaminingFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentAchievementSummaryFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentCourseMatchFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentItemManagementFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentProjectInstructionCheckFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentProjectInstructionExaminingFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentProjectInstructionUploadFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentSchedulingFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentalConsumablesManagementFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentalCourseSelectionFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentalEquipmentFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentalOrganizationFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentalTeachingAssignmentFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.HelpFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.LaboratoryPersonnelManagementFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.LoginFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.MaintenanceOfTeachingExperimentalClassFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.RulesOfSelectingCoursesFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.TeachingAssignmentOfExperimentalProjectFragment;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.githang.statusbar.StatusBarCompat;
import com.orhanobut.logger.Logger;

import java.util.Objects;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.ui.activity.FragmentSelectActivity.java
 * @Description: Fragment选择器，用于做为整个应用的Fragment的容器
 * @author: 周清
 * @date: 2020-02-07 21:45
 */
public class FragmentSelectActivity extends AppCompatActivity {

    /**
     * 仅仅启动FragmentSelector
     *
     * @param context
     * @param fragmentName
     */
    public static void startFragmentSelector(Context context, String fragmentName) {
        Intent intent = new Intent(context, FragmentSelectActivity.class);
        intent.putExtra("Fragment", fragmentName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 启动FragmentSelector并附带参数
     *
     * @param context
     * @param fragmentName
     * @param otherInformations
     */
    public static void startFragmentSelector(Context context, String fragmentName, String... otherInformations) {
        if (otherInformations.length % 2 != 0) {
            Logger.e("输入参数数量错误");
        }
        Intent intent = new Intent(context, FragmentSelectActivity.class);
        intent.putExtra("Fragment", fragmentName);
        for (int i = 0; i < otherInformations.length; i += 2) {
            intent.putExtra(otherInformations[i], otherInformations[i + 1]);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 想要通过Fragment选择器启动Fragment，必须在这里进行注册
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_select);

        //StatusBarUtils.translucent(this);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#FF108EE9"));

        Intent intent = getIntent();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, choiceFragment(Objects.requireNonNull(intent.getStringExtra("Fragment")))).commitAllowingStateLoss();
    }

    private Fragment choiceFragment(String fragmentName) {
        Fragment fragment = null;
        switch (fragmentName) {
            case "LoginFragment":
                fragment = new LoginFragment();
                break;
            case "CourseExperimentProjectFragment":
                fragment = new CourseExperimentProjectFragment();
                break;
            case "ExperimentalOrganizationFragment":
                fragment = new ExperimentalOrganizationFragment();
                break;
            case "LaboratoryPersonnelManagementFragment":
                fragment = new LaboratoryPersonnelManagementFragment();
                break;
            case "ExperimentalEquipmentFragment":
                fragment = new ExperimentalEquipmentFragment();
                break;
            case "ExperimentalConsumablesManagementFragment":
                fragment = new ExperimentalConsumablesManagementFragment();
                break;
            case "ExperimentalProjectManagementFragment":
                fragment = new ExperimentItemManagementFragment();
                break;
            case "CourseExperimentOutlineFragment":
                fragment = new CourseExperimentOutlineFragment();
                break;
            case "MaintenanceOfTeachingExperimentalClassFragment":
                fragment = new MaintenanceOfTeachingExperimentalClassFragment();
                break;
            case "ExperimentalTeachingAssignmentFragment":
                fragment = new ExperimentalTeachingAssignmentFragment();
                break;
            case "TeachingAssignmentOfExperimentalProjectFragment":
                fragment = new TeachingAssignmentOfExperimentalProjectFragment();
                break;
            case "ExperimentSchedulingFragment":
                fragment = new ExperimentSchedulingFragment();
                break;
            case "RulesOfSelectingCoursesFragment":
                fragment = new RulesOfSelectingCoursesFragment();
                break;
            case "ExperimentProjectInstructionUploadFragment":
                fragment = new ExperimentProjectInstructionUploadFragment();
                break;
            case "ExperimentProjectInstructionExaminingFragment":
                fragment = new ExperimentProjectInstructionExaminingFragment();
                break;
            case "ExperimentProjectInstructionCheckFragment":
                fragment = new ExperimentProjectInstructionCheckFragment();
                break;
            case "ExperimentalCourseSelectionFragment":
                fragment = new ExperimentalCourseSelectionFragment();
                break;
            case "ExperimentCourseMatchFragment":
                fragment = new ExperimentCourseMatchFragment();
                break;
            case "HelpFragment":
                fragment = new HelpFragment();
                break;
            case "ExperimentalItemAchievementEntryFragment":
                fragment = new ExperimentAchievementEntryFragment();
                break;
            case "ExperimentAchievementSummaryFragment":
                fragment = new ExperimentAchievementSummaryFragment();
                break;
            case "ExperimentAchievementExaminingFragment":
                fragment = new ExperimentAchievementExaminingFragment();
                break;
            case "CheckOutExperimentAchievementFragment":
                fragment = new CheckOutExperimentAchievementFragment();
                break;
            default:
                XToastUtils.toast("Fragment加载错误");
                break;
        }
        return fragment;
    }
}
