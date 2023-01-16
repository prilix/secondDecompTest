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
import androidx.core.view.GravityCompat;
import butterknife.Unbinder;
import com.jch_hitachi.aircloudglobal.R;

public class SingleChoiceDialog extends Dialog implements DialogInterface.OnShowListener {
    private String mButtonPositiveStr = "";
    private String mDialogSubTitleStr = "";
    private String mDialogTitleStr = "";
    private Button mPositiveButton;
    public TextView mSubTitle;
    private TextView mTitle;
    private Unbinder mUnbinder;
    private View mView;

    public interface CustomOnClickListener {
        boolean onButtonClickListener(Dialog dialog, View view);
    }

    private void init(Context context) {
        requestWindowFeature(1);
        this.mView = LayoutInflater.from(context).inflate(R.layout.single_choice_dialog_constraint, (ViewGroup) null, false);
        initViews();
        setCancelable(false);
        setOnShowListener(new SingleChoiceDialog$$ExternalSyntheticLambda0(this));
    }

    private void initViews() {
        Button button = (Button) this.mView.findViewById(R.id.button_positive);
        this.mPositiveButton = button;
        button.setVisibility(4);
        this.mTitle = (TextView) this.mView.findViewById(R.id.text_view_confirm_dialog_title);
        this.mSubTitle = (TextView) this.mView.findViewById(R.id.text_view_confirm_dialog_sub_title);
    }

    public SingleChoiceDialog(Context context) {
        super(context);
        init(context);
        setContentView(this.mView);
        getWindow().getDecorView().setBackgroundResource(17170445);
    }

    private void updateView() {
        this.mTitle.setText(this.mDialogTitleStr);
        this.mSubTitle.setText(this.mDialogSubTitleStr);
        this.mPositiveButton.setText(this.mButtonPositiveStr);
    }

    public SingleChoiceDialog(Context context, String str, String str2, String str3, String str4) {
        super(context);
        init(context);
        this.mDialogTitleStr = str;
        this.mDialogSubTitleStr = str2;
        this.mButtonPositiveStr = str3;
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
                if (customOnClickListener.onButtonClickListener(SingleChoiceDialog.this, view)) {
                    SingleChoiceDialog.this.dismiss();
                }
            }
        });
    }

    private void ifMultilineThenLeftAlign() {
        this.mSubTitle.post(new Runnable() {
            public void run() {
                if (SingleChoiceDialog.this.mSubTitle.getLineCount() > 1) {
                    SingleChoiceDialog.this.leftAlignMessage();
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

    public void leftAlignMessage() {
        this.mSubTitle.setGravity(GravityCompat.START);
    }

    public void setMessageString(Spanned spanned) {
        this.mSubTitle.setText(spanned);
    }

    public void onShow(DialogInterface dialogInterface) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(getWindow().getAttributes());
        layoutParams.width = (int) (((double) getContext().getResources().getDisplayMetrics().widthPixels) * 0.95d);
        layoutParams.height = -2;
        getWindow().setAttributes(layoutParams);
        ifMultilineThenLeftAlign();
    }

    public static class Builder {
        private Context mContext;
        private String mMessage;
        private CustomOnClickListener mNegativeButtonClickListener;
        private String mNegativeButtonStr;
        private CustomOnClickListener mPositiveButtonClickListener;
        private String mPositiveButtonStr;
        private String mTitle;
        private int subTitleGravity;

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

        public int getGravity() {
            return this.subTitleGravity;
        }

        public void setPositiveButton(String str, CustomOnClickListener customOnClickListener) {
            this.mPositiveButtonStr = str;
            this.mPositiveButtonClickListener = customOnClickListener;
        }

        public void setNegativeButton(String str, CustomOnClickListener customOnClickListener) {
            this.mNegativeButtonStr = str;
            this.mNegativeButtonClickListener = customOnClickListener;
        }

        public SingleChoiceDialog create() {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mContext);
            singleChoiceDialog.setTitleString(getTitle());
            singleChoiceDialog.setMessageString(getMessage());
            singleChoiceDialog.setPositiveButton(this.mPositiveButtonStr, this.mPositiveButtonClickListener);
            return singleChoiceDialog;
        }
    }
}
