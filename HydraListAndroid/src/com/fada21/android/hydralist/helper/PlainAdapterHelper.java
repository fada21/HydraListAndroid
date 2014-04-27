package com.fada21.android.hydralist.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fada21.android.hydralist.data.HydraListDataProvider;
import com.fada21.android.hydralist.data.HydraListItem;
import com.fada21.android.hydralist.util.HydraListConsts;
import com.fada21.android.hydralist.util.HydraListUtils;

/**
 * Plain adapter that servers common adapter tasks besides of expansion adapter helpers
 */
public abstract class PlainAdapterHelper<T extends HydraListItem> extends HydraListAdapterHelper<T> {

	public static final String TAG = "PlainAdapterHelper";

	private final int itemLayout;
	protected final LayoutInflater inflater;
	private final Class<? extends HydraListViewHolder> viewHolderClass;

	/**
	 * Constructor with viewHolder support disabled;
	 * 
	 * @param ctx
	 * @param itemLayout
	 *            list item layour; pass {@link HydraListConsts#UNSET} to force always running setupPlainView with new convertView
	 */
	public PlainAdapterHelper(Context ctx, int itemLayout) {
		super(ctx);
		inflater = LayoutInflater.from(context);
		this.itemLayout = itemLayout;
		this.viewHolderClass = null;
	}

	/**
	 * Constructor with viewHolder support enabled;
	 * 
	 * @param ctx
	 * @param itemLayout
	 *            list item layour; pass {@link HydraListConsts#UNSET} to force running setupPlainView with always recreated convertView
	 */
	public PlainAdapterHelper(Context ctx, int itemLayout, Class<? extends HydraListViewHolder> viewHolderClass) {
		super(ctx);
		inflater = LayoutInflater.from(context);
		this.itemLayout = itemLayout;
		this.viewHolderClass = viewHolderClass;
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

	public View newView(ViewGroup parent) {
		View newView = inflater.inflate(itemLayout, parent, false);
		if (hasViewHolderClass()) {
			newView.setTag(itemLayout, createViewHolder(newView));
		}
		return newView;
	}

	public boolean hasViewHolderClass() {
		return viewHolderClass != null;
	}

	private HydraListViewHolder createViewHolder(View viewWithoutTag) {
		HydraListViewHolder viewHolder = null;
		try {
			viewHolder = (HydraListViewHolder) viewHolderClass.newInstance();
			viewHolder.initViewHolder(viewWithoutTag);
		} catch (InstantiationException e) {
			HydraListUtils.logd(TAG, "Error during viewHolder instantiation! There won't be any viewHolder", e);
		} catch (IllegalAccessException e) {
			HydraListUtils.logd(TAG, "Error during viewHolder instantiation! There won't be any viewHolder either. No f*cking way!", e);
		}
		return viewHolder;
	}

/**
	 * <p>
	 * Setups plain view of a list item.
	 * </p><p>
	 * This method or {@link #setupPlainView(View, HydraListItem) should be implemented. 
	 * It depends on whether user supplied ViewHolder class.
	 * If there is several view types it may be not easy to use view holder.
	 * </p>
	 * 
	 * @param viewHolder
	 *            viewHolder to bind
	 * @param data
	 *            to be filled
	 */
	public abstract void bindView(HydraListViewHolder viewHolder, T data);

/**
	 * <p>
	 * Setups plain view of a list item.
	 * </p><p>
	 * This method or {@link #bindView(HydraListViewHolder, HydraListItem) should be implemented. 
	 * It depends on whether user supplied ViewHolder class.
	 * </p>
	 * 
	 * @param convertView
	 *            view to alter, may be null ifn unset layout was provided in constructor
	 * @param data
	 *            to be filled
	 */
	public abstract void setupPlainView(ViewGroup parent, View convertView, T data);

}
