package p015me.dm7.barcodescanner.core;

import android.hardware.Camera;
import java.util.Objects;

/* renamed from: me.dm7.barcodescanner.core.CameraWrapper */
public class CameraWrapper {
    public final Camera mCamera;
    public final int mCameraId;

    private CameraWrapper(Camera camera, int i) {
        Objects.requireNonNull(camera, "Camera cannot be null");
        this.mCamera = camera;
        this.mCameraId = i;
    }

    public static CameraWrapper getWrapper(Camera camera, int i) {
        if (camera == null) {
            return null;
        }
        return new CameraWrapper(camera, i);
    }
}
