package com.eonsahead.schauspieler;



public enum ImageSizes {
    // poster sizes
    w92 ("w92"),
    w154 ("w154"),
    w185 ("w185"),
    w342 ("w342"),
    w500 ("w500"),
    w780 ("w780"),
    original ("original");

    private final String sizeSpecifier;

    private ImageSizes( String sizeSpecifier ) {
        this.sizeSpecifier = sizeSpecifier;
    } // ImageSize( String )

    public String getSizeSpecifier() {
        return sizeSpecifier;
    } // getSizeSpecifier()
} // ImageSizes
