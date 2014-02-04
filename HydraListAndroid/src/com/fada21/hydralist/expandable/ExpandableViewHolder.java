package com.fada21.hydralist.expandable;

import android.view.View;

public class ExpandableViewHolder {

    private final int            expandingLayout;

    public ExpandingLayout expandingLayoutViewGroup;

    public ExpandableViewHolder(View convertView, int expandingLayoutResId) {
        expandingLayout = expandingLayoutResId;
        expandingLayoutViewGroup = getExpandingLayoutSafely(convertView);
    }

    public ExpandingLayout getExpandingLayoutSafely(View convertView) {
        View expandingLayoutCandidate = convertView.findViewById(expandingLayout);
        if (expandingLayoutCandidate == null || !(expandingLayoutCandidate instanceof ExpandingLayout)) {
            throw new IllegalStateException(
                    "ExpandingListAdapter should be provided with ExpandingLayout widget in its layout");
        } else {
            return (ExpandingLayout) expandingLayoutCandidate;
        }
    }

}
