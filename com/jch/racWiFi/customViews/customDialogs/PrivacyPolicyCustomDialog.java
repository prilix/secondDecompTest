package com.jch.racWiFi.customViews.customDialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.userManagement.model.PrivacyPolicyModel;
import com.jch_hitachi.aircloudglobal.R;

public class PrivacyPolicyCustomDialog extends Dialog {
    @BindView(2131362127)
    Button cancel;
    @BindView(2131362146)
    ImageButton dismiss;
    @BindView(2131362155)
    Button iAgree;
    @BindView(2131364423)
    TextView mPrivacyPolicy;
    private String mPrivacyPolicyString;
    @BindView(2131364424)
    TextView mPrivacyPolicyTitle;
    private Unbinder mUnbinder;
    private View mView;
    PrivacyPolicyModel.PrivacyPolicyData privacyPolicyData;

    public interface ICustomClickListener {
        void clickListener();
    }

    public PrivacyPolicyCustomDialog(Context context) {
        super(context);
    }

    public void init(Context context, PrivacyPolicyModel.PrivacyPolicyData privacyPolicyData2) {
        this.privacyPolicyData = privacyPolicyData2;
        if (privacyPolicyData2 != null) {
            this.mPrivacyPolicyString = privacyPolicyData2.privacyPolicyMessage;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.privacy_policy, (ViewGroup) null, false);
        this.mView = inflate;
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        initViews();
        setCancelable(false);
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(PrivacyPolicyCustomDialog.this.getWindow().getAttributes());
                layoutParams.dimAmount = 0.2f;
                layoutParams.width = -1;
                layoutParams.height = -1;
                PrivacyPolicyCustomDialog.this.getWindow().setAttributes(layoutParams);
            }
        });
        setContentView(this.mView);
    }

    private void initViews() {
        this.mPrivacyPolicyTitle.setVisibility(8);
        String str = this.mPrivacyPolicyString;
        if (str != null) {
            this.mPrivacyPolicy.setText(Html.fromHtml(str));
        }
    }

    public void setOnCancelListener(final ICustomClickListener iCustomClickListener) {
        this.cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                iCustomClickListener.clickListener();
            }
        });
    }

    public void setOnAgreeListener(final ICustomClickListener iCustomClickListener) {
        this.iAgree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                iCustomClickListener.clickListener();
            }
        });
    }
}
