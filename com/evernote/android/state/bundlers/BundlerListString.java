package com.evernote.android.state.bundlers;

import android.os.Bundle;
import com.evernote.android.state.Bundler;
import java.util.ArrayList;
import java.util.List;

public class BundlerListString implements Bundler<List<String>> {
    public void put(String str, List<String> list, Bundle bundle) {
        bundle.putStringArrayList(str, list instanceof ArrayList ? (ArrayList) list : new ArrayList(list));
    }

    public List<String> get(String str, Bundle bundle) {
        return bundle.getStringArrayList(str);
    }
}
