package com.fada21.hydralist;

import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.fada21.hydralist.data.HydraListDataProvider;
import com.fada21.hydralist.data.HydraListItem;
import com.fada21.hydralist.dragable.Dragable;
import com.fada21.hydralist.dragable.DragableAdapterHelper;
import com.fada21.hydralist.expandable.ExpandingAdapterHelper;
import com.fada21.hydralist.helper.BaseAdapterHelper;
import com.fada21.hydralist.helper.HydraAdapterHelper;
import com.fada21.hydralist.util.HydraListConsts;

/**
 * 
 * <p>
 * Adapter for {@link HydraListView}. It works as container for various behaviors implemented in helpers which extends {@link HydraAdapterHelper}.
 * </p>
 * <p>
 * Adapter class cannot be extended. In order to extends adapter and implement extra views, animations or behavior one must extends one of the helpers which is
 * pass on to the builder.
 * </p>
 * <p>
 * In order to add extra list behavior in a modularized way please add a helper and change relevant parts in {@link HydraListView} class.
 * </p>
 * 
 * @param <T>
 */
public final class HydraListAdapter<T extends HydraListItem> extends BaseAdapter {

    protected final HydraListDataProvider<T>  dataProvider;

    protected final int                       itemLayout;

    private final BaseAdapterHelper<T>        baseAdapterHelper;
    protected final ExpandingAdapterHelper<T> expandingHelper;
    protected final DragableAdapterHelper<T>  dragableHelper;

    @SuppressWarnings("unchecked")
    private HydraListAdapter(Builder<T> b) {
        dataProvider = b.dataProvider;

        itemLayout = b.baseAdapterHelper.getItemLayout();

        baseAdapterHelper = b.baseAdapterHelper;
        expandingHelper = b.expandingAdapterHelper;
        dragableHelper = b.dragableAdapterHelper;

        ensureCompliance(baseAdapterHelper, expandingHelper, dragableHelper);
    }

    /**
     * Initiates builder for adapter
     * 
     * @param <BT>
     *            generic parameter for {@link Builder}
     * 
     * @param itemLayout
     *            - layout to inflate for the view
     * @param clazz
     *            only for parameterization
     * @return HydraBuilder for constructing HydraList Adapter
     */
    public static <BT extends HydraListItem> Builder<BT> builder(BaseAdapterHelper<BT> baseAdapterHelper, Class<BT> clazz) {
        return new Builder<BT>(baseAdapterHelper);
    }

    public static class Builder<BT extends HydraListItem> {

        private HydraListDataProvider<BT>  dataProvider;

        private BaseAdapterHelper<BT>      baseAdapterHelper      = null;
        private ExpandingAdapterHelper<BT> expandingAdapterHelper = null;
        private DragableAdapterHelper<BT>  dragableAdapterHelper  = null;

        public Builder(BaseAdapterHelper<BT> helper) {
            this.baseAdapterHelper = helper;
        }

        public Builder<BT> data(HydraListDataProvider<BT> dataProvider) {
            this.dataProvider = dataProvider;
            return this;
        }

        public Builder<BT> data(Cursor cursor) {
            throw new IllegalArgumentException("Cursors not yet supported");
        }

        public Builder<BT> expandable(ExpandingAdapterHelper<BT> helper) {
            expandingAdapterHelper = helper;
            return this;
        }

        public Builder<BT> dragable(DragableAdapterHelper<BT> helper) {
            dragableAdapterHelper = helper;
            return this;
        }

        public HydraListAdapter<BT> build() {
            return new HydraListAdapter<BT>(this);
        }

    }

    // ====== Behavior switchers

    public boolean isExpandable() {
        return expandingHelper != null;
    }

    public boolean isDragable() {
        return dragableHelper != null;
    }

    public BaseAdapterHelper<T> getBaseAdapterHelper() {
        return baseAdapterHelper;
    }

    public ExpandingAdapterHelper<T> getExpandingHelper() {
        return expandingHelper;
    }

    public DragableAdapterHelper<T> getDragableHelper() {
        return dragableHelper;
    }

    /**
     * Checks for general compliance, and if helpers are properly initialized
     * 
     * @param helpers
     */
    private void ensureCompliance(HydraAdapterHelper<T>... helpers) {
        if (helpers.length > 0) {
            for (HydraAdapterHelper<T> hydraAdapterHelper : helpers) {
                if (hydraAdapterHelper != null) {
                    hydraAdapterHelper.ensureCompliance();
                }
            }
        }

        if (baseAdapterHelper == null) {
            throw new IllegalStateException("BaseAdapterHelper instance must be provided!");
        }

        if (isDragable()) {
            if (!(dataProvider instanceof Dragable)) {
                throw new IllegalStateException("Data provider must implement Dragable interface!");
            }
        }
    }

    public HydraListDataProvider<T> getDataProvider() {
        return dataProvider;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getCount() {
        return dataProvider.size();
    }

    @Override
    public T getItem(int position) {
        return dataProvider.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (position < 0 || position >= dataProvider.size()) {
            return HydraListConsts.INVALID;
        } else {
            return dataProvider.get(position).getId();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final T data = dataProvider.get(position);

        if (isExpandable()) {
            if (convertView == null) {
                convertView = baseAdapterHelper.newView(parent);
                expandingHelper.storeExpandableViewHolderAsTag(convertView);
            }
            baseAdapterHelper.setupCollapsedView(convertView, data);
            expandingHelper.getExpandedView(convertView, data);
        } else {
            if (convertView == null) {
                convertView = baseAdapterHelper.newView(parent);
            }
            baseAdapterHelper.setupCollapsedView(convertView, data);
        }

        int layout_width = AbsListView.LayoutParams.MATCH_PARENT;
        int layout_height = AbsListView.LayoutParams.WRAP_CONTENT;
        convertView.setLayoutParams(new ListView.LayoutParams(layout_width, layout_height));

        return convertView;
    }

}
