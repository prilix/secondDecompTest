package androidx.navigation.fragment;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgs;
import androidx.navigation.NavArgsLazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\b¨\u0006\u0005"}, mo36738d2 = {"navArgs", "Landroidx/navigation/NavArgsLazy;", "Args", "Landroidx/navigation/NavArgs;", "Landroidx/fragment/app/Fragment;", "navigation-fragment-ktx_release"}, mo36739k = 2, mo36740mv = {1, 1, 16})
/* compiled from: FragmentNavArgsLazy.kt */
public final class FragmentNavArgsLazyKt {
    public static final /* synthetic */ <Args extends NavArgs> NavArgsLazy<Args> navArgs(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$navArgs");
        Intrinsics.reifiedOperationMarker(4, "Args");
        return new NavArgsLazy<>(Reflection.getOrCreateKotlinClass(NavArgs.class), new FragmentNavArgsLazyKt$navArgs$1(fragment));
    }
}
