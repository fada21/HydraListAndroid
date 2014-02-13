package com.fada21.android.hydralist.helper;

import android.content.Context;

import com.fada21.android.hydralist.data.HydraListDataProvider;
import com.fada21.android.hydralist.data.HydraListItem;

/**
 * Base class for adapter helpers for library extension
 */
public abstract class HydraListAdapterHelper<T extends HydraListItem> {

    protected final Context context;

    public HydraListAdapterHelper(Context ctx) {
        context = ctx;
    }

    /**
     * Ensures if adapter is properly initialized and provides proper item for the list.
     */
    public abstract void ensureCompliance(HydraListDataProvider<T> dataProvider) throws IllegalStateException;
}
