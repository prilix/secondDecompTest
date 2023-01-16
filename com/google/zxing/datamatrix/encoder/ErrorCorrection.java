package com.google.zxing.datamatrix.encoder;

import com.jch.racWiFi.C1662R2;

public final class ErrorCorrection {
    private static final int[] ALOG = new int[255];
    private static final int[][] FACTORS = {new int[]{C1662R2.attr.checkMarkCompat, 48, 15, 111, 62}, new int[]{23, 68, C1662R2.attr.badgeWidePadding, C1662R2.attr.backgroundOverlayColorAlpha, C1662R2.attr.checkedTextViewStyle, 92, 254}, new int[]{28, 24, C1662R2.attr.brightness, C1662R2.attr.borderRoundPercent, C1662R2.attr.carousel_previousState, C1662R2.attr.chipIconTint, 116, 255, 110, 61}, new int[]{C1662R2.attr.boxBackgroundMode, C1662R2.attr.backgroundTintMode, C1662R2.attr.cameraTargetLat, 12, C1662R2.attr.buttonGravity, C1662R2.attr.borderlessButtonStyle, 39, C1662R2.attr.chipIcon, 60, 97, 120}, new int[]{41, C1662R2.attr.behavior_expandedOffset, C1662R2.attr.behavior_peekHeight, 91, 61, 42, C1662R2.attr.badgeStyle, C1662R2.attr.cardMaxElevation, 97, C1662R2.attr.boxCornerRadiusBottomStart, 100, C1662R2.attr.chipCornerRadius}, new int[]{C1662R2.attr.behavior_hideable, 97, C1662R2.attr.buttonBarStyle, 252, 95, 9, C1662R2.attr.behavior_overlapTop, 119, C1662R2.attr.backgroundTintMode, 45, 18, C1662R2.attr.bubbleColor, 83, C1662R2.attr.brightness}, new int[]{83, C1662R2.attr.buttonIconDimen, 100, 39, C1662R2.attr.buttonBarButtonStyle, 75, 66, 61, C1662R2.attr.chipBackgroundColor, C1662R2.attr.cardMaxElevation, 109, C1662R2.attr.backgroundColor, 94, 254, C1662R2.attr.carousel_touchUp_dampeningFactor, 48, 90, C1662R2.attr.buttonBarButtonStyle}, new int[]{15, C1662R2.attr.buttonIconDimen, C1662R2.attr.chipGroupStyle, 9, C1662R2.attr.checkedChip, 71, C1662R2.attr.borderlessButtonStyle, 2, C1662R2.attr.buttonBarButtonStyle, C1662R2.attr.behavior_skipCollapsed, C1662R2.attr.behavior_expandedOffset, C1662R2.attr.badgeWithTextRadius, 253, 79, 108, 82, 27, C1662R2.attr.boxBackgroundColor, C1662R2.attr.bubbleColor, C1662R2.attr.bottomSheetDialogTheme}, new int[]{52, C1662R2.attr.buttonBarNeutralButtonStyle, 88, C1662R2.attr.cameraTargetLat, 109, 39, C1662R2.attr.boxCollapsedPaddingTop, 21, C1662R2.attr.behavior_halfExpandedRatio, C1662R2.attr.buttonSize, 251, C1662R2.attr.carousel_previousState, C1662R2.attr.behavior_halfExpandedRatio, 21, 5, C1662R2.attr.bottomSheetDialogTheme, 254, 124, 12, C1662R2.attr.boxStrokeColor, C1662R2.attr.boxStrokeWidthFocused, 96, 50, C1662R2.attr.buttonCompat}, new int[]{C1662R2.attr.cardElevation, C1662R2.attr.checkboxStyle, 43, 97, 71, 96, 103, C1662R2.attr.boxBackgroundColor, 37, C1662R2.attr.behavior_autoShrink, C1662R2.attr.bottomInsetScrimEnabled, 53, 75, 34, 249, 121, 17, C1662R2.attr.backgroundTintMode, 110, C1662R2.attr.cardMaxElevation, C1662R2.attr.badgeRadius, 136, 120, C1662R2.attr.behavior_autoShrink, C1662R2.attr.checkedChip, C1662R2.attr.borderlessButtonStyle, 93, 255}, new int[]{C1662R2.attr.chipIcon, 127, C1662R2.attr.chipCornerRadius, C1662R2.attr.carousel_emptyViewsBehavior, C1662R2.attr.backgroundInsetBottom, 250, C1662R2.attr.borderAlpha, C1662R2.attr.boxStrokeColor, 102, 120, 84, C1662R2.attr.boxCornerRadiusTopEnd, C1662R2.attr.carousel_forwardTransition, 251, 80, C1662R2.attr.boxStrokeErrorColor, C1662R2.attr.checkMarkTint, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, C1662R2.attr.boxBackgroundMode, C1662R2.attr.boxStrokeWidthFocused, 59, 25, C1662R2.attr.carousel_touchUp_dampeningFactor, 98, 81, 112}, new int[]{77, C1662R2.attr.buttonCompat, 137, 31, 19, 38, 22, C1662R2.attr.behavior_expandedOffset, C1662R2.attr.chipIconSize, 105, 122, 2, C1662R2.attr.chipIcon, C1662R2.attr.backgroundInsetTop, C1662R2.attr.chipCornerRadius, 8, C1662R2.attr.boxBackgroundMode, 95, 100, 9, C1662R2.attr.borderWidth, 105, C1662R2.attr.cardPreventCornerOverlap, 111, 57, 121, 21, 1, 253, 57, 54, 101, C1662R2.attr.chipIconTint, 202, 69, 50, C1662R2.attr.behavior_autoHide, 177, C1662R2.attr.carousel_touchUp_velocityThreshold, 5, 9, 5}, new int[]{C1662R2.attr.chipIcon, 132, C1662R2.attr.bottomSheetDialogTheme, C1662R2.attr.carousel_previousState, 96, 32, 117, 22, C1662R2.attr.checkedIconTint, C1662R2.attr.backgroundInsetTop, C1662R2.attr.checkedIconTint, C1662R2.attr.checkboxStyle, C1662R2.attr.cameraTargetLat, C1662R2.attr.buttonBarButtonStyle, C1662R2.attr.checkedIconSize, 87, C1662R2.attr.buttonBarPositiveButtonStyle, 106, 16, 147, 118, 23, 37, 90, C1662R2.attr.bottomInsetScrimEnabled, C1662R2.attr.cameraTargetLat, C1662R2.attr.backgroundInsetEnd, 88, 120, 100, 66, C1662R2.attr.backgroundTintMode, C1662R2.attr.bubbleColor, C1662R2.attr.checkedTextViewStyle, 82, 44, C1662R2.attr.boxCollapsedPaddingTop, 87, C1662R2.attr.bubbleTextColor, 147, C1662R2.attr.behavior_skipCollapsed, C1662R2.attr.boxBackgroundMode, 69, C1662R2.attr.cardMaxElevation, 92, 253, C1662R2.attr.carousel_touchUp_dampeningFactor, 19}, new int[]{C1662R2.attr.boxBackgroundMode, 9, C1662R2.attr.carousel_previousState, C1662R2.attr.checkedIconTint, 12, 17, C1662R2.attr.carousel_forwardTransition, C1662R2.attr.cameraZoom, 100, 29, C1662R2.attr.boxBackgroundMode, C1662R2.attr.bottomInsetScrimEnabled, C1662R2.attr.checkMarkTintMode, C1662R2.attr.buttonBarStyle, C1662R2.attr.cardUseCompatPadding, C1662R2.attr.checkedIconEnabled, C1662R2.attr.behavior_autoHide, C1662R2.attr.behavior_saveFlags, 36, C1662R2.attr.carousel_previousState, 38, 200, 132, 54, C1662R2.attr.checkMarkCompat, C1662R2.attr.barLength, C1662R2.attr.carousel_emptyViewsBehavior, 234, 117, C1662R2.attr.cameraMaxZoomPreference, 29, C1662R2.attr.checkedButton, C1662R2.attr.badgeWidePadding, C1662R2.attr.checkedIconTint, 22, C1662R2.attr.behavior_autoHide, 201, 117, 62, C1662R2.attr.cameraTilt, C1662R2.attr.borderLength, 13, 137, C1662R2.attr.chipIcon, 127, 67, C1662R2.attr.chipIconSize, 28, C1662R2.attr.behavior_halfExpandedRatio, 43, C1662R2.attr.cameraMaxZoomPreference, 107, C1662R2.attr.checkedChip, 53, C1662R2.attr.badgeTextColor, 46}, new int[]{C1662R2.attr.chipCornerRadius, 93, C1662R2.attr.bottomAppBarStyle, 50, C1662R2.attr.badgeWidePadding, C1662R2.attr.cardCornerRadius, 39, 118, 202, C1662R2.attr.buttonBarButtonStyle, 201, C1662R2.attr.buttonBarNegativeButtonStyle, C1662R2.attr.badgeTextColor, 108, C1662R2.attr.buttonPanelSideLayout, 37, C1662R2.attr.brightness, 112, C1662R2.attr.backgroundOverlayColorAlpha, C1662R2.attr.checkMarkTintMode, C1662R2.attr.chipIcon, 63, C1662R2.attr.buttonSize, C1662R2.attr.buttonBarNeutralButtonStyle, 250, 106, C1662R2.attr.brightness, C1662R2.attr.carousel_infinite, C1662R2.attr.boxBackgroundMode, 64, 114, 71, C1662R2.attr.blendSrc, 44, 147, 6, 27, C1662R2.attr.carousel_emptyViewsBehavior, 51, 63, 87, 10, 40, C1662R2.attr.backgroundInsetBottom, C1662R2.attr.buttonBarButtonStyle, 17, C1662R2.attr.borderColor, 31, C1662R2.attr.boxCollapsedPaddingTop, C1662R2.attr.bottomInsetScrimEnabled, 4, 107, C1662R2.attr.checkedButton, 7, 94, C1662R2.attr.borderRoundPercent, C1662R2.attr.carousel_touchUpMode, 124, 86, 47, 11, C1662R2.attr.cameraMinZoomPreference}, new int[]{C1662R2.attr.carousel_forwardTransition, C1662R2.attr.checkMarkCompat, C1662R2.attr.bottomSheetStyle, 89, 251, C1662R2.attr.barrierMargin, C1662R2.attr.behavior_saveFlags, 56, 89, 33, 147, C1662R2.attr.chipGroupStyle, C1662R2.attr.behavior_fitToContents, 36, 73, 127, C1662R2.attr.cardMaxElevation, 136, C1662R2.attr.chipIconTint, C1662R2.attr.boxCornerRadiusTopStart, 234, C1662R2.attr.buttonSize, C1662R2.attr.behavior_peekHeight, 177, 68, 122, 93, C1662R2.attr.cardMaxElevation, 15, C1662R2.attr.behavior_skipCollapsed, C1662R2.attr.chainUseRtl, C1662R2.attr.checkedIconMargin, 66, C1662R2.attr.backspaceIcon, C1662R2.attr.behavior_expandedOffset, C1662R2.attr.brightness, 202, C1662R2.attr.borderWidth, C1662R2.attr.boxCornerRadiusTopEnd, 25, C1662R2.attr.carousel_forwardTransition, C1662R2.attr.checkedButton, 96, C1662R2.attr.cardCornerRadius, C1662R2.attr.checkboxStyle, 136, C1662R2.attr.carousel_previousState, C1662R2.attr.checkedIconVisible, C1662R2.attr.boxStrokeColor, C1662R2.attr.chipBackgroundColor, 59, 52, C1662R2.attr.bottomSheetDialogTheme, 25, 49, C1662R2.attr.checkedButton, C1662R2.attr.cardElevation, C1662R2.attr.buttonBarNegativeButtonStyle, 64, 54, 108, C1662R2.attr.behavior_expandedOffset, 132, 63, 96, 103, 82, C1662R2.attr.bubbleColor}};
    private static final int[] FACTOR_SETS = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[] LOG = new int[256];
    private static final int MODULO_VALUE = 301;

