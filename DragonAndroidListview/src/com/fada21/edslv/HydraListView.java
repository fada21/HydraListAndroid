package com.fada21.edslv;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ListAdapter;

import com.fada21.edslv.expandable.ExpandingListAdapter;
import com.fada21.edslv.expandable.ExpandingListView;

/**
 * <p>
 * Provides various custom list view behavior and effects with animations. It composes these in a way of being a proxy. Behavior is switched on end of by
 * methods:
 * <li>
 * {@link #setExpandable()} - to switch expandable effects implemented in {@link ExpandingListView}, as adapter supply {@link ExpandingListAdapter}</li>
 * </p>
 * 
 * <br/>
 * <p>
 * Note that {@link #setAdapter(ListAdapter)} must be called aftet behavior switching methods or these will throw {@link IllegalStateException}
 * </p>
 */
public class HydraListView extends NakedListView {

    private boolean           adapterEstablished = false;
    private boolean           isExpandable       = false;
    private boolean           isDragable         = false;

    private ExpandingListView expandingListView  = null;

    public HydraListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public HydraListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HydraListView(Context context) {
        super(context);
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public boolean isDragable() {
        return isDragable;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        adapterEstablished = true;
        if (isExpandable) {
            if (!(adapter instanceof ExpandingListAdapter)) {
                throw new UnsupportedOperationException("Must use adapter of ExpandableListAdapter<? extends ExpandableListItem>");
            }
        }
        super.setAdapter(adapter);
    }

    public void setExpandable(boolean isExpandable) {
        if (adapterEstablished)
            throw new IllegalStateException("Can not change expandable property after adapter has been set");

        if (isExpandable) {
            expandingListView = new ExpandingListView(this);
        } else {
            expandingListView = null;
        }
        this.isExpandable = isExpandable;
    }

    public void setDragable(boolean isDragable) {
        if (adapterEstablished)
            throw new IllegalStateException("Can not change dragable property after adapter has been set");

        this.isDragable = isDragable;
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (isExpandable) {
            expandingListView.dispatchDraw(canvas);
        }
    }

}
