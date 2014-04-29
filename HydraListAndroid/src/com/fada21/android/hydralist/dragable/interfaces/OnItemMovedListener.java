package com.fada21.android.hydralist.dragable.interfaces;

import android.widget.ListView;

import com.fada21.android.hydralist.dragable.DragableListViewDelegate;

/**
 * Implement this interface to be notified of ordering changes. Call {@link DragableListViewDelegate#setOnItemMovedListener(OnItemMovedListener)}
 */
public interface OnItemMovedListener {

    /**
     * Called after an item is dropped and moved.
     * 
     * @param movedId
     *            id of moved item
     * @param aboveId
     *            id of item at position above moved item; can be {@link ListView.INVALID_ROW_ID} or -1 when invalid
     * @param belowId
     *            id of item at position below moved item; can be {@link ListView.INVALID_ROW_ID} or -1 when invalid
     */
    public void onItemMoved(long movedId, long aboveId, long belowId);
}
