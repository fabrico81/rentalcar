package com.rentalcar.server.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author faber
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class CarControllerTest {

    @Autowired
    private WebApplicationContext context;

    private TestRestTemplate template = new TestRestTemplate();
    private MockMvc mockMvc;
    public final String HOST = "http://localhost";
    public final String GET_ALL = "/cars";
    @Value("${local.server.port}")
    private int port;


    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void contexLoads() throws Exception {

    }
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/cars/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("{")));

        String url = HOST + ":" + port + GET_ALL;
        System.out.println(url);
        String body = template.getForEntity(url, String.class).getBody();
        System.out.println(body);
        assertNotNull(body);
    }
    @Ignore
    @Test(expected = JsonMappingException.class)
    public void addCarTest () throws Exception {

        String json = "\"id\": \"100\",\n" +
                "\"brand\": \"opel\",\n" +
                "    \"model\": \"meriva\",\n" +
                "    \"mileage\": 100,\n" +
                "    \"color\": \"black\",\n" +
                "    \"licencePlate\": \"HH643AB\"";

        mockMvc.perform(post("/car/")

                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }
}
