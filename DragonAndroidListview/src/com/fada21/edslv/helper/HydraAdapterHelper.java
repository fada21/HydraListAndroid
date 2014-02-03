package com.fada21.edslv.helper;

import com.fada21.edslv.data.HydraListItem;

/**
 * 
 *
 */
public abstract class HydraAdapterHelper<T extends HydraListItem> {

    /**
     * Ensures if adapter is properly initialised and provides proper item for the list.
     * 
     * @param type
     *            obtained through reflection generic type parameter to check for complinace
     */
    public abstract void ensureCompliance() throws IllegalArgumentException;
}
