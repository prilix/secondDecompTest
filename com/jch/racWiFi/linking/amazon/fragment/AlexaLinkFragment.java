package com.jch.racWiFi.linking.amazon.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.linking.amazon.model.Fallback;
import com.jch.racWiFi.linking.amazon.view_model.AlexaViewModel;
import com.jch.racWiFi.linking.google.model.SkillStatus;
import com.jch.racWiFi.linking.google.view_model.AppFlipViewModel;
import com.jch.racWiFi.p010di.model.Resource;
import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelProviderFactory;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch_hitachi.aircloudglobal.R;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

public class AlexaLinkFragment extends GenericFragment implements View.OnClickListener {
    private AlexaViewModel mAlexaViewModel;
    @BindView(2131362104)
    Button mAllowButton;
    private AppFlipViewModel mAppFlipViewModel;
    @BindView(2131362105)
    Button mNotNowButton;
    private Unbinder mUnBinder;
    @Inject
    ViewModelProviderFactory providerFactory;

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_alexa_link, viewGroup, false);
        this.mUnBinder = ButterKnife.bind((Object) this, inflate);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mAlexaViewModel = (AlexaViewModel) new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) this.providerFactory).get(AlexaViewModel.class);
        this.mAppFlipViewModel = (AppFlipViewModel) new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) this.providerFactory).get(AppFlipViewModel.class);
        this.mAllowButton.setOnClickListener(this);
        this.mNotNowButton.setOnClickListener(this);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mUnBinder.unbind();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLinkWithAlexaAllow:
                getFallBack();
                return;
            case R.id.btnLinkWithAlexaNotNow:
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    activity.finish();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void test() {
        this.mAppFlipViewModel.getSkillStatus().removeObservers(getViewLifecycleOwner());
        this.mAppFlipViewModel.getSkillStatus().observe(getViewLifecycleOwner(), new AlexaLinkFragment$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: lambda$test$0$com-jch-racWiFi-linking-amazon-fragment-AlexaLinkFragment */
    public /* synthetic */ void mo31583x3031303d(Resource resource) {
        SkillStatus.Body body;
        if (resource != null) {
            int i = C22682.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            if (i == 1) {
                Logger.m47e(GenericFragment.TAG, "test: Error - " + resource.message);
                dismissPleaseWaitDialog();
            } else if (i == 2) {
                showPleaseWaitDialog();
                Logger.m47e(GenericFragment.TAG, "test: Loading - " + resource.message);
            } else if (i == 3) {
                dismissPleaseWaitDialog();
                Logger.m47e(GenericFragment.TAG, "test: persistToken: Success");
                SkillStatus skillStatus = (SkillStatus) resource.response;
                if (skillStatus != null && (body = skillStatus.getBody()) != null) {
                    Logger.m47e(GenericFragment.TAG, "test: " + body.isSkillEnabled());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void getFallBack() {
        this.mAlexaViewModel.getFallback().removeObservers(getViewLifecycleOwner());
        this.mAlexaViewModel.getFallback().observe(getViewLifecycleOwner(), new AlexaLinkFragment$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: com.jch.racWiFi.linking.amazon.fragment.AlexaLinkFragment$2 */
    static /* synthetic */ class C22682 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$di$model$Resource$Status;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.jch.racWiFi.di.model.Resource$Status[] r0 = com.jch.racWiFi.p010di.model.Resource.Status.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$di$model$Resource$Status = r0
                com.jch.racWiFi.di.model.Resource$Status r1 = com.jch.racWiFi.p010di.model.Resource.Status.ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$di$model$Resource$Status     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.di.model.Resource$Status r1 = com.jch.racWiFi.p010di.model.Resource.Status.LOADING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$di$model$Resource$Status     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.di.model.Resource$Status r1 = com.jch.racWiFi.p010di.model.Resource.Status.SUCCESS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.linking.amazon.fragment.AlexaLinkFragment.C22682.<clinit>():void");
        }
    }

    /* renamed from: lambda$getFallBack$1$com-jch-racWiFi-linking-amazon-fragment-AlexaLinkFragment */
    public /* synthetic */ void mo31582x96a35aca(Resource resource) {
        Fallback.Body body;
        if (resource != null) {
            int i = C22682.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            if (i == 1) {
                Logger.m47e(GenericFragment.TAG, "testAlexa: Error - " + resource.message);
                dismissPleaseWaitDialog();
                if (((Fallback) resource.response).getMeta().getCode() == 401) {
                    getCoreActivity().refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            AlexaLinkFragment.this.getFallBack();
                        }
                    }, false);
                }
            } else if (i == 2) {
                showPleaseWaitDialog();
                Logger.m47e(GenericFragment.TAG, "testAlexa: Loading - " + resource.message);
            } else if (i == 3) {
                dismissPleaseWaitDialog();
                Logger.m47e(GenericFragment.TAG, "testAlexa: persistToken: Success");
                Fallback fallback = (Fallback) resource.response;
                if (fallback != null && (body = fallback.getBody()) != null) {
                    parse(body.getLwaFallbackUrl());
                }
            }
        }
    }

    private void parse(String str) {
        String str2 = str + Constants.Alexa.STATE_CODE;
        Logger.m47e(GenericFragment.TAG, "parse: lwaFallbackUrl : " + str2);
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str2)));
    }
}
