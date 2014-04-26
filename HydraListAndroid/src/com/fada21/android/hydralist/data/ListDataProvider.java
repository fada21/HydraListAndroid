package com.fada21.android.hydralist.data;

import java.util.List;

public class ListDataProvider<T extends HydraListItem> implements HydraListDataProvider<T> {

    protected final List<T> data;
	private final Class<T> clazz;

    public ListDataProvider(List<T> data, Class<T> clazz) {
        this.data = data;
        this.clazz = clazz;
    }
    
    @Override
    public Class<T> getHydraListItemType() {
    	return clazz;
    }

    @Override
    public boolean empty() {
        return data.isEmpty();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public T get(int index) {
        return data.get(index);
    }

    @Override
    public T getById(long id) {
        for (T item : data) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }

}
