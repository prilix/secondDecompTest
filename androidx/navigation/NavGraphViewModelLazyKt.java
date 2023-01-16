package androidx.navigation;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.C0534ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

@Metadata(mo36736bv = {1, 0, 3}, mo36737d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a;\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0010\b\n\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\b¨\u0006\n"}, mo36738d2 = {"navGraphViewModels", "Lkotlin/Lazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/fragment/app/Fragment;", "navGraphId", "", "factoryProducer", "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "navigation-fragment-ktx_release"}, mo36739k = 2, mo36740mv = {1, 1, 16})
/* compiled from: NavGraphViewModelLazy.kt */
public final class NavGraphViewModelLazyKt {
    public static /* synthetic */ Lazy navGraphViewModels$default(Fragment fragment, int i, Function0 function0, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            Function0 function02 = null;
            function0 = null;
        }
        Intrinsics.checkParameterIsNotNull(fragment, "$this$navGraphViewModels");
        Lazy lazy = LazyKt.lazy(new NavGraphViewModelLazyKt$navGraphViewModels$backStackEntry$2(fragment, i));
        Intrinsics.reifiedOperationMarker(4, "VM");
        return FragmentViewModelLazyKt.createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(C0534ViewModel.class), new NavGraphViewModelLazyKt$navGraphViewModels$storeProducer$1(lazy, (KProperty) null), new NavGraphViewModelLazyKt$navGraphViewModels$1(function0, lazy, (KProperty) null));
    }

    public static final /* synthetic */ <VM extends C0534ViewModel> Lazy<VM> navGraphViewModels(Fragment fragment, int i, Function0<? extends ViewModelProvider.Factory> function0) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$navGraphViewModels");
        Lazy lazy = LazyKt.lazy(new NavGraphViewModelLazyKt$navGraphViewModels$backStackEntry$2(fragment, i));
        Intrinsics.reifiedOperationMarker(4, "VM");
        return FragmentViewModelLazyKt.createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(C0534ViewModel.class), new NavGraphViewModelLazyKt$navGraphViewModels$storeProducer$1(lazy, (KProperty) null), new NavGraphViewModelLazyKt$navGraphViewModels$1(function0, lazy, (KProperty) null));
    }
}
