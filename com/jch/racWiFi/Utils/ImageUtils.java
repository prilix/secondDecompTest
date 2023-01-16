package com.jch.racWiFi.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;

public class ImageUtils {
    public static synchronized Bitmap convertBase64ToBitmap(String str) {
        Bitmap decodeByteArray;
        synchronized (ImageUtils.class) {
            byte[] decode = Base64.decode(str, 0);
            decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        }
        return decodeByteArray;
    }

    public static Bitmap getScaledBitmap(Bitmap bitmap, int i, int i2) {
        return Bitmap.createScaledBitmap(bitmap, i, i2, false);
    }

    public static boolean isPickedFileIsImage(Context context, Uri uri) {
        return getMimeType(context, uri).split("/")[0].equals("image");
    }

    public static Bitmap getBitmapFromFile(File file) {
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    public static String getMimeType(Context context, Uri uri) {
        if (FirebaseAnalytics.Param.CONTENT.equals(uri.getScheme())) {
            return context.getContentResolver().getType(uri);
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()).toLowerCase());
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 <= i2 && i4 <= i) {
            return 1;
        }
        int round = Math.round(((float) i3) / ((float) i2));
        int round2 = Math.round(((float) i4) / ((float) i));
        if (round >= round2) {
            round = round2;
        }
        while (((float) (i4 * i3)) / ((float) (round * round)) > ((float) (i * i2 * 2))) {
            round++;
        }
        return round;
    }

    private static Bitmap rotateImage(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return createBitmap;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Bitmap scaleToFit(Bitmap bitmap, int i, int i2, boolean z) {
        if (z) {
            float width = ((float) (bitmap.getWidth() - i)) / ((float) (i / 100));
            int height = bitmap.getHeight();
            return Bitmap.createScaledBitmap(bitmap, i, (int) (((float) height) - (((float) (height / 100)) * width)), true);
        }
        float height2 = ((float) (bitmap.getHeight() - i2)) / ((float) (i2 / 100));
        int width2 = bitmap.getWidth();
        return Bitmap.createScaledBitmap(bitmap, (int) (((float) width2) - (((float) (width2 / 100)) * height2)), i2, true);
    }
}
