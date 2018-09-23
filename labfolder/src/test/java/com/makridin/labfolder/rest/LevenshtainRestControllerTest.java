package com.makridin.labfolder.rest;

import com.makridin.labfolder.LabfolderApplication;
import com.makridin.labfolder.rest.bean.LevenshtainBean;
import com.makridin.labfolder.service.data.LevenshteinReportData;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = LabfolderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LevenshtainRestControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testRetrieveStudentCourse() throws JSONException
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");

        LevenshtainBean bean = new LevenshtainBean();
        bean.setText("Word sWord word Wor ord");
        bean.setKeyword("Word");

        HttpEntity<LevenshtainBean> entity = new HttpEntity<>(bean, headers);

        ResponseEntity<LevenshteinReportData> response = restTemplate.exchange(
                createURLWithPort("/labfolder/api/find_frequency"),
                HttpMethod.POST,
                entity,
                LevenshteinReportData.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        LevenshteinReportData data = response.getBody();
        Assert.assertEquals(data.getFrequency(), 1);
        Assert.assertEquals(data.getKeyword(), "Word");
        Assert.assertEquals(data.getSimilarWords().size(), 4);
    }

    private String createURLWithPort(String uri)
    {
        return "http://localhost:" + port + uri;
    }
}
