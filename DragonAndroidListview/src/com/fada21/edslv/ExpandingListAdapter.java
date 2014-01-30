package com.fada21.edslv;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public abstract class ExpandingListAdapter<T extends ExpandableListItem> extends ArrayAdapter<T> {

    protected List<T>   mData;
    protected final int mLayoutViewResourceId;
    protected final int mExpandingLayoutResId;

    public ExpandingListAdapter(Context context, int layoutViewResourceId, int expandingLayoutResourceId, List<T> data) {
        super(context, layoutViewResourceId, data);
        mLayoutViewResourceId = layoutViewResourceId;
        mExpandingLayoutResId = expandingLayoutResourceId;
        mData = data;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final T data = mData.get(position);

        ExpandableViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(mLayoutViewResourceId, parent, false);
            viewHolder = new ExpandableViewHolder(convertView, mExpandingLayoutResId);
            convertView.setTag(mExpandingLayoutResId, viewHolder);
        } else {
            viewHolder = (ExpandableViewHolder) convertView.getTag(mExpandingLayoutResId);
        }

        setupCollapsedView(convertView, data);

        getExpandedView(convertView, position);

        convertView.setLayoutParams(new ListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                AbsListView.LayoutParams.WRAP_CONTENT));

        return convertView;
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
    protected abstract void setupCollapsedView(View convertView, T data);

    protected ExpandingLayout getExpandedView(View convertView, int position) {
        T data = mData.get(position);
        ExpandableViewHolder viewHolder = (ExpandableViewHolder) convertView.getTag(mExpandingLayoutResId);
        ExpandingLayout expandingLayout = viewHolder.expandingLayout;
        expandingLayout.setExpandedHeight(data.getExpandedHeight());
        expandingLayout.setSizeChangedListener(data);

        if (data.isExpandble())
            setupExpandedView(convertView, data);

        if (!data.isExpanded()) {
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
