package com.eonsahead.schauspieler;


public class DataGenerator {

    public static String word(int index, int wordLength) {
        char letter = (char) ('A' + index % 26);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < wordLength; i++) {
            result.append(letter);
        } // for
        return result.toString();
    } // word( int, int )

    public static String paragraph(int index, int wordLength, int wordCount) {
        String word = word(index, wordLength);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < wordCount - 1; i++) {
            result.append(word + " ");
        } // for
        result.append(word);
        return result.toString();
    } // paragraph( int, int, int )

} // DataGenerator

