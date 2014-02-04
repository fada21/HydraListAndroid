package com.fada21.hydralist.helper;

import android.content.Context;

import com.fada21.hydralist.data.HydraListItem;

/**
 * 
 *
 */
public abstract class HydraAdapterHelper<T extends HydraListItem> {

    protected final Context context;

    public HydraAdapterHelper(Context ctx) {
        context = ctx;
    }

    /**
     * Ensures if adapter is properly initialised and provides proper item for the list.
     * 
     * @param type
     *            obtained through reflection generic type parameter to check for complinace
     */
    public abstract void ensureCompliance() throws IllegalArgumentException;
}
