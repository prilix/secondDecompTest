package p006co.simplecrop.android.simplecropimage;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.media.FaceDetector;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.view.View;
import android.widget.Toast;
import com.accord.common.utils.Logger;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import p006co.simplecrop.android.simplecropimage.BitmapManager;

/* renamed from: co.simplecrop.android.simplecropimage.CropImage */
public class CropImage extends MonitoredActivity {
    public static final String ACTION_INLINE_DATA = "inline-data";
    public static final String ASPECT_X = "aspectX";
    public static final String ASPECT_Y = "aspectY";
    public static final int CANNOT_STAT_ERROR = -2;
    public static final String CIRCLE_CROP = "circleCrop";
    public static final String IMAGE_PATH = "image-path";
    public static final String IMAGE_QUALITY = "IMAGE_QUALITY";
    public static final int NO_STORAGE_ERROR = -1;
    public static final String ORIENTATION_IN_DEGREES = "orientation_in_degrees";
    public static final String OUTPUT_X = "outputX";
    public static final String OUTPUT_Y = "outputY";
    public static final String RETURN_DATA = "return-data";
    public static final String RETURN_DATA_AS_BITMAP = "data";
    public static final String SCALE = "scale";
    public static final String SCALE_UP_IF_NEEDED = "scaleUpIfNeeded";
    private static final String TAG = "CropImage";
    final int IMAGE_MAX_SIZE = 1024;
    /* access modifiers changed from: private */
    public int mAspectX;
    /* access modifiers changed from: private */
    public int mAspectY;
    /* access modifiers changed from: private */
    public Bitmap mBitmap;
    /* access modifiers changed from: private */
    public boolean mCircleCrop = false;
    private ContentResolver mContentResolver;
    HighlightView mCrop;
    private final BitmapManager.ThreadSet mDecodingThreads = new BitmapManager.ThreadSet();
    /* access modifiers changed from: private */
    public boolean mDoFaceDetection = true;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();
    private String mImagePath;
    /* access modifiers changed from: private */
    public CropImageView mImageView;
    private Bitmap.CompressFormat mOutputFormat = Bitmap.CompressFormat.JPEG;
    private int mOutputX;
    private int mOutputY;
    private int mPhotoQualityPercentage = 90;
    Runnable mRunFaceDetection = new Runnable() {
        FaceDetector.Face[] mFaces = new FaceDetector.Face[3];
        Matrix mImageMatrix;
        int mNumFaces;
        float mScale = 1.0f;

        /* access modifiers changed from: private */
        public void handleFace(FaceDetector.Face face) {
            PointF pointF = new PointF();
            face.getMidPoint(pointF);
            pointF.x *= this.mScale;
            pointF.y *= this.mScale;
            HighlightView highlightView = new HighlightView(CropImage.this.mImageView);
            Rect rect = new Rect(0, 0, CropImage.this.mBitmap.getWidth(), CropImage.this.mBitmap.getHeight());
            float f = (float) ((int) pointF.x);
            float f2 = (float) ((int) pointF.y);
            RectF rectF = new RectF(f, f2, f, f2);
            float f3 = (float) (-(((int) (face.eyesDistance() * this.mScale)) * 2));
            rectF.inset(f3, f3);
            if (rectF.left < 0.0f) {
                rectF.inset(-rectF.left, -rectF.left);
            }
            if (rectF.top < 0.0f) {
                rectF.inset(-rectF.top, -rectF.top);
            }
            if (rectF.right > ((float) rect.right)) {
                rectF.inset(rectF.right - ((float) rect.right), rectF.right - ((float) rect.right));
            }
            if (rectF.bottom > ((float) rect.bottom)) {
                rectF.inset(rectF.bottom - ((float) rect.bottom), rectF.bottom - ((float) rect.bottom));
            }
            highlightView.setup(this.mImageMatrix, rect, rectF, CropImage.this.mCircleCrop, (CropImage.this.mAspectX == 0 || CropImage.this.mAspectY == 0) ? false : true);
            CropImage.this.mImageView.add(highlightView);
        }

        /* access modifiers changed from: private */
        public void makeDefault() {
            int i;
            HighlightView highlightView = new HighlightView(CropImage.this.mImageView);
            int width = CropImage.this.mBitmap.getWidth();
            int height = CropImage.this.mBitmap.getHeight();
            Rect rect = new Rect(0, 0, width, height);
            int min = (Math.min(width, height) * 4) / 5;
            if (CropImage.this.mAspectX == 0 || CropImage.this.mAspectY == 0) {
                i = min;
            } else if (CropImage.this.mAspectX > CropImage.this.mAspectY) {
                i = (CropImage.this.mAspectY * min) / CropImage.this.mAspectX;
            } else {
                i = min;
                min = (CropImage.this.mAspectX * min) / CropImage.this.mAspectY;
            }
            int i2 = (width - min) / 2;
            int i3 = (height - i) / 2;
            highlightView.setup(this.mImageMatrix, rect, new RectF((float) i2, (float) i3, (float) (i2 + min), (float) (i3 + i)), CropImage.this.mCircleCrop, (CropImage.this.mAspectX == 0 || CropImage.this.mAspectY == 0) ? false : true);
            CropImage.this.mImageView.mHighlightViews.clear();
            CropImage.this.mImageView.add(highlightView);
        }

        private Bitmap prepareBitmap() {
            if (CropImage.this.mBitmap == null) {
                return null;
            }
            if (CropImage.this.mBitmap.getWidth() > 256) {
                this.mScale = 256.0f / ((float) CropImage.this.mBitmap.getWidth());
            }
            Matrix matrix = new Matrix();
            float f = this.mScale;
            matrix.setScale(f, f);
            return Bitmap.createBitmap(CropImage.this.mBitmap, 0, 0, CropImage.this.mBitmap.getWidth(), CropImage.this.mBitmap.getHeight(), matrix, true);
        }

        public void run() {
            this.mImageMatrix = CropImage.this.mImageView.getImageMatrix();
            Bitmap prepareBitmap = prepareBitmap();
            this.mScale = 1.0f / this.mScale;
            if (prepareBitmap != null && CropImage.this.mDoFaceDetection) {
                this.mNumFaces = new FaceDetector(prepareBitmap.getWidth(), prepareBitmap.getHeight(), this.mFaces.length).findFaces(prepareBitmap, this.mFaces);
            }
            if (!(prepareBitmap == null || prepareBitmap == CropImage.this.mBitmap)) {
                prepareBitmap.recycle();
            }
            CropImage.this.mHandler.post(new Runnable() {
                public void run() {
                    CropImage.this.mWaitingToPick = C08526.this.mNumFaces > 1;
                    if (C08526.this.mNumFaces > 0) {
                        for (int i = 0; i < C08526.this.mNumFaces; i++) {
                            C08526 r1 = C08526.this;
                            r1.handleFace(r1.mFaces[i]);
                        }
                    } else {
                        C08526.this.makeDefault();
                    }
                    CropImage.this.mImageView.invalidate();
                    if (CropImage.this.mImageView.mHighlightViews.size() == 1) {
                        CropImage.this.mCrop = CropImage.this.mImageView.mHighlightViews.get(0);
                        CropImage.this.mCrop.setFocus(true);
                    }
                    if (C08526.this.mNumFaces > 1) {
                        Toast.makeText(CropImage.this, "Multi face crop help", 0).show();
                    }
                }
            });
        }
    };
    private Uri mSaveUri = null;
    boolean mSaving;
    private boolean mScale;
    private boolean mScaleUp = true;
    boolean mWaitingToPick;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContentResolver = getContentResolver();
        requestWindowFeature(1);
        setContentView(C0856R.layout.cropimage);
        this.mImageView = (CropImageView) findViewById(C0856R.C0859id.image);
        showStorageToast(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getString(CIRCLE_CROP) != null) {
                if (Build.VERSION.SDK_INT > 11) {
                    this.mImageView.setLayerType(1, (Paint) null);
                }
                this.mCircleCrop = true;
                this.mAspectX = 1;
                this.mAspectY = 1;
            }
            String string = extras.getString(IMAGE_PATH);
            this.mImagePath = string;
            this.mSaveUri = getImageUri(string);
            this.mBitmap = getBitmap(this.mImagePath);
            if (extras.containsKey(IMAGE_QUALITY) && (extras.get(IMAGE_QUALITY) instanceof Integer)) {
                this.mPhotoQualityPercentage = extras.getInt(IMAGE_QUALITY);
            }
            if (!extras.containsKey(ASPECT_X) || !(extras.get(ASPECT_X) instanceof Integer)) {
                throw new IllegalArgumentException("aspect_x must be integer");
            }
            this.mAspectX = extras.getInt(ASPECT_X);
            if (!extras.containsKey(ASPECT_Y) || !(extras.get(ASPECT_Y) instanceof Integer)) {
                throw new IllegalArgumentException("aspect_y must be integer");
            }
            this.mAspectY = extras.getInt(ASPECT_Y);
            this.mOutputX = extras.getInt(OUTPUT_X);
            this.mOutputY = extras.getInt(OUTPUT_Y);
            this.mScale = extras.getBoolean(SCALE, true);
            this.mScaleUp = extras.getBoolean(SCALE_UP_IF_NEEDED, true);
        }
        if (this.mBitmap == null) {
            Logger.m45d(TAG, "finish!!!");
            finish();
            return;
        }
        getWindow().addFlags(1024);
        findViewById(C0856R.C0859id.rL_cancel).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CropImage.this.setResult(0);
                CropImage.this.finish();
            }
        });
        findViewById(C0856R.C0859id.rL_done).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    CropImage.this.onSaveClicked();
                } catch (Exception unused) {
                    CropImage.this.finish();
                }
            }
        });
        findViewById(C0856R.C0859id.rotateLeft).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CropImage cropImage = CropImage.this;
                Bitmap unused = cropImage.mBitmap = Util.rotateImage(cropImage.mBitmap, -90.0f);
                CropImage.this.mImageView.setImageRotateBitmapResetBase(new RotateBitmap(CropImage.this.mBitmap), true);
                CropImage.this.mRunFaceDetection.run();
            }
        });
        startFaceDetection();
        File file = new File(this.mImagePath);
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Locale locale = Locale.getDefault();
        updateBaseContextLocale(getBaseContext());
        updateResourcesLocale(getApplicationContext(), locale);
        updateResourcesLocaleLegacy(getApplicationContext(), locale);
    }

    private Uri getImageUri(String str) {
        return Uri.fromFile(new File(str));
    }

    private Bitmap getBitmap(String str) {
        Uri imageUri = getImageUri(str);
        try {
            InputStream openInputStream = this.mContentResolver.openInputStream(imageUri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(openInputStream, (Rect) null, options);
            openInputStream.close();
            if (options.outHeight > 1024 || options.outWidth > 1024) {
                i = (int) Math.pow(2.0d, (double) ((int) Math.round(Math.log(1024.0d / ((double) Math.max(options.outHeight, options.outWidth))) / Math.log(0.5d))));
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = i;
            InputStream openInputStream2 = this.mContentResolver.openInputStream(imageUri);
            Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream2, (Rect) null, options2);
            openInputStream2.close();
            return decodeStream;
        } catch (FileNotFoundException unused) {
            Logger.m47e(TAG, "file " + str + " not found");
            return null;
        } catch (IOException unused2) {
            Logger.m47e(TAG, "file " + str + " not found");
            return null;
        }
    }

    private void startFaceDetection() {
        if (!isFinishing()) {
            this.mImageView.setImageBitmapResetBase(this.mBitmap, true);
            Util.startBackgroundJob(this, (String) null, "Please wait…", new Runnable() {
                public void run() {
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    final Bitmap access$100 = CropImage.this.mBitmap;
                    CropImage.this.mHandler.post(new Runnable() {
                        public void run() {
                            if (!(access$100 == CropImage.this.mBitmap || access$100 == null)) {
                                CropImage.this.mImageView.setImageBitmapResetBase(access$100, true);
                                CropImage.this.mBitmap.recycle();
                                Bitmap unused = CropImage.this.mBitmap = access$100;
                            }
                            if (CropImage.this.mImageView.getScale() == 1.0f) {
                                CropImage.this.mImageView.center(true, true);
                            }
                            countDownLatch.countDown();
                        }
                    });
                    try {
                        countDownLatch.await();
                        CropImage.this.mRunFaceDetection.run();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, this.mHandler);
        }
    }

    /* access modifiers changed from: private */
    public void onSaveClicked() throws Exception {
        HighlightView highlightView;
        int i;
        Bitmap bitmap;
        if (!this.mSaving && (highlightView = this.mCrop) != null) {
            this.mSaving = true;
            Rect cropRect = highlightView.getCropRect();
            int width = cropRect.width();
            int height = cropRect.height();
            try {
                final Bitmap createBitmap = Bitmap.createBitmap(width, height, this.mCircleCrop ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
                if (createBitmap != null) {
                    new Canvas(createBitmap).drawBitmap(this.mBitmap, cropRect, new Rect(0, 0, width, height), (Paint) null);
                    if (this.mCircleCrop) {
                        Canvas canvas = new Canvas(createBitmap);
                        Path path = new Path();
                        float f = ((float) width) / 2.0f;
                        path.addCircle(f, ((float) height) / 2.0f, f, Path.Direction.CW);
                        canvas.clipPath(path, Region.Op.DIFFERENCE);
                        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    }
                    int i2 = this.mOutputX;
                    if (!(i2 == 0 || (i = this.mOutputY) == 0)) {
                        if (this.mScale) {
                            bitmap = Util.transform(new Matrix(), createBitmap, this.mOutputX, this.mOutputY, this.mScaleUp);
                            if (createBitmap != bitmap) {
                                createBitmap.recycle();
                            }
                        } else {
                            bitmap = Bitmap.createBitmap(i2, i, Bitmap.Config.RGB_565);
                            Canvas canvas2 = new Canvas(bitmap);
                            Rect cropRect2 = this.mCrop.getCropRect();
                            Rect rect = new Rect(0, 0, this.mOutputX, this.mOutputY);
                            int width2 = (cropRect2.width() - rect.width()) / 2;
                            int height2 = (cropRect2.height() - rect.height()) / 2;
                            cropRect2.inset(Math.max(0, width2), Math.max(0, height2));
                            rect.inset(Math.max(0, -width2), Math.max(0, -height2));
                            canvas2.drawBitmap(this.mBitmap, cropRect2, rect, (Paint) null);
                            createBitmap.recycle();
                        }
                        createBitmap = bitmap;
                    }
                    Bundle extras = getIntent().getExtras();
                    if (extras == null || (extras.getParcelable("data") == null && !extras.getBoolean(RETURN_DATA))) {
                        Util.startBackgroundJob(this, (String) null, getString(C0856R.string.saving_image), new Runnable() {
                            public void run() {
                                CropImage.this.saveOutput(createBitmap);
                            }
                        }, this.mHandler);
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("data", createBitmap);
                    setResult(-1, new Intent().setAction(ACTION_INLINE_DATA).putExtras(bundle));
                    finish();
                }
            } catch (Exception e) {
                throw e;
            }
        }
    }

    /* access modifiers changed from: private */
    public void saveOutput(Bitmap bitmap) {
        Uri uri = this.mSaveUri;
        if (uri != null) {
            try {
                OutputStream openOutputStream = this.mContentResolver.openOutputStream(uri);
                if (openOutputStream != null) {
                    bitmap.compress(this.mOutputFormat, this.mPhotoQualityPercentage, openOutputStream);
                }
                Util.closeSilently(openOutputStream);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(this.mSaveUri.toString());
                intent.putExtras(bundle);
                intent.putExtra(IMAGE_PATH, this.mImagePath);
                intent.putExtra(ORIENTATION_IN_DEGREES, Util.getOrientationInDegree(this));
                setResult(-1, intent);
            } catch (IOException e) {
                Logger.m48e(TAG, "Cannot open file: " + this.mSaveUri, e);
                setResult(0);
                finish();
                Util.closeSilently((Closeable) null);
                return;
            } catch (Throwable th) {
                Util.closeSilently((Closeable) null);
                throw th;
            }
        } else {
            Logger.m47e(TAG, "not defined image url");
        }
        bitmap.recycle();
        finish();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        BitmapManager.instance().cancelThreadDecoding(this.mDecodingThreads);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    public static void showStorageToast(Activity activity) {
        showStorageToast(activity, calculatePicturesRemaining(activity));
    }

    public static void showStorageToast(Activity activity, int i) {
        String str;
        if (i == -1) {
            str = Environment.getExternalStorageState().equals("checking") ? activity.getString(C0856R.string.preparing_card) : activity.getString(C0856R.string.no_storage_card);
        } else {
            str = i < 1 ? activity.getString(C0856R.string.not_enough_space) : null;
        }
        if (str != null) {
            Toast.makeText(activity, str, 1).show();
        }
    }

    public static int calculatePicturesRemaining(Activity activity) {
        String str;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                str = Environment.getExternalStorageDirectory().toString();
            } else {
                str = activity.getFilesDir().toString();
            }
            StatFs statFs = new StatFs(str);
            return (int) ((((float) statFs.getAvailableBlocks()) * ((float) statFs.getBlockSize())) / 400000.0f);
        } catch (Exception unused) {
            return -2;
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateBaseContextLocale(getBaseContext());
        Locale locale = Locale.getDefault();
        updateResourcesLocale(getApplicationContext(), locale);
        updateResourcesLocaleLegacy(getApplicationContext(), locale);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(updateBaseContextLocale(context));
    }

    private Context updateBaseContextLocale(Context context) {
        Locale locale = Locale.getDefault();
        Locale.setDefault(locale);
        if (Build.VERSION.SDK_INT >= 24) {
            return updateResourcesLocale(context, locale);
        }
        return updateResourcesLocaleLegacy(context, locale);
    }

    private Context updateResourcesLocale(Context context, Locale locale) {
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    private Context updateResourcesLocaleLegacy(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }
}
