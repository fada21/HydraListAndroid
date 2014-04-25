package com.fada21.android.hydralist.dragable.interfaces;

import com.fada21.android.hydralist.dragable.DragableListViewDelegate;

/**
 * Implement this interface to be notified of ordering changes. Call {@link DragableListViewDelegate#setOnItemMovedListener(OnItemMovedListener)}
 */
public interface OnItemMovedListener {

	/**
	 * Called after an item is dropped and moved.
	 * 
	 * @param newPosition
	 *            the new position of the item.
	 */
	public void onItemMoved(int newPosition);
}
