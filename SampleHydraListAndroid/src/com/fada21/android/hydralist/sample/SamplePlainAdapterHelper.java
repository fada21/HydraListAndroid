package com.fada21.android.hydralist.sample;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SamplePlainAdapterHelper extends com.fada21.android.hydralist.helper.PlainAdapterHelper<SampleListItem> {

    public SamplePlainAdapterHelper(Context ctx, int itemLayout) {
        super(ctx, itemLayout);
    }

    @Override
    public void setupCollapsedView(View convertView, final SampleListItem data) {
        LinearLayout linearLayout = (LinearLayout) (convertView.findViewById(R.id.collapsed_layout));
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, data.getCollapsedHeight());
        linearLayout.setLayoutParams(linearLayoutParams);

        TextView titleView = (TextView) convertView.findViewById(R.id.title_view);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.image_view);
        imgView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(context, data.getSc().getName() + " " + data.getNumber(), Toast.LENGTH_SHORT).show();
            }
        });

        titleView.setText(data.getSc().getName() + " " + data.getNumber());
        imgView.setImageResource(data.getSc().getIconResId());
    }

}
