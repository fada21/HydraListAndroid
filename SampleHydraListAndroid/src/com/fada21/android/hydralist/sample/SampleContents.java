package com.fada21.android.hydralist.sample;


public enum SampleContents {

    HYDRALISK("Hydralisk", R.drawable.hydralisk, R.dimen.item_default_height, R.string.hydralisk_details),
    MARINE("Marine", R.drawable.marine, R.dimen.item_default_height, R.string.marine_details),
    ZEALOT("Zealot", R.drawable.zealot, R.dimen.item_default_height, R.string.zealot_details);

    private final String name;
    private final int    iconResId;
    private final int    defHeightResId;
    private final int    textResId;

    private SampleContents(String name, int iconResId, int defHeightResId, int textResId) {
        this.name = name;
        this.iconResId = iconResId;
        this.defHeightResId = defHeightResId;
        this.textResId = textResId;
    }

    public String getName() {
        return name;
    }

    public int getIconResId() {
        return iconResId;
    }

    public int getDefHeightResId() {
        return defHeightResId;
    }

    public int getTextResId() {
        return textResId;
    }
    
    /**
     * Tells which should expand. Protoss protect their secrets and won't expand to easily.
     * @return <code>true</code> for expandable
     */
    public boolean isSampleExpandable() {
    	return this != ZEALOT;
    }
    
    /**
     * Tells which should be dragable. Terrans ones sieged is not to be dragged easily.
     * @return
     */
    public boolean isDragable() {
    	return this != MARINE;
    }

}
