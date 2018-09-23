package com.makridin.labfolder.service.impl;

public class FindFrequency
{
    protected boolean isWordLengthEligible(String word, String keyword)
    {
        return word.length() >= keyword.length() - 1 && word.length() <= keyword.length() + 1;
    }

    protected String[] splitText(String text)
    {
        return text.replaceAll("[^a-zA-Z0-9- ]", "").split("\\s+");
    }
}
