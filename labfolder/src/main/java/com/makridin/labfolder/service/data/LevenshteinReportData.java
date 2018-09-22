package com.makridin.labfolder.service.data;

import java.util.HashSet;
import java.util.Set;

public class LevenshteinReportData
{
    private final String keyword;
    private final int frequency;
    private final Set<String> similarWords;

    public LevenshteinReportData(String keyword, int frequency, Set<String> similarWords)
    {
        this.keyword = keyword;
        this.frequency = frequency;
        this.similarWords = similarWords;
    }

    public LevenshteinReportData()
    {
        this.keyword = "";
        this.frequency = 0;
        this.similarWords = new HashSet<>(0);
    }

    public String getKeyword()
    {
        return keyword;
    }

    public int getFrequency()
    {
        return frequency;
    }

    public Set<String> getSimilarWords()
    {
        return similarWords;
    }
}
