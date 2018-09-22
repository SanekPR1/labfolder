package com.makridin.labfolder.service;

import com.makridin.labfolder.service.data.LevenshteinReportData;

public interface FindFrequencyService
{
    LevenshteinReportData findFrequencyAndSimilarWords(String text, String keyword);
}
