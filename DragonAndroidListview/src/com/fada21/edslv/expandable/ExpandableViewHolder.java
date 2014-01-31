package com.fada21.edslv.expandable;

import android.view.View;

public class ExpandableViewHolder {

    private int            mExpandingLayoutResId;

    public ExpandingLayout expandingLayout;

    public ExpandableViewHolder(View convertView, int expandingLayoutResId) {
        mExpandingLayoutResId = expandingLayoutResId;
        expandingLayout = getExpandingLayoutSafely(convertView);
    }

    public ExpandingLayout getExpandingLayoutSafely(View convertView) {
        View expandingLayoutCandidate = convertView.findViewById(mExpandingLayoutResId);
        if (expandingLayoutCandidate == null || !(expandingLayoutCandidate instanceof ExpandingLayout)) {
            throw new IllegalStateException(
                    "ExpandingListAdapter should be provided with ExpandingLayout widget in its layout");
        } else {
            return (ExpandingLayout) expandingLayoutCandidate;
        }
    }

}
