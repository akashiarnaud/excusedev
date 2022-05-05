package com.excuse.excusedev;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import com.excuse.excusedev.domain.Excuse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ExcuseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void list() throws Exception {
        mockMvc.perform(get("/excuses/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].http_code", is(701)));
    }

    @Test
    public void add() throws Exception {
        Excuse excuse = Excuse.createExcuse("test", "testing");
        mockMvc.perform(post("/excuses/add")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(excuse)))
                .andExpect(status().isOk());
    }
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }  
}
