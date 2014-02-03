package com.fada21.edslv;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.fada21.edslv.data.HydraListDataProvider;
import com.fada21.edslv.data.HydraListItem;
import com.fada21.edslv.dragable.Dragable;
import com.fada21.edslv.expandable.ExpandableViewHolder;
import com.fada21.edslv.helper.DragableAdapterHelper;
import com.fada21.edslv.helper.ExpandingAdapterHelper;
import com.fada21.edslv.helper.HydraAdapterHelper;
import com.fada21.edslv.util.HydraListConsts;

/**
 * Adapter for {@link HydraListView}. Extend this to customize list item appearance.
 * 
 * @param <T>
 *            extension to HydraListItem. Determines data operations and initialization compliance
 */
public final class HydraListAdapter<T extends HydraListItem> extends BaseAdapter {

    protected final Context                   ctx;

    protected final boolean                   isExpandable;
    protected final boolean                   isDragable;

    protected final HydraListDataProvider<T>  dataProvider;

    protected final int                       layoutViewResource;

    protected final ExpandingAdapterHelper<T> expandingHelper;
    protected final DragableAdapterHelper<T>  dragableHelper;

    @SuppressWarnings("unchecked")
    private HydraListAdapter(Builder<T> b) {
        ctx = b.ctx;
        isExpandable = b.isExpandable;
        isDragable = b.isDragable;
        dataProvider = b.dataProvider;

        layoutViewResource = b.layoutViewResource;

        expandingHelper = b.expandingAdapterHelper;
        dragableHelper = b.dragableAdapterHelper;

        ensureCompliance(expandingHelper, dragableHelper);
    }

    /**
     * Initiates builder
     * 
     * @param <BT>
     * 
     * @param context
     * @return HydraBuilder for constructing HydraList Adapter
     */
    public static <BT extends HydraListItem> Builder<BT> with(Context context, int layoutViewResource, Class<BT> clazz) {
        return new Builder<BT>(context, layoutViewResource);
    }

    public static class Builder<BT extends HydraListItem> {
        private Context                    ctx;
        private boolean                    isExpandable           = false;
        private boolean                    isDragable             = false;

        private HydraListDataProvider<BT>  dataProvider;

        private int                        layoutViewResource;

        private ExpandingAdapterHelper<BT> expandingAdapterHelper = null;
        private DragableAdapterHelper<BT>  dragableAdapterHelper  = null;

        public Builder(Context context, int layoutViewResource) {
            ctx = context;
            this.layoutViewResource = layoutViewResource;
        }

        public Builder<BT> data(HydraListDataProvider<BT> dataProvider) {
            this.dataProvider = dataProvider;
            return this;
        }

        public Builder<BT> data(Cursor cursor) {
            throw new IllegalArgumentException("Cursors not yet supported");
        }

        public Builder<BT> expandable(ExpandingAdapterHelper<BT> helper) {
            isExpandable = true;
            expandingAdapterHelper = helper;
            return this;
        }

        public Builder<BT> dragable(DragableAdapterHelper<BT> helper) {
            isDragable = true;
            dragableAdapterHelper = helper;
            return this;
        }

        public HydraListAdapter<BT> build() {
            return new HydraListAdapter<BT>(this);
        }

    }

    // ====== Behavior switchers

    public boolean isExpandable() {
        return isExpandable;
    }

    public boolean isDragable() {
        return isDragable;
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

        if (isDragable) {
            if (!(dataProvider instanceof Dragable)) {
                throw new IllegalStateException("Data provider must implement Dragable interface");
            }
        }
    }

    protected Context getContext() {
        return ctx;
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

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final T data = dataProvider.get(position);

        ExpandableViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(layoutViewResource, parent, false);

            if (isExpandable) {
                viewHolder = new ExpandableViewHolder(convertView, expandingHelper.getExpandingLayoutResId());
                convertView.setTag(expandingHelper.getExpandingLayoutResId(), viewHolder);
            }

        } else {

            if (isExpandable) {
                viewHolder = (ExpandableViewHolder) convertView.getTag(expandingHelper.getExpandingLayoutResId());
            }

        }

        if (isExpandable) {
            expandingHelper.setupCollapsedView(convertView, data);
            expandingHelper.getExpandedView(convertView, data);
        }

        convertView.setLayoutParams(new ListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                AbsListView.LayoutParams.WRAP_CONTENT));

        return convertView;
    }

}
