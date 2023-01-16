package com.google.android.gms.common.api;

import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.C1048Api.ApiOptions;
import com.google.android.gms.common.api.internal.ApiKey;

/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
public interface HasApiKey<O extends C1048Api.ApiOptions> {
    ApiKey<O> getApiKey();
}
