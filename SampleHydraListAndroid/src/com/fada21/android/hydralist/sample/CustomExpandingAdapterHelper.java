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

package com.fada21.android.hydralist.sample;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fada21.android.hydralist.expandable.ExpandingAdapterHelper;

/**
 * This is a custom array adapter used to populate the listview whose items will
 * expand to display extra content in addition to the default display.
 */
public class CustomExpandingAdapterHelper extends ExpandingAdapterHelper<SampleListItem> {

    public CustomExpandingAdapterHelper(Context context, int expandingLayoutRes) {
        super(context, expandingLayoutRes);
    }

    @Override
    protected void setupExpandedView(View convertView, final SampleListItem data) {
        ImageView expImgView = (ImageView) convertView.findViewById(R.id.exp_image_view);
        expImgView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(context, data.getSc().getName() + " " + data.getNumber(), Toast.LENGTH_SHORT).show();
            }
        });
        TextView textView = (TextView) convertView.findViewById(R.id.text_view);

        expImgView.setImageResource(data.getSc().getIconResId());
        textView.setText(context.getString(data.getSc().getTextResId()));
    }

}