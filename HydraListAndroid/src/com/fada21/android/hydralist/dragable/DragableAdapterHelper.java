package com.fada21.android.hydralist.dragable;

import static com.fada21.android.hydralist.util.HydraListConsts.UNSET;
import android.content.Context;

import com.fada21.android.hydralist.data.HydraListDataProvider;
import com.fada21.android.hydralist.data.HydraListItem;
import com.fada21.android.hydralist.helper.HydraListAdapterHelper;

public class DragableAdapterHelper<T extends HydraListItem> extends HydraListAdapterHelper<T> {

	private final int dragableTouchResId;
	private final int allowedRangeStartPosition;
	private final int allowedRangeEndPosition;

	public DragableAdapterHelper(Context context) {
		super(context);
		dragableTouchResId = 0;
		this.allowedRangeStartPosition = UNSET;
		this.allowedRangeEndPosition = UNSET;
	}

	public DragableAdapterHelper(Context context, int dragableTouchResId) {
		super(context);
		this.dragableTouchResId = dragableTouchResId;
		this.allowedRangeStartPosition = UNSET;
		this.allowedRangeEndPosition = UNSET;
	}

	//    public DragableAdapterHelper(Context context, int allowedRangeStartPosition) {
	//        super(context);
	//        this.allowedRangeStartPosition = allowedRangeStartPosition;
	//        this.allowedRangeEndPosition = UNSET;
	//    }
	//
	//    public DragableAdapterHelper(Context context, int allowedRangeStartPosition, int allowedRangeEndPosition) {
	//        super(context);
	//        this.allowedRangeStartPosition = allowedRangeStartPosition;
	//        this.allowedRangeEndPosition = allowedRangeEndPosition;
	//    }
	//
	//    public int getAllowedRangeStartPosition() {
	//        return allowedRangeStartPosition;
	//    }
	//
	//    public int getAllowedRangeEndPosition() {
	//        return allowedRangeEndPosition;
	//    }

	@Override
	public void ensureCompliance(HydraListDataProvider<T> dataProvider) throws IllegalStateException {
		if (allowedRangeStartPosition < UNSET) {
			throw new IllegalStateException("Defined allowedRangeStartPosition must be nonnegative");
		} else if (allowedRangeEndPosition < allowedRangeStartPosition) {
			throw new IllegalStateException("Defined allowedRangeStartPosition must be greater than allowedRangeEndPosition");
		}

		if (!(dataProvider instanceof Swappable)) {
			throw new IllegalStateException("Data provider must implement Dragable interface!");
		}
	}

	public int getDragableTouchResId() {
		return dragableTouchResId;
	}
}
