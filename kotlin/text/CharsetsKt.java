package kotlin.text;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\b¨\u0006\u0004"}, mo36738d2 = {"charset", "Ljava/nio/charset/Charset;", "charsetName", "", "kotlin-stdlib"}, mo36739k = 2, mo36740mv = {1, 5, 1})
/* compiled from: Charsets.kt */
public final class CharsetsKt {
    private static final Charset charset(String str) {
        Charset forName = Charset.forName(str);
        Intrinsics.checkNotNullExpressionValue(forName, "Charset.forName(charsetName)");
        return forName;
    }
}
