package com.gemini.always.experimentmanagementsystem.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.ui.fragment.CourseExperimentOutlineFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.CourseExperimentProjectFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentItemManagementFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentProjectInstructionCheckFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentProjectInstructionExaminingFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentProjectInstructionUploadFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentSchedulingFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentalConsumablesManagementFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentalEquipmentFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentalOrganizationFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.ExperimentalTeachingAssignmentFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.LaboratoryPersonnelManagementFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.LoginFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.MaintenanceOfTeachingExperimentalClassFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.RulesOfSelectingCoursesFragment;
import com.gemini.always.experimentmanagementsystem.ui.fragment.TeachingAssignmentOfExperimentalProjectFragment;
import com.gemini.always.experimentmanagementsystem.util.XToastUtils;
import com.githang.statusbar.StatusBarCompat;
import com.orhanobut.logger.Logger;

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
     * 仅仅启动FragmentSelecter
     *
     * @param context
     * @param fragmentName
     */
    public static void startFragmentSelecter(Context context, String fragmentName) {
        Intent intent = new Intent(context, FragmentSelectActivity.class);
        intent.putExtra("Fragment", fragmentName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 启动FragmentSelecter并附带参数
     *
     * @param context
     * @param fragmentName
     * @param otherInformations
     */
    public static void startFragmentSelecter(Context context, String fragmentName, String... otherInformations) {
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
        switch (intent.getStringExtra("Fragment")) {
            case "LoginFragment":
                LoginFragment loginFragment = new LoginFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, loginFragment).commitAllowingStateLoss();
                break;
            case "CourseExperimentProjectFragment":
                CourseExperimentProjectFragment courseExperimentProjectFragment = new CourseExperimentProjectFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, courseExperimentProjectFragment).commitAllowingStateLoss();
                break;
            case "ExperimentalOrganizationFragment":
                ExperimentalOrganizationFragment experimentalOrganizationFragment = new ExperimentalOrganizationFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, experimentalOrganizationFragment).commitAllowingStateLoss();
                break;
            case "LaboratoryPersonnelManagementFragment":
                LaboratoryPersonnelManagementFragment laboratoryPersonnelManagementFragment = new LaboratoryPersonnelManagementFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, laboratoryPersonnelManagementFragment).commitAllowingStateLoss();
                break;
            case "ExperimentalEquipmentFragment":
                ExperimentalEquipmentFragment experimentalEquipmentFragment = new ExperimentalEquipmentFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, experimentalEquipmentFragment).commitAllowingStateLoss();
                break;
            case "ExperimentalConsumablesManagementFragment":
                ExperimentalConsumablesManagementFragment experimentalConsumablesManagementFragment = new ExperimentalConsumablesManagementFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, experimentalConsumablesManagementFragment).commitAllowingStateLoss();
                break;
            case "ExperimentalProjectManagementFragment":
                ExperimentItemManagementFragment experimentItemManagementFragment = new ExperimentItemManagementFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, experimentItemManagementFragment).commitAllowingStateLoss();
                break;
            case "CourseExperimentOutlineFragment":
                CourseExperimentOutlineFragment courseExperimentOutlineFragment = new CourseExperimentOutlineFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, courseExperimentOutlineFragment).commitAllowingStateLoss();
                break;
            case "MaintenanceOfTeachingExperimentalClassFragment":
                MaintenanceOfTeachingExperimentalClassFragment maintenanceOfTeachingExperimentalClassFragment = new MaintenanceOfTeachingExperimentalClassFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, maintenanceOfTeachingExperimentalClassFragment).commitAllowingStateLoss();
                break;
            case "ExperimentalTeachingAssignmentFragment":
                ExperimentalTeachingAssignmentFragment experimentalTeachingAssignmentFragment = new ExperimentalTeachingAssignmentFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, experimentalTeachingAssignmentFragment).commitAllowingStateLoss();
                break;
            case "TeachingAssignmentOfExperimentalProjectFragment":
                TeachingAssignmentOfExperimentalProjectFragment teachingAssignmentOfExperimentalProjectFragment = new TeachingAssignmentOfExperimentalProjectFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, teachingAssignmentOfExperimentalProjectFragment).commitAllowingStateLoss();
                break;
            case "ExperimentSchedulingFragment":
                ExperimentSchedulingFragment experimentSchedulingFragment = new ExperimentSchedulingFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, experimentSchedulingFragment).commitAllowingStateLoss();
                break;
            case "RulesOfSelectingCoursesFragment":
                RulesOfSelectingCoursesFragment rulesOfSelectingCoursesFragment = new RulesOfSelectingCoursesFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, rulesOfSelectingCoursesFragment).commitAllowingStateLoss();
                break;
            case "ExperimentProjectInstructionUploadFragment":
                ExperimentProjectInstructionUploadFragment experimentProjectInstructionUploadFragment = new ExperimentProjectInstructionUploadFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, experimentProjectInstructionUploadFragment).commitAllowingStateLoss();
                break;
            case "ExperimentProjectInstructionExaminingFragment":
                ExperimentProjectInstructionExaminingFragment experimentProjectInstructionExaminingFragment = new ExperimentProjectInstructionExaminingFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, experimentProjectInstructionExaminingFragment).commitAllowingStateLoss();
                break;
            case "ExperimentProjectInstructionCheckFragment":
                ExperimentProjectInstructionCheckFragment experimentProjectInstructionCheckFragment = new ExperimentProjectInstructionCheckFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, experimentProjectInstructionCheckFragment).commitAllowingStateLoss();
                break;
            default:
                XToastUtils.toast("Fragment加载错误");
                break;
        }
    }
}
