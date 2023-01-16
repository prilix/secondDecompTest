package p015me.dm7.barcodescanner.zxing;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import p015me.dm7.barcodescanner.core.BarcodeScannerView;
import p015me.dm7.barcodescanner.core.DisplayUtils;

/* renamed from: me.dm7.barcodescanner.zxing.ZXingScannerView */
public class ZXingScannerView extends BarcodeScannerView {
    public static final List<BarcodeFormat> ALL_FORMATS;
    private static final String TAG = "ZXingScannerView";
    private List<BarcodeFormat> mFormats;
    private MultiFormatReader mMultiFormatReader;
    /* access modifiers changed from: private */
    public ResultHandler mResultHandler;

    /* renamed from: me.dm7.barcodescanner.zxing.ZXingScannerView$ResultHandler */
    public interface ResultHandler {
        void handleResult(Result result);
    }

    static {
        ArrayList arrayList = new ArrayList();
        ALL_FORMATS = arrayList;
        arrayList.add(BarcodeFormat.AZTEC);
        arrayList.add(BarcodeFormat.CODABAR);
        arrayList.add(BarcodeFormat.CODE_39);
        arrayList.add(BarcodeFormat.CODE_93);
        arrayList.add(BarcodeFormat.CODE_128);
        arrayList.add(BarcodeFormat.DATA_MATRIX);
        arrayList.add(BarcodeFormat.EAN_8);
        arrayList.add(BarcodeFormat.EAN_13);
        arrayList.add(BarcodeFormat.ITF);
        arrayList.add(BarcodeFormat.MAXICODE);
        arrayList.add(BarcodeFormat.PDF_417);
        arrayList.add(BarcodeFormat.QR_CODE);
        arrayList.add(BarcodeFormat.RSS_14);
        arrayList.add(BarcodeFormat.RSS_EXPANDED);
        arrayList.add(BarcodeFormat.UPC_A);
        arrayList.add(BarcodeFormat.UPC_E);
        arrayList.add(BarcodeFormat.UPC_EAN_EXTENSION);
    }

    public ZXingScannerView(Context context) {
        super(context);
        initMultiFormatReader();
    }

    public ZXingScannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initMultiFormatReader();
    }

    public void setFormats(List<BarcodeFormat> list) {
        this.mFormats = list;
        initMultiFormatReader();
    }

    public void setResultHandler(ResultHandler resultHandler) {
        this.mResultHandler = resultHandler;
    }

    public Collection<BarcodeFormat> getFormats() {
        List<BarcodeFormat> list = this.mFormats;
        return list == null ? ALL_FORMATS : list;
    }

    private void initMultiFormatReader() {
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        enumMap.put(DecodeHintType.POSSIBLE_FORMATS, getFormats());
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        this.mMultiFormatReader = multiFormatReader;
        multiFormatReader.setHints(enumMap);
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        final Result result;
        MultiFormatReader multiFormatReader;
        MultiFormatReader multiFormatReader2;
        if (this.mResultHandler != null) {
            try {
                Camera.Size previewSize = camera.getParameters().getPreviewSize();
                int i = previewSize.width;
                int i2 = previewSize.height;
                if (DisplayUtils.getScreenOrientation(getContext()) == 1) {
                    int rotationCount = getRotationCount();
                    if (rotationCount == 1 || rotationCount == 3) {
                        int i3 = i;
                        i = i2;
                        i2 = i3;
                    }
                    bArr = getRotatedData(bArr, camera);
                }
                result = null;
                PlanarYUVLuminanceSource buildLuminanceSource = buildLuminanceSource(bArr, i, i2);
                if (buildLuminanceSource != null) {
                    result = this.mMultiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(buildLuminanceSource)));
                    multiFormatReader = this.mMultiFormatReader;
                    multiFormatReader.reset();
                    if (result == null) {
                        result = this.mMultiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(buildLuminanceSource.invert())));
                        multiFormatReader2 = this.mMultiFormatReader;
                        multiFormatReader2.reset();
                    }
                }
            } catch (NotFoundException unused) {
                multiFormatReader2 = this.mMultiFormatReader;
            } catch (ReaderException unused2) {
                multiFormatReader = this.mMultiFormatReader;
            } catch (NullPointerException unused3) {
                multiFormatReader = this.mMultiFormatReader;
            } catch (ArrayIndexOutOfBoundsException unused4) {
                multiFormatReader = this.mMultiFormatReader;
            } catch (RuntimeException e) {
                Log.e(TAG, e.toString(), e);
                return;
            } catch (Throwable th) {
                this.mMultiFormatReader.reset();
                throw th;
            }
            if (result != null) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        ResultHandler access$000 = ZXingScannerView.this.mResultHandler;
                        ResultHandler unused = ZXingScannerView.this.mResultHandler = null;
                        ZXingScannerView.this.stopCameraPreview();
                        if (access$000 != null) {
                            access$000.handleResult(result);
                        }
                    }
                });
            } else {
                camera.setOneShotPreviewCallback(this);
            }
        }
    }

    public void resumeCameraPreview(ResultHandler resultHandler) {
        this.mResultHandler = resultHandler;
        super.resumeCameraPreview();
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] bArr, int i, int i2) {
        Rect framingRectInPreview = getFramingRectInPreview(i, i2);
        if (framingRectInPreview == null) {
            return null;
        }
        try {
            return new PlanarYUVLuminanceSource(bArr, i, i2, framingRectInPreview.left, framingRectInPreview.top, framingRectInPreview.width(), framingRectInPreview.height(), false);
        } catch (Exception unused) {
            return null;
        }
    }
}
