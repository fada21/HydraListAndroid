package com.fada21.edslv.helper;

import android.content.Context;
import android.view.View;

import com.fada21.edslv.data.HydraListItem;
import com.fada21.edslv.expandable.ExpandableListItem;
import com.fada21.edslv.expandable.ExpandableViewHolder;
import com.fada21.edslv.expandable.ExpandingLayout;
import com.fada21.edslv.util.HydraListConsts;

public abstract class ExpandingAdapterHelper<T extends HydraListItem> extends HydraAdapterHelper<T> {

    protected final Context ctx;
    private final int       expandingLayoutResId;

    public ExpandingAdapterHelper(Context context, int expandingLayoutResId) {
        super();
        ctx = context;
        this.expandingLayoutResId = expandingLayoutResId;
    }

    public int getExpandingLayoutResId() {
        return expandingLayoutResId;
    }

    @Override
    public void ensureCompliance() throws IllegalStateException {

        if (expandingLayoutResId == HydraListConsts.UNSET) {
            throw new IllegalStateException("Must provide resource id for expanding view (expandableResId)");
        }
    }

    /**
     * Implement look of collapsed view of a list item
     * 
     * @param convertView
     *            view to alter
     * @param data
     *            to be filled
     * @param viewHelper
     *            pattern for performance
     */
    public abstract void setupCollapsedView(View convertView, T data);

    public ExpandingLayout getExpandedView(View convertView, T data) {
        ExpandableListItem expData = (ExpandableListItem) data;
        ExpandableViewHolder viewHolder = (ExpandableViewHolder) convertView.getTag(expandingLayoutResId);
        ExpandingLayout expandingLayout = viewHolder.expandingLayout;
        expandingLayout.setExpandedHeight(expData.getExpandedHeight());
        expandingLayout.setSizeChangedListener(expData);

        if (expData.isExpandable())
            setupExpandedView(convertView, data);

        if (!expData.isExpanded()) {
            expandingLayout.setVisibility(View.GONE);
        } else {
            expandingLayout.setVisibility(View.VISIBLE);
        }

        return expandingLayout;
    }

    /**
     * Implement look of expanded view of a list item
     * 
     * @param convertView
     *            view to alter
     * @param data
     *            to be filled
     */
    protected abstract void setupExpandedView(View convertView, T data);

}
