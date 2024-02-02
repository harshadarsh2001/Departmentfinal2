package com.harshproject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SchedulerComponentTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSendScheduledTaskConfirmationEmailEvery10sec() throws Exception {
        mockMvc.perform(get("/schedule/task0"))
                .andExpect(status().isForbidden())  // Update this expectation based on your actual requirements
                .andExpect(content().string(""));
    }


    @Test
    void testSendScheduledTaskConfirmationEmailEvery5Seconds() throws Exception {
        mockMvc.perform(get("/schedule/task1"))
                .andExpect(status().isForbidden())  // Update this expectation based on your actual requirements
                .andExpect(content().string(""));
    }


    @Test
    void testSendScheduledTaskConfirmationEmailEvery30Minutes() throws Exception {
        mockMvc.perform(get("/schedule/task2"))
                .andExpect(status().isForbidden())
                .andExpect(content().string("")) // Update this expectation based on your actual requirements
                .andReturn();
    }




    @Test
    void testSendScheduledTaskConfirmationEmailEvery1hr() throws Exception {
        mockMvc.perform(get("/schedule/task3"))
                .andExpect(status().isForbidden())  // Update this expectation based on your actual requirements
                .andExpect(content().string(""));
    }

}
