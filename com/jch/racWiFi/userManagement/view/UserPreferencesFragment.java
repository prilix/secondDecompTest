package com.jch.racWiFi.userManagement.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.lifecycle.Lifecycle;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.UserPreferenceQuestionAnswer;
import com.jch.racWiFi.userManagement.presenter.UserPreferenceQuestionAnswerPresenter;
import com.jch_hitachi.aircloudglobal.R;

public class UserPreferencesFragment extends GenericFragment implements View.OnClickListener, UserPreferenceQuestionAnswerPresenter.IUserPreferenceQuestionAnswerPresenter {
    @BindView(2131363642)
    LinearLayout linearLayout;
    private UserPreferenceQuestionAnswer.Answer[] mAnswers = new UserPreferenceQuestionAnswer.Answer[3];
    @BindView(2131364837)
    LinearLayout mBackgroundImageLayout;
    private int[] mBackgroundImagesDrawable = new int[3];
    @BindView(2131362160)
    Button mNo;
    @BindView(2131364544)
    ImageButton mSkip;
    @BindArray(2130903048)
    String[] mStepsArray;
    @BindView(2131364575)
    TextView mStepsTextView;
    private Unbinder mUnbinder;
    private UserPreferenceQuestionAnswerPresenter mUserPreferenceQuestionAnswerPresenter;
    @BindArray(2130903049)
    String[] mUserPreferenceQuestions;
    @BindView(2131364710)
    TextView mUserPreferenceQuestionsTextView;
    @BindView(2131362186)
    Button mYes;
    private int questionIndex = 0;

    public void onNetworkCallSuccess() {
    }

    public static UserPreferencesFragment newInstance() {
        UserPreferencesFragment userPreferencesFragment = new UserPreferencesFragment();
        userPreferencesFragment.setNewBundleAsArgument();
        return userPreferencesFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.user_prefrence_frame, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        this.mBackgroundImageLayout.setBackgroundResource(R.drawable.user_preference_weekends);
        int[] iArr = this.mBackgroundImagesDrawable;
        iArr[0] = R.drawable.user_preference_weekends;
        iArr[1] = R.drawable.user_preference_weekdays;
        iArr[2] = R.drawable.user_preference_sensitive_to_cold;
        UserPreferenceQuestionAnswerPresenter userPreferenceQuestionAnswerPresenter = new UserPreferenceQuestionAnswerPresenter(this);
        this.mUserPreferenceQuestionAnswerPresenter = userPreferenceQuestionAnswerPresenter;
        userPreferenceQuestionAnswerPresenter.registerEventBus();
        return inflate;
    }

