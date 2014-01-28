package com.fada21.edslv.sample;

import static com.fada21.edslv.sample.SampleConsts.CELL_DEFAULT_HEIGHT;

import com.fada21.edslv.R;

public enum SampleContents {

    CHAMELEON("Chameleon", R.drawable.chameleon, CELL_DEFAULT_HEIGHT, R.string.short_lorem_ipsum),
    ROCK("Rock", R.drawable.rock, CELL_DEFAULT_HEIGHT, R.string.medium_lorem_ipsum),
    FLOWER("Flower", R.drawable.flower, CELL_DEFAULT_HEIGHT, R.string.long_lorem_ipsum);

    private final String name;
    private final int    iconResId;
    private final int    defHeight;
    private final int    textResId;

    private SampleContents(String name, int iconResId, int defHeight, int textResId) {
        this.name = name;
        this.iconResId = iconResId;
        this.defHeight = defHeight;
        this.textResId = textResId;
    }

    public String getName() {
        return name;
    }

    public int getIconResId() {
        return iconResId;
    }

    public int getDefHeight() {
        return defHeight;
    }

    public int getTextResId() {
        return textResId;
    }

}
