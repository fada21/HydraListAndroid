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

public abstract class ExpandableListAdapter<T extends ExpandableListItem> extends ArrayAdapter<T> {

    protected List<T>   mData;
    protected final int mLayoutViewResourceId;
    protected final int mExpandingLayoutResId;

    public ExpandableListAdapter(Context context, int layoutViewResourceId, int expandingLayoutResourceId, List<T> data) {
        super(context, layoutViewResourceId, data);
        mLayoutViewResourceId = layoutViewResourceId;
        mExpandingLayoutResId = expandingLayoutResourceId;
        mData = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final T data = mData.get(position);

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(mLayoutViewResourceId, parent, false);
        }

        setupCollapsedView(convertView, data);

        if (data.isExpandble()) {
            getExpandedView(position, convertView);
        }

        convertView.setLayoutParams(new ListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                AbsListView.LayoutParams.WRAP_CONTENT));

        return convertView;
    }

    protected abstract void setupCollapsedView(View convertView, T data);

    public ExpandingLayout getExpandedView(int position, View convertView) {
        final T data = mData.get(position);

        View expandingLayoutCandidate = convertView.findViewById(mExpandingLayoutResId);
        ensureHasExpandableLayout(expandingLayoutCandidate);

        ExpandingLayout expandingLayout = (ExpandingLayout) expandingLayoutCandidate;
        expandingLayout.setExpandedHeight(data.getExpandedHeight());
        expandingLayout.setSizeChangedListener(data);

        setupExpandedView(convertView, data);

        if (!data.isExpanded()) {
            expandingLayout.setVisibility(View.GONE);
        } else {
            expandingLayout.setVisibility(View.VISIBLE);
        }
        return expandingLayout;
    }

    private void ensureHasExpandableLayout(View expLay) {
        if (expLay == null || !(expLay instanceof ExpandingLayout)) {
            throw new IllegalStateException("Expandable list adapter should provide ExpandingLayout");
        }
    }

    protected abstract void setupExpandedView(View convertView, T data);

}
