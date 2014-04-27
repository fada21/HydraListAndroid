package com.fada21.android.hydralist.sample;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fada21.android.hydralist.helper.HydraListViewHolder;

public class SampleViewHolder implements HydraListViewHolder {

	public LinearLayout collapsedView;
	public ImageView mainIcon;
	public TextView title;

	@Override
	public void initViewHolder(View view) {
		collapsedView = (LinearLayout) view.findViewById(R.id.collapsed_layout);
		mainIcon = (ImageView) view.findViewById(R.id.main_icon);
		title = (TextView) view.findViewById(R.id.title);

	}

}
