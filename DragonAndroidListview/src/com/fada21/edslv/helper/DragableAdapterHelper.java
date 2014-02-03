package com.fada21.edslv.helper;

import static com.fada21.edslv.util.HydraListConsts.UNSET;

import com.fada21.edslv.data.HydraListItem;

public class DragableAdapterHelper<T extends HydraListItem> extends HydraAdapterHelper<T> {

    private final int allowedRangeStartPosition;
    private final int allowedRangeEndPosition;

    public DragableAdapterHelper() {
        super();
        this.allowedRangeStartPosition = UNSET;
        this.allowedRangeEndPosition = UNSET;
    }

    public DragableAdapterHelper(int allowedRangeStartPosition) {
        super();
        this.allowedRangeStartPosition = allowedRangeStartPosition;
        this.allowedRangeEndPosition = UNSET;
    }

    public DragableAdapterHelper(int allowedRangeStartPosition, int allowedRangeEndPosition) {
        super();
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
