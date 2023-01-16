package com.jch.racWiFi.userManagement.network;

import android.webkit.MimeTypeMap;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.NetworkDispatch.AbstractNetworkDispatcher;
import com.jch.racWiFi.genericModels.GenericResponse;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class ProfilePicNetworkDispatcher extends AbstractNetworkDispatcher implements Callback<ResponseBody> {
    public static final String METHOD_DELETE_PROFILE_PIC = "/iam/user/v2/profile-picture/delete";
    public static final String METHOD_UPLOAD_PROFILE_PIC_MULTIPART = "/iam/user/v2/profile-picture/upload";
    private Process process;
    private SingleLiveEvent<GenericResponse> responseSingleLiveEvent = new SingleLiveEvent<>();

    public enum Process {
        UPLOADING,
        DOWNLOADING,
        DELETE
    }

    public interface UpdateProfilePicApi {
        @DELETE("/iam/user/v2/profile-picture/delete")
        Call<ResponseBody> deleteProfilePic();

        @POST("/iam/user/v2/profile-picture/upload")
        @Multipart
        Call<ResponseBody> updateProfilePicture(@Part MultipartBody.Part part, @Header("filePath") String str);
    }

    public Process getProcess() {
        return this.process;
    }

    public SingleLiveEvent<GenericResponse> updateProfilePic(File file) {
        ((UpdateProfilePicApi) getRetrofitService().create(UpdateProfilePicApi.class)).updateProfilePicture(MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse(getMimeType(file.getAbsolutePath())), file)), file.getAbsolutePath()).enqueue(this);
        this.process = Process.UPLOADING;
        return this.responseSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse> deleteProfilePic() {
        ((UpdateProfilePicApi) getRetrofitService().create(UpdateProfilePicApi.class)).deleteProfilePic().enqueue(this);
        this.process = Process.DELETE;
        return this.responseSingleLiveEvent;
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        this.responseSingleLiveEvent.setValue(new GenericResponse.GenericResponseBuilder().withResponse(response).build());
    }

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        this.responseSingleLiveEvent.setValue(new GenericResponse.GenericResponseBuilder().withThrowable(th).build());
    }

    public static String getMimeType(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (fileExtensionFromUrl != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return null;
    }
}
