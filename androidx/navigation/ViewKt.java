package androidx.navigation;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, mo36738d2 = {"findNavController", "Landroidx/navigation/NavController;", "Landroid/view/View;", "navigation-runtime-ktx_release"}, mo36739k = 2, mo36740mv = {1, 1, 16})
/* compiled from: View.kt */
public final class ViewKt {
    public static final NavController findNavController(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$this$findNavController");
        NavController findNavController = Navigation.findNavController(view);
        Intrinsics.checkExpressionValueIsNotNull(findNavController, "Navigation.findNavController(this)");
        return findNavController;
    }
}
