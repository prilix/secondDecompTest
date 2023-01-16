package com.warkiz.tickseekbar;

public interface OnSeekChangeListener {
    void onSeeking(SeekParams seekParams);

    void onStartTrackingTouch(TickSeekBar tickSeekBar);

    void onStopTrackingTouch(TickSeekBar tickSeekBar);
}
