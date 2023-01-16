package com.jch.racWiFi.customViews.customDialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import com.jch_hitachi.aircloudglobal.R;

public class ConfirmationDialog extends Dialog implements DialogInterface.OnShowListener {
    private String mButtonNegativeStr = "";
    private String mButtonPositiveStr = "";
    private String mDialogOperationalModeTitle = "";
    private String mDialogOperationalModeValue = "";
    private String mDialogSubTitleStr = "";
    private String mDialogTitleStr = "";
    private Button mNegativeButton;
    private TextView mOperationalModeTitle;
    private TextView mOperationalModeValue;
    private Button mPositiveButton;
    private TextView mSubTitle;
    private TextView mTitle;
    private Unbinder mUnbinder;
    private View mView;
    private boolean operationalModeVisiblity = false;

    public interface CustomOnClickListener {
        boolean onButtonClickListener(Dialog dialog, View view);
    }

    public void onShow(DialogInterface dialogInterface) {
    }

    public void setParentView(final View view) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(ConfirmationDialog.this.getWindow().getAttributes());
                layoutParams.dimAmount = 0.2f;
                layoutParams.width = -1;
                layoutParams.height = -2;
                ConfirmationDialog.this.getWindow().addFlags(2);
                ConfirmationDialog.this.getWindow().setAttributes(layoutParams);
                View view = view;
                if (view != null) {
                    view.setBackgroundResource(R.drawable.white_blur);
                }
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                View view = view;
                if (view != null) {
                    view.setBackgroundResource(R.drawable.transparent);
                }
            }
        });
    }

    public void setParentView(View view, String str) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(ConfirmationDialog.this.getWindow().getAttributes());
                layoutParams.width = -1;
                layoutParams.height = -2;
                ConfirmationDialog.this.getWindow().setAttributes(layoutParams);
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
    }

    private void init(Context context) {
        requestWindowFeature(1);
        this.mView = LayoutInflater.from(context).inflate(R.layout.confirm_dialog_constraint, (ViewGroup) null, false);
        initViews();
        setCancelable(false);
    }

    private void initViews() {
        this.mPositiveButton = (Button) this.mView.findViewById(R.id.button_positive);
        this.mNegativeButton = (Button) this.mView.findViewById(R.id.button_negative);
        this.mPositiveButton.setVisibility(0);
        this.mNegativeButton.setVisibility(0);
        this.mTitle = (TextView) this.mView.findViewById(R.id.text_view_confirm_dialog_title);
        this.mOperationalModeTitle = (TextView) this.mView.findViewById(R.id.text_view_selected_operation_title);
        this.mOperationalModeValue = (TextView) this.mView.findViewById(R.id.text_view_selected_operation);
        this.mSubTitle = (TextView) this.mView.findViewById(R.id.text_view_confirm_dialog_sub_title);
        if (this.operationalModeVisiblity) {
            this.mOperationalModeTitle.setVisibility(0);
            this.mOperationalModeValue.setVisibility(0);
        }
    }

    public ConfirmationDialog(Context context) {
        super(context);
        init(context);
        setContentView(this.mView);
        getWindow().getDecorView().setBackgroundResource(17170445);
    }

    public ConfirmationDialog(Context context, boolean z) {
        super(context);
        this.operationalModeVisiblity = z;
        init(context);
        setContentView(this.mView);
        getWindow().getDecorView().setBackgroundResource(17170445);
    }

    private void updateView() {
        this.mTitle.setText(this.mDialogTitleStr);
        this.mSubTitle.setText(this.mDialogSubTitleStr);
        this.mPositiveButton.setText(this.mButtonPositiveStr);
        this.mNegativeButton.setText(this.mButtonNegativeStr);
        this.mOperationalModeTitle.setText(this.mDialogOperationalModeTitle);
        this.mOperationalModeValue.setText(this.mDialogOperationalModeValue);
    }

    public ConfirmationDialog(Context context, String str, String str2, String str3, String str4) {
        super(context);
        init(context);
        this.mDialogTitleStr = str;
        this.mDialogSubTitleStr = str2;
        this.mButtonPositiveStr = str3;
        this.mButtonNegativeStr = str4;
        updateView();
    }

    public void show() {
        super.show();
    }

    public void setPositiveButton(String str, final CustomOnClickListener customOnClickListener) {
        this.mButtonPositiveStr = str;
        this.mPositiveButton.setText(str);
        this.mPositiveButton.setVisibility(0);
        this.mPositiveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (customOnClickListener.onButtonClickListener(ConfirmationDialog.this, view)) {
                    ConfirmationDialog.this.dismiss();
                }
            }
        });
    }

    public void setNegativeButton(String str, final CustomOnClickListener customOnClickListener) {
        this.mButtonNegativeStr = str;
        this.mNegativeButton.setText(str);
        this.mNegativeButton.setVisibility(0);
        this.mNegativeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (customOnClickListener.onButtonClickListener(ConfirmationDialog.this, view)) {
                    ConfirmationDialog.this.dismiss();
                }
            }
        });
    }

    public void setTitleString(String str) {
        this.mDialogTitleStr = str;
        this.mTitle.setText(str);
    }

    public void setmDialogTitleStr(String str) {
        this.mDialogTitleStr = str;
    }

    public void setmDialogSubTitleStr(String str) {
        this.mDialogSubTitleStr = str;
    }

    public void setMessageString(String str) {
        this.mDialogSubTitleStr = str;
        this.mSubTitle.setText(str);
    }

    public void setMessageString(int i) {
        this.mSubTitle.setText(i);
    }

    public void setMessageSpanned(Spanned spanned) {
        this.mDialogSubTitleStr = spanned.toString();
        this.mSubTitle.setText(spanned);
    }

    public void setmDialogOperationalModeTitleString(String str) {
        this.mDialogOperationalModeTitle = str;
        this.mOperationalModeTitle.setText(str);
    }

    public void setmDialogOperationalModeTitleValueString(String str) {
        this.mDialogOperationalModeValue = str;
        this.mOperationalModeValue.setText(str);
    }

    public boolean isOperationModeVisiblity() {
        return this.operationalModeVisiblity;
    }

    public static class Builder {
        private Context mContext;
        private String mMessage;
        private CustomOnClickListener mNegativeButtonClickListener;
        private String mNegativeButtonStr;
        private CustomOnClickListener mPositiveButtonClickListener;
        private String mPositiveButtonStr;
        private String mTitle;

        public Builder(Context context) {
            this.mContext = context;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public void setTitle(String str) {
            this.mTitle = str;
        }

        public String getMessage() {
            return this.mMessage;
        }

        public void setMessage(String str) {
            this.mMessage = str;
        }

        public void setPositiveButton(String str, CustomOnClickListener customOnClickListener) {
            this.mPositiveButtonStr = str;
            this.mPositiveButtonClickListener = customOnClickListener;
        }

        public void setNegativeButton(String str, CustomOnClickListener customOnClickListener) {
            this.mNegativeButtonStr = str;
            this.mNegativeButtonClickListener = customOnClickListener;
        }

        public ConfirmationDialog create() {
            ConfirmationDialog confirmationDialog = new ConfirmationDialog(this.mContext);
            confirmationDialog.setTitleString(getTitle());
            confirmationDialog.setMessageString(getMessage());
            confirmationDialog.setPositiveButton(this.mPositiveButtonStr, this.mPositiveButtonClickListener);
            confirmationDialog.setNegativeButton(this.mNegativeButtonStr, this.mNegativeButtonClickListener);
            return confirmationDialog;
        }
    }
}
