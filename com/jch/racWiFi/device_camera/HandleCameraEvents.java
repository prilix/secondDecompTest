package com.jch.racWiFi.device_camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.Fragment;
import com.accord.common.utils.Logger;
import com.github.mikephil.charting.utils.C1030Utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import p006co.simplecrop.android.simplecropimage.CropImage;

public class HandleCameraEvents {
    public static final int CAMERA_CAPTURE = 1;
    public static final int PIC_CROP = 3;
    public static final int SELECT_GALLERY_IMG_REQ_CODE = 2;
    private static final String TAG = "HandleCameraEvents";
    private boolean isImageValid = false;
    private Activity mActivity;
    private File mFile;
    private Fragment mFragment;

    public HandleCameraEvents(Activity activity, File file, Fragment fragment) {
        this.mActivity = activity;
        this.mFile = file;
        this.mFragment = fragment;
    }

    public void takePicture() {
        Activity activity = this.mActivity;
        Uri uriForFile = FileProvider.getUriForFile(activity, this.mActivity.getPackageName() + ".provider", this.mFile);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", uriForFile);
        intent.putExtra(CropImage.RETURN_DATA, true);
        this.mFragment.startActivityForResult(intent, 1);
    }

    public void openGallery() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        Fragment fragment = this.mFragment;
        if (fragment != null) {
            fragment.startActivityForResult(intent, 2);
        } else {
            this.mActivity.startActivityForResult(intent, 2);
        }
    }

    public void startCropImage() {
        Intent intent = new Intent(this.mActivity, CropImage.class);
        intent.putExtra(CropImage.IMAGE_PATH, this.mFile.getPath());
        intent.putExtra(CropImage.SCALE, true);
        intent.putExtra(CropImage.ASPECT_X, 3);
        intent.putExtra(CropImage.ASPECT_Y, 3);
        intent.putExtra(CropImage.CIRCLE_CROP, true);
        Logger.m47e("/upload before crop", String.valueOf(getFileSizeMegaBytes(this.mFile)));
        getFileSizeMegaBytes(this.mFile);
        double fileSizeMegaBytes = (double) getFileSizeMegaBytes(this.mFile);
        if (fileSizeMegaBytes > 40.0d) {
            Toast.makeText(this.mActivity, "file size is too large", 1).show();
            return;
        }
        Double valueOf = Double.valueOf(C1030Utils.DOUBLE_EPSILON);
        Double valueOf2 = Double.valueOf(100.0d - Double.valueOf(((fileSizeMegaBytes - valueOf.doubleValue()) * 100.0d) / (Double.valueOf(40.0d).doubleValue() - valueOf.doubleValue())).doubleValue());
        intent.putExtra(CropImage.IMAGE_QUALITY, valueOf2);
        Logger.m47e("image_percentage", valueOf2 + "   : file size :" + fileSizeMegaBytes);
        Fragment fragment = this.mFragment;
        if (fragment != null) {
            fragment.startActivityForResult(intent, 3);
        } else {
            this.mActivity.startActivityForResult(intent, 3);
        }
    }

    public static float getFileSizeMegaBytes(File file) {
        return ((float) file.length()) / 1048576.0f;
    }

    public boolean isImageValid() {
        return this.isImageValid;
    }

    public void copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        this.isImageValid = false;
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
                this.isImageValid = true;
            } else {
                return;
            }
        }
    }

    public Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(SupportMenu.CATEGORY_MASK);
        canvas.drawOval(rectF, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();
        return createBitmap;
    }
}
