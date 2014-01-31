package com.fada21.edslv;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListAdapter;

import com.fada21.edslv.dragable.DragableListView;
import com.fada21.edslv.expandable.ExpandingListAdapter;
import com.fada21.edslv.expandable.ExpandingListView;
import com.fada21.edslv.util.PublicListView;

/**
 * <p>
 * Provides various custom ListViews behavior and effects with animations. Behavior is switched on/off by methods:
 * <li>
 * {@link #setExpandable()} - to switch expanding effects implemented in {@link ExpandingListView}, remember to supply instance of {@link ExpandingListAdapter}</li>
 * <li>
 * {@link #setDragable()} - to switch expandable effects implemented in {@link ExpandingListView}, as adapter supply {@link ExpandingListAdapter}</li>
 * </p>
 * 
 * <br/>
 * <p>
 * Note that {@link #setAdapter(ListAdapter)} must not be called before behavior switching methods or these will throw {@link IllegalStateException}
 * </p>
 */
public class HydraListView extends PublicListView {

    private boolean           adapterEstablished = false;
    private boolean           isExpandable       = false;
    private boolean           isDragable         = false;

    private ExpandingListView expandingListView  = null;
    private DragableListView  dragableListView   = null;

    // ====== standaard constuctors

    public HydraListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public HydraListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HydraListView(Context context) {
        super(context);
    }

    // ====== behaviour switchers

    public boolean isExpandable() {
        return isExpandable;
    }

    public boolean isDragable() {
        return isDragable;
    }

    public void setExpandable(boolean isExpandable) {
        unsureAdapterNotSet();

        if (isExpandable) {
            expandingListView = new ExpandingListView(this);
        } else {
            expandingListView = null;
        }
        this.isExpandable = isExpandable;
    }

    public void setDragable(boolean isDragable) {
        unsureAdapterNotSet();

        if (isDragable) {
            dragableListView = new DragableListView(this);
        } else {
            dragableListView = null;
        }
        this.isDragable = isDragable;
    }

    private void unsureAdapterNotSet() {
        if (adapterEstablished)
            throw new IllegalStateException("Can not change behavior properties after adapter has been set");
    }

    // ====== must be overridden methods and mix custom list views behavior, be careful when extending these

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

    @Override
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (isExpandable) {
            expandingListView.dispatchDraw(canvas);
        }
        if (isDragable) {
            dragableListView.dispatchDraw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isDragable && dragableListView.onTouchEvent(ev)) {
            return true;
        } else {
            return super.onTouchEvent(ev);
        }
    }

}
