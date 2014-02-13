package com.fada21.hydralist.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fada21.hydralist.data.HydraListDataProvider;
import com.fada21.hydralist.data.HydraListItem;
import com.fada21.hydralist.util.HydraListConsts;

/**
 * Plain adapter that servers common adapter tasks besides of expansion adapter helpers
 */
public abstract class PlainAdapterHelper<T extends HydraListItem> extends HydraListAdapterHelper<T> {

    private final int            itemLayout;
    private final LayoutInflater inflater;

    public PlainAdapterHelper(Context ctx, int itemLayout) {
        super(ctx);
        inflater = LayoutInflater.from(context);
        this.itemLayout = itemLayout;
    }

    /**
     * Provides list item layout resource id. Layout shall be inflated from this resource.
     * 
     * @return
     */
    public int getItemLayout() {
        return itemLayout;
    }

    @Override
    public void ensureCompliance(HydraListDataProvider<T> dataProvider) throws IllegalArgumentException {

        if (itemLayout == HydraListConsts.UNSET && itemLayout > 0) {
            throw new IllegalStateException("Must provide resource id for item view (itemLayout)");
        }
    }

    /**
     * Setups collapsed view of a list item (regular non expanded or non expandable view)
     * 
     * @param convertView
     *            view to alter
     * @param data
     *            to be filled
     */
    public abstract void setupCollapsedView(View convertView, T data);

    public View newView(ViewGroup parent) {
        return inflater.inflate(itemLayout, parent, false);
    }

}
