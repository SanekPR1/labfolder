package com.makridin.labfolder.service.impl;

import com.makridin.labfolder.service.data.LevenshteinReportData;
import com.makridin.labfolder.service.FindFrequencyService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component(value = "myFindFrequencyService")
@Scope("prototype")
public class FindFrequencyServiceMyImpl implements FindFrequencyService
{
    @Override
    public LevenshteinReportData findFrequencyAndSimilarWords(String text, String keyword)
    {
        int keywordCounter = 0;
        Set<String> similarWords = new HashSet<>();
        String[] mass = text.replaceAll("[^a-zA-Z0-9- ]", "").split("\\s+");
        for (String word : mass)
        {
            if(isWordLengthEligible(word, keyword))
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


    //PRIVATE REGION
    private boolean isWordLengthEligible(String word, String keyword)
    {
        return word.length() >= keyword.length() - 1 && word.length() <= keyword.length() + 1;
    }

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

    public static void main(String[] args)
    {
        String text = "Word,!? word-word % word1, - SWord( *ord !Words @Ward #Cord $:;Wosd ^&?Work, Word. /pWor| d \\Sword \"sWord\"";
        StringBuffer b = new StringBuffer(text);
        long milis = new Date().getTime();
        for (int i = 0; i < 1_000_00; i++)
        {
            b.append(" " + i + "Word");
            System.out.println(i);
        }
        System.out.println(new Date().getTime() - milis);
        text = b.toString();
        System.out.println("After word creation");

        String keyword = "Word";

        milis = new Date().getTime();
        FindFrequencyServiceMyImpl imp = new FindFrequencyServiceMyImpl();
        LevenshteinReportData data = imp.findFrequencyAndSimilarWords(text, keyword);
        System.out.println(data.getKeyword());
        System.out.println(data.getFrequency());
        for (String str : data.getSimilarWords())
        {
            System.out.println(str);
        }
        System.out.println(new Date().getTime() - milis);
    }
}
