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

package com.fada21.edslv.sample;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fada21.edslv.ExpandingListAdapter;
import com.fada21.edslv.R;

/**
 * This is a custom array adapter used to populate the listview whose items will
 * expand to display extra content in addition to the default display.
 */
public class CustomArrayAdapter extends ExpandingListAdapter<SampleListItem> {

    public CustomArrayAdapter(Context context, int layoutViewResourceId, List<SampleListItem> data) {
        super(context, layoutViewResourceId, R.id.expanding_layout, data);
    }

    /**
     * Crops a circle out of the thumbnail photo.
     */
    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);

        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);

        int halfWidth = bitmap.getWidth() / 2;
        int halfHeight = bitmap.getHeight() / 2;

        canvas.drawCircle(halfWidth, halfHeight, Math.max(halfWidth, halfHeight), paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    @Override
    protected void setupCollapsedView(View convertView, final SampleListItem data) {
        LinearLayout linearLayout = (LinearLayout) (convertView.findViewById(R.id.item_linear_layout));
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, data.getCollapsedHeight());
        linearLayout.setLayoutParams(linearLayoutParams);

        TextView titleView = (TextView) convertView.findViewById(R.id.title_view);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.image_view);
        imgView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), data.getSc().getName() + " " + data.getNumber(), Toast.LENGTH_SHORT)
                        .show();
            }
        });

        titleView.setText(data.getSc().getName() + " " + data.getNumber());
        imgView.setImageBitmap(getCroppedBitmap(BitmapFactory.decodeResource(getContext().getResources(), data.getSc()
                .getIconResId(), null)));

    }

    @Override
    protected void setupExpandedView(View convertView, final SampleListItem data) {
        ImageView expImgView = (ImageView) convertView.findViewById(R.id.exp_image_view);
        expImgView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), data.getSc().getName() + " " + data.getNumber(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
        TextView textView = (TextView) convertView.findViewById(R.id.text_view);

        expImgView.setImageBitmap(getCroppedBitmap(BitmapFactory.decodeResource(getContext().getResources(), data
                .getSc().getIconResId(), null)));
        textView.setText(getContext().getString(data.getSc().getTextResId()));
    }

}