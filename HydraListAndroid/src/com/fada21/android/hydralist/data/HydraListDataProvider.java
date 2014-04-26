package com.fada21.android.hydralist.data;

/**
 * Interface for data set item providers. It't very important to ensure, that ids are unique. It's needed by stable ids adapter.
 *
 * @param <T> list item type
 */
public interface HydraListDataProvider<T extends HydraListItem> {

    boolean empty();

    int size();
    
    Class<T> getHydraListItemType();

    T get(int index);

    T getById(long id);

}
