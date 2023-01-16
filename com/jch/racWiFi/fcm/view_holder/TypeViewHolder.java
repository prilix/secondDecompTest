package com.jch.racWiFi.fcm.view_holder;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.fcm.adapter.TypeAdapter;
import com.jch.racWiFi.fcm.model.Section;
import com.jch.racWiFi.fcm.standard.FcmListener;
import com.jch_hitachi.aircloudglobal.R;

public class TypeViewHolder extends RecyclerView.ViewHolder {
    private TypeAdapter mAdapter;
    @BindView(2131364111)
    TextView mDeviceName;
    private FcmListener<Section, TypeAdapter> mListener;
    @BindView(2131363223)
    ConstraintLayout mOuterLayout;
    @BindView(2131363005)
    ImageView mTickMark;

    public TypeViewHolder(View view, FcmListener<Section, TypeAdapter> fcmListener, TypeAdapter typeAdapter) {
        super(view);
        ButterKnife.bind((Object) this, view);
        this.mListener = fcmListener;
        this.mAdapter = typeAdapter;
    }

    @OnClick({2131363223})
    public void onClickItem(ConstraintLayout constraintLayout) {
        this.mListener.onClick(constraintLayout, (Section) constraintLayout.getTag(), this.mAdapter);
    }

    public void bind(Section section) {
        this.mDeviceName.setText(section.getTitle());
        if (section.isSelectedForFilter()) {
            this.mTickMark.setImageResource(R.drawable.ic_red_tick_svg);
            ConstraintLayout constraintLayout = this.mOuterLayout;
            constraintLayout.setBackgroundColor(constraintLayout.getContext().getResources().getColor(R.color.lyt_grey));
        } else {
            this.mTickMark.setImageResource(0);
            ConstraintLayout constraintLayout2 = this.mOuterLayout;
            constraintLayout2.setBackgroundColor(constraintLayout2.getContext().getResources().getColor(R.color.white));
        }
        this.mOuterLayout.setTag(section);
    }
}
