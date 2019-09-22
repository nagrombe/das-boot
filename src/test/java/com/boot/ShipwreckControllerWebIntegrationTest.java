package com.boot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ShipwreckControllerWebIntegrationTest {
    @Test
    public void testListAll() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/v1/shipwrecks", String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(response.getBody());

        assertThat(responseJson.isMissingNode(), is(false));
        var responseShipwrecks = "[{\"id\":5,\"name\":\"Titanic update88\",\"description\":\"damaged\",\"condition\":\"Not good!\",\"depth\":5600,\"latitude\":45.9,\"longitude\":42.7,\"yearDiscovered\":1945},{\"id\":7,\"name\":\"Titanic update\",\"description\":\"Iceberg damaged\",\"condition\":\"Not good\",\"depth\":5600,\"latitude\":56.89,\"longitude\":120.45,\"yearDiscovered\":1896}]";
        assertThat(responseJson.toString(), equalTo(responseShipwrecks));

    }
}
