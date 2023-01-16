package com.jch.racWiFi.fcm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.databinding.ItemFcmSmartFenceBinding;
import com.jch.racWiFi.fcm.model.SmartFence;
import com.jch.racWiFi.fcm.standard.FcmListener;
import com.jch.racWiFi.fcm.view_holder.SmartFenceViewHolder;
import com.jch_hitachi.aircloudglobal.R;
import java.util.List;

public class SmartFenceAdapter extends RecyclerView.Adapter<SmartFenceViewHolder> {
    private FcmListener<SmartFence, SmartFenceAdapter> mFcmListener;
    private List<SmartFence> mSmartFenceList;

    public void setSmartFenceList(List<SmartFence> list) {
        this.mSmartFenceList = list;
        notifyDataSetChanged();
    }

    public void setListener(FcmListener<SmartFence, SmartFenceAdapter> fcmListener) {
        this.mFcmListener = fcmListener;
    }

    public SmartFenceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SmartFenceViewHolder((ItemFcmSmartFenceBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_fcm_smart_fence, viewGroup, false), this.mFcmListener, this);
    }

    public void onBindViewHolder(SmartFenceViewHolder smartFenceViewHolder, int i) {
        SmartFence smartFence = this.mSmartFenceList.get(i);
        smartFenceViewHolder.bind(smartFence);
        int i2 = C18121.$SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory[smartFence.getSubCategory().ordinal()];
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            smartFenceViewHolder.bind(smartFence);
        }
    }

    /* renamed from: com.jch.racWiFi.fcm.adapter.SmartFenceAdapter$1 */
    static /* synthetic */ class C18121 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory[] r0 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory = r0
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.ARRIVING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.LEAVING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$fcm$util$SmartFenceSubCategory     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.fcm.util.SmartFenceSubCategory r1 = com.jch.racWiFi.fcm.util.SmartFenceSubCategory.ENABLE_LOCATION_ACCESS_PERMISSION     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.fcm.adapter.SmartFenceAdapter.C18121.<clinit>():void");
        }
    }

    public int getItemCount() {
        return this.mSmartFenceList.size();
    }
}
