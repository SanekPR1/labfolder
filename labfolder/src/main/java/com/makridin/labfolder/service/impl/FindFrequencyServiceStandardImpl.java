package com.makridin.labfolder.service.impl;

import com.makridin.labfolder.service.data.LevenshteinReportData;
import com.makridin.labfolder.service.FindFrequencyService;
import com.makridin.labfolder.service.utils.FindFrequencyUtil;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Component(value = "findFrequencyService")
public class FindFrequencyServiceStandardImpl implements FindFrequencyService
{

    @Override
    public LevenshteinReportData findFrequencyAndSimilarWords(String text, String keyword)
    {
        if(!StringUtils.hasText(text) || !StringUtils.hasText(keyword))
        {
            return new LevenshteinReportData(keyword == null ? "" : keyword, 0, new HashSet<>(0));
        }

        keyword = keyword.trim();
        String[] mass = FindFrequencyUtil.splitText(text.trim());
        LevenshteinDistance distance = new LevenshteinDistance(1);
        int frequency = 0;
        Set<String> similarWords = new HashSet<>();
        for (String word : mass)
        {
            int diff;
            if(FindFrequencyUtil.isWordLengthEligible(word, keyword))
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
