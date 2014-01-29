package com.fada21.edslv.sample;

import com.fada21.edslv.ExpandableListItem;

public class SampleListItem extends ExpandableListItem {

    private SampleContents sc;

    public SampleListItem(SampleContents sc) {
        super(sc.getDefHeight(), sc != SampleContents.ROCK);
        this.sc = sc;
    }

    public SampleContents getSc() {
        return sc;
    }

    public void setSc(SampleContents sc) {
        this.sc = sc;
    }

}
