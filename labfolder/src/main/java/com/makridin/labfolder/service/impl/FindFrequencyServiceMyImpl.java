package com.makridin.labfolder.service.impl;

import com.makridin.labfolder.rest.data.LevenshteinReportData;
import com.makridin.labfolder.service.FindFrequencyService;
import org.springframework.stereotype.Component;

@Component(value = "myFindFrequencyService")
public class FindFrequencyServiceMyImpl implements FindFrequencyService
{
    @Override
    public LevenshteinReportData findFrequencyAndSimilarWords(String text, String keyword)
    {

        return null;
    }
}
