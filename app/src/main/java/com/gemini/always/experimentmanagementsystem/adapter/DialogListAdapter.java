package com.gemini.always.experimentmanagementsystem.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.bean.DialogListItem;
import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinner;

import java.util.List;

public class DialogListAdapter extends ArrayAdapter {

    private Context context;
    private List<DialogListItem> list;

    public DialogListAdapter(@NonNull Context context, int resource, @NonNull List<DialogListItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DialogListItem dialogListItem = list.get(position);
        View view = null;
        if (dialogListItem.getType() == 0) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_dialog_custom_have_spinner, null);//实例化一个对象
            TextView itemText = view.findViewById(R.id.text);//获取该布局内的文本视图
            MaterialSpinner spinner = view.findViewById(R.id.spinner);
            itemText.setText(dialogListItem.getText() + ":");//为文本视图设置文本内容
            dialogListItem.setSpinner(spinner);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, R.layout.module_spinner_item, list.get(position).getDataList());
            arrayAdapter.setDropDownViewResource(R.layout.module_spinner_item);
            spinner.setAdapter(arrayAdapter);
            spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                    dialogListItem.setSelected(dialogListItem.getDataList().get(position));
                }
            });
        } else if (dialogListItem.getType() == 1) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_dialog_custom_have_edit_text, null);//实例化一个对象
            TextView itemText = view.findViewById(R.id.text);//获取该布局内的文本视图
            EditText editText = view.findViewById(R.id.edit_text);
            itemText.setText(dialogListItem.getText() + ":");//为文本视图设置文本内容
            editText.setText(dialogListItem.getEditedText());
            dialogListItem.setEditText(editText);
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
                    dialogListItem.setEditedText(s.toString());
                }
            };
            editText.addTextChangedListener(textWatcher);
            editText.setTag(textWatcher);
        }
        return view;
    }

    public String getEditText(int position) {
        return list.get(position).getEditedText() == null ? "" : list.get(position).getEditedText();
    }

    public String getSpinnerSelected(int position) {
        return list.get(position).getSelected() == null ? "全部" : list.get(position).getSelected();
    }
}
