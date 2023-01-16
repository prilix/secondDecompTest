package com.warkiz.tickseekbar;

public class SeekParams {
    public boolean fromUser;
    public int progress;
    public float progressFloat;
    public TickSeekBar seekBar;
    public int thumbPosition;
    public String tickText;

    SeekParams(TickSeekBar tickSeekBar) {
        this.seekBar = tickSeekBar;
    }
}
