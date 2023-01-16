package com.jch.racWiFi.fcm.model;

import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.fcm.util.Type;

public class Section {
    private RecyclerView.Adapter adapter;
    private boolean clearAll;
    private boolean expanded;
    private boolean selectedForFilter;
    private int title;
    private Type type;

    public int getTitle() {
        return this.title;
    }

    public void setTitle(int i) {
        this.title = i;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type2) {
        this.type = type2;
    }

    public boolean isClearAll() {
        return this.clearAll;
    }

    public void setClearAll(boolean z) {
        this.clearAll = z;
    }

    public RecyclerView.Adapter getAdapter() {
        return this.adapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter2) {
        this.adapter = adapter2;
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    public void setExpanded(boolean z) {
        this.expanded = z;
    }

    public boolean isSelectedForFilter() {
        return this.selectedForFilter;
    }

    public void setSelectedForFilter(boolean z) {
        this.selectedForFilter = z;
    }
}
