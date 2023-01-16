package com.jch.racWiFi.customViews.HintCase.hintcontentholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jch.racWiFi.customViews.HintCase.C1687HintCase;

public class ByLayoutHintContentHolder extends HintContentHolder {
    int resLayoutId;

    public ByLayoutHintContentHolder(int i) {
        this.resLayoutId = i;
    }

    public View getView(Context context, C1687HintCase hintCase, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(this.resLayoutId, viewGroup, false);
    }
}
