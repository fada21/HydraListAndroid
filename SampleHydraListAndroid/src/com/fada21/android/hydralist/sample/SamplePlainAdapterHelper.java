package com.fada21.android.hydralist.sample;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fada21.android.hydralist.helper.HydraListViewHolder;
import com.fada21.android.hydralist.helper.PlainAdapterHelper;

public class SamplePlainAdapterHelper extends PlainAdapterHelper<SampleListItem> {

	public SamplePlainAdapterHelper(Context ctx) {
		super(ctx, R.layout.list_view_item, SampleViewHolder.class);
	}

	@Override
	public void setupPlainView(View convertView, final SampleListItem data) {
		LinearLayout linearLayout = (LinearLayout) (convertView.findViewById(R.id.collapsed_layout));
		LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, data.getCollapsedHeight());
		linearLayout.setLayoutParams(linearLayoutParams);

		TextView titleView = (TextView) convertView.findViewById(R.id.title);
		ImageView imgView = (ImageView) convertView.findViewById(R.id.main_icon);
		imgView.setImageResource(data.getSc().getIconResId());
		imgView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(context, data.getSc().getName() + " " + data.getNumber(), Toast.LENGTH_SHORT).show();
			}
		});

		titleView.setText(data.getSc().getName() + " " + data.getNumber());
	}

	@Override
	public void bindView(HydraListViewHolder viewHolder, SampleListItem data) {
		SampleViewHolder svh = (SampleViewHolder) viewHolder;
		svh.mainIcon.setImageResource(data.getSc().getIconResId());
		svh.mainIcon.setOnClickListener(setupOnClickListener(data));

		svh.title.setText(data.getSc().getName() + " " + data.getNumber());
	}

	private View.OnClickListener setupOnClickListener(final SampleListItem data) {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(context, data.getSc().getName() + " " + data.getNumber(), Toast.LENGTH_SHORT).show();
			}
		};
	}
}
