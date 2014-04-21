package com.fada21.android.hydralist.sample;

import java.util.List;

import com.fada21.android.hydralist.data.ListDataProvider;
import com.fada21.android.hydralist.dragable.IDragableAdapter;

public class SampleDataProvider extends ListDataProvider<SampleListItem> implements IDragableAdapter {

	public SampleDataProvider(List<SampleListItem> data) {
		super(data);
	}

	@Override
	public void swap(int indexOne, int indexTwo) {
		SampleListItem temp = data.get(indexOne);
		data.set(indexOne, data.get(indexTwo));
		data.set(indexTwo, temp);
	}

}
