package com.bumptech.glide.gifdecoder;

class GifFrame {
    static final int DISPOSAL_BACKGROUND = 2;
    static final int DISPOSAL_NONE = 1;
    static final int DISPOSAL_PREVIOUS = 3;
    static final int DISPOSAL_UNSPECIFIED = 0;
    int bufferFrameStart;
    int delay;
    int dispose;

    /* renamed from: ih */
    int f132ih;
    boolean interlace;

    /* renamed from: iw */
    int f133iw;

    /* renamed from: ix */
    int f134ix;

    /* renamed from: iy */
    int f135iy;
    int[] lct;
    int transIndex;
    boolean transparency;

    GifFrame() {
    }
}
