package com.jch.racWiFi.customViews.customDialogs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.iduManagement.model.IDUNotificationType;
import com.jch_hitachi.aircloudglobal.R;

public class NotificationBannerPopUp extends PopupWindow {
    public int height;
    public IDUNotificationType iduNotificationType;
    private ImageButton mClear;
    private ImageView mImage;
    private int mPopUpImage;
    private String mPopUpSubTitleStr = "";
    private String mPopUpTitleStr = "";
    private TextView mSubTitle;
    private TextView mTitle;
    private Unbinder mUnbinder;
    private View mView;
    private int notificationId;
    private ConstraintLayout parent;

    public ImageButton getClearButton() {
        return this.mClear;
    }

    public void hideCloseButton() {
        this.mClear.setVisibility(4);
    }

    public void close() {
        this.mClear.performClick();
    }

    public int getNotificationId() {
        return this.notificationId;
    }

    public void setNotificationId(int i) {
        this.notificationId = i;
    }

    private void init(Context context) {
        setBackgroundDrawable(new ColorDrawable(-1));
        View inflate = LayoutInflater.from(context).inflate(R.layout.cleaning_pop_up, (ViewGroup) null, false);
        this.mView = inflate;
        inflate.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.height = this.mView.getMeasuredHeight();
        this.height = Math.round(ViewUtils.convertDpToPixel(130.0f, context));
        initViews();
        this.mClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NotificationBannerPopUp.this.dismiss();
            }
        });
    }

    public void setOnClickListenerClose(View.OnClickListener onClickListener) {
        this.mClear.setTag(Integer.valueOf(this.notificationId));
        this.mClear.setOnClickListener(onClickListener);
    }

    private void initViews() {
        this.mTitle = (TextView) this.mView.findViewById(R.id.text_view_cleaning_pop_up_title);
        this.mSubTitle = (TextView) this.mView.findViewById(R.id.text_view_cleaning_pop_up_sub_title);
        this.mImage = (ImageView) this.mView.findViewById(R.id.image_view_cleaning_pop_up);
        this.mClear = (ImageButton) this.mView.findViewById(R.id.image_button_close_cleaning_pop_up);
    }

    private void updateView() {
        this.mTitle.setText(this.mPopUpTitleStr);
        this.mSubTitle.setText(this.mPopUpSubTitleStr);
        this.mImage.setImageResource(this.mPopUpImage);
    }

    public NotificationBannerPopUp(Context context) {
        super(context);
        init(context);
        setContentView(this.mView);
    }

    public NotificationBannerPopUp(Context context, String str, String str2, int i) {
        super(context);
        init(context);
        this.mPopUpTitleStr = str;
        this.mPopUpSubTitleStr = str2;
        this.mPopUpImage = i;
        updateView();
    }

    public void setTitleString(String str) {
        this.mPopUpTitleStr = str;
        this.mTitle.setText(str);
    }

    public void setMessageString(String str) {
        this.mPopUpSubTitleStr = str;
        this.mSubTitle.setText(str);
    }

    public void setImage(int i) {
        this.mPopUpImage = i;
        this.mImage.setImageResource(i);
    }

    public void showPopup(Activity activity) {
        setBackgroundDrawable((Drawable) null);
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i = point.x;
        setWidth(i - 90);
        setOutsideTouchable(false);
        setWindowLayoutMode(i, this.height);
        showAtLocation(this.mView, 48, 0, (int) activity.getResources().getDimension(R.dimen.pop_up_location));
    }

    public boolean equals(Object obj) {
        return obj != null && this.notificationId == ((NotificationBannerPopUp) obj).notificationId;
    }

    public void setNotificationType(IDUNotificationType iDUNotificationType) {
        this.iduNotificationType = iDUNotificationType;
    }
}
