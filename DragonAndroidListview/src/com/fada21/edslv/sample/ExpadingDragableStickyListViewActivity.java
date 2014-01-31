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

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.fada21.edslv.HydraListView;
import com.fada21.edslv.R;

/**
 * This activity creates a listview whose items can be clicked to expand and show
 * additional content.
 * 
 * In this specific demo, each item in a listview displays an image and a corresponding
 * title. These two items are centered in the default (collapsed) state of the listview's
 * item. When the item is clicked, it expands to display text of some varying length.
 * The item persists in this expanded state (even if the user scrolls away and then scrolls
 * back to the same location) until it is clicked again, at which point the cell collapses
 * back to its default state.
 */
public class ExpadingDragableStickyListViewActivity extends Activity {

    private final int     NUM_OF_CELLS = 45;

    private HydraListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SampleContents[] sampleContents = SampleContents.values();

        List<SampleListItem> mData = new ArrayList<SampleListItem>();

        for (int i = 0; i < NUM_OF_CELLS; i++) {
            SampleContents sc = sampleContents[i % sampleContents.length];
            mData.add(new SampleListItem(sc, i + 1));
        }

        CustomArrayAdapter adapter = new CustomArrayAdapter(this, R.layout.list_view_item, mData);

        mListView = (HydraListView) findViewById(R.id.main_list_view);
        mListView.setExpandable(true);
        mListView.setDragable(true);
        mListView.setAdapter(adapter);
        mListView.setDivider(null);
    }
}