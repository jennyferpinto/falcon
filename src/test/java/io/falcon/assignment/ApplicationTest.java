package io.falcon.assignment;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/payload")
    .content("{\"content\": \"racecar\"}")
    .contentType(MediaType.APPLICATION_JSON);

    @Test
    public void testPayloadPost() throws Exception {
        this.mockMvc.perform(request).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("palindromeLength").value(7))
                .andExpect(jsonPath("content").value("racecar"))
                .andExpect(jsonPath("payloadId").value(1));
    }

    @Test
    public void testPayloadGet() throws Exception {
        this.mockMvc.perform(get("/payload/1")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("content").value("racecar"))
        .andExpect(jsonPath("palindromeLength").value(7));
    }
}

