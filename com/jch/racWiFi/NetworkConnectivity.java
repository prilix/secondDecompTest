package com.jch.racWiFi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.accord.common.utils.Logger;
import com.amplitude.api.Constants;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.jch.racWiFi.Utils.SimUtils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkConnectivity {
    public static boolean isNetworkAvailable(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        boolean z = Build.VERSION.SDK_INT < 26 ? !(!SimUtils.getSimState(context) || telephonyManager.getSimState() != 5 || telephonyManager.getDataState() == 0) : !(!SimUtils.getSimState(context) || !telephonyManager.isDataEnabled());
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((activeNetworkInfo != null && activeNetworkInfo.isConnected()) || z || Constants.IS_DEMO_MODE) {
            return true;
        }
        return false;
    }

    public static class CheckInternet extends AsyncTask<String, String, String> {
        private int inter = 0;
        WeakReference<InternetConnectivityCallback> internetConnectivityCallbackWeakReference;
        private int what = -1;

        public interface InternetConnectivityCallback {
            void onInternetConnectionCheck(boolean z, int i);
        }

        public CheckInternet(InternetConnectivityCallback internetConnectivityCallback, int i) {
            this.internetConnectivityCallbackWeakReference = new WeakReference<>(internetConnectivityCallback);
            this.what = i;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
        }

        /* access modifiers changed from: protected */
        public String doInBackground(String... strArr) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
                httpURLConnection.setRequestProperty(AbstractSpiCall.HEADER_USER_AGENT, Constants.PLATFORM);
                httpURLConnection.setRequestProperty("Connection", "close");
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                httpURLConnection.disconnect();
                this.inter = responseCode;
                return null;
            } catch (IOException e) {
                Logger.m48e("qweqwe", "Error checking internet connection", e);
                this.inter = 500;
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
            int i = this.inter;
            if (i < 200 || i >= 300) {
                if (this.internetConnectivityCallbackWeakReference.get() != null) {
                    ((InternetConnectivityCallback) this.internetConnectivityCallbackWeakReference.get()).onInternetConnectionCheck(false, this.what);
                }
            } else if (this.internetConnectivityCallbackWeakReference.get() != null) {
                ((InternetConnectivityCallback) this.internetConnectivityCallbackWeakReference.get()).onInternetConnectionCheck(true, this.what);
            }
        }
    }
}
