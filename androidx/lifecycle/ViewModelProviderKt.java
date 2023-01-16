package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\b¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, mo36738d2 = {"get", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/lifecycle/ViewModelProvider;", "(Landroidx/lifecycle/ViewModelProvider;)Landroidx/lifecycle/ViewModel;", "lifecycle-viewmodel-ktx_release"}, mo36739k = 2, mo36740mv = {1, 4, 1})
/* compiled from: ViewModelProvider.kt */
public final class ViewModelProviderKt {
    public static final /* synthetic */ <VM extends C0534ViewModel> VM get(ViewModelProvider viewModelProvider) {
        Intrinsics.checkNotNullParameter(viewModelProvider, "$this$get");
        Intrinsics.reifiedOperationMarker(4, "VM");
        VM vm = viewModelProvider.get(C0534ViewModel.class);
        Intrinsics.checkNotNullExpressionValue(vm, "get(VM::class.java)");
        return vm;
    }
}
