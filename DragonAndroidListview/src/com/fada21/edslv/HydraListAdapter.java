package com.fada21.edslv;

import android.widget.BaseAdapter;

public class HydraListAdapter extends BaseAdapter {

    public final int INVALID_ID   = -1;

    private boolean  isExpandable = false;
    private boolean  isDragable   = false;

    // ====== behaviour switchers

    public boolean isExpandable() {
        return isExpandable;
    }

    public boolean isDragable() {
        return isDragable;
    }

    public void setExpandable(boolean isExpandable) {

        if (isExpandable) {

        } else {

        }
        this.isExpandable = isExpandable;
    }

    public void setDragable(boolean isDragable) {

        if (isDragable) {

        } else {

        }
        this.isDragable = isDragable;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

}
