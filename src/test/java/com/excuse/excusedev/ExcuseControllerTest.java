package com.excuse.excusedev;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
            .andExpect(jsonPath("$[0].http_code").value(701));
    }
    @Test
    public void add() throws Exception {
        mockMvc.perform(post("/excuses/list"))
            .andExpect(status().isOk());
    }
}
