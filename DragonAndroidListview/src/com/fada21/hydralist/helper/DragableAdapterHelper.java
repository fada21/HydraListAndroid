package com.fada21.hydralist.helper;

import static com.fada21.hydralist.util.HydraListConsts.UNSET;
import android.content.Context;

import com.fada21.hydralist.data.HydraListItem;

public class DragableAdapterHelper<T extends HydraListItem> extends HydraAdapterHelper<T> {

    private final int allowedRangeStartPosition;
    private final int allowedRangeEndPosition;

    public DragableAdapterHelper(Context context) {
        super(context);
        this.allowedRangeStartPosition = UNSET;
        this.allowedRangeEndPosition = UNSET;
    }

    public DragableAdapterHelper(Context context, int allowedRangeStartPosition) {
        super(context);
        this.allowedRangeStartPosition = allowedRangeStartPosition;
        this.allowedRangeEndPosition = UNSET;
    }

    public DragableAdapterHelper(Context context, int allowedRangeStartPosition, int allowedRangeEndPosition) {
        super(context);
        this.allowedRangeStartPosition = allowedRangeStartPosition;
        this.allowedRangeEndPosition = allowedRangeEndPosition;
    }

    public int getAllowedRangeStartPosition() {
        return allowedRangeStartPosition;
    }

    public int getAllowedRangeEndPosition() {
        return allowedRangeEndPosition;
    }

    @Override
    public void ensureCompliance() throws IllegalArgumentException {
        // nothing to implement
    }

}
