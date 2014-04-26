package com.fada21.android.hydralist.expandable;

import android.content.Context;
import android.view.View;

import com.fada21.android.hydralist.data.HydraListDataProvider;
import com.fada21.android.hydralist.expandable.interfaces.Expandable;
import com.fada21.android.hydralist.helper.HydraListAdapterHelper;
import com.fada21.android.hydralist.util.HydraListConsts;

public abstract class ExpandingAdapterHelper<T extends Expandable> extends HydraListAdapterHelper<T> {

	private final int expandingLayout;

	public ExpandingAdapterHelper(Context context, int expandingLayout) {
		super(context);
		this.expandingLayout = expandingLayout;
	}

	/**
	 * Provides id in layout in which expanding layout should be filled
	 * 
	 * @return view element id for expanding part
	 */
	public int getExpandingLayout() {
		return expandingLayout;
	}

	@Override
	public void ensureCompliance(HydraListDataProvider<T> dataProvider) throws IllegalStateException {
		if (expandingLayout == HydraListConsts.UNSET) {
			throw new IllegalStateException("Must provide resource id for expanding view (expandingLayoutResId)");
		}
		
		if (! Expandable.class.isAssignableFrom(dataProvider.getHydraListItemType())) {
			throw new IllegalStateException("Data provider items must implement Expandable interface!");
		}
	}

	public void storeExpandableViewHolderAsTag(View convertView) {
		ExpandableViewHolder viewHolder = new ExpandableViewHolder(convertView, getExpandingLayout());
		convertView.setTag(getExpandingLayout(), viewHolder);
	}

	public ExpandingLayout getExpandedView(View convertView, final T data) {
		ExpandableViewHolder viewHolder = (ExpandableViewHolder) convertView.getTag(expandingLayout);
		ExpandingLayout expandingLayout = viewHolder.expandingLayoutViewGroup;
		expandingLayout.setExpandedHeight(data.getExpandedHeight());
		expandingLayout.setSizeChangedListener(data.getOnSizeChangedListener());

		if (data.isExpandable())
			setupExpandedView(convertView, data);

		if (!data.isExpanded()) {
			expandingLayout.setVisibility(View.GONE);
		} else {
			expandingLayout.setVisibility(View.VISIBLE);
		}

		return expandingLayout;
	}

	/**
	 * Implement look of expanded view of a list item
	 * 
	 * @param convertView
	 *            view to alter
	 * @param data
	 *            to be filled
	 */
	protected abstract void setupExpandedView(View convertView, final T data);

}
