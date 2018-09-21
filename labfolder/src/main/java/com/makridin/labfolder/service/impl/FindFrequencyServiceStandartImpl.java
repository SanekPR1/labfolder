package com.makridin.labfolder.service.impl;

import com.makridin.labfolder.rest.data.LevenshteinReportData;
import com.makridin.labfolder.service.FindFrequencyService;
import org.springframework.stereotype.Component;

@Component(value = "findFrequencyService")
public class FindFrequencyServiceStandartImpl implements FindFrequencyService
{

    @Override
    public LevenshteinReportData findFrequencyAndSimilarWords(String text, String keyword)
    {
        
        return null;
    }
}
