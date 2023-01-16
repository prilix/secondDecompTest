package com.google.zxing.datamatrix.encoder;

import com.jch.racWiFi.C1662R2;

final class DataMatrixSymbolInfo144 extends SymbolInfo {
    public int getDataLengthForInterleavedBlock(int i) {
        return i <= 8 ? C1662R2.attr.behavior_hideable : C1662R2.attr.behavior_halfExpandedRatio;
    }

    public int getInterleavedBlockCount() {
        return 10;
    }

    DataMatrixSymbolInfo144() {
        super(false, C1662R2.C1663color.m3_ref_palette_dynamic_neutral0, C1662R2.attr.isMaterialTheme, 22, 22, 36, -1, 62);
    }
}
