package okhttp3;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B)\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u0016\u0010\u0006\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tH\u0007J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\r\u0010\u0005\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0010J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\r\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0012R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\bR\u0013\u0010\u0004\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\b¨\u0006\u0014"}, mo36738d2 = {"Lokhttp3/MediaType;", "", "mediaType", "", "type", "subtype", "charset", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "()Ljava/lang/String;", "Ljava/nio/charset/Charset;", "defaultValue", "equals", "", "other", "hashCode", "", "-deprecated_subtype", "toString", "-deprecated_type", "Companion", "okhttp"}, mo36739k = 1, mo36740mv = {1, 1, 15})
/* compiled from: MediaType.kt */
public final class MediaType {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Pattern PARAMETER = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
    private static final String QUOTED = "\"([^\"]*)\"";
    private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
    /* access modifiers changed from: private */
    public static final Pattern TYPE_SUBTYPE = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    private final String charset;
    private final String mediaType;
    private final String subtype;
    private final String type;

    @JvmStatic
    public static final MediaType get(String str) {
        return Companion.get(str);
    }

    @JvmStatic
    public static final MediaType parse(String str) {
        return Companion.parse(str);
    }

    public final Charset charset() {
        return charset$default(this, (Charset) null, 1, (Object) null);
    }

    private MediaType(String str, String str2, String str3, String str4) {
        this.mediaType = str;
        this.type = str2;
        this.subtype = str3;
        this.charset = str4;
    }

    public /* synthetic */ MediaType(String str, String str2, String str3, String str4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4);
    }

    public final String type() {
        return this.type;
    }

    public final String subtype() {
        return this.subtype;
    }

    public static /* synthetic */ Charset charset$default(MediaType mediaType2, Charset charset2, int i, Object obj) {
        if ((i & 1) != 0) {
            charset2 = null;
            Charset charset3 = null;
        }
        return mediaType2.charset(charset2);
    }

    public final Charset charset(Charset charset2) {
        try {
            String str = this.charset;
            return str != null ? Charset.forName(str) : charset2;
        } catch (IllegalArgumentException unused) {
            return charset2;
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "type", imports = {}))
    /* renamed from: -deprecated_type  reason: not valid java name */
    public final String m3097deprecated_type() {
        return this.type;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "subtype", imports = {}))
    /* renamed from: -deprecated_subtype  reason: not valid java name */
    public final String m3096deprecated_subtype() {
        return this.subtype;
    }

    public String toString() {
        return this.mediaType;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MediaType) && Intrinsics.areEqual((Object) ((MediaType) obj).mediaType, (Object) this.mediaType);
    }

    public int hashCode() {
        return this.mediaType.hashCode();
    }

    @Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0007¢\u0006\u0002\b\rJ\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0007¢\u0006\u0002\b\u000fJ\u0011\u0010\u0010\u001a\u00020\u000b*\u00020\u0007H\u0007¢\u0006\u0002\b\nJ\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u000b*\u00020\u0007H\u0007¢\u0006\u0002\b\u000eR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo36738d2 = {"Lokhttp3/MediaType$Companion;", "", "()V", "PARAMETER", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "QUOTED", "", "TOKEN", "TYPE_SUBTYPE", "get", "Lokhttp3/MediaType;", "mediaType", "-deprecated_get", "parse", "-deprecated_parse", "toMediaType", "toMediaTypeOrNull", "okhttp"}, mo36739k = 1, mo36740mv = {1, 1, 15})
    /* compiled from: MediaType.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final MediaType get(String str) {
            Intrinsics.checkParameterIsNotNull(str, "$this$toMediaType");
            CharSequence charSequence = str;
            Matcher matcher = MediaType.TYPE_SUBTYPE.matcher(charSequence);
            if (matcher.lookingAt()) {
                String group = matcher.group(1);
                Intrinsics.checkExpressionValueIsNotNull(group, "typeSubtype.group(1)");
                Locale locale = Locale.US;
                Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
                if (group != null) {
                    String lowerCase = group.toLowerCase(locale);
                    Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                    String group2 = matcher.group(2);
                    Intrinsics.checkExpressionValueIsNotNull(group2, "typeSubtype.group(2)");
                    Locale locale2 = Locale.US;
                    Intrinsics.checkExpressionValueIsNotNull(locale2, "Locale.US");
                    if (group2 != null) {
                        String lowerCase2 = group2.toLowerCase(locale2);
                        Intrinsics.checkExpressionValueIsNotNull(lowerCase2, "(this as java.lang.String).toLowerCase(locale)");
                        String str2 = null;
                        Matcher matcher2 = MediaType.PARAMETER.matcher(charSequence);
                        int end = matcher.end();
                        String str3 = null;
                        while (end < str.length()) {
                            matcher2.region(end, str.length());
                            if (matcher2.lookingAt()) {
                                String group3 = matcher2.group(1);
                                if (group3 == null || !StringsKt.equals(group3, "charset", true)) {
                                    end = matcher2.end();
                                } else {
                                    String group4 = matcher2.group(2);
                                    boolean z = false;
                                    if (group4 == null) {
                                        group4 = matcher2.group(3);
                                        Intrinsics.checkExpressionValueIsNotNull(group4, "parameter.group(3)");
                                    } else if (StringsKt.startsWith$default(group4, "'", false, 2, (Object) null) && StringsKt.endsWith$default(group4, "'", false, 2, (Object) null) && group4.length() > 2) {
                                        group4 = group4.substring(1, group4.length() - 1);
                                        Intrinsics.checkExpressionValueIsNotNull(group4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                    }
                                    if (str3 == null || StringsKt.equals(group4, str3, true)) {
                                        z = true;
                                    }
                                    if (z) {
                                        str3 = group4;
                                        end = matcher2.end();
                                    } else {
                                        throw new IllegalArgumentException(("Multiple charsets defined: \"" + str3 + "\" and: \"" + group4 + "\" for: \"" + str + Typography.quote).toString());
                                    }
                                }
                            } else {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Parameter is not formatted correctly: \"");
                                String substring = str.substring(end);
                                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                                sb.append(substring);
                                sb.append("\" for: \"");
                                sb.append(str);
                                sb.append(Typography.quote);
                                throw new IllegalArgumentException(sb.toString().toString());
                            }
                        }
                        return new MediaType(str, lowerCase, lowerCase2, str3, (DefaultConstructorMarker) null);
                    }
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            throw new IllegalArgumentException(("No subtype found for: \"" + str + Typography.quote).toString());
        }

        @JvmStatic
        public final MediaType parse(String str) {
            Intrinsics.checkParameterIsNotNull(str, "$this$toMediaTypeOrNull");
            try {
                Companion companion = this;
                return get(str);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "mediaType.toMediaType()", imports = {"okhttp3.MediaType.Companion.toMediaType"}))
        /* renamed from: -deprecated_get  reason: not valid java name */
        public final MediaType m3098deprecated_get(String str) {
            Intrinsics.checkParameterIsNotNull(str, "mediaType");
            Companion companion = this;
            return get(str);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "mediaType.toMediaTypeOrNull()", imports = {"okhttp3.MediaType.Companion.toMediaTypeOrNull"}))
        /* renamed from: -deprecated_parse  reason: not valid java name */
        public final MediaType m3099deprecated_parse(String str) {
            Intrinsics.checkParameterIsNotNull(str, "mediaType");
            Companion companion = this;
            return parse(str);
        }
    }
}
