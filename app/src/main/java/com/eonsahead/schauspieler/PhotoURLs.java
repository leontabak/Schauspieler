package com.eonsahead.schauspieler;


import java.util.ArrayList;
import java.util.List;

public class PhotoURLs {
    private static PhotoURLs sPhotoURLs = null;
    private final List<String> mURLs;
    private int mCurrentIndex;

    private PhotoURLs() {
        mURLs = new ArrayList<>();
        mCurrentIndex = 0;

        mURLs.add("http://www.countingfromzero.com/images/arboretum00.jpg");
        mURLs.add("http://www.countingfromzero.com/images/arboretum01.jpg");
        mURLs.add("http://www.countingfromzero.com/images/arboretum02.jpg");
        mURLs.add("http://www.countingfromzero.com/images/arboretum03.jpg");
        mURLs.add("http://www.countingfromzero.com/images/arboretum04.jpg");
        mURLs.add("http://www.countingfromzero.com/images/arboretum05.jpg");
        mURLs.add("http://www.countingfromzero.com/images/arboretum06.jpg");
        mURLs.add("http://www.countingfromzero.com/images/arboretum07.jpg");
        mURLs.add("http://www.countingfromzero.com/images/arboretum08.jpg");
    } // PhotoURLs()

    public static PhotoURLs getInstance() {
        if (sPhotoURLs == null) {
            sPhotoURLs = new PhotoURLs();
        } // if
        return sPhotoURLs;
    } // getInstance()

    public List<String> getURLs() {
        return mURLs;
    } // getURLs()

    public String getURL(int index) {
        return mURLs.get(index);
    } // getURL( int )

    public String getNextURL() {
        String result = mURLs.get( mCurrentIndex );
        mCurrentIndex = (mCurrentIndex + 1) % mURLs.size();
        return result;
    } // getNextURL()

    public int size() {
        return mURLs.size();
    } // size()
} // PhotoURLs
