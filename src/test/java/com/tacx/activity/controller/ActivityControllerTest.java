package com.tacx.activity.controller;

import com.tacx.activity.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ContextConfiguration(classes = {Application.class})
class ActivityControllerTest {


    @Autowired
    private WebApplicationContext wac;

    private static MockMvc mockMvc;

    public ActivityControllerTest() {
    }

    @BeforeEach
    void setup() {
        if (mockMvc == null) {
            mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
            log.warn("spring started!!!");
        }
    }

    @Test
    void should_getActivitySummaryList_return_successResponse() throws Exception {
        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/activities/summary")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        //then
        int status = result.getResponse().getStatus();

        assertThat(status).isEqualTo(200);
    }


    @Test
    void should_getActivitySummary_returnException_when_activityNotExist() throws Exception {

        UUID uuid = UUID.randomUUID();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/activities/summary/" + uuid)
                .contentType(MediaType.APPLICATION_JSON_VALUE).requestAttr("activityId", uuid))
                .andReturn();

        int status = result.getResponse().getStatus();

        assertThat(status).isEqualTo(422);
    }

    @Test
    void should_deleteActivity_return_successResponse() throws Exception {

        UUID uuid = UUID.randomUUID();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/activities/" + uuid)
                .contentType(MediaType.APPLICATION_JSON_VALUE).requestAttr("activityId", uuid))
                .andReturn();

        int status = result.getResponse().getStatus();

        assertThat(status).isEqualTo(200);
    }
}

