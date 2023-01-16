package okhttp3;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016¨\u0006\n"}, mo36738d2 = {"okhttp3/CookieJar$Companion$NO_COOKIES$1", "Lokhttp3/CookieJar;", "loadForRequest", "", "Lokhttp3/Cookie;", "url", "Lokhttp3/HttpUrl;", "saveFromResponse", "", "cookies", "okhttp"}, mo36739k = 1, mo36740mv = {1, 1, 15})
/* compiled from: CookieJar.kt */
public final class CookieJar$Companion$NO_COOKIES$1 implements CookieJar {
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        Intrinsics.checkParameterIsNotNull(httpUrl, ImagesContract.URL);
        Intrinsics.checkParameterIsNotNull(list, "cookies");
    }

    CookieJar$Companion$NO_COOKIES$1() {
    }

    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        Intrinsics.checkParameterIsNotNull(httpUrl, ImagesContract.URL);
        return CollectionsKt.emptyList();
    }
}
