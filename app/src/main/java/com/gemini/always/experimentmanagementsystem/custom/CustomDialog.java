package com.gemini.always.experimentmanagementsystem.custom;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gemini.always.experimentmanagementsystem.R;
import com.gemini.always.experimentmanagementsystem.adapter.DialogListAdapter;
import com.gemini.always.experimentmanagementsystem.bean.DialogListItem;

import java.util.ArrayList;
import java.util.List;

public class CustomDialog extends Dialog {
    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }

    public interface DialogIF {
        void onPositive(CustomDialog dialog, List<String> list);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String positiveButtonText;
        private String negativeButtonText;
        private List<String> editList = new ArrayList<>();
        private List<String> spinnerTextList = new ArrayList<>();
        private List<List<String>> spinnerDataList = new ArrayList<>();
        private View contentView;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;
        private DialogIF dialogIF;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder serOnPositive(String positiveButtonText, DialogIF dialogIF) {
            this.positiveButtonText = positiveButtonText;
            this.dialogIF = dialogIF;
            return this;
        }

        public Builder setEditList(List<String> editList) {
            this.editList = editList;
            return this;
        }

        public Builder setSpinnerTextList(List<String> spinnerTextList) {
            this.spinnerTextList = spinnerTextList;
            return this;
        }

        public Builder setSpinnerDataList(List<List<String>> spinnerDataList) {
            this.spinnerDataList = spinnerDataList;
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final CustomDialog dialog = new CustomDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_custom_base_add, null);
            dialog.addContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            // set the dialog title
            ((TextView) layout.findViewById(R.id.title)).setText(title);
            // set the confirm button

            List<DialogListItem> list = new ArrayList<>();
            for (int i = 0; i < spinnerTextList.size(); i++) {
                DialogListItem dialogListItem = new DialogListItem(spinnerTextList.get(i), null);
                dialogListItem.setType(0);
                dialogListItem.setDataList(spinnerDataList.get(i));
                list.add(dialogListItem);
            }
            for (String string : editList) {
                DialogListItem dialogListItem = new DialogListItem(string, null);
                dialogListItem.setType(1);
                list.add(dialogListItem);
            }
            ListView listView = layout.findViewById(R.id.list_view);
            DialogListAdapter adapter = new DialogListAdapter(context, R.layout.item_dialog_custom_have_edit_text, list);
            listView.setAdapter(adapter);

            if (dialogIF != null) {
                ((TextView) layout.findViewById(R.id.btn_positive))
                        .setText(positiveButtonText);
                ((TextView) layout.findViewById(R.id.btn_positive))
                        .setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                List<String> list = new ArrayList<>();
                                for (int i = 0; i < spinnerTextList.size() + editList.size(); i++) {
                                    if (i < spinnerTextList.size()) {
                                        list.add(adapter.getSpinnerSelected(i));
                                    } else {
                                        list.add(adapter.getEditText(i));
                                    }
                                }
                                dialogIF.onPositive(dialog, list);
                            }
                        });
            }

            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.btn_positive))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.btn_positive))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.btn_positive).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.btn_negative))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.btn_negative))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.btn_negative).setVisibility(
                        View.GONE);
            }
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
