package com.jch.racWiFi.userManagement.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import com.accord.common.utils.Logger;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;
import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.Utils.ImageUtils;
import com.jch_hitachi.aircloudglobal.R;
import java.io.File;

public class ProfilePicture implements Parcelable {
    public static final Parcelable.Creator<ProfilePicture> CREATOR = new Parcelable.Creator<ProfilePicture>() {
        public ProfilePicture createFromParcel(Parcel parcel) {
            return new ProfilePicture(parcel);
        }

        public ProfilePicture[] newArray(int i) {
            return new ProfilePicture[i];
        }
    };
    private static final String TAG = "ProfilePicture";
    public boolean demoMode;
    public String filePath = null;
    public Bitmap profilePicBitmap;
    @SerializedName("updatedOn")
    public long timeStamp;
    @SerializedName("profilePictureUrl")
    public String url;

    public int describeContents() {
        return 0;
    }

    protected ProfilePicture(Parcel parcel) {
        this.url = parcel.readString();
        this.timeStamp = parcel.readLong();
        this.demoMode = parcel.readByte() != 0;
        this.profilePicBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
    }

    public boolean isProfilePictureAvailable() {
        return this.url != null;
    }

    public boolean needToFetchFromAwsS3(ProfilePicture profilePicture) {
        return this.timeStamp != profilePicture.timeStamp;
    }

    public ProfilePicture(ProfilePicture profilePicture) {
        this.url = profilePicture.url;
        this.timeStamp = profilePicture.timeStamp;
        this.demoMode = profilePicture.demoMode;
        this.profilePicBitmap = profilePicture.profilePicBitmap;
        this.filePath = profilePicture.filePath;
    }

    public ProfilePicture() {
    }

    public static void loadIntoImageView(ImageView imageView, ProfilePicture profilePicture) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(imageView.getContext());
        circularProgressDrawable.setStrokeWidth(5.0f);
        circularProgressDrawable.setCenterRadius(30.0f);
        circularProgressDrawable.start();
        RequestOptions requestOptions = new RequestOptions();
        int i = R.drawable.ic_user_pic;
        RequestOptions requestOptions2 = (RequestOptions) ((RequestOptions) ((RequestOptions) ((RequestOptions) ((RequestOptions) requestOptions.placeholder((int) R.drawable.ic_user_pic)).diskCacheStrategy(DiskCacheStrategy.ALL)).priority(Priority.HIGH)).error((int) R.drawable.ic_user_pic)).fitCenter();
        if (profilePicture == null) {
            if (Constants.IS_DEMO_MODE) {
                i = R.drawable.ic_app_logo_global;
            }
            Logger.m47e("Profile Pic", "profile pic not available");
            ((RequestBuilder) ((RequestBuilder) Glide.with(imageView.getContext()).load(Integer.valueOf(i)).signature(new ObjectKey(String.valueOf(i)))).diskCacheStrategy(DiskCacheStrategy.ALL)).apply((BaseRequestOptions<?>) requestOptions2).into(imageView);
        } else if (Constants.IS_DEMO_MODE) {
            if (profilePicture.filePath == null) {
                profilePicture.profilePicBitmap = BitmapFactory.decodeResource(imageView.getContext().getResources(), R.drawable.ic_app_logo_global);
            } else {
                profilePicture.profilePicBitmap = ImageUtils.getBitmapFromFile(new File(profilePicture.filePath));
            }
            ((RequestBuilder) ((RequestBuilder) Glide.with(imageView.getContext()).load(profilePicture.profilePicBitmap).signature(new ObjectKey(String.valueOf(profilePicture.timeStamp)))).diskCacheStrategy(DiskCacheStrategy.ALL)).apply((BaseRequestOptions<?>) requestOptions2).listener(new RequestListener<Drawable>() {
                public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    Logger.m47e("Glide", "fail");
                    return false;
                }

                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    Logger.m47e("Glide", "pass");
                    return false;
                }
            }).into(imageView);
        } else {
            ((RequestBuilder) ((RequestBuilder) Glide.with(imageView.getContext()).load(profilePicture.url).signature(new ObjectKey(String.valueOf(profilePicture.timeStamp)))).diskCacheStrategy(DiskCacheStrategy.ALL)).apply((BaseRequestOptions<?>) requestOptions2).listener(new RequestListener<Drawable>() {
                public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    Logger.m47e("Glide", "fail");
                    return false;
                }

                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    Logger.m47e("Glide", "pass");
                    return false;
                }
            }).into(imageView);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeLong(this.timeStamp);
        parcel.writeByte(this.demoMode ? (byte) 1 : 0);
        parcel.writeParcelable(this.profilePicBitmap, i);
    }
}
