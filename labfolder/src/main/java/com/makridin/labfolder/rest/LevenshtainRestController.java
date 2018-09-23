package com.makridin.labfolder.rest;

import com.makridin.labfolder.rest.bean.LevenshtainBean;
import com.makridin.labfolder.service.data.LevenshteinReportData;
import com.makridin.labfolder.service.FindFrequencyService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api")
@Component
public class LevenshtainRestController
{
    @Resource(name = "myFindFrequencyService")
    private FindFrequencyService myFindFrequencyService;

    @POST
    @Path("/find_frequency")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LevenshteinReportData findFrequencyAndSimilarWords(LevenshtainBean request)
    {
        if(request.getText() == null || request.getText().trim().isEmpty() || request.getKeyword() == null || request.getKeyword().trim().isEmpty())
        {
            return new LevenshteinReportData();
        }

        return myFindFrequencyService.findFrequencyAndSimilarWords(request.getText().trim(), request.getKeyword().trim());
    }
}
