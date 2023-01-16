package androidx.lifecycle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ViewModelStore {
    private final HashMap<String, C0534ViewModel> mMap = new HashMap<>();

    /* access modifiers changed from: package-private */
    public final void put(String str, C0534ViewModel viewModel) {
        C0534ViewModel put = this.mMap.put(str, viewModel);
        if (put != null) {
            put.onCleared();
        }
    }

    /* access modifiers changed from: package-private */
    public final C0534ViewModel get(String str) {
        return this.mMap.get(str);
    }

    /* access modifiers changed from: package-private */
    public Set<String> keys() {
        return new HashSet(this.mMap.keySet());
    }

    public final void clear() {
        for (C0534ViewModel clear : this.mMap.values()) {
            clear.clear();
        }
        this.mMap.clear();
    }
}
