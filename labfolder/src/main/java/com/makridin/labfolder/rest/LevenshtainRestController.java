package com.makridin.labfolder.rest;

import com.makridin.labfolder.rest.bean.LevenshtainBean;
import com.makridin.labfolder.service.data.LevenshteinReportData;
import com.makridin.labfolder.service.FindFrequencyService;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class LevenshtainRestController
{
    @Resource(name = "myFindFrequencyService")
    private FindFrequencyService myFindFrequencyService;

    @POST
    @Path("/find_frequency")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LevenshteinReportData findFrequencyAndSimilarWords(LevenshtainBean reguest)
    {
        if(reguest.getText() == null || reguest.getText().trim().isEmpty() || reguest.getKeyword() == null || reguest.getKeyword().trim().isEmpty())
        {
            return new LevenshteinReportData();
        }

        myFindFrequencyService.findFrequencyAndSimilarWords(reguest.getText().trim(), reguest.getKeyword().trim());
        return new LevenshteinReportData();
    }
}