    @OnClick({2131362160, 2131362186, 2131364544})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_no) {
            int i = this.questionIndex;
            if (i <= 2) {
                this.mAnswers[i] = UserPreferenceQuestionAnswer.Answer.NO;
                String nextQuestion = getNextQuestion();
                String nextStepString = getNextStepString();
                Integer nextBackgroundDrawable = getNextBackgroundDrawable();
                if (nextQuestion != null) {
                    this.mUserPreferenceQuestionsTextView.setText(nextQuestion);
                    this.mStepsTextView.setText(nextStepString);
                    if (nextBackgroundDrawable != null) {
                        this.mBackgroundImageLayout.setBackgroundResource(nextBackgroundDrawable.intValue());
                    }
                }
            }
            if (this.questionIndex == 3) {
                saveUserPreferences();
            }
        } else if (id == R.id.button_yes) {
            int i2 = this.questionIndex;
            if (i2 <= 2) {
                this.mAnswers[i2] = UserPreferenceQuestionAnswer.Answer.YES;
                String nextQuestion2 = getNextQuestion();
                String nextStepString2 = getNextStepString();
                Integer nextBackgroundDrawable2 = getNextBackgroundDrawable();
                if (nextQuestion2 != null) {
                    this.mUserPreferenceQuestionsTextView.setText(nextQuestion2);
                    this.mStepsTextView.setText(nextStepString2);
                    if (nextBackgroundDrawable2 != null) {
                        this.mBackgroundImageLayout.setBackgroundResource(nextBackgroundDrawable2.intValue());
                    }
                }
            }
            if (this.questionIndex == 3) {
                saveUserPreferences();
            }
        } else if (id == R.id.text_view_skip) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }

    public void saveUserPreferences() {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(getActivity());
        confirmationDialog.setTitleString(getActivity().getString(R.string.userPref_lbl_savePreference));
        confirmationDialog.setMessageString(getActivity().getString(R.string.weeklyTimer_alert_confirmCopyDesc));
        confirmationDialog.setPositiveButton(getActivity().getString(R.string.common_btn_continue), new UserPreferencesFragment$$ExternalSyntheticLambda0(this));
        confirmationDialog.setNegativeButton(getActivity().getString(R.string.common_btn_cancel), UserPreferencesFragment$$ExternalSyntheticLambda1.INSTANCE);
        confirmationDialog.setParentView(this.linearLayout, (String) null);
        confirmationDialog.show();
    }

    /* renamed from: lambda$saveUserPreferences$0$com-jch-racWiFi-userManagement-view-UserPreferencesFragment */
    public /* synthetic */ boolean mo33112x7ad12b61(Dialog dialog, View view) {
        dialog.dismiss();
        this.mUserPreferenceQuestionAnswerPresenter.saveUserPreferenceQuestionAnswer(getViewLifecycleOwner(), generateBooleanArrayFromAnswers(this.mAnswers));
        showPleaseWaitDialog();
        return false;
    }

    private boolean[] generateBooleanArrayFromAnswers(UserPreferenceQuestionAnswer.Answer[] answerArr) {
        boolean[] zArr = new boolean[3];
        for (int i = 0; i < answerArr.length; i++) {
            zArr[i] = answerArr[i].equals(UserPreferenceQuestionAnswer.Answer.YES);
        }
        return zArr;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mUserPreferenceQuestionAnswerPresenter.unregisterEventBus();
        this.mUserPreferenceQuestionAnswerPresenter.removeCallbacks();
        this.mUnbinder.unbind();
    }

    private String getNextQuestion() {
        int i = this.questionIndex + 1;
        this.questionIndex = i;
        if (i > 2) {
            return null;
        }
        return this.mUserPreferenceQuestions[i];
    }

    private String getNextStepString() {
        int i = this.questionIndex;
        if (i > 2) {
            return null;
        }
        return this.mStepsArray[i];
    }

    private Integer getNextBackgroundDrawable() {
        int i = this.questionIndex;
        if (i > 2) {
            return null;
        }
        return Integer.valueOf(this.mBackgroundImagesDrawable[i]);
    }

    public void onNetworkCallFailure(Throwable th) {
        if (!DemoModeModel.DEMO_MODE_ON) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getString(R.string.common_alert_unableToConnectToNw));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
                public boolean onButtonClickListener(Dialog dialog, View view) {
                    return true;
                }
            });
            singleChoiceDialog.show();
        }
        dismissPleaseWaitDialog();
    }

    public void serverException() {
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_somethingWentWrong), 0);
        dismissPleaseWaitDialog();
    }

    public void onPreferenceUpdateSuccess() {
        dismissPleaseWaitDialog();
        Toaster.makeToast(getActivity(), getString(R.string.userPref_alert_updateSuccessful), 0);
        getActivity().onBackPressed();
    }

    public void onPreferenceUpdateFailure(GenericResponse genericResponse) {
        dismissPleaseWaitDialog();
        if (genericResponse.getResponse().code() == 412) {
            alertDialog(getString(R.string.errorCode_alert_PCF009));
        } else {
            Toaster.makeToast(getActivity(), getString(R.string.userPefrence_alert_updateFailed), 0);
        }
    }

    private void alertDialog(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(getActivity().getString(R.string.common_alert));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setPositiveButton(getActivity().getString(R.string.common_btn_ok), new UserPreferencesFragment$$ExternalSyntheticLambda2(singleChoiceDialog));
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            singleChoiceDialog.show();
        }
    }
}
