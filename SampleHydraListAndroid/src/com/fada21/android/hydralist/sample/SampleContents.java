package com.fada21.android.hydralist.sample;

import static com.fada21.android.hydralist.sample.SampleConsts.CELL_DEFAULT_HEIGHT;

public enum SampleContents {

    HYDRALISK("Hydralisk", R.drawable.hydralisk, CELL_DEFAULT_HEIGHT, R.string.short_lorem_ipsum),
    MARINE("Marine", R.drawable.marine, CELL_DEFAULT_HEIGHT + 100, R.string.medium_lorem_ipsum),
    ZEALOT("Zealot", R.drawable.zealot, CELL_DEFAULT_HEIGHT, R.string.long_lorem_ipsum);

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
    
    /**
     * Tells which should expand. Protoss protect their secrets and won't expand to easly.
     * @return <code>true</code> for expandable
     */
    public boolean isSampleExpandable() {
    	return this != ZEALOT;
    }
    
    /**
     * Tells which should be dragable. Terrans ones sieged is not to be dragged easly.
     * @return
     */
    public boolean isDragable() {
    	return this != MARINE;
    }

}
