package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.C1048Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* compiled from: com.google.android.gms:play-services-base@@18.0.1 */
abstract class zag<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zah> {
    public zag(GoogleApiClient googleApiClient) {
        super((C1048Api<?>) Common.API, googleApiClient);
    }
}
