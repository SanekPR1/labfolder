package com.makridin.labfolder.service.utils;

public class FindFrequencyUtil
{
    public static boolean isWordLengthEligible(String word, String keyword)
    {
        return word.length() >= keyword.length() - 1 && word.length() <= keyword.length() + 1;
    }

    public static String[] splitText(String text)
    {
        return text.replaceAll("[^a-zA-Z0-9- ]", "").split("\\s+");
    }
}
