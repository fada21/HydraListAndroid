package com.fada21.android.hydralist.data;

public abstract class HydraListItem {

    private int mCollapsedHeight;

    public HydraListItem(int defaultCollapsedHeight) {
        mCollapsedHeight = defaultCollapsedHeight;
    }

    /**
     * Provide unique id for this item. Collection must have stable ids.
     * 
     * @return long id
     */
    public abstract long getId();

    public boolean isExpandable() {
        return false;
    }

    public int getCollapsedHeight() {
        return mCollapsedHeight;
    }

    public void setCollapsedHeight(int collapsedHeight) {
        mCollapsedHeight = collapsedHeight;
    }

}
