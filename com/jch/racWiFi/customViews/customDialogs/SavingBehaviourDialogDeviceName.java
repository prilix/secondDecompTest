package com.jch.racWiFi.customViews.customDialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import com.jch_hitachi.aircloudglobal.R;

public class SavingBehaviourDialogDeviceName extends Dialog implements DialogInterface.OnShowListener {
    private String mButtonNegativeStr = "";
    private String mButtonPositiveStr = "";
    private String mDialogSubTitleStr = "";
    private String mDialogTitleStr = "";
    private Button mNegativeButton;
    private TextView mOperation;
    private String mOperationStr = "";
    private TextView mOperationTilte;
    private String mOperationTitleStr = "";
    private Button mPositiveButton;
    private TextView mSubTitle;
    private TextView mTitle;
    private Unbinder mUnbinder;
    private View mView;

    public interface CustomOnClickListener {
        boolean onButtonClickListener(Dialog dialog, View view);
    }

    public void onShow(DialogInterface dialogInterface) {
    }

    public void setParentView(final View view) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(SavingBehaviourDialogDeviceName.this.getWindow().getAttributes());
                layoutParams.dimAmount = 0.2f;
                layoutParams.width = -1;
                layoutParams.height = -2;
                SavingBehaviourDialogDeviceName.this.getWindow().addFlags(2);
                SavingBehaviourDialogDeviceName.this.getWindow().setAttributes(layoutParams);
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

    private void init(Context context) {
        requestWindowFeature(1);
        this.mView = LayoutInflater.from(context).inflate(R.layout.saving_behaviour_dialog_device_name, (ViewGroup) null, false);
        initViews();
        setCancelable(false);
    }

    private void initViews() {
        this.mPositiveButton = (Button) this.mView.findViewById(R.id.button_positive);
        this.mNegativeButton = (Button) this.mView.findViewById(R.id.button_negative);
        this.mPositiveButton.setVisibility(0);
        this.mNegativeButton.setVisibility(0);
        this.mTitle = (TextView) this.mView.findViewById(R.id.text_view_confirm_dialog_title);
        this.mSubTitle = (TextView) this.mView.findViewById(R.id.text_view_confirm_dialog_sub_title);
        this.mOperationTilte = (TextView) this.mView.findViewById(R.id.text_view_selected_operation_title);
        this.mOperation = (TextView) this.mView.findViewById(R.id.text_view_selected_operation);
    }

    public SavingBehaviourDialogDeviceName(Context context) {
        super(context);
        init(context);
        setContentView(this.mView);
        getWindow().getDecorView().setBackgroundResource(17170445);
    }

    private void updateView() {
        this.mTitle.setText(this.mDialogTitleStr);
        this.mSubTitle.setText(this.mDialogSubTitleStr);
        this.mOperationTilte.setText(this.mOperationTitleStr);
        this.mOperation.setText(this.mOperationStr);
        this.mPositiveButton.setText(this.mButtonPositiveStr);
        this.mNegativeButton.setText(this.mButtonNegativeStr);
    }

    public SavingBehaviourDialogDeviceName(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        super(context);
        init(context);
        this.mDialogTitleStr = str;
        this.mDialogSubTitleStr = str2;
        this.mOperationTitleStr = str3;
        this.mOperationStr = str4;
        this.mButtonPositiveStr = str5;
        this.mButtonNegativeStr = str6;
        updateView();
    }

    public void show() {
        updateView();
        super.show();
    }

    public void setPositiveButton(String str, final CustomOnClickListener customOnClickListener) {
        this.mButtonPositiveStr = str;
        this.mPositiveButton.setText(str);
        this.mPositiveButton.setVisibility(0);
        this.mPositiveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (customOnClickListener.onButtonClickListener(SavingBehaviourDialogDeviceName.this, view)) {
                    SavingBehaviourDialogDeviceName.this.dismiss();
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
                if (customOnClickListener.onButtonClickListener(SavingBehaviourDialogDeviceName.this, view)) {
                    SavingBehaviourDialogDeviceName.this.dismiss();
                }
            }
        });
    }

    public void setTitleString(String str) {
        this.mDialogTitleStr = str;
        this.mTitle.setText(str);
    }

    public void setMessageString(String str) {
        this.mDialogSubTitleStr = str;
        this.mSubTitle.setText(str);
    }

    public void setOperationTitleString(String str) {
        this.mOperationTitleStr = str;
        this.mOperationTilte.setText(str);
    }

    public void setOperationString(String str) {
        this.mOperationStr = str;
        this.mOperation.setText(str);
    }

    public static class Builder {
        private Context mContext;
        private String mMessage;
        private CustomOnClickListener mNegativeButtonClickListener;
        private String mNegativeButtonStr;
        private String mOperation;
        private String mOperationTitle;
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

        public String getOperationTitle() {
            return this.mOperationTitle;
        }

        public void setOperationTitle(String str) {
            this.mOperationTitle = str;
        }

        public String getOperation() {
            return this.mOperation;
        }

        public void setOperation(String str) {
            this.mOperation = str;
        }

        public void setPositiveButton(String str, CustomOnClickListener customOnClickListener) {
            this.mPositiveButtonStr = str;
            this.mPositiveButtonClickListener = customOnClickListener;
        }

        public void setNegativeButton(String str, CustomOnClickListener customOnClickListener) {
            this.mNegativeButtonStr = str;
            this.mNegativeButtonClickListener = customOnClickListener;
        }

        public SavingBehaviourDialogDeviceName create() {
            SavingBehaviourDialogDeviceName savingBehaviourDialogDeviceName = new SavingBehaviourDialogDeviceName(this.mContext);
            savingBehaviourDialogDeviceName.setTitleString(getTitle());
            savingBehaviourDialogDeviceName.setMessageString(getMessage());
            savingBehaviourDialogDeviceName.setOperationTitleString(getOperationTitle());
            savingBehaviourDialogDeviceName.setOperationString(getOperation());
            savingBehaviourDialogDeviceName.setPositiveButton(this.mPositiveButtonStr, this.mPositiveButtonClickListener);
            savingBehaviourDialogDeviceName.setNegativeButton(this.mNegativeButtonStr, this.mNegativeButtonClickListener);
            return savingBehaviourDialogDeviceName;
        }
    }
}
