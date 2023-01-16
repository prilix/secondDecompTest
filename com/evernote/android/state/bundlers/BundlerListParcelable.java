package com.evernote.android.state.bundlers;

import android.os.Bundle;
import android.os.Parcelable;
import com.evernote.android.state.Bundler;
import java.util.ArrayList;
import java.util.List;

public class BundlerListParcelable implements Bundler<List<? extends Parcelable>> {
    public void put(String str, List<? extends Parcelable> list, Bundle bundle) {
        bundle.putParcelableArrayList(str, list instanceof ArrayList ? (ArrayList) list : new ArrayList(list));
    }

    public List<? extends Parcelable> get(String str, Bundle bundle) {
        return bundle.getParcelableArrayList(str);
    }
}
