package com.fada21.hydralist;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListAdapter;

import com.fada21.hydralist.data.HydraListItem;
import com.fada21.hydralist.dragable.DragableListViewImpl;
import com.fada21.hydralist.expandable.ExpandingListViewImpl;
import com.fada21.hydralist.util.PublicListView;

/**
 * <p>
 * Provides various custom ListViews behavior and effects with animations. Behavior is switched on/off by supplied
 * adapter triggering methods:
 * <li>
 * {@link #setExpandable()} - to switch expanding effects implemented in {@link ExpandingListViewImpl}</li>
 * <li>
 * {@link #setDragable()} - to switch expandable effects implemented in {@link ExpandingListViewImpl}</li>
 * </p>
 * 
 * <br/>
 * <p>
 * Note that {@link #setAdapter(ListAdapter)} must not be called before behavior switching methods or these will throw
 * {@link IllegalStateException}
 * </p>
 */
public class HydraListView extends PublicListView {

    private boolean               isExpandable      = false;
    private boolean               isDragable        = false;

    private ExpandingListViewImpl expandingListView = null;
    private DragableListViewImpl  dragableListView  = null;

    // ====== standard constructors

    public HydraListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public HydraListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HydraListView(Context context) {
        super(context);
    }

    // ====== Behavior switchers

    public boolean isExpandable() {
        return isExpandable;
    }

    public boolean isDragable() {
        return isDragable;
    }

    public void setExpandable(boolean isExpandable) {
        if (isExpandable) {
            expandingListView = new ExpandingListViewImpl(this);
        } else {
            expandingListView = null;
        }
        this.isExpandable = isExpandable;
    }

    public void setDragable(boolean isDragable) {
        if (isDragable) {
            dragableListView = new DragableListViewImpl(this);
        } else {
            dragableListView = null;
        }
        this.isDragable = isDragable;
    }

    // ====== must be overridden methods and mix custom list views behavior, be careful when extending these

    @Override
    public void setAdapter(ListAdapter adapter) {
        if (!(adapter instanceof HydraListAdapter)) {
            throw new UnsupportedOperationException("Must use adapter of type HydraListAdapter");
        } else {
            setHydraListAdapter((HydraListAdapter<?>) adapter);
        }

    }

    /**
     * Ensures {@link HydraListView} has proper behavior upon supplied adapter. Once adapter is supplied it cannot be
     * changed.
     * 
     * @param adapter
     */
    public void setHydraListAdapter(HydraListAdapter<? extends HydraListItem> adapter) {
        setExpandable(adapter.isExpandable());
        setDragable(adapter.isDragable());
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
