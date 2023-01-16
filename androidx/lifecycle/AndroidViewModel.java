package androidx.lifecycle;

import android.app.Application;

public class AndroidViewModel extends C0534ViewModel {
    private Application mApplication;

    public AndroidViewModel(Application application) {
        this.mApplication = application;
    }

    public <T extends Application> T getApplication() {
        return this.mApplication;
    }
}
