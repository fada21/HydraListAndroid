package com.fada21.hydralist.expandable;

import android.content.Context;
import android.view.View;

import com.fada21.hydralist.data.HydraListItem;
import com.fada21.hydralist.helper.HydraAdapterHelper;
import com.fada21.hydralist.util.HydraListConsts;

public abstract class ExpandingAdapterHelper<T extends HydraListItem> extends HydraAdapterHelper<T> {

    private final int       expandingLayout;

    public ExpandingAdapterHelper(Context context, int expandingLayout) {
        super(context);
        this.expandingLayout = expandingLayout;
    }

    /**
     * Provides id in layout in which expanding layout should be filled
     * 
     * @return view element id for expanding part
     */
    public int getExpandingLayout() {
        return expandingLayout;
    }

    @Override
    public void ensureCompliance() throws IllegalStateException {
        if (expandingLayout == HydraListConsts.UNSET) {
            throw new IllegalStateException("Must provide resource id for expanding view (expandingLayoutResId)");
        }
    }

    public void storeExpandableViewHolderAsTag(View convertView) {
        ExpandableViewHolder viewHolder = new ExpandableViewHolder(convertView, getExpandingLayout());
        convertView.setTag(getExpandingLayout(), viewHolder);
    }

    public ExpandingLayout getExpandedView(View convertView, T data) {
        ExpandableListItem expData = (ExpandableListItem) data;
        ExpandableViewHolder viewHolder = (ExpandableViewHolder) convertView.getTag(expandingLayout);
        ExpandingLayout expandingLayout = viewHolder.expandingLayoutViewGroup;
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
