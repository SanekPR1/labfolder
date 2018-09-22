package com.makridin.labfolder.service;

import com.makridin.labfolder.service.data.LevenshteinReportData;
import com.makridin.labfolder.service.impl.FindFrequencyServiceMyImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class FindFrequencyServiceMyImplTest
{
    @Test
    public void testFindFrequencyAndSimilarWords()
    {
        String text = "Word,!? word-word % word1, - SWord( *ord !Words @Ward #Cord $:;Wosd ^&?Work, Word. /pWor| d \\Sword \"sWord\"";
        String keyword = "Word";

        FindFrequencyServiceMyImpl imp = new FindFrequencyServiceMyImpl();
        LevenshteinReportData data = imp.findFrequencyAndSimilarWords(text, keyword);
        Assert.assertEquals(2, data.getFrequency());
        Assert.assertEquals(keyword, data.getKeyword());
        Assert.assertEquals(8, data.getSimilarWords().size());
    }

    @Test
    public void loadTestFindFrequencyAndSimilarWords()
    {
        String text = "Word,!? word-word % word1, - SWord( *ord !Words @Ward #Cord $:;Wosd ^&?Work, Word. /pWor| d \\Sword \"sWord\"";

        StringBuffer b = new StringBuffer(text);
        long milis = new Date().getTime();
        for (int i = 0; i < 1_000_000; i++)
        {
            b.append(" " + i + "Word");
        }
        System.out.println("Word creation takes: " + (new Date().getTime() - milis) + " milliseconds");

        text = b.toString();

        String keyword = "Word";

        milis = new Date().getTime();
        FindFrequencyServiceMyImpl imp = new FindFrequencyServiceMyImpl();
        LevenshteinReportData data = imp.findFrequencyAndSimilarWords(text, keyword);
        System.out.println("To count frequency of the word in the text and search of similar words: " + (new Date().getTime() - milis) + " milliseconds");
        Assert.assertEquals(2, data.getFrequency());
        Assert.assertEquals(keyword, data.getKeyword());
        Assert.assertEquals(18, data.getSimilarWords().size());
    }
}
