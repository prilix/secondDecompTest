package com.evernote.android.state.bundlers;

import android.os.Bundle;
import com.evernote.android.state.Bundler;
import java.util.ArrayList;
import java.util.List;

public class BundlerListInteger implements Bundler<List<Integer>> {
    public void put(String str, List<Integer> list, Bundle bundle) {
        bundle.putIntegerArrayList(str, list instanceof ArrayList ? (ArrayList) list : new ArrayList(list));
    }

    public List<Integer> get(String str, Bundle bundle) {
        return bundle.getIntegerArrayList(str);
    }
}
