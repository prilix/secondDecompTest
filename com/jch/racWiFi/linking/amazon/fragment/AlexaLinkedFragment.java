package com.jch.racWiFi.linking.amazon.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.jch.racWiFi.linking.amazon.model.SkillResponse;
import com.jch.racWiFi.linking.amazon.model.TokenResponse;
import com.jch.racWiFi.linking.amazon.view_model.AlexaViewModel;
import com.jch.racWiFi.p010di.model.Resource;
import com.jch.racWiFi.p010di.module.view_model.factory.ViewModelProviderFactory;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch_hitachi.aircloudglobal.R;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

public class AlexaLinkedFragment extends GenericFragment implements View.OnClickListener {
    private static final String TAG = "AlexaLinkedFragment";
    @BindView(2131362106)
    Button mAlexaCloseButton;
    private AlexaViewModel mAlexaViewModel;
    @BindView(2131362103)
    Button mErrorCloseButton;
    @BindView(2131362039)
    ConstraintLayout mErrorView;
    @BindView(2131362043)
    ConstraintLayout mLinkedView;
    private Unbinder mUnBinder;
    @Inject
    ViewModelProviderFactory providerFactory;

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_alexa_linked, viewGroup, false);
        this.mUnBinder = ButterKnife.bind((Object) this, inflate);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mAlexaViewModel = (AlexaViewModel) new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) this.providerFactory).get(AlexaViewModel.class);
        this.mErrorCloseButton.setOnClickListener(this);
        this.mAlexaCloseButton.setOnClickListener(this);
        getCallBack(getArguments());
    }

    private void getCallBack(Bundle bundle) {
        if (bundle != null) {
            Uri uri = (Uri) bundle.getParcelable(Constants.Alexa.CALLBACK);
            Logger.m47e(TAG, "onViewCreated: URI - " + uri);
            if (uri != null) {
                String queryParameter = uri.getQueryParameter(Constants.Alexa.ERROR_DESCRIPTION);
                String queryParameter2 = uri.getQueryParameter("error");
                if (queryParameter == null || queryParameter2 == null) {
                    getAmazonToken(uri.getQueryParameter(Constants.Alexa.AUTH_CODE), uri.getQueryParameter(Constants.Alexa.REDIRECT_URL), uri.getQueryParameter(Constants.Alexa.OAUTH_CODE));
                    return;
                }
                this.mLinkedView.setVisibility(4);
                this.mErrorView.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void getAmazonToken(String str, String str2, String str3) {
        Logger.m47e(TAG, "getAmazonToken: Redirect URI " + str2);
        Logger.m47e(TAG, "getAmazonToken: amazonAuthorizationCode " + str);
        this.mAlexaViewModel.getAmazonToken(str, str2).removeObservers(getViewLifecycleOwner());
        this.mAlexaViewModel.getAmazonToken(str, str2).observe(getViewLifecycleOwner(), new AlexaLinkedFragment$$ExternalSyntheticLambda0(this, str, str2, str3));
    }

    /* renamed from: lambda$getAmazonToken$0$com-jch-racWiFi-linking-amazon-fragment-AlexaLinkedFragment */
    public /* synthetic */ void mo31586xdc5f1629(final String str, final String str2, final String str3, Resource resource) {
        TokenResponse.Body body;
        if (resource != null) {
            int i = C22713.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            if (i == 1) {
                Logger.m47e(TAG, "getAmazonToken: Error - " + resource.message);
                dismissPleaseWaitDialog();
                if (resource.response != null && ((TokenResponse) resource.response).getMeta().getCode() == 401) {
                    getCoreActivity().refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            AlexaLinkedFragment.this.getAmazonToken(str, str2, str3);
                        }
                    }, false);
                }
            } else if (i == 2) {
                showPleaseWaitDialog();
                Logger.m47e(TAG, "getAmazonToken: Loading - " + resource.message);
            } else if (i == 3) {
                Logger.m47e(TAG, "getAmazonToken : Success");
                TokenResponse tokenResponse = (TokenResponse) resource.response;
                if (tokenResponse != null && (body = tokenResponse.getBody()) != null) {
                    Logger.m47e(TAG, "getAmazonToken: " + body.getAmazonToken());
                    skillEnable(str3, str2, body.getAmazonToken());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void skillEnable(String str, String str2, String str3) {
        Logger.m47e(TAG, "skillEnable: oAuthCode : " + str);
        Logger.m47e(TAG, "skillEnable: Uri : " + str2);
        Logger.m47e(TAG, "skillEnable: Token : " + str3);
        this.mAlexaViewModel.skillEnable(str, str2, str3).removeObservers(getViewLifecycleOwner());
        this.mAlexaViewModel.skillEnable(str, str2, str3).observe(getViewLifecycleOwner(), new AlexaLinkedFragment$$ExternalSyntheticLambda1(this, str, str2, str3));
    }

    /* renamed from: com.jch.racWiFi.linking.amazon.fragment.AlexaLinkedFragment$3 */
    static /* synthetic */ class C22713 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.linking.amazon.fragment.AlexaLinkedFragment.C22713.<clinit>():void");
        }
    }

    /* renamed from: lambda$skillEnable$1$com-jch-racWiFi-linking-amazon-fragment-AlexaLinkedFragment */
    public /* synthetic */ void mo31587x68b10ba5(final String str, final String str2, final String str3, Resource resource) {
        SkillResponse.Body body;
        if (resource != null) {
            int i = C22713.$SwitchMap$com$jch$racWiFi$di$model$Resource$Status[resource.status.ordinal()];
            if (i == 1) {
                Logger.m47e(TAG, "skillEnable: Error - " + resource.message);
                dismissPleaseWaitDialog();
                if (resource.response != null && ((SkillResponse) resource.response).getMeta().getCode() == 401) {
                    getCoreActivity().refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            AlexaLinkedFragment.this.skillEnable(str, str2, str3);
                        }
                    }, false);
                }
            } else if (i == 2) {
                showPleaseWaitDialog();
                Logger.m47e(TAG, "skillEnable: Loading - " + resource.message);
            } else if (i == 3) {
                dismissPleaseWaitDialog();
                this.mErrorView.setVisibility(4);
                this.mLinkedView.setVisibility(0);
                Logger.m47e(TAG, "skillEnable: Success");
                SkillResponse skillResponse = (SkillResponse) resource.response;
                if (skillResponse != null && (body = skillResponse.getBody()) != null) {
                    Logger.m47e(TAG, "skillEnable message :" + body.getMessage());
                }
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mUnBinder.unbind();
    }

    public void onClick(View view) {
        FragmentActivity activity;
        int id = view.getId();
        if ((id == R.id.btnAlexaErrorClose || id == R.id.btnLinkedWithAlexaClose) && (activity = getActivity()) != null) {
            activity.finish();
        }
    }
}
