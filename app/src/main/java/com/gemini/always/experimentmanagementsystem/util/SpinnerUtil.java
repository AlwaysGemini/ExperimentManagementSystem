package com.gemini.always.experimentmanagementsystem.util;

import android.app.Dialog;
import android.widget.ArrayAdapter;

import com.gemini.always.experimentmanagementsystem.MyApplication;
import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinner;

import java.util.List;
import java.util.Objects;

public class SpinnerUtil {

    public static void initSpinner(MaterialSpinner spinner, Dialog dialog, int spinnerId, ArrayAdapter<String> arrayAdapter, int layoutId, List<String> list) {
        spinner = Objects.requireNonNull(dialog.getWindow()).findViewById(spinnerId);
        arrayAdapter = new ArrayAdapter<>(MyApplication.getContext(), layoutId, list);
        arrayAdapter.setDropDownViewResource(layoutId);
        spinner.setAdapter(arrayAdapter);
    }
}
