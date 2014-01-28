package com.fada21.edslv;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

public abstract class ExpandableListAdapter<T extends ExpandableListItem> extends ArrayAdapter<T> {

    protected List<T>   mData;
    protected final int mLayoutViewResourceId;

    public ExpandableListAdapter(Context context, int layoutViewResourceId, List<T> data) {
        super(context, layoutViewResourceId, data);
        mData = data;
        mLayoutViewResourceId = layoutViewResourceId;
    }

    public abstract ExpandingLayout getExpandingView(int position, View convertView);
}
