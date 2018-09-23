package com.makridin.labfolder.service.impl;

import com.makridin.labfolder.service.data.LevenshteinReportData;
import com.makridin.labfolder.service.FindFrequencyService;
import com.makridin.labfolder.service.utils.FindFrequencyUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Component(value = "myFindFrequencyService")
public class FindFrequencyServiceMyImpl implements FindFrequencyService
{
    @Override
    public LevenshteinReportData findFrequencyAndSimilarWords(String text, String keyword)
    {
        if(!StringUtils.hasText(text) || !StringUtils.hasText(keyword))
        {
            return new LevenshteinReportData(keyword == null ? "" : keyword, 0, new HashSet<>(0));
        }
        keyword = keyword.trim();
        int keywordCounter = 0;
        Set<String> similarWords = new HashSet<>();

        String[] mass = FindFrequencyUtil.splitText(text.trim());
        for (String word : mass)
        {
            if(FindFrequencyUtil.isWordLengthEligible(word, keyword))
            {
                int dif = getDifference(keyword, word);

                if(dif == 0)
                {
                    keywordCounter++;
                }
                else if (dif == 1)
                {
                    similarWords.add(word);
                }
            }
        }

        return new LevenshteinReportData(keyword, keywordCounter, similarWords);
    }

    //region Private
    private int getDifference(String keyword, String word) {
        int dif;
        int shift = 0;
        if (word.length() == keyword.length())
        {
            dif = 0;
            dif = levenshteinAlgorithm(dif, shift, keyword, word);
        }
        else
        {
            dif = 1;
            if(word.charAt(0) != keyword.charAt(0))
            {
                shift++;
            }
            if(word.length() == keyword.length() + 1)
            {
                dif = levenshteinAlgorithm(dif, shift, word, keyword);
            }
            else
            {
                dif = levenshteinAlgorithm(dif, shift, keyword, word);
            }
        }
        return dif;
    }

    private int levenshteinAlgorithm(int dif, int shift, String longWord, String smallWord)
    {
        for (int i = 0; i < smallWord.length(); i++)
        {
            if (dif > 1)
            {
                break;
            }
            else
            {
                if(longWord.charAt(i + shift) != smallWord.charAt(i))
                {
                    dif++;
                }
            }
        }
        return dif;
    }
    //endregion
}
