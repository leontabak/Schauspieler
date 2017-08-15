package com.eonsahead.schauspieler;

import java.io.Serializable;

public class Review implements Serializable {
    private String mAuthor;
    private String mContent;

    public Review(String author, String content) {
        setAuthor(author);
        setContent(content);
    } // Review( String, String )

    public String getAuthor() {
        return mAuthor;
    } // getAuthor()

    public void setAuthor(String author) {
        mAuthor = author;
    } // setAuthor( String )

    public String getContent() {
        return mContent;
    } // getContent()

    public void setContent(String content) {
        mContent = content;
    } // setContent( String )
} // Review
