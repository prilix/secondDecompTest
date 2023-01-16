package com.jch.racWiFi.amplitude.model;

import com.jch.racWiFi.amplitude.util.Mode;
import com.jch.racWiFi.p010di.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J1\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\bHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\n\"\u0004\b\r\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006 "}, mo36738d2 = {"Lcom/jch/racWiFi/amplitude/model/Scenario;", "", "mode", "Lcom/jch/racWiFi/amplitude/util/Mode;", "isWithoutLaunch", "", "isRationale", "resultCode", "", "(Lcom/jch/racWiFi/amplitude/util/Mode;ZZI)V", "()Z", "setRationale", "(Z)V", "setWithoutLaunch", "getMode", "()Lcom/jch/racWiFi/amplitude/util/Mode;", "setMode", "(Lcom/jch/racWiFi/amplitude/util/Mode;)V", "getResultCode", "()I", "setResultCode", "(I)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: Scenario.kt */
public final class Scenario {
    private boolean isRationale;
    private boolean isWithoutLaunch;
    private Mode mode;
    private int resultCode;

    public static /* synthetic */ Scenario copy$default(Scenario scenario, Mode mode2, boolean z, boolean z2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            mode2 = scenario.mode;
        }
        if ((i2 & 2) != 0) {
            z = scenario.isWithoutLaunch;
        }
        if ((i2 & 4) != 0) {
            z2 = scenario.isRationale;
        }
        if ((i2 & 8) != 0) {
            i = scenario.resultCode;
        }
        return scenario.copy(mode2, z, z2, i);
    }

    public final Mode component1() {
        return this.mode;
    }

    public final boolean component2() {
        return this.isWithoutLaunch;
    }

    public final boolean component3() {
        return this.isRationale;
    }

    public final int component4() {
        return this.resultCode;
    }

    public final Scenario copy(Mode mode2, boolean z, boolean z2, int i) {
        Intrinsics.checkNotNullParameter(mode2, Constants.FCM.MODE);
        return new Scenario(mode2, z, z2, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scenario)) {
            return false;
        }
        Scenario scenario = (Scenario) obj;
        return this.mode == scenario.mode && this.isWithoutLaunch == scenario.isWithoutLaunch && this.isRationale == scenario.isRationale && this.resultCode == scenario.resultCode;
    }

    public int hashCode() {
        int hashCode = this.mode.hashCode() * 31;
        boolean z = this.isWithoutLaunch;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i = (hashCode + (z ? 1 : 0)) * 31;
        boolean z3 = this.isRationale;
        if (!z3) {
            z2 = z3;
        }
        return ((i + (z2 ? 1 : 0)) * 31) + this.resultCode;
    }

    public String toString() {
        return "Scenario(mode=" + this.mode + ", isWithoutLaunch=" + this.isWithoutLaunch + ", isRationale=" + this.isRationale + ", resultCode=" + this.resultCode + ')';
    }

    public Scenario(Mode mode2, boolean z, boolean z2, int i) {
        Intrinsics.checkNotNullParameter(mode2, Constants.FCM.MODE);
        this.mode = mode2;
        this.isWithoutLaunch = z;
        this.isRationale = z2;
        this.resultCode = i;
    }

    public final Mode getMode() {
        return this.mode;
    }

    public final boolean isRationale() {
        return this.isRationale;
    }

    public final boolean isWithoutLaunch() {
        return this.isWithoutLaunch;
    }

    public final void setMode(Mode mode2) {
        Intrinsics.checkNotNullParameter(mode2, "<set-?>");
        this.mode = mode2;
    }

    public final void setRationale(boolean z) {
        this.isRationale = z;
    }

    public final void setWithoutLaunch(boolean z) {
        this.isWithoutLaunch = z;
    }

    public final int getResultCode() {
        return this.resultCode;
    }

    public final void setResultCode(int i) {
        this.resultCode = i;
    }
}
