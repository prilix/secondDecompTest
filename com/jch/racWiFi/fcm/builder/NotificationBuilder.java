package com.jch.racWiFi.fcm.builder;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import androidx.core.app.NotificationCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.reflect.TypeToken;
import com.jch.racWiFi.App;
import com.jch.racWiFi.fcm.model.Alert;
import com.jch.racWiFi.fcm.model.Error;
import com.jch.racWiFi.fcm.model.Maintenance;
import com.jch.racWiFi.fcm.model.Reminder;
import com.jch.racWiFi.fcm.model.SmartFence;
import com.jch.racWiFi.fcm.repository.ModelRepository;
import com.jch.racWiFi.fcm.util.DeepLinkFactory;
import com.jch.racWiFi.fcm.util.EnumUtil;
import com.jch.racWiFi.fcm.util.MaintenanceSubCategory;
import com.jch.racWiFi.fcm.util.Type;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.FamilyIdGeoFenceDataMap;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.GoogleGeoFenceApiExtension;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFencePair;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceServerResponseModel;
import com.jch.racWiFi.iduManagement.smartFence.model.LocationControlStateResponseModel;
import com.jch.racWiFi.iduManagement.smartFence.networkDispatcher.GeoFencesNetworkDispatcher;
import com.jch.racWiFi.iduManagement.view.viewImpl.HomePageActivity;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.view.viewImpl.UserManagementActivity;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationBuilder {
    private Context context;
    private final Application mApplication;

    public NotificationBuilder(Application application) {
        this.mApplication = application;
    }

    public void filter(RemoteMessage remoteMessage, ModelRepository modelRepository, DeepLinkFactory deepLinkFactory, Context context2) {
        String str;
        this.context = context2;
        if (remoteMessage != null && (str = remoteMessage.getData().get(Constants.FCM.CATEGORY)) != null) {
            String replace = str.replace(Constants.FCM.DASH, Constants.FCM.UNDER_SCORE);
            String str2 = remoteMessage.getData().get(Constants.FCM.SUB_CATEGORY);
            if (str2 != null) {
                create(replace, str2.replace(Constants.FCM.DASH, Constants.FCM.UNDER_SCORE), System.currentTimeMillis(), remoteMessage.getMessageId(), remoteMessage.getData(), modelRepository, deepLinkFactory);
            }
        }
    }

    private void create(String str, String str2, long j, String str3, Map<String, String> map, ModelRepository modelRepository, DeepLinkFactory deepLinkFactory) {
        Class cls;
        Type type = (Type) EnumUtil.getInstance().getString(Type.class, str);
        if (type != null) {
            switch (C18194.$SwitchMap$com$jch$racWiFi$fcm$util$Type[type.ordinal()]) {
                case 1:
                    SmartFence smartFence = modelRepository.getSmartFence(str2, j, str3, map);
                    if (smartFence.getSubCategory() != null) {
                        Bundle bundle = new Bundle();
                        bundle.putAll(deepLinkFactory.getDeepLink(smartFence).getBundle());
                        bundle.putAll(smartFence.getBundle());
                        createNotification(smartFence.getTitle(this.mApplication), smartFence.getDescription(this.mApplication), smartFence.getSubCategory().getIcon(), smartFence.getId().hashCode(), bundle, HomePageActivity.class);
                        App.initialize(this.context);
                        final GoogleGeoFenceApiExtension googleGeoFenceApiExtension = new GoogleGeoFenceApiExtension(this.context);
                        switch (C18194.$SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory[smartFence.getSubCategory().ordinal()]) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                                new GeoFencesNetworkDispatcher().getGeoFenceStatus(new Callback<ResponseBody>() {
                                    public void onFailure(Call<ResponseBody> call, Throwable th) {
                                    }

                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        GenericResponse successGenericResponse = GenericResponse.getSuccessGenericResponse(response);
                                        if (successGenericResponse.isApiSuccessful()) {
                                            List<LocationControlStateResponseModel> list = (List) successGenericResponse.getBodyOfType(new TypeToken<ArrayList<LocationControlStateResponseModel>>() {
                                            }.getType());
                                            final HashMap hashMap = new HashMap();
                                            if (list != null) {
                                                for (LocationControlStateResponseModel locationControlStateResponseModel : list) {
                                                    hashMap.put(Long.valueOf((long) locationControlStateResponseModel.familyId), locationControlStateResponseModel);
                                                }
                                            }
                                            new GeoFencesNetworkDispatcher().getAllGeoFences(new Callback<ResponseBody>() {
                                                static final /* synthetic */ boolean $assertionsDisabled = false;

                                                public void onFailure(Call<ResponseBody> call, Throwable th) {
                                                }

                                                static {
                                                    Class<NotificationBuilder> cls = NotificationBuilder.class;
                                                }

                                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                    GenericResponse successGenericResponse = GenericResponse.getSuccessGenericResponse(response);
                                                    if (successGenericResponse.isApiSuccessful()) {
                                                        FamilyIdGeoFenceDataMap familyIdGeoFenceDataMap = new FamilyIdGeoFenceDataMap();
                                                        for (GeoFenceServerResponseModel geoFenceServerResponseModel : (List) successGenericResponse.getBodyOfType(new TypeToken<ArrayList<GeoFenceServerResponseModel>>() {
                                                        }.getType())) {
                                                            familyIdGeoFenceDataMap.put(geoFenceServerResponseModel.getFamilyId(), geoFenceServerResponseModel.createInstanceFromGeoFenceServerResponseModel());
                                                            GeoFencePair geoFencePair = (GeoFencePair) familyIdGeoFenceDataMap.get(geoFenceServerResponseModel.getFamilyId());
                                                            Objects.requireNonNull(geoFencePair);
                                                            GeoFencePair geoFencePair2 = geoFencePair;
                                                            LocationControlStateResponseModel locationControlStateResponseModel = (LocationControlStateResponseModel) hashMap.get(geoFenceServerResponseModel.getFamilyId());
                                                            Objects.requireNonNull(locationControlStateResponseModel);
                                                            LocationControlStateResponseModel locationControlStateResponseModel2 = locationControlStateResponseModel;
                                                            geoFencePair.isEnabled = locationControlStateResponseModel.enabled;
                                                        }
                                                        familyIdGeoFenceDataMap.persist();
                                                        googleGeoFenceApiExtension.setFamilyIdGeoFenceDataMap(familyIdGeoFenceDataMap);
                                                        googleGeoFenceApiExtension.removeGeofences(new NotificationBuilder$1$2$$ExternalSyntheticLambda0(googleGeoFenceApiExtension));
                                                    }
                                                }

                                                static /* synthetic */ void lambda$onResponse$1(GoogleGeoFenceApiExtension googleGeoFenceApiExtension, Task task) {
                                                    if (task.isSuccessful()) {
                                                        googleGeoFenceApiExtension.addGeoFencesAll(NotificationBuilder$1$2$$ExternalSyntheticLambda1.INSTANCE);
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });
                                return;
                            case 6:
                                googleGeoFenceApiExtension.removeGeofences(NotificationBuilder$$ExternalSyntheticLambda0.INSTANCE);
                                return;
                            default:
                                return;
                        }
                    } else {
                        return;
                    }
                case 2:
                    Error error = modelRepository.getError(str2, j, str3, map);
                    if (error.getErrorSubCategory() != null) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putAll(deepLinkFactory.getDeepLink(error).getBundle());
                        bundle2.putAll(error.getBundle());
                        createNotification(error.getPushTitle(this.mApplication), error.getPushDescription(this.mApplication), R.drawable.ic_red_critical_error_notification, error.getId().hashCode(), bundle2, HomePageActivity.class);
                        return;
                    }
                    return;
                case 3:
                    Alert alert = modelRepository.getAlert(str2, j, str3, map);
                    if (alert.getSubCategory() != null) {
                        Bundle bundle3 = new Bundle();
                        bundle3.putAll(deepLinkFactory.getDeepLink(alert).getBundle());
                        bundle3.putAll(alert.getBundle());
                        createNotification(alert.getTitle(this.mApplication), alert.getDescription(this.mApplication), alert.getSubCategory().getIcon(), alert.getId().hashCode(), bundle3, HomePageActivity.class);
                        return;
                    }
                    return;
                case 4:
                case 5:
                    Reminder reminder = modelRepository.getReminder(str2, j, str3, map);
                    if (reminder.getSubCategory() != null) {
                        Bundle bundle4 = new Bundle();
                        bundle4.putAll(deepLinkFactory.getDeepLink(reminder).getBundle());
                        bundle4.putAll(reminder.getBundle());
                        createNotification(reminder.getTitle(this.mApplication), reminder.getDescription(this.mApplication), reminder.getSubCategory().getIcon(), reminder.getId().hashCode(), bundle4, HomePageActivity.class);
                        return;
                    }
                    return;
                case 6:
                    Maintenance maintenance = modelRepository.getMaintenance(str2, str3, map);
                    if (maintenance.getSubCategory() != null) {
                        if (maintenance.getSubCategory() == MaintenanceSubCategory.SERVICE_UNAVAILABLE) {
                            maintenance.persist();
                        }
                        Bundle bundle5 = new Bundle();
                        bundle5.putAll(deepLinkFactory.getDeepLink(maintenance).getBundle());
                        bundle5.putAll(maintenance.getBundle());
                        String title = maintenance.getTitle(this.mApplication);
                        String description = maintenance.getDescription(this.mApplication);
                        int icon = maintenance.getSubCategory().getIcon();
                        int hashCode = maintenance.getId().hashCode();
                        if (maintenance.getSubCategory() == MaintenanceSubCategory.SERVICE_UNAVAILABLE) {
                            cls = UserManagementActivity.class;
                        } else {
                            cls = HomePageActivity.class;
                        }
                        createNotification(title, description, icon, hashCode, bundle5, cls);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: com.jch.racWiFi.fcm.builder.NotificationBuilder$4 */
    static /* synthetic */ class C18194 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory;
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|1|2|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Can't wrap try/catch for region: R(30:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|(2:17|18)|19|21|22|23|25|26|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0082 */
        static {
            /*
                com.jch.racWiFi.fcm.util.Type[] r0 = com.jch.racWiFi.fcm.util.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$fcm$util$Type = r0
                r1 = 1
                com.jch.racWiFi.fcm.util.Type r2 = com.jch.racWiFi.fcm.util.Type.SMART_FENCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.fcm.util.Type r3 = com.jch.racWiFi.fcm.util.Type.ERRORS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.fcm.util.Type r4 = com.jch.racWiFi.fcm.util.Type.ALERTS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.fcm.util.Type r5 = com.jch.racWiFi.fcm.util.Type.REMINDER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.fcm.util.Type r6 = com.jch.racWiFi.fcm.util.Type.REMINDERS     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                r5 = 6
                int[] r6 = $SwitchMap$com$jch$racWiFi$fcm$util$Type     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.fcm.util.Type r7 = com.jch.racWiFi.fcm.util.Type.MAINTENANCE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory[] r6 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory = r6
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r7 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.ARRIVING_ENABLED     // Catch:{ NoSuchFieldError -> 0x005a }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                int[] r1 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x0064 }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r6 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.ARRIVING_DISABLED     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r1[r6] = r0     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x006e }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.LEAVING_ENABLED     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.LEAVING_DISABLED     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x0082 }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.LOCATION_CONTROLS_SETTINGS_UPDATED     // Catch:{ NoSuchFieldError -> 0x0082 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0082 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x0082 }
            L_0x0082:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x008c }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.LOCATION_CONTROLS_DISABLED     // Catch:{ NoSuchFieldError -> 0x008c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008c }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x008c }
            L_0x008c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.fcm.builder.NotificationBuilder.C18194.<clinit>():void");
        }
    }

    private void createNotification(String str, String str2, int i, int i2, Bundle bundle, Class<?> cls) {
        if (Build.VERSION.SDK_INT >= 24) {
            if (str == null) {
                str = "";
            }
            Spanned fromHtml = Html.fromHtml(str, 63);
            if (str2 == null) {
                str2 = "";
            }
            launchNotification(fromHtml, i, Html.fromHtml(str2, 63), i2, bundle, cls);
            return;
        }
        if (str == null) {
            str = "";
        }
        Spanned fromHtml2 = Html.fromHtml(str);
        if (str2 == null) {
            str2 = "";
        }
        launchNotification(fromHtml2, i, Html.fromHtml(str2), i2, bundle, cls);
    }

    private PendingIntent getPendingIntent(Bundle bundle, Class<?> cls) {
        Intent intent = new Intent(this.mApplication, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setFlags(268468224);
        if (Build.VERSION.SDK_INT >= 23) {
            return PendingIntent.getActivity(this.mApplication, 0, intent, 201326592);
        }
        return PendingIntent.getActivity(this.mApplication, 0, intent, 134217728);
    }

    private static PendingIntent getPendingIntent(Class<?> cls, Context context2) {
        Intent intent = new Intent(context2, cls);
        intent.setFlags(268468224);
        if (Build.VERSION.SDK_INT >= 23) {
            return PendingIntent.getActivity(context2, 0, intent, 201326592);
        }
        return PendingIntent.getActivity(context2, 0, intent, 134217728);
    }

    public void launchNotification(Spanned spanned, int i, Spanned spanned2, int i2, Bundle bundle, Class<?> cls) {
        final NotificationManager notificationManager = (NotificationManager) this.mApplication.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel("000007", this.mApplication.getString(R.string.app_name), 3));
        }
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this.mApplication);
        builder.setSmallIcon(Build.VERSION.SDK_INT >= 21 ? R.drawable.ic_test_icon : R.mipmap.ic_launcher_global).setColor(this.mApplication.getResources().getColor(R.color.colorRed)).setContentTitle(spanned).setContentText(spanned2).setContentIntent(getPendingIntent(bundle, cls)).setStyle(new NotificationCompat.BigTextStyle().bigText(spanned2));
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setChannelId("000007");
        }
        final int i3 = i2;
        Glide.with((Context) this.mApplication).asBitmap().load(Integer.valueOf(i)).into(new CustomTarget<Bitmap>(64, 64) {
            public void onLoadCleared(Drawable drawable) {
            }

            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                builder.setLargeIcon(NotificationBuilder.createSquaredBitmap(bitmap));
                builder.setAutoCancel(true);
                notificationManager.notify(i3, builder.build());
            }
        });
    }

    public static void launchNotification(Context context2) {
        final NotificationManager notificationManager = (NotificationManager) context2.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel("000008", context2.getString(R.string.app_name), 3));
        }
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context2);
        builder.setSmallIcon((int) R.mipmap.ic_launcher_global).setColor(context2.getResources().getColor(R.color.white)).setContentTitle(context2.getResources().getString(R.string.notification_alert_session_expired_title)).setContentText(context2.getResources().getString(R.string.notification_alert_session_expired_description)).setContentIntent(getPendingIntent((Class<?>) HomePageActivity.class, context2));
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setChannelId("000008");
        }
        Glide.with(context2).asBitmap().load(Integer.valueOf(R.mipmap.ic_launcher_global)).into(new CustomTarget<Bitmap>(64, 64) {
            public void onLoadCleared(Drawable drawable) {
            }

            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                builder.setLargeIcon(NotificationBuilder.createSquaredBitmap(bitmap));
                builder.setAutoCancel(true);
                notificationManager.notify(1234567, builder.build());
            }
        });
    }

    /* access modifiers changed from: private */
    public static Bitmap createSquaredBitmap(Bitmap bitmap) {
        int max = Math.max(bitmap.getWidth(), bitmap.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap(max, max, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        canvas.drawBitmap(bitmap, (float) ((max - bitmap.getWidth()) / 2), (float) ((max - bitmap.getHeight()) / 2), (Paint) null);
        return createBitmap;
    }
}
