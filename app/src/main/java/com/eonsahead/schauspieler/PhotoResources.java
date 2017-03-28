package com.eonsahead.schauspieler;


import java.util.ArrayList;
import java.util.List;

public class PhotoResources {
    private static PhotoResources sPhotoResources = null;
    private final List<Integer> mResources;
    private int mCurrentIndex;

    private PhotoResources() {
        mResources = new ArrayList<>();
        mCurrentIndex = 0;

        mResources.add(R.drawable.arboretum00);
        mResources.add(R.drawable.arboretum01);
        mResources.add(R.drawable.arboretum02);
        mResources.add(R.drawable.arboretum03);
        mResources.add(R.drawable.arboretum04);
        mResources.add(R.drawable.arboretum05);
        mResources.add(R.drawable.arboretum06);
        mResources.add(R.drawable.arboretum07);
        mResources.add(R.drawable.arboretum08);
    } // PhotoResources()

    public static PhotoResources getInstance() {
        if (sPhotoResources == null) {
            sPhotoResources = new PhotoResources();
        } // if

        return sPhotoResources;
    } // getInstance()

    public List<Integer> getResources() {
        return mResources;
    } // getResources()

    public Integer getResource(int index) {
        return mResources.get(index);
    } // getResource( int )

    public Integer getNextResource() {
        Integer result = mResources.get( mCurrentIndex );
        mCurrentIndex = (mCurrentIndex + 1) % mResources.size();
        return result;
    } // getNextResource()

    public int size() {
        return mResources.size();
    } // size()
} // PhotoResources
