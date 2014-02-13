package com.fada21.android.hydralist.data;


public interface HydraListDataProvider<T extends HydraListItem> {

    boolean empty();

    int size();

    T get(int index);

    T getById(long id);

}
