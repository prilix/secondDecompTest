package com.jch.racWiFi.settings.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.jch.racWiFi.C1662R2;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.SharedPreference.SharedPref;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.settings.adapter.LanguageSelectionAdapter;
import com.jch.racWiFi.settings.model.LanguageModel;
import com.jch_hitachi.aircloudglobal.R;

public class LanguageSelectionDialog extends AlertDialog {
    private boolean isFromSettings = false;
    @BindView(2131362123)
    public Button mApplyLanguage;
    @BindView(2131362249)
    public View mCloseButton;
    private LanguageSelectionAdapter mLanguageSelectionAdapter;
    private OnLanguageSelectedApplyListener mOnLanguageSelectedApplyListener;
    @BindView(2131363102)
    public RecyclerView mRecyclerView;
    private Unbinder mUnbinder;

    public interface OnLanguageSelectedApplyListener {
        void onLanguageApply(LanguageModel languageModel);
    }

    public void setShowingDialogFromSettings(boolean z) {
        this.isFromSettings = z;
    }

    @OnClick({2131362249})
    public void onClickClose() {
        if (this.isFromSettings) {
            dismiss();
        } else {
            sendLangUpdateCallback(LocaleConfiguration.DEFAULT_LANGUAGE_MODEL);
        }
    }

    @OnClick({2131362123})
    public void onClickApply() {
        LanguageSelectionAdapter languageSelectionAdapter = this.mLanguageSelectionAdapter;
        if (languageSelectionAdapter != null) {
            sendLangUpdateCallback(languageSelectionAdapter.getSelectedLanguageModel());
        }
    }

    private void sendLangUpdateCallback(LanguageModel languageModel) {
        OnLanguageSelectedApplyListener onLanguageSelectedApplyListener = this.mOnLanguageSelectedApplyListener;
        if (onLanguageSelectedApplyListener != null) {
            onLanguageSelectedApplyListener.onLanguageApply(languageModel);
        }
    }

    public void setLanguageSelectedApplyListener(OnLanguageSelectedApplyListener onLanguageSelectedApplyListener) {
        this.mOnLanguageSelectedApplyListener = onLanguageSelectedApplyListener;
    }

    public LanguageSelectionDialog(Context context) {
        super(context);
        initViews(context);
    }

    protected LanguageSelectionDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        initViews(context);
    }

    protected LanguageSelectionDialog(Context context, int i) {
        super(context, i);
        initViews(context);
    }

    public void setParentView(final View view) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(LanguageSelectionDialog.this.getWindow().getAttributes());
                layoutParams.dimAmount = 0.1f;
                layoutParams.width = -1;
                layoutParams.height = -2;
                LanguageSelectionDialog.this.getWindow().addFlags(2);
                LanguageSelectionDialog.this.getWindow().setAttributes(layoutParams);
                View view = view;
                if (view != null) {
                    view.setBackgroundResource(R.drawable.white_blur);
                }
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                View view = view;
                if (view != null) {
                    view.setBackgroundResource(R.drawable.transparent);
                }
            }
        });
    }

    public void setParentViewSplash(final View view) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(LanguageSelectionDialog.this.getWindow().getAttributes());
                layoutParams.dimAmount = 0.2f;
                layoutParams.width = -1;
                layoutParams.height = -2;
                LanguageSelectionDialog.this.getWindow().addFlags(2);
                LanguageSelectionDialog.this.getWindow().setAttributes(layoutParams);
                View view = view;
                if (view != null) {
                    view.setBackgroundResource(R.drawable.backgroung_pink);
                }
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                View view = view;
                if (view != null) {
                    view.setBackgroundResource(R.drawable.transparent);
                }
            }
        });
    }

    private void initViews(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.language_selection_dialog_layout, (ViewGroup) null, false);
        setView(inflate);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        setOnDismissListener(new LanguageSelectionDialog$$ExternalSyntheticLambda0(this));
        this.mLanguageSelectionAdapter = new LanguageSelectionAdapter(context);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.mRecyclerView.setAdapter(this.mLanguageSelectionAdapter);
    }

    /* renamed from: lambda$initViews$0$com-jch-racWiFi-settings-view-LanguageSelectionDialog */
    public /* synthetic */ void mo31934xe56b5979(DialogInterface dialogInterface) {
        this.mUnbinder.unbind();
    }

    public void hideCloseButton() {
        this.mCloseButton.setVisibility(4);
    }

    public static class Persistence {
        public static final String KEY = "LSDK";

        public static void setDoNotShowOnNextStartUp() {
            SharedPref.getInstance().getSharedPreferenceEditor().putBoolean(KEY, true).commit();
        }

        public static boolean checkIfNeededToShowLanguageSelectionDialog() {
            return !SharedPref.getInstance().getSharedPreferencesReader().getBoolean(KEY, false);
        }
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(C1662R2.C1665id.alexaLinkedView);
    }

    public void hideSystemUIWhenDialogIsOpen() {
        hideSystemUI();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            hideSystemUIWhenDialogIsOpen();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        hideSystemUIWhenDialogIsOpen();
        return super.onTouchEvent(motionEvent);
    }
}
