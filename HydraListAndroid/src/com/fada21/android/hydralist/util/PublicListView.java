package com.fada21.android.hydralist.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.ListView;

/**
 * Exposes all essential non public methods of {@link ListView}
 */
public abstract class PublicListView extends ListView {

    public PublicListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public PublicListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PublicListView(Context context) {
        super(context);
    }

    // =============== non public ListView methods made public

    @Override
    public void layoutChildren() {
        super.layoutChildren();
    }

    @Override
    public boolean canAnimate() {
        return super.canAnimate();
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override
    public boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return super.drawChild(canvas, child, drawingTime);
    }

    @Override
    public void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    // =============== nonpublic AbsListView methods made public

    @Override
    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override
    public int computeVerticalScrollOffset() {
        return super.computeVerticalScrollOffset();
    }

    @Override
    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    @Override
    public float getTopFadingEdgeStrength() {
        return super.getTopFadingEdgeStrength();
    }

    @Override
    public float getBottomFadingEdgeStrength() {
        return super.getBottomFadingEdgeStrength();
    }

    @Override
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean isPaddingOffsetRequired() {
        return super.isPaddingOffsetRequired();
    }

    @Override
    public int getLeftPaddingOffset() {
        return super.getLeftPaddingOffset();
    }

    @Override
    public int getTopPaddingOffset() {
        return super.getTopPaddingOffset();
    }

    @Override
    public int getRightPaddingOffset() {
        return super.getRightPaddingOffset();
    }

    @Override
    public int getBottomPaddingOffset() {
        return super.getBottomPaddingOffset();
    }

    @Override
    public void drawableStateChanged() {
        super.drawableStateChanged();
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        return super.onCreateDrawableState(extraSpace);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public ContextMenuInfo getContextMenuInfo() {
        return super.getContextMenuInfo();
    }

    @Override
    public void dispatchSetPressed(boolean pressed) {
        super.dispatchSetPressed(pressed);
    }

    @Override
    public void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    public void handleDataChanged() {
        super.handleDataChanged();
    }

    @Override
    public void onDisplayHint(int hint) {
        super.onDisplayHint(hint);
    }

    @Override
    public boolean isInFilterMode() {
        return super.isInFilterMode();
    }

    @Override
    public android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override
    public android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return super.generateLayoutParams(p);
    }

    @Override
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return super.checkLayoutParams(p);
    }

}
