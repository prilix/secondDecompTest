package com.jch.racWiFi.userManagement.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.NetworkDispatch.INetworkConnectivity;
import com.jch.racWiFi.Presenter.AbstractPresenter;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.network.ProfilePicNetworkDispatcher;
import java.io.File;

public class MyAccountProfilePicturePresenter extends AbstractPresenter {
    /* access modifiers changed from: private */
    public IMyAccountProfilePicPresenter iMyAccountProfilePicPresenter;

    public interface IMyAccountProfilePicPresenter extends INetworkConnectivity {
        void onProfilePicDeleteFailure(GenericResponse genericResponse);

        void onProfilePicDeleteSuccess(GenericResponse genericResponse);

        void onProfilePicUpdateFailure(GenericResponse genericResponse);

        void onProfilePicUpdateSuccess(GenericResponse genericResponse);
    }

    public void registerEventBus() {
    }

    public void unregisterEventBus() {
    }

    public MyAccountProfilePicturePresenter(IMyAccountProfilePicPresenter iMyAccountProfilePicPresenter2) {
        this.iMyAccountProfilePicPresenter = iMyAccountProfilePicPresenter2;
    }

    public void uploadProfilePicMultipart(LifecycleOwner lifecycleOwner, File file) {
        final ProfilePicNetworkDispatcher profilePicNetworkDispatcher = new ProfilePicNetworkDispatcher();
        profilePicNetworkDispatcher.updateProfilePic(file).observeSingleEvent(lifecycleOwner, new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                if (MyAccountProfilePicturePresenter.this.iMyAccountProfilePicPresenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        MyAccountProfilePicturePresenter.this.iMyAccountProfilePicPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (!profilePicNetworkDispatcher.getProcess().equals(ProfilePicNetworkDispatcher.Process.UPLOADING) || !genericResponse.isApiSuccessful()) {
                        MyAccountProfilePicturePresenter.this.iMyAccountProfilePicPresenter.onProfilePicUpdateFailure(genericResponse);
                    } else {
                        MyAccountProfilePicturePresenter.this.iMyAccountProfilePicPresenter.onProfilePicUpdateSuccess(genericResponse);
                    }
                }
            }
        });
    }

    public void deleteProfilePicture(LifecycleOwner lifecycleOwner) {
        final ProfilePicNetworkDispatcher profilePicNetworkDispatcher = new ProfilePicNetworkDispatcher();
        profilePicNetworkDispatcher.deleteProfilePic().observeSingleEvent(lifecycleOwner, new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                if (MyAccountProfilePicturePresenter.this.iMyAccountProfilePicPresenter != null) {
                    if (genericResponse.unableToReachServer()) {
                        MyAccountProfilePicturePresenter.this.iMyAccountProfilePicPresenter.onNetworkCallFailure(genericResponse.getThrowable());
                    } else if (!profilePicNetworkDispatcher.getProcess().equals(ProfilePicNetworkDispatcher.Process.DELETE) || !genericResponse.isApiSuccessful()) {
                        MyAccountProfilePicturePresenter.this.iMyAccountProfilePicPresenter.onProfilePicDeleteFailure(genericResponse);
                    } else {
                        MyAccountProfilePicturePresenter.this.iMyAccountProfilePicPresenter.onProfilePicDeleteSuccess(genericResponse);
                    }
                }
            }
        });
    }

    public void removeCallbacks() {
        this.iMyAccountProfilePicPresenter = null;
    }
}
