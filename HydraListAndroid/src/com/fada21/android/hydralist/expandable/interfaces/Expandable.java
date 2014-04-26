package com.fada21.android.hydralist.expandable.interfaces;

import com.fada21.android.hydralist.data.HydraListItem;


public interface Expandable extends HydraListItem {

	boolean isExpandable();
	void setExpandable(boolean isExpandable);

	int getCollapsedHeight();
	void setCollapsedHeight(int collapsedHeight);

	boolean isExpanded();
	void setExpanded(boolean isExpanded);

	int getExpandedHeight();
	void setExpandedHeight(int expandedHeight);
	
	OnSizeChangedListener getOnSizeChangedListener();
}
