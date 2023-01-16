package com.jch.racWiFi.p010di.module.builder;

import com.jch.racWiFi.linking.amazon.fragment.AlexaLinkFragment;
import com.jch.racWiFi.linking.amazon.fragment.AlexaLinkedFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
/* renamed from: com.jch.racWiFi.di.module.builder.AlexaFragmentBuilderModule */
public abstract class AlexaFragmentBuilderModule {
    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract AlexaLinkFragment contributeAlexaLinkFragment();

    /* access modifiers changed from: package-private */
    @ContributesAndroidInjector
    public abstract AlexaLinkedFragment contributeAlexaLinkedFragment();
}
