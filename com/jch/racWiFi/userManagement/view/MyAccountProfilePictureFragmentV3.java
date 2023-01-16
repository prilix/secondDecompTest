package com.jch.racWiFi.userManagement.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.amplitude.util.Mode;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.databinding.MyAccountPictureFrameBinding;
import com.jch.racWiFi.device_camera.HandleCameraEvents;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.ProfilePicture;
import com.jch.racWiFi.userManagement.presenter.MyAccountProfilePicturePresenter;
import com.jch.racWiFi.util.dialog.JCIAlertDialog;
import com.jch.racWiFi.util.listeners.AlertListener;
import com.jch_hitachi.aircloudglobal.R;
import dagger.android.support.AndroidSupportInjection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.inject.Inject;
import p006co.simplecrop.android.simplecropimage.CropImage;

public class MyAccountProfilePictureFragmentV3 extends GenericFragment implements MyAccountProfilePicturePresenter.IMyAccountProfilePicPresenter {
    private String[] cameraPermissionArr;
    private ConfirmationDialog confirmationDialog;
    private int currentRequestCode = -1;
    private boolean deleteProfilePic;
    private boolean imageCapturedOrSelected = false;
    private boolean imageFromCamera = false;
    private Activity mActivity;
    /* access modifiers changed from: private */
    public final ActivityResultLauncher<Intent> mActivityResultCameraShowNotRationale = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new MyAccountProfilePictureFragmentV3$$ExternalSyntheticLambda11(this));
    /* access modifiers changed from: private */
    public final ActivityResultLauncher<Intent> mActivityResultMultiplePermissionsShowNotRationale = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new MyAccountProfilePictureFragmentV3$$ExternalSyntheticLambda10(this));
    private MyAccountPictureFrameBinding mBinding;
    /* access modifiers changed from: private */
    public final ActivityResultLauncher<String> mCameraPermissionResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new MyAccountProfilePictureFragmentV3$$ExternalSyntheticLambda12(this));
    private File mFile;
    private HandleCameraEvents mHandleCameraEvents;
    @Inject
    JCIAlertDialog mJciAlertDialog;
    private MyAccountProfilePicturePresenter mMyAccountProfilePicturePresenter;
    private File selectedImageFile = null;

    static /* synthetic */ boolean lambda$onNetworkCallFailure$11(Dialog dialog, View view) {
        return true;
    }

    public static MyAccountProfilePictureFragmentV3 newInstance() {
        MyAccountProfilePictureFragmentV3 myAccountProfilePictureFragmentV3 = new MyAccountProfilePictureFragmentV3();
        myAccountProfilePictureFragmentV3.setNewBundleAsArgument();
        return myAccountProfilePictureFragmentV3;
    }

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        MyAccountPictureFrameBinding myAccountPictureFrameBinding = (MyAccountPictureFrameBinding) DataBindingUtil.inflate(layoutInflater, R.layout.my_account_picture_frame, viewGroup, false);
        this.mBinding = myAccountPictureFrameBinding;
        File file = new File(myAccountPictureFrameBinding.getRoot().getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "temp.jpg");
        this.mFile = file;
        if (file.exists()) {
            this.mFile.delete();
        }
        this.mHandleCameraEvents = new HandleCameraEvents(this.mActivity, this.mFile, this);
        this.mMyAccountProfilePicturePresenter = new MyAccountProfilePicturePresenter(this);
        this.mBinding.include.imageViewProfilePictureMyAccount.setBorderWidth((int) ViewUtils.convertDpToPixel(1.0f, this.mBinding.getRoot().getContext()));
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.textViewSave.setOnClickListener(new MyAccountProfilePictureFragmentV3$$ExternalSyntheticLambda0(this));
        this.mBinding.include.imageViewCamera.setOnClickListener(new MyAccountProfilePictureFragmentV3$$ExternalSyntheticLambda6(this));
        this.mBinding.include.imageViewGallery.setOnClickListener(new MyAccountProfilePictureFragmentV3$$ExternalSyntheticLambda7(this));
        this.mBinding.include.imageViewRemoveProfilePicture.setOnClickListener(new MyAccountProfilePictureFragmentV3$$ExternalSyntheticLambda8(this));
        this.mBinding.backButton.setOnClickListener(new MyAccountProfilePictureFragmentV3$$ExternalSyntheticLambda9(this));
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-userManagement-view-MyAccountProfilePictureFragmentV3 */
    public /* synthetic */ void mo32981xda233d1b(View view) {
        onClickSave();
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-userManagement-view-MyAccountProfilePictureFragmentV3 */
    public /* synthetic */ void mo32982xbd4ef05c(View view) {
        onClickCamera();
    }

    /* renamed from: lambda$onViewCreated$2$com-jch-racWiFi-userManagement-view-MyAccountProfilePictureFragmentV3 */
    public /* synthetic */ void mo32983xa07aa39d(View view) {
        onClickGallery();
    }

    /* renamed from: lambda$onViewCreated$3$com-jch-racWiFi-userManagement-view-MyAccountProfilePictureFragmentV3 */
    public /* synthetic */ void mo32984x83a656de(View view) {
        onClickRemove();
    }

    /* renamed from: lambda$onViewCreated$4$com-jch-racWiFi-userManagement-view-MyAccountProfilePictureFragmentV3 */
    public /* synthetic */ void mo32985x66d20a1f(View view) {
        onClickBack();
    }

    public void onStart() {
        super.onStart();
        if (!this.imageCapturedOrSelected) {
            ProfilePicture.loadIntoImageView(this.mBinding.include.imageViewProfilePictureMyAccount, UserInfo.getCurrentUserInfo(getCoreActivity()).profilePicture);
        }
    }

    public void onResume() {
        super.onResume();
        if (UserInfo.getCurrentUserInfo(getCoreActivity()).profilePicture == null) {
            this.mBinding.include.imageViewRemoveProfilePicture.setDeleteButtonEnbled(false);
        } else {
            this.mBinding.include.imageViewRemoveProfilePicture.setDeleteButtonEnbled(true);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mMyAccountProfilePicturePresenter.removeCallbacks();
    }

    public void onClickSave() {
        if (this.imageCapturedOrSelected || this.deleteProfilePic) {
            saveProfilePicture();
        } else {
            showSingleChiocePopUp(getString(R.string.myAcc_alert_imageNotSelected));
        }
    }

    private void saveProfilePicture() {
        if (this.deleteProfilePic) {
            ConfirmationDialog confirmationDialog2 = new ConfirmationDialog(this.mBinding.getRoot().getContext());
            this.confirmationDialog = confirmationDialog2;
            confirmationDialog2.setCancelable(false);
            this.confirmationDialog.setTitleString(getString(R.string.common_alert_saveChanges));
            this.confirmationDialog.setMessageString(getString(R.string.myAcc_alert_deleteProfilePicDesc));
            this.confirmationDialog.setPositiveButton(getString(R.string.common_btn_yes), new MyAccountProfilePictureFragmentV3$$ExternalSyntheticLambda13(this));
            this.confirmationDialog.setNegativeButton(getString(R.string.common_btn_no), new MyAccountProfilePictureFragmentV3$$ExternalSyntheticLambda14(this));
            this.confirmationDialog.setParentView(this.mBinding.parent);
            this.confirmationDialog.show();
        } else if (this.imageCapturedOrSelected) {
            showPleaseWaitDialog(30000);
            this.mMyAccountProfilePicturePresenter.uploadProfilePicMultipart(getViewLifecycleOwner(), this.selectedImageFile);
        } else {
            getActivity().runOnUiThread(new MyAccountProfilePictureFragmentV3$$ExternalSyntheticLambda5(this));
        }
    }

    /* renamed from: lambda$saveProfilePicture$5$com-jch-racWiFi-userManagement-view-MyAccountProfilePictureFragmentV3 */
    public /* synthetic */ boolean mo32986xd93fc848(Dialog dialog, View view) {
        this.mMyAccountProfilePicturePresenter.deleteProfilePicture(getViewLifecycleOwner());
        this.confirmationDialog.dismiss();
        showPleaseWaitDialog(30000);
        return false;
    }

    /* renamed from: lambda$saveProfilePicture$6$com-jch-racWiFi-userManagement-view-MyAccountProfilePictureFragmentV3 */
    public /* synthetic */ boolean mo32987xbc6b7b89(Dialog dialog, View view) {
        this.confirmationDialog.dismiss();
        return false;
    }

    /* renamed from: lambda$saveProfilePicture$7$com-jch-racWiFi-userManagement-view-MyAccountProfilePictureFragmentV3 */
    public /* synthetic */ void mo32988x9f972eca() {
        showSingleChiocePopUp(getString(R.string.myAcc_alert_imageNotSelected));
        dismissPleaseWaitDialog();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* renamed from: lambda$new$8$com-jch-racWiFi-userManagement-view-MyAccountProfilePictureFragmentV3 */
    public /* synthetic */ void mo32977xd3ea035f(Boolean bool) {
        if (bool.booleanValue()) {
            onCameraPermissionGranted();
        } else {
            showNotRationale(getString(R.string.android_permission_alert_camera), Mode.CAMERA);
        }
    }

    private void showNotRationale(String str, final Mode mode) {
        this.mJciAlertDialog.showDialog(this.mBinding.getRoot().getContext(), (String) null, getString(R.string.android_permission_alert_deniedMsg, str), getString(R.string.android_permission_btn_goToSetting), getString(R.string.common_btn_cancel), new AlertListener() {
            public void onPositive() {
                Intent intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", MyAccountProfilePictureFragmentV3.this.getCoreActivity().getPackageName(), (String) null));
                int i = C25365.$SwitchMap$com$jch$racWiFi$amplitude$util$Mode[mode.ordinal()];
                if (i == 1) {
                    MyAccountProfilePictureFragmentV3.this.mActivityResultCameraShowNotRationale.launch(intent);
                } else if (i == 2) {
                    MyAccountProfilePictureFragmentV3.this.mActivityResultMultiplePermissionsShowNotRationale.launch(intent);
                }
            }

            public void onNegative() {
                if (MyAccountProfilePictureFragmentV3.this.getCoreActivity().getNavController() != null) {
                    MyAccountProfilePictureFragmentV3.this.getCoreActivity().getNavController().navigateUp();
                }
            }
        });
    }

    /* renamed from: com.jch.racWiFi.userManagement.view.MyAccountProfilePictureFragmentV3$5 */
    static /* synthetic */ class C25365 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$amplitude$util$Mode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.jch.racWiFi.amplitude.util.Mode[] r0 = com.jch.racWiFi.amplitude.util.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$amplitude$util$Mode = r0
                com.jch.racWiFi.amplitude.util.Mode r1 = com.jch.racWiFi.amplitude.util.Mode.CAMERA     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$amplitude$util$Mode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.amplitude.util.Mode r1 = com.jch.racWiFi.amplitude.util.Mode.GALLERY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.userManagement.view.MyAccountProfilePictureFragmentV3.C25365.<clinit>():void");
        }
    }

    /* renamed from: lambda$new$9$com-jch-racWiFi-userManagement-view-MyAccountProfilePictureFragmentV3 */
    public /* synthetic */ void mo32978xb715b6a0(ActivityResult activityResult) {
        if (checkCameraPermission()) {
            onCameraPermissionGranted();
        } else {
            showNotRationale(getString(R.string.android_permission_alert_camera), Mode.CAMERA);
        }
    }

    /* renamed from: lambda$new$10$com-jch-racWiFi-userManagement-view-MyAccountProfilePictureFragmentV3 */
    public /* synthetic */ void mo32976x29f574a8(ActivityResult activityResult) {
        if (checkReadWriteExternalPermission()) {
            onPermissionGranted();
        } else {
            showNotRationale(getString(R.string.android_permission_alert_storage), Mode.GALLERY);
        }
    }

    private void onCameraPermissionGranted() {
        if (this.imageFromCamera) {
            this.mHandleCameraEvents.takePicture();
        } else {
            this.mHandleCameraEvents.openGallery();
        }
    }

    private void onPermissionGranted() {
        if (this.imageFromCamera) {
            this.mHandleCameraEvents.takePicture();
        } else {
            this.mHandleCameraEvents.openGallery();
        }
    }

    /* access modifiers changed from: package-private */
    public void onClickCamera() {
        this.imageFromCamera = true;
        commonTask();
    }

    private void commonTask() {
        if (checkCameraPermission()) {
            onCameraPermissionGranted();
        } else if (shouldShowRequestPermissionRationale("android.permission.CAMERA")) {
            showRationale(getString(R.string.android_permission_alert_camera));
        } else {
            this.mCameraPermissionResult.launch("android.permission.CAMERA");
        }
    }

    private void showRationale(String str) {
        this.mJciAlertDialog.showDialog(this.mBinding.getRoot().getContext(), getString(R.string.android_permission_alert_permissionDenied), getString(R.string.android_permission_alert_permissionDeniedAlertDesc, str), getString(R.string.android_permission_btn_allow), getString(R.string.common_btn_no), new AlertListener() {
            public void onNegative() {
            }

            public void onPositive() {
                MyAccountProfilePictureFragmentV3.this.mCameraPermissionResult.launch("android.permission.CAMERA");
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void onClickGallery() {
        this.imageFromCamera = false;
        commonTask();
    }

    /* access modifiers changed from: package-private */
    public void onClickRemove() {
        if (UserInfo.getCurrentUserInfo(getCoreActivity()).profilePicture != null) {
            this.deleteProfilePic = true;
            this.mBinding.include.imageViewProfilePictureMyAccount.setImageResource(R.drawable.ic_user_pic);
            this.mBinding.textViewSave.setColorFilter(ContextCompat.getColor(this.mBinding.getRoot().getContext(), R.color.colorRed));
        }
    }

    /* access modifiers changed from: package-private */
    public void onClickBack() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public void onNetworkCallSuccess() {
        dismissPleaseWaitDialog();
    }

    public void onNetworkCallFailure(Throwable th) {
        if (!DemoModeModel.DEMO_MODE_ON) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mBinding.getRoot().getContext());
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getString(R.string.common_alert_unableToConnectToNw));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), MyAccountProfilePictureFragmentV3$$ExternalSyntheticLambda4.INSTANCE);
            singleChoiceDialog.show();
        }
        dismissPleaseWaitDialog();
    }

    public void serverException() {
        showSingleChiocePopUp(getString(R.string.common_alert_somethingWentWrong));
        dismissPleaseWaitDialog();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 1) {
                Logger.m49i("", "File = " + this.mFile);
                this.mHandleCameraEvents.startCropImage();
            } else if (i != 2) {
                if (i != 3) {
                    if (i != 132 || !checkPermissions(this.cameraPermissionArr)) {
                        return;
                    }
                    if (this.imageFromCamera) {
                        this.mHandleCameraEvents.takePicture();
                    } else {
                        this.mHandleCameraEvents.openGallery();
                    }
                } else if (intent != null && intent.getStringExtra(CropImage.IMAGE_PATH) != null) {
                    Logger.m47e("/upload after crop", String.valueOf(HandleCameraEvents.getFileSizeMegaBytes(this.mFile)));
                    Bitmap decodeFile = BitmapFactory.decodeFile(this.mFile.getPath());
                    this.mBinding.textViewSave.setColorFilter(ContextCompat.getColor(this.mBinding.getRoot().getContext(), R.color.colorRed));
                    this.mBinding.include.imageViewProfilePictureMyAccount.setImageBitmap(decodeFile);
                    this.selectedImageFile = this.mFile;
                    this.imageCapturedOrSelected = true;
                    this.deleteProfilePic = false;
                    this.currentRequestCode = i;
                }
            } else if (intent != null) {
                try {
                    if (intent.getData() != null) {
                        InputStream openInputStream = this.mActivity.getContentResolver().openInputStream(intent.getData());
                        FileOutputStream fileOutputStream = new FileOutputStream(this.mFile);
                        this.mHandleCameraEvents.copyStream(openInputStream, fileOutputStream);
                        fileOutputStream.close();
                        openInputStream.close();
                        if (this.mHandleCameraEvents.isImageValid()) {
                            this.mHandleCameraEvents.startCropImage();
                        } else {
                            showSingleChiocePopUp(getString(R.string.myAcc_alert_selectedImageIsNotValid));
                        }
                    }
                } catch (Exception e) {
                    Logger.m48e(GenericFragment.TAG, "Error while creating temp file", e);
                }
            }
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 177) {
            return;
        }
        if (!checkPermissions(this.cameraPermissionArr)) {
            String[] strArr2 = this.cameraPermissionArr;
            int length = strArr2.length;
            int i2 = 0;
            while (i2 < length) {
                String str = strArr2[i2];
                if (ContextCompat.checkSelfPermission(this.mActivity, str) == 0) {
                    i2++;
                } else if (!shouldShowRequestPermissionRationale(str)) {
                    showPermissionSettingDialog(str, this, (NavController) null);
                    return;
                } else {
                    showPermissionDeniedDialog(this.mBinding.parent, str, (NavController) null);
                    return;
                }
            }
        } else if (this.imageFromCamera) {
            this.mHandleCameraEvents.takePicture();
        } else {
            this.mHandleCameraEvents.openGallery();
        }
    }

    public void onProfilePicUpdateSuccess(GenericResponse genericResponse) {
        UserInfo.getCurrentUserInfo(getCoreActivity()).profilePicture = (ProfilePicture) genericResponse.getBodyOfType(ProfilePicture.class);
        dismissPleaseWaitDialog();
        this.deleteProfilePic = false;
        this.imageCapturedOrSelected = false;
        showSingleChiocePopUp2(getString(R.string.myAcc_alert_profilePicUpdateSuccess)).setPositiveButton(getString(R.string.common_btn_ok), new MyAccountProfilePictureFragmentV3$$ExternalSyntheticLambda3(this));
    }

    /* renamed from: lambda$onProfilePicUpdateSuccess$12$com-jch-racWiFi-userManagement-view-MyAccountProfilePictureFragmentV3 */
    public /* synthetic */ boolean mo32980x3ed0a9d0(Dialog dialog, View view) {
        dialog.dismiss();
        this.mFragmentToActivityCallback.getNavController().navigateUp();
        return false;
    }

    public void onProfilePicUpdateFailure(GenericResponse genericResponse) {
        dismissPleaseWaitDialog();
        if (genericResponse.getResponse().code() != 401) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            return;
        }
        showPleaseWaitDialog();
        getCoreActivity().refreshToken(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                MyAccountProfilePictureFragmentV3.this.callDisableAPI();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callDisableAPI() {
        dismissPleaseWaitDialog();
        this.mMyAccountProfilePicturePresenter.uploadProfilePicMultipart(getViewLifecycleOwner(), this.selectedImageFile);
    }

    public void onProfilePicDeleteSuccess(GenericResponse genericResponse) {
        dismissPleaseWaitDialog();
        UserInfo.getCurrentUserInfo(getCoreActivity()).profilePicture = null;
        this.deleteProfilePic = false;
        this.imageCapturedOrSelected = false;
        showSingleChiocePopUp2(getString(R.string.myAcc_alert_profilePicDeletedSucces)).setPositiveButton(getString(R.string.common_btn_ok), new MyAccountProfilePictureFragmentV3$$ExternalSyntheticLambda2(this));
    }

    /* renamed from: lambda$onProfilePicDeleteSuccess$13$com-jch-racWiFi-userManagement-view-MyAccountProfilePictureFragmentV3 */
    public /* synthetic */ boolean mo32979x790895f3(Dialog dialog, View view) {
        dialog.dismiss();
        this.mFragmentToActivityCallback.getNavController().navigateUp();
        return false;
    }

    private void showSingleChiocePopUp(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new MyAccountProfilePictureFragmentV3$$ExternalSyntheticLambda1(singleChoiceDialog));
        singleChoiceDialog.show();
    }

    private SingleChoiceDialog showSingleChiocePopUp2(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mBinding.getRoot().getContext());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.show();
        return singleChoiceDialog;
    }

    public void onProfilePicDeleteFailure(GenericResponse genericResponse) {
        dismissPleaseWaitDialog();
        if (genericResponse.getResponse().code() != 401) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            return;
        }
        showPleaseWaitDialog();
        getCoreActivity().refreshToken(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                MyAccountProfilePictureFragmentV3.this.callDeleteAPI();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callDeleteAPI() {
        dismissPleaseWaitDialog();
        this.mMyAccountProfilePicturePresenter.deleteProfilePicture(getViewLifecycleOwner());
    }
}
