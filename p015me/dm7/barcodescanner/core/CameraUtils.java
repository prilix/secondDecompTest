package p015me.dm7.barcodescanner.core;

import android.hardware.Camera;
import java.util.List;
import kotlinx.coroutines.DebugKt;

/* renamed from: me.dm7.barcodescanner.core.CameraUtils */
public class CameraUtils {
    public static Camera getCameraInstance() {
        return getCameraInstance(getDefaultCameraId());
    }

    public static int getDefaultCameraId() {
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int i = -1;
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == 0) {
                return i2;
            }
            i = i2;
        }
        return i;
    }

    public static Camera getCameraInstance(int i) {
        if (i != -1) {
            return Camera.open(i);
        }
        try {
            return Camera.open();
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean isFlashSupported(Camera camera) {
        List<String> supportedFlashModes;
        if (camera != null) {
            Camera.Parameters parameters = camera.getParameters();
            if (parameters.getFlashMode() != null && (supportedFlashModes = parameters.getSupportedFlashModes()) != null && !supportedFlashModes.isEmpty() && (supportedFlashModes.size() != 1 || !supportedFlashModes.get(0).equals(DebugKt.DEBUG_PROPERTY_VALUE_OFF))) {
                return true;
            }
        }
        return false;
    }
}
