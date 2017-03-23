package com.eonsahead.schauspieler;

import android.graphics.Path;
import android.net.Uri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

public class APIKey {
    private static APIKey sAPIKey;
    private String mKey = "code";

    private APIKey() {
      mKey = "abracadabra";
    } // APIKey()

    public static APIKey getInstance() {
        if (sAPIKey == null) {
            sAPIKey = new APIKey();
        } // if
        return sAPIKey;
    } // APIKey()

    public String getKey() {
        return mKey;
    } // getKey()
} // APIKey