    static {
        int i = 1;
        for (int i2 = 0; i2 < 255; i2++) {
            ALOG[i2] = i;
            LOG[i] = i2;
            i <<= 1;
            if (i >= 256) {
                i ^= 301;
            }
        }
    }

    private ErrorCorrection() {
    }

    public static String encodeECC200(String str, SymbolInfo symbolInfo) {
        if (str.length() == symbolInfo.getDataCapacity()) {
            StringBuilder sb = new StringBuilder(symbolInfo.getDataCapacity() + symbolInfo.getErrorCodewords());
            sb.append(str);
            int interleavedBlockCount = symbolInfo.getInterleavedBlockCount();
            if (interleavedBlockCount == 1) {
                sb.append(createECCBlock(str, symbolInfo.getErrorCodewords()));
            } else {
                sb.setLength(sb.capacity());
                int[] iArr = new int[interleavedBlockCount];
                int[] iArr2 = new int[interleavedBlockCount];
                int[] iArr3 = new int[interleavedBlockCount];
                int i = 0;
                while (i < interleavedBlockCount) {
                    int i2 = i + 1;
                    iArr[i] = symbolInfo.getDataLengthForInterleavedBlock(i2);
                    iArr2[i] = symbolInfo.getErrorLengthForInterleavedBlock(i2);
                    iArr3[i] = 0;
                    if (i > 0) {
                        iArr3[i] = iArr3[i - 1] + iArr[i];
                    }
                    i = i2;
                }
                for (int i3 = 0; i3 < interleavedBlockCount; i3++) {
                    StringBuilder sb2 = new StringBuilder(iArr[i3]);
                    for (int i4 = i3; i4 < symbolInfo.getDataCapacity(); i4 += interleavedBlockCount) {
                        sb2.append(str.charAt(i4));
                    }
                    String createECCBlock = createECCBlock(sb2.toString(), iArr2[i3]);
                    int i5 = i3;
                    int i6 = 0;
                    while (i5 < iArr2[i3] * interleavedBlockCount) {
                        sb.setCharAt(symbolInfo.getDataCapacity() + i5, createECCBlock.charAt(i6));
                        i5 += interleavedBlockCount;
                        i6++;
                    }
                }
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
    }

    private static String createECCBlock(CharSequence charSequence, int i) {
        return createECCBlock(charSequence, 0, charSequence.length(), i);
    }

    private static String createECCBlock(CharSequence charSequence, int i, int i2, int i3) {
        int i4 = 0;
        while (true) {
            int[] iArr = FACTOR_SETS;
            if (i4 >= iArr.length) {
                i4 = -1;
                break;
            } else if (iArr[i4] == i3) {
                break;
            } else {
                i4++;
            }
        }
        if (i4 >= 0) {
            int[] iArr2 = FACTORS[i4];
            char[] cArr = new char[i3];
            for (int i5 = 0; i5 < i3; i5++) {
                cArr[i5] = 0;
            }
            for (int i6 = i; i6 < i + i2; i6++) {
                int i7 = i3 - 1;
                char charAt = cArr[i7] ^ charSequence.charAt(i6);
                while (i7 > 0) {
                    if (charAt == 0 || iArr2[i7] == 0) {
                        cArr[i7] = cArr[i7 - 1];
                    } else {
                        char c = cArr[i7 - 1];
                        int[] iArr3 = ALOG;
                        int[] iArr4 = LOG;
                        cArr[i7] = (char) (c ^ iArr3[(iArr4[charAt] + iArr4[iArr2[i7]]) % 255]);
                    }
                    i7--;
                }
                if (charAt == 0 || iArr2[0] == 0) {
                    cArr[0] = 0;
                } else {
                    int[] iArr5 = ALOG;
                    int[] iArr6 = LOG;
                    cArr[0] = (char) iArr5[(iArr6[charAt] + iArr6[iArr2[0]]) % 255];
                }
            }
            char[] cArr2 = new char[i3];
            for (int i8 = 0; i8 < i3; i8++) {
                cArr2[i8] = cArr[(i3 - i8) - 1];
            }
            return String.valueOf(cArr2);
        }
        throw new IllegalArgumentException("Illegal number of error correction codewords specified: ".concat(String.valueOf(i3)));
    }
}
