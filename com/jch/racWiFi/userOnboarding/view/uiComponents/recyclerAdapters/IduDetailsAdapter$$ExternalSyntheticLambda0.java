package com.jch.racWiFi.userOnboarding.view.uiComponents.recyclerAdapters;

import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;
import java.util.Comparator;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class IduDetailsAdapter$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ IduDetailsAdapter$$ExternalSyntheticLambda0 INSTANCE = new IduDetailsAdapter$$ExternalSyntheticLambda0();

    private /* synthetic */ IduDetailsAdapter$$ExternalSyntheticLambda0() {
    }

    public final int compare(Object obj, Object obj2) {
        return ((IduDetailsModel) obj).getId().compareTo(((IduDetailsModel) obj2).getId());
    }
}
