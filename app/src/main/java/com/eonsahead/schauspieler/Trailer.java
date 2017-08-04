package com.eonsahead.schauspieler;


public class Trailer {
    private String mName;
    private String mKey;
    private String mType;

    public Trailer(String name, String key, String type) {
        setName(name);
        setKey(key);
        setType(type);
    } // Trailer( String, String, String )

    public String getName() {
        return mName;
    } // getName()

    public void setName(String name) {
        mName = name;
    } // setName( String )

    public String getKey() {
        return mKey;
    } // getKey()

    public void setKey(String key) {
        mKey = key;
    } // setKey( String )

    public String getType() {
        return mType;
    } // getType()

    public void setType(String type) {
        mType = type;
    } // setType( String )
} // Trailer
