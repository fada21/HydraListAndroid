/*
 * Copyright (C) 2013 The Android Open Source Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fada21.android.hydralist.expandable;

import com.fada21.android.hydralist.expandable.interfaces.ExpandableListItem;
import com.fada21.android.hydralist.expandable.interfaces.OnSizeChangedListener;

/**
 * <p>
 * This custom object is used to populate the list adapter. It contains a reference to an image, title, and the extra text to be displayed. Furthermore, it
 * keeps track of the current state (collapsed/expanded) of the corresponding item in the list, as well as store the height of the cell in its collapsed state.
 * </p>
 * <p>
 * It should also state if item is expandable with method {@link #isExpandable()}.
 * </p>
 */
public abstract class BasicExpandableListItem implements ExpandableListItem {

	private int mCollapsedHeight;
	private boolean mIsExpandable;
	private boolean mIsExpanded;
	private int mExpandedHeight;

	private final OnSizeChangedListener mOnSizeCHangeListener;

	public BasicExpandableListItem(int defaultCollapsedHeight, boolean isExpandable) {
		mCollapsedHeight = defaultCollapsedHeight;
		mIsExpandable = isExpandable;
		mIsExpanded = false;
		mExpandedHeight = -1;
		mOnSizeCHangeListener = initOnSizeChangeListener();
	}

	private OnSizeChangedListener initOnSizeChangeListener() {
		return new OnSizeChangedListener() {

			@Override
			public void onSizeChanged(int newHeight) {
				setExpandedHeight(newHeight);
			}
		};
	}

	@Override
	public int getCollapsedHeight() {
		return mCollapsedHeight;
	}

	@Override
	public void setCollapsedHeight(int collapsedHeight) {
		mCollapsedHeight = collapsedHeight;
	}

	@Override
	public boolean isExpandable() {
		return mIsExpandable;
	}

	@Override
	public void setExpandable(boolean isExpandable) {
		mIsExpandable = isExpandable;
	}

	@Override
	public boolean isExpanded() {
		return mIsExpanded;
	}

	@Override
	public void setExpanded(boolean isExpanded) {
		mIsExpanded = isExpanded;
	}

	@Override
	public int getExpandedHeight() {
		return mExpandedHeight;
	}

	@Override
	public void setExpandedHeight(int expandedHeight) {
		mExpandedHeight = expandedHeight;
	}

	public OnSizeChangedListener getOnSizeChangedListener() {
		return mOnSizeCHangeListener;
	}

}
