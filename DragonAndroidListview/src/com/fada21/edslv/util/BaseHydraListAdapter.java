package com.fada21.edslv.util;

import android.widget.BaseAdapter;

public class BaseHydraListAdapter extends BaseAdapter {
    public final int INVALID_ID = -1;

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
