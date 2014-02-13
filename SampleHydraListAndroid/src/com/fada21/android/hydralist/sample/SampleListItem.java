package com.fada21.android.hydralist.sample;

import com.fada21.android.hydralist.expandable.ExpandableListItem;

public class SampleListItem extends ExpandableListItem {

    private SampleContents sc;
    private int            number;

    public SampleListItem(SampleContents sc, int number) {
        super(sc.getDefHeight(), sc != SampleContents.ROCK);
        this.sc = sc;
        this.number = number;
    }

    public SampleContents getSc() {
        return sc;
    }

    public void setSc(SampleContents sc) {
        this.sc = sc;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public long getId() {
        return number;
    }

}
