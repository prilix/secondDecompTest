package com.jch.racWiFi.linking.amazon.fragment;

import androidx.lifecycle.Observer;
import com.jch.racWiFi.p010di.model.Resource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AlexaLinkedFragment$$ExternalSyntheticLambda0 implements Observer {
    public final /* synthetic */ AlexaLinkedFragment f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ AlexaLinkedFragment$$ExternalSyntheticLambda0(AlexaLinkedFragment alexaLinkedFragment, String str, String str2, String str3) {
        this.f$0 = alexaLinkedFragment;
        this.f$1 = str;
        this.f$2 = str2;
        this.f$3 = str3;
    }

    public final void onChanged(Object obj) {
        this.f$0.mo31586xdc5f1629(this.f$1, this.f$2, this.f$3, (Resource) obj);
    }
}
