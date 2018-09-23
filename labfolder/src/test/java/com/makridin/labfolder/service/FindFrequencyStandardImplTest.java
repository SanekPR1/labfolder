package com.makridin.labfolder.service;

import com.makridin.labfolder.service.data.LevenshteinReportData;
import com.makridin.labfolder.service.impl.FindFrequencyServiceMyImpl;
import com.makridin.labfolder.service.impl.FindFrequencyServiceStandardImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

@ActiveProfiles("test")
public class FindFrequencyStandardImplTest
{
    @Test
    public void testFindFrequencyAndSimilarWords()
    {
        String text = "Word,!? word-word % word1, - SWord( *ord !Words @Ward #Cord $:;Wosd ^&?Work, Word. /pWor| d \\Sword \"sWord\"";
        String keyword = "Word";

        FindFrequencyServiceStandardImpl imp = new FindFrequencyServiceStandardImpl();
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
        FindFrequencyServiceStandardImpl imp = new FindFrequencyServiceStandardImpl();
        LevenshteinReportData data = imp.findFrequencyAndSimilarWords(text, keyword);
        System.out.println("To count frequency of the word in the text and search of similar words: " + (new Date().getTime() - milis) + " milliseconds");
        Assert.assertEquals(2, data.getFrequency());
        Assert.assertEquals(keyword, data.getKeyword());
        Assert.assertEquals(18, data.getSimilarWords().size());
    }
}
