package com.gemini.always.experimentmanagementsystem.custom.customDialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.gemini.always.experimentmanagementsystem.R;
import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinner;
import com.xuexiang.xui.widget.textview.autofit.AutoFitTextView;

import java.util.List;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.custom.ustomDialog.DialogListAdapter.java
 * @Description: 自定义Dialog中的ConstraintHeightListView的Item的适配器，对Item进行加载
 * @author: 周清
 * @date: 2020-02-07 21:42
 */
public class DialogListAdapter extends ArrayAdapter {

    private Context context;
    private List<DialogListItem> list;

    public DialogListAdapter(@NonNull Context context, int resource, @NonNull List<DialogListItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
    }

    /**
     * @param position
     * @param convertView
     * @param parent
     * @return view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DialogListItem dialogListItem = list.get(position);
        View view = null;
        if (dialogListItem.getType() == DialogListItem.TYPE_SPINNER) {
            DialogListSpinnerItem spinnerItem = dialogListItem.getSpinnerItem();
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_dialog_custom_have_spinner, null);//实例化一个对象
            AutoFitTextView itemText = view.findViewById(R.id.text);//获取该布局内的文本视图
            MaterialSpinner spinner = view.findViewById(R.id.spinner);
            itemText.setText(spinnerItem.getText() + ":");//为文本视图设置文本内容
            spinnerItem.setSpinner(spinner);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, R.layout.module_spinner_item, spinnerItem.getSpinnerDataList());
            arrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
            spinner.setAdapter(arrayAdapter);
            spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                    spinnerItem.setSelected(spinnerItem.getSpinnerDataList().get(position));
                }
            });
        } else if (dialogListItem.getType() == DialogListItem.TYPE_EDITTEXT) {
            DialogListEditTextItem editTextItem = dialogListItem.getEditTextItem();
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_dialog_custom_have_edit_text, null);//实例化一个对象
            AutoFitTextView itemText = view.findViewById(R.id.text);//获取该布局内的文本视图
            EditText editText = view.findViewById(R.id.edit_text);
            editText.setEnabled(editTextItem.isEnabled());
            itemText.setText(editTextItem.getText() + ":");//为文本视图设置文本内容
            editText.setText(editTextItem.getEdited());
            editText.setHint(editTextItem.getHint());
            editTextItem.setEditText(editText);
            if (editText.getTag() != null && editText.getTag() instanceof TextWatcher) {
                editText.removeTextChangedListener((TextWatcher) editText.getTag());
            }
            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    editTextItem.setEdited(s.toString());
                }
            };
            editText.addTextChangedListener(textWatcher);
            editText.setTag(textWatcher);
        }
        return view;
    }

    public String getEditText(int position) {
        return list.get(position).getEditTextItem().getEdited() == null ? "" : list.get(position).getEditTextItem().getEdited();
    }

    public String getSpinnerSelected(int position) {
        return list.get(position).getSpinnerItem().getSelected() == null ? "全部" : list.get(position).getSpinnerItem().getSelected();
    }
}
