package com.jch.racWiFi.energyConsumption.model.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0012"}, mo36738d2 = {"Lcom/jch/racWiFi/energyConsumption/model/response/AllRacResponseBody;", "", "allRacList", "", "Lcom/jch/racWiFi/energyConsumption/model/response/AllRac;", "(Ljava/util/List;)V", "getAllRacList", "()Ljava/util/List;", "setAllRacList", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: AllRacResponseBody.kt */
public final class AllRacResponseBody {
    @SerializedName("allRacList")
    private List<AllRac> allRacList;

    public static /* synthetic */ AllRacResponseBody copy$default(AllRacResponseBody allRacResponseBody, List<AllRac> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = allRacResponseBody.allRacList;
        }
        return allRacResponseBody.copy(list);
    }

    public final List<AllRac> component1() {
        return this.allRacList;
    }

    public final AllRacResponseBody copy(List<AllRac> list) {
        Intrinsics.checkNotNullParameter(list, "allRacList");
        return new AllRacResponseBody(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof AllRacResponseBody) && Intrinsics.areEqual((Object) this.allRacList, (Object) ((AllRacResponseBody) obj).allRacList);
    }

    public int hashCode() {
        return this.allRacList.hashCode();
    }

    public String toString() {
        return "AllRacResponseBody(allRacList=" + this.allRacList + ')';
    }

    public AllRacResponseBody(List<AllRac> list) {
        Intrinsics.checkNotNullParameter(list, "allRacList");
        this.allRacList = list;
    }

    public final List<AllRac> getAllRacList() {
        return this.allRacList;
    }

    public final void setAllRacList(List<AllRac> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.allRacList = list;
    }
}
