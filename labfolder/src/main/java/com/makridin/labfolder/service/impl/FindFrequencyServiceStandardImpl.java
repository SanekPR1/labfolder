package com.makridin.labfolder.service.impl;

import com.makridin.labfolder.service.data.LevenshteinReportData;
import com.makridin.labfolder.service.FindFrequencyService;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component(value = "findFrequencyService")
public class FindFrequencyServiceStandardImpl extends FindFrequency implements FindFrequencyService
{

    @Override
    public LevenshteinReportData findFrequencyAndSimilarWords(String text, String keyword)
    {
        String[] mass = splitText(text);
        LevenshteinDistance distance = new LevenshteinDistance(1);
        int frequency = 0;
        Set<String> similarWords = new HashSet<>();
        for (String word : mass)
        {
            int diff;
            if(isWordLengthEligible(word, keyword))
            {
                diff = distance.apply(word, keyword);
                if(diff == 0)
                {
                    frequency++;
                }
                else if (diff == 1)
                {
                    similarWords.add(word);
                }
            }
        }
        return new LevenshteinReportData(keyword, frequency, similarWords);
    }
}
