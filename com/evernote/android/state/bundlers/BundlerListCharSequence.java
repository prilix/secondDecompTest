package com.evernote.android.state.bundlers;

import android.os.Bundle;
import com.evernote.android.state.Bundler;
import java.util.ArrayList;
import java.util.List;

public class BundlerListCharSequence implements Bundler<List<CharSequence>> {
    public void put(String str, List<CharSequence> list, Bundle bundle) {
        bundle.putCharSequenceArrayList(str, list instanceof ArrayList ? (ArrayList) list : new ArrayList(list));
    }

    public List<CharSequence> get(String str, Bundle bundle) {
        return bundle.getCharSequenceArrayList(str);
    }
}
