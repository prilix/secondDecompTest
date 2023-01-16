package com.jch.racWiFi.energyConsumption.model.request;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B-\u0012\u001e\u0010\u0002\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003j\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u0001`\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0002\u0010\u0007J!\u0010\f\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003j\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u0001`\u0005HÆ\u0003J\t\u0010\r\u001a\u00020\u0004HÆ\u0003J5\u0010\u000e\u001a\u00020\u00002 \b\u0002\u0010\u0002\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003j\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u0001`\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0016\u0010\u0006\u001a\u00020\u00048\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR.\u0010\u0002\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003j\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u0001`\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, mo36738d2 = {"Lcom/jch/racWiFi/energyConsumption/model/request/ECSettings;", "", "racIds", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "familyId", "(Ljava/util/ArrayList;J)V", "getFamilyId", "()J", "getRacIds", "()Ljava/util/ArrayList;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: ECSettings.kt */
public final class ECSettings {
    @SerializedName("familyId")
    private final long familyId;
    @SerializedName("racIds")
    private final ArrayList<Long> racIds;

    public static /* synthetic */ ECSettings copy$default(ECSettings eCSettings, ArrayList<Long> arrayList, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = eCSettings.racIds;
        }
        if ((i & 2) != 0) {
            j = eCSettings.familyId;
        }
        return eCSettings.copy(arrayList, j);
    }

    public final ArrayList<Long> component1() {
        return this.racIds;
    }

    public final long component2() {
        return this.familyId;
    }

    public final ECSettings copy(ArrayList<Long> arrayList, long j) {
        return new ECSettings(arrayList, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ECSettings)) {
            return false;
        }
        ECSettings eCSettings = (ECSettings) obj;
        return Intrinsics.areEqual((Object) this.racIds, (Object) eCSettings.racIds) && this.familyId == eCSettings.familyId;
    }

    public int hashCode() {
        ArrayList<Long> arrayList = this.racIds;
        return ((arrayList == null ? 0 : arrayList.hashCode()) * 31) + ECSettings$$ExternalSyntheticBackport0.m148m(this.familyId);
    }

    public String toString() {
        return "ECSettings(racIds=" + this.racIds + ", familyId=" + this.familyId + ')';
    }

    public ECSettings(ArrayList<Long> arrayList, long j) {
        this.racIds = arrayList;
        this.familyId = j;
    }

    public final ArrayList<Long> getRacIds() {
        return this.racIds;
    }

    public final long getFamilyId() {
        return this.familyId;
    }
}
