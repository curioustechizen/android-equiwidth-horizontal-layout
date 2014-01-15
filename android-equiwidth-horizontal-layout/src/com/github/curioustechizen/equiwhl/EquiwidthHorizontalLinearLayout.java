package com.github.curioustechizen.equiwhl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class EquiwidthHorizontalLinearLayout extends LinearLayout {
	public EquiwidthHorizontalLinearLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
	}

	public EquiwidthHorizontalLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0);
	}

	public EquiwidthHorizontalLinearLayout(Context context) {
		super(context);
		init(context, null, 0);
	}

	private void init(Context context, AttributeSet attrs, int defStyle) {
		setOrientation(HORIZONTAL);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int childCount = getChildCount();

		/*
		 * First Pass: Measure each child with UNSPECIFIED to find out how big
		 * it wants to be. During this pass, also determine the width of the
		 * largest child.
		 */

		int specifiedWidth = MeasureSpec.getSize(widthMeasureSpec);
		int dividedWidth = specifiedWidth / childCount;

		int largestChildWidth = Integer.MIN_VALUE;
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			final LayoutParams lp = (LayoutParams) child.getLayoutParams();
			int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
					dividedWidth - lp.leftMargin - lp.rightMargin,
					MeasureSpec.UNSPECIFIED);
			child.measure(childWidthMeasureSpec, heightMeasureSpec);
			largestChildWidth = child.getMeasuredWidth() > largestChildWidth ? child
					.getMeasuredWidth() : largestChildWidth;
		}

		/*
		 * Second Pass: Impose the largest width as the EXACT width for each
		 * child.
		 */

		int normalizedWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
				largestChildWidth, MeasureSpec.EXACTLY);
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			child.measure(normalizedWidthMeasureSpec, heightMeasureSpec);
		}
	}
}
