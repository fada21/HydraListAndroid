package com.fada21.edslv.dragable;

import android.widget.BaseAdapter;

public interface Dragable {
    /**
     * Swaps elements in ordered adapter. Should invoke {@link BaseAdapter#notifyDataSetChanged()} after swap completed
     * 
     * @param indexOne
     * @param indexTwo
     */
    void swap(int indexOne, int indexTwo);
}
