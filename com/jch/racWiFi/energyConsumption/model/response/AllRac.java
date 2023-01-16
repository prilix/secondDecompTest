package com.jch.racWiFi.energyConsumption.model.response;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.p010di.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J1\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u00072\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u001e\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\n\"\u0004\b\r\u0010\fR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001f"}, mo36738d2 = {"Lcom/jch/racWiFi/energyConsumption/model/response/AllRac;", "", "racId", "", "racName", "", "isSelected", "", "isEnergyConsumptionSupported", "(ILjava/lang/String;ZZ)V", "()Z", "setEnergyConsumptionSupported", "(Z)V", "setSelected", "getRacId", "()I", "setRacId", "(I)V", "getRacName", "()Ljava/lang/String;", "setRacName", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: AllRac.kt */
public final class AllRac {
    @SerializedName("isEnergyConsumptionSupported")
    private boolean isEnergyConsumptionSupported;
    @SerializedName("isSelected")
    private boolean isSelected;
    @SerializedName("racId")
    private int racId;
    @SerializedName("racName")
    private String racName;

    public static /* synthetic */ AllRac copy$default(AllRac allRac, int i, String str, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = allRac.racId;
        }
        if ((i2 & 2) != 0) {
            str = allRac.racName;
        }
        if ((i2 & 4) != 0) {
            z = allRac.isSelected;
        }
        if ((i2 & 8) != 0) {
            z2 = allRac.isEnergyConsumptionSupported;
        }
        return allRac.copy(i, str, z, z2);
    }

    public final int component1() {
        return this.racId;
    }

    public final String component2() {
        return this.racName;
    }

    public final boolean component3() {
        return this.isSelected;
    }

    public final boolean component4() {
        return this.isEnergyConsumptionSupported;
    }

    public final AllRac copy(int i, String str, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(str, Constants.FCM.RAC_NAME);
        return new AllRac(i, str, z, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AllRac)) {
            return false;
        }
        AllRac allRac = (AllRac) obj;
        return this.racId == allRac.racId && Intrinsics.areEqual((Object) this.racName, (Object) allRac.racName) && this.isSelected == allRac.isSelected && this.isEnergyConsumptionSupported == allRac.isEnergyConsumptionSupported;
    }

    public int hashCode() {
        int hashCode = ((this.racId * 31) + this.racName.hashCode()) * 31;
        boolean z = this.isSelected;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i = (hashCode + (z ? 1 : 0)) * 31;
        boolean z3 = this.isEnergyConsumptionSupported;
        if (!z3) {
            z2 = z3;
        }
        return i + (z2 ? 1 : 0);
    }

    public String toString() {
        return "AllRac(racId=" + this.racId + ", racName=" + this.racName + ", isSelected=" + this.isSelected + ", isEnergyConsumptionSupported=" + this.isEnergyConsumptionSupported + ')';
    }

    public AllRac(int i, String str, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(str, Constants.FCM.RAC_NAME);
        this.racId = i;
        this.racName = str;
        this.isSelected = z;
        this.isEnergyConsumptionSupported = z2;
    }

    public final int getRacId() {
        return this.racId;
    }

    public final void setRacId(int i) {
        this.racId = i;
    }

    public final String getRacName() {
        return this.racName;
    }

    public final void setRacName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.racName = str;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }

    public final boolean isEnergyConsumptionSupported() {
        return this.isEnergyConsumptionSupported;
    }

    public final void setEnergyConsumptionSupported(boolean z) {
        this.isEnergyConsumptionSupported = z;
    }
}
