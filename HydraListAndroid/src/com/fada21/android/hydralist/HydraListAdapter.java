package com.fada21.android.hydralist;

import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.fada21.android.hydralist.data.HydraListDataProvider;
import com.fada21.android.hydralist.data.HydraListItem;
import com.fada21.android.hydralist.dragable.DragableAdapterHelper;
import com.fada21.android.hydralist.dragable.interfaces.Dragable;
import com.fada21.android.hydralist.expandable.ExpandingAdapterHelper;
import com.fada21.android.hydralist.expandable.interfaces.Expandable;
import com.fada21.android.hydralist.helper.HydraListAdapterHelper;
import com.fada21.android.hydralist.helper.PlainAdapterHelper;
import com.fada21.android.hydralist.util.HydraListConsts;

/**
 * 
 * <p>
 * Adapter for {@link HydraListView}. It works as container for various behaviors implemented in helpers which extends {@link HydraListAdapterHelper}.
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

	protected final HydraListDataProvider<T> dataProvider;

	protected final int itemLayout;

	private final PlainAdapterHelper<T> plainAdapterHelper;
	protected final ExpandingAdapterHelper<Expandable> expandingHelper;
	protected final DragableAdapterHelper<Dragable> dragableHelper;

	@SuppressWarnings("unchecked")
	private HydraListAdapter(Builder<T> b) {
		dataProvider = b.dataProvider;

		itemLayout = b.baseAdapterHelper.getItemLayout();

		plainAdapterHelper = b.baseAdapterHelper;
		expandingHelper = b.expandingAdapterHelper;
		dragableHelper = b.dragableAdapterHelper;

		ensureCompliance((HydraListAdapterHelper<T>)plainAdapterHelper, (HydraListAdapterHelper<T>)expandingHelper, (HydraListAdapterHelper<T>)dragableHelper);
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
	public static <BT extends HydraListItem> Builder<BT> builder(PlainAdapterHelper<BT> baseAdapterHelper, Class<BT> clazz) {
		return new Builder<BT>(baseAdapterHelper);
	}

	public static class Builder<BT extends HydraListItem> {

		private HydraListDataProvider<BT> dataProvider;

		private PlainAdapterHelper<BT> baseAdapterHelper = null;
		private ExpandingAdapterHelper<Expandable> expandingAdapterHelper = null;
		private DragableAdapterHelper<Dragable> dragableAdapterHelper = null;

		public Builder(PlainAdapterHelper<BT> helper) {
			this.baseAdapterHelper = helper;
		}

		public Builder<BT> data(HydraListDataProvider<BT> dataProvider) {
			this.dataProvider = dataProvider;
			return this;
		}

		public Builder<BT> data(Cursor cursor) {
			throw new IllegalArgumentException("Cursors not yet supported");
		}

		@SuppressWarnings("unchecked")
		public Builder<BT> expandable(ExpandingAdapterHelper<? extends Expandable> helper) {
			expandingAdapterHelper = (ExpandingAdapterHelper<Expandable>) helper;
			return this;
		}

		@SuppressWarnings("unchecked")
		public Builder<BT> dragable(DragableAdapterHelper<? extends Dragable> helper) {
			dragableAdapterHelper = (DragableAdapterHelper<Dragable>) helper;
			return this;
		}

		public HydraListAdapter<BT> build() {
			return new HydraListAdapter<BT>(this);
		}

	}

	// ====== Behavior switchers =================================

	// ====== basic plain adapter helper, must be present ========
	public PlainAdapterHelper<T> getPlainAdapterHelper() {
		return plainAdapterHelper;
	}

	// ====== expanding functionality ============================
	public boolean isExpandable() {
		return expandingHelper != null;
	}

	public ExpandingAdapterHelper<Expandable> getExpandingHelper() {
		return expandingHelper;
	}

	// ====== dragable functionality =============================
	public boolean isDragable() {
		return dragableHelper != null;
	}

	public DragableAdapterHelper<Dragable> getDragableHelper() {
		return dragableHelper;
	}

	/**
	 * Checks for general compliance, and if helpers are properly initialized. Therefore should be invoked after object was built.
	 * 
	 * @param helpers
	 */
	private void ensureCompliance(HydraListAdapterHelper<T>... helpers) throws IllegalStateException {
		if (plainAdapterHelper == null) {
			throw new IllegalStateException("BaseAdapterHelper instance must be provided!");
		}

		if (helpers.length > 1) {
			for (HydraListAdapterHelper<T> hydraAdapterHelper : helpers) {
				if (hydraAdapterHelper != null) {
					hydraAdapterHelper.ensureCompliance(dataProvider);
				}
			}
		}

	}

	public HydraListDataProvider<T> getDataProvider() {
		return dataProvider;
	}

	/**
	 * Important to force that in data set {@link HydraListDataProvider}
	 */
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
			return HydraListConsts.INVALID_ID;
		} else {
			return dataProvider.get(position).getId();
		}
	}

	/*
	 * Standard getView method
	 *  very important or new plugins(non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final T data = dataProvider.get(position);

		if (isExpandable()) {
			if (convertView == null) {
				convertView = plainAdapterHelper.newView(parent);
				expandingHelper.storeExpandableViewHolderAsTag(convertView);
			}
			plainAdapterHelper.setupPlainView(convertView, data);
			expandingHelper.getExpandedView(convertView, (Expandable)data);
		} else {
			if (convertView == null) {
				convertView = plainAdapterHelper.newView(parent);
			}
			plainAdapterHelper.setupPlainView(convertView, data);
		}

		int layout_width = AbsListView.LayoutParams.MATCH_PARENT;
		int layout_height = AbsListView.LayoutParams.WRAP_CONTENT;
		convertView.setLayoutParams(new ListView.LayoutParams(layout_width, layout_height));

		return convertView;
	}

}
