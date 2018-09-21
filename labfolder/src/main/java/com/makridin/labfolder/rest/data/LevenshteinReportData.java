package com.makridin.labfolder.rest.data;

import java.util.List;

public class LevenshteinReportData
{
    private String keyword;
    private int frequency;
    private List<String> similar_words;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public List<String> getSimilar_words() {
        return similar_words;
    }

    public void setSimilar_words(List<String> similar_words) {
        this.similar_words = similar_words;
    }
}
