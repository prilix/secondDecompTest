package com.jch.racWiFi.weather;

public class WeatherHttpClient {
    private static String IMG_URL = "http://openweathermap.org/img/w/";
    private static String URL_BY_CITY_NAME = "http://api.openweathermap.org/data/2.5/weather?APPID=82d3d1a474dd67e908cf6e7527fddf61&units=metric&q=";
    private static String URL_BY_ZIP_CODE = "http://api.openweathermap.org/data/2.5/weather?APPID=82d3d1a474dd67e908cf6e7527fddf61&units=metric&zip=";

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00e5 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00fa */
    /* JADX WARNING: Missing exception handler attribute for start block: B:58:0x0102 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getWeatherData(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            r0 = 0
            java.util.Locale r1 = com.jch.racWiFi.Localization.LocaleConfiguration.getCurrentAppLocale()     // Catch:{ all -> 0x00f1 }
            java.lang.String r1 = r1.getLanguage()     // Catch:{ all -> 0x00f1 }
            java.lang.String r1 = r1.toLowerCase()     // Catch:{ all -> 0x00f1 }
            r2 = -1
            int r3 = r1.hashCode()     // Catch:{ all -> 0x00f1 }
            r4 = 3241(0xca9, float:4.542E-42)
            r5 = 2
            r6 = 1
            if (r3 == r4) goto L_0x0035
            r4 = 3383(0xd37, float:4.74E-42)
            if (r3 == r4) goto L_0x002b
            r4 = 3398(0xd46, float:4.762E-42)
            if (r3 == r4) goto L_0x0021
            goto L_0x003e
        L_0x0021:
            java.lang.String r3 = "jp"
            boolean r3 = r1.equals(r3)     // Catch:{ all -> 0x00f1 }
            if (r3 == 0) goto L_0x003e
            r2 = 0
            goto L_0x003e
        L_0x002b:
            java.lang.String r3 = "ja"
            boolean r3 = r1.equals(r3)     // Catch:{ all -> 0x00f1 }
            if (r3 == 0) goto L_0x003e
            r2 = 1
            goto L_0x003e
        L_0x0035:
            java.lang.String r3 = "en"
            boolean r3 = r1.equals(r3)     // Catch:{ all -> 0x00f1 }
            if (r3 == 0) goto L_0x003e
            r2 = 2
        L_0x003e:
            java.lang.String r3 = ","
            if (r2 == 0) goto L_0x0070
            if (r2 == r6) goto L_0x0070
            if (r2 == r5) goto L_0x0048
            r9 = r0
            goto L_0x0097
        L_0x0048:
            java.net.URL r2 = new java.net.URL     // Catch:{ all -> 0x00f1 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f1 }
            r4.<init>()     // Catch:{ all -> 0x00f1 }
            java.lang.String r5 = URL_BY_ZIP_CODE     // Catch:{ all -> 0x00f1 }
            r4.append(r5)     // Catch:{ all -> 0x00f1 }
            r4.append(r9)     // Catch:{ all -> 0x00f1 }
            r4.append(r3)     // Catch:{ all -> 0x00f1 }
            r4.append(r10)     // Catch:{ all -> 0x00f1 }
            java.lang.String r9 = "&lang=en"
            r4.append(r9)     // Catch:{ all -> 0x00f1 }
            java.lang.String r9 = r4.toString()     // Catch:{ all -> 0x00f1 }
            r2.<init>(r9)     // Catch:{ all -> 0x00f1 }
            java.net.URLConnection r9 = r2.openConnection()     // Catch:{ all -> 0x00f1 }
            java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch:{ all -> 0x00f1 }
            goto L_0x0097
        L_0x0070:
            java.net.URL r2 = new java.net.URL     // Catch:{ all -> 0x00f1 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f1 }
            r4.<init>()     // Catch:{ all -> 0x00f1 }
            java.lang.String r5 = URL_BY_ZIP_CODE     // Catch:{ all -> 0x00f1 }
            r4.append(r5)     // Catch:{ all -> 0x00f1 }
            r4.append(r9)     // Catch:{ all -> 0x00f1 }
            r4.append(r3)     // Catch:{ all -> 0x00f1 }
            r4.append(r10)     // Catch:{ all -> 0x00f1 }
            java.lang.String r9 = "&lang=ja"
            r4.append(r9)     // Catch:{ all -> 0x00f1 }
            java.lang.String r9 = r4.toString()     // Catch:{ all -> 0x00f1 }
            r2.<init>(r9)     // Catch:{ all -> 0x00f1 }
            java.net.URLConnection r9 = r2.openConnection()     // Catch:{ all -> 0x00f1 }
            java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch:{ all -> 0x00f1 }
        L_0x0097:
            java.lang.String r10 = "Weather"
            com.accord.common.utils.Logger.m47e(r10, r1)     // Catch:{ all -> 0x00eb }
            java.lang.String r10 = "GET"
            r9.setRequestMethod(r10)     // Catch:{ all -> 0x00eb }
            r9.setDoInput(r6)     // Catch:{ all -> 0x00eb }
            r9.setDoOutput(r6)     // Catch:{ all -> 0x00eb }
            r9.connect()     // Catch:{ all -> 0x00eb }
            java.lang.StringBuffer r10 = new java.lang.StringBuffer     // Catch:{ all -> 0x00eb }
            r10.<init>()     // Catch:{ all -> 0x00eb }
            java.io.InputStream r1 = r9.getInputStream()     // Catch:{ all -> 0x00eb }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ all -> 0x00e9 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x00e9 }
            r3.<init>(r1)     // Catch:{ all -> 0x00e9 }
            r2.<init>(r3)     // Catch:{ all -> 0x00e9 }
        L_0x00bd:
            java.lang.String r3 = r2.readLine()     // Catch:{ all -> 0x00e9 }
            if (r3 == 0) goto L_0x00d8
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e9 }
            r4.<init>()     // Catch:{ all -> 0x00e9 }
            r4.append(r3)     // Catch:{ all -> 0x00e9 }
            java.lang.String r3 = "\r\n"
            r4.append(r3)     // Catch:{ all -> 0x00e9 }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x00e9 }
            r10.append(r3)     // Catch:{ all -> 0x00e9 }
            goto L_0x00bd
        L_0x00d8:
            r1.close()     // Catch:{ all -> 0x00e9 }
            r9.disconnect()     // Catch:{ all -> 0x00e9 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x00e9 }
            r1.close()     // Catch:{ all -> 0x00e5 }
        L_0x00e5:
            r9.disconnect()     // Catch:{ all -> 0x00e8 }
        L_0x00e8:
            return r10
        L_0x00e9:
            r10 = move-exception
            goto L_0x00ed
        L_0x00eb:
            r10 = move-exception
            r1 = r0
        L_0x00ed:
            r7 = r10
            r10 = r9
            r9 = r7
            goto L_0x00f4
        L_0x00f1:
            r9 = move-exception
            r10 = r0
            r1 = r10
        L_0x00f4:
            r9.printStackTrace()     // Catch:{ all -> 0x00fe }
            r1.close()     // Catch:{ all -> 0x00fa }
        L_0x00fa:
            r10.disconnect()     // Catch:{ all -> 0x00fd }
        L_0x00fd:
            return r0
        L_0x00fe:
            r9 = move-exception
            r1.close()     // Catch:{ all -> 0x0102 }
        L_0x0102:
            r10.disconnect()     // Catch:{ all -> 0x0105 }
        L_0x0105:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.weather.WeatherHttpClient.getWeatherData(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00d7 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00ec */
    /* JADX WARNING: Missing exception handler attribute for start block: B:56:0x00f4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getWeatherData(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 0
            java.util.Locale r1 = com.jch.racWiFi.Localization.LocaleConfiguration.getCurrentAppLocale()     // Catch:{ all -> 0x00e3 }
            java.lang.String r1 = r1.getLanguage()     // Catch:{ all -> 0x00e3 }
            java.lang.String r1 = r1.toLowerCase()     // Catch:{ all -> 0x00e3 }
            r2 = -1
            int r3 = r1.hashCode()     // Catch:{ all -> 0x00e3 }
            r4 = 3241(0xca9, float:4.542E-42)
            r5 = 2
            r6 = 1
            if (r3 == r4) goto L_0x0035
            r4 = 3383(0xd37, float:4.74E-42)
            if (r3 == r4) goto L_0x002b
            r4 = 3398(0xd46, float:4.762E-42)
            if (r3 == r4) goto L_0x0021
            goto L_0x003e
        L_0x0021:
            java.lang.String r3 = "jp"
            boolean r3 = r1.equals(r3)     // Catch:{ all -> 0x00e3 }
            if (r3 == 0) goto L_0x003e
            r2 = 0
            goto L_0x003e
        L_0x002b:
            java.lang.String r3 = "ja"
            boolean r3 = r1.equals(r3)     // Catch:{ all -> 0x00e3 }
            if (r3 == 0) goto L_0x003e
            r2 = 1
            goto L_0x003e
        L_0x0035:
            java.lang.String r3 = "en"
            boolean r3 = r1.equals(r3)     // Catch:{ all -> 0x00e3 }
            if (r3 == 0) goto L_0x003e
            r2 = 2
        L_0x003e:
            if (r2 == 0) goto L_0x0068
            if (r2 == r6) goto L_0x0068
            if (r2 == r5) goto L_0x0046
            r9 = r0
            goto L_0x0089
        L_0x0046:
            java.net.URL r2 = new java.net.URL     // Catch:{ all -> 0x00e3 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e3 }
            r3.<init>()     // Catch:{ all -> 0x00e3 }
            java.lang.String r4 = URL_BY_CITY_NAME     // Catch:{ all -> 0x00e3 }
            r3.append(r4)     // Catch:{ all -> 0x00e3 }
            r3.append(r9)     // Catch:{ all -> 0x00e3 }
            java.lang.String r9 = "&lang=en"
            r3.append(r9)     // Catch:{ all -> 0x00e3 }
            java.lang.String r9 = r3.toString()     // Catch:{ all -> 0x00e3 }
            r2.<init>(r9)     // Catch:{ all -> 0x00e3 }
            java.net.URLConnection r9 = r2.openConnection()     // Catch:{ all -> 0x00e3 }
            java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch:{ all -> 0x00e3 }
            goto L_0x0089
        L_0x0068:
            java.net.URL r2 = new java.net.URL     // Catch:{ all -> 0x00e3 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e3 }
            r3.<init>()     // Catch:{ all -> 0x00e3 }
            java.lang.String r4 = URL_BY_CITY_NAME     // Catch:{ all -> 0x00e3 }
            r3.append(r4)     // Catch:{ all -> 0x00e3 }
            r3.append(r9)     // Catch:{ all -> 0x00e3 }
            java.lang.String r9 = "&lang=ja"
            r3.append(r9)     // Catch:{ all -> 0x00e3 }
            java.lang.String r9 = r3.toString()     // Catch:{ all -> 0x00e3 }
            r2.<init>(r9)     // Catch:{ all -> 0x00e3 }
            java.net.URLConnection r9 = r2.openConnection()     // Catch:{ all -> 0x00e3 }
            java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch:{ all -> 0x00e3 }
        L_0x0089:
            java.lang.String r2 = "Weather"
            com.accord.common.utils.Logger.m47e(r2, r1)     // Catch:{ all -> 0x00dd }
            java.lang.String r1 = "GET"
            r9.setRequestMethod(r1)     // Catch:{ all -> 0x00dd }
            r9.setDoInput(r6)     // Catch:{ all -> 0x00dd }
            r9.setDoOutput(r6)     // Catch:{ all -> 0x00dd }
            r9.connect()     // Catch:{ all -> 0x00dd }
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ all -> 0x00dd }
            r1.<init>()     // Catch:{ all -> 0x00dd }
            java.io.InputStream r2 = r9.getInputStream()     // Catch:{ all -> 0x00dd }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ all -> 0x00db }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ all -> 0x00db }
            r4.<init>(r2)     // Catch:{ all -> 0x00db }
            r3.<init>(r4)     // Catch:{ all -> 0x00db }
        L_0x00af:
            java.lang.String r4 = r3.readLine()     // Catch:{ all -> 0x00db }
            if (r4 == 0) goto L_0x00ca
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00db }
            r5.<init>()     // Catch:{ all -> 0x00db }
            r5.append(r4)     // Catch:{ all -> 0x00db }
            java.lang.String r4 = "\r\n"
            r5.append(r4)     // Catch:{ all -> 0x00db }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x00db }
            r1.append(r4)     // Catch:{ all -> 0x00db }
            goto L_0x00af
        L_0x00ca:
            r2.close()     // Catch:{ all -> 0x00db }
            r9.disconnect()     // Catch:{ all -> 0x00db }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x00db }
            r2.close()     // Catch:{ all -> 0x00d7 }
        L_0x00d7:
            r9.disconnect()     // Catch:{ all -> 0x00da }
        L_0x00da:
            return r0
        L_0x00db:
            r1 = move-exception
            goto L_0x00df
        L_0x00dd:
            r1 = move-exception
            r2 = r0
        L_0x00df:
            r7 = r1
            r1 = r9
            r9 = r7
            goto L_0x00e6
        L_0x00e3:
            r9 = move-exception
            r1 = r0
            r2 = r1
        L_0x00e6:
            r9.printStackTrace()     // Catch:{ all -> 0x00f0 }
            r2.close()     // Catch:{ all -> 0x00ec }
        L_0x00ec:
            r1.disconnect()     // Catch:{ all -> 0x00ef }
        L_0x00ef:
            return r0
        L_0x00f0:
            r9 = move-exception
            r2.close()     // Catch:{ all -> 0x00f4 }
        L_0x00f4:
            r1.disconnect()     // Catch:{ all -> 0x00f7 }
        L_0x00f7:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.weather.WeatherHttpClient.getWeatherData(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:20|21|24|25|26|27|28|29|30) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x004b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x005d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0065 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getImage(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ all -> 0x0054 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0054 }
            r2.<init>()     // Catch:{ all -> 0x0054 }
            java.lang.String r3 = IMG_URL     // Catch:{ all -> 0x0054 }
            r2.append(r3)     // Catch:{ all -> 0x0054 }
            r2.append(r7)     // Catch:{ all -> 0x0054 }
            java.lang.String r7 = r2.toString()     // Catch:{ all -> 0x0054 }
            r1.<init>(r7)     // Catch:{ all -> 0x0054 }
            java.net.URLConnection r7 = r1.openConnection()     // Catch:{ all -> 0x0054 }
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ all -> 0x0054 }
            java.lang.String r1 = "GET"
            r7.setRequestMethod(r1)     // Catch:{ all -> 0x0051 }
            r1 = 1
            r7.setDoInput(r1)     // Catch:{ all -> 0x0051 }
            r7.setDoOutput(r1)     // Catch:{ all -> 0x0051 }
            r7.connect()     // Catch:{ all -> 0x0051 }
            java.io.InputStream r1 = r7.getInputStream()     // Catch:{ all -> 0x0051 }
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch:{ all -> 0x004f }
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x004f }
            r3.<init>()     // Catch:{ all -> 0x004f }
        L_0x0039:
            int r4 = r1.read(r2)     // Catch:{ all -> 0x004f }
            r5 = -1
            if (r4 == r5) goto L_0x0044
            r3.write(r2)     // Catch:{ all -> 0x004f }
            goto L_0x0039
        L_0x0044:
            byte[] r0 = r3.toByteArray()     // Catch:{ all -> 0x004f }
            r1.close()     // Catch:{ all -> 0x004b }
        L_0x004b:
            r7.disconnect()     // Catch:{ all -> 0x004e }
        L_0x004e:
            return r0
        L_0x004f:
            r2 = move-exception
            goto L_0x0057
        L_0x0051:
            r2 = move-exception
            r1 = r0
            goto L_0x0057
        L_0x0054:
            r2 = move-exception
            r7 = r0
            r1 = r7
        L_0x0057:
            r2.printStackTrace()     // Catch:{ all -> 0x0061 }
            r1.close()     // Catch:{ all -> 0x005d }
        L_0x005d:
            r7.disconnect()     // Catch:{ all -> 0x0060 }
        L_0x0060:
            return r0
        L_0x0061:
            r0 = move-exception
            r1.close()     // Catch:{ all -> 0x0065 }
        L_0x0065:
            r7.disconnect()     // Catch:{ all -> 0x0068 }
        L_0x0068:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.weather.WeatherHttpClient.getImage(java.lang.String):byte[]");
    }
}
