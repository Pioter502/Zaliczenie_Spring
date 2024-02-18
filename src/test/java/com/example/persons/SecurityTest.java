package com.example.persons;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(roles = {"USER"})
    void shouldUserGetResponseForGetAllPersons() throws Exception{
        mockMvc.perform(get("/person/persons"))
                .andExpect(status().is(200));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void shouldNotAddPersonByUser() throws Exception{
        mockMvc.perform(post("/person/add"))
                .andExpect(status().is(403));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void shouldNotUpdatePersonByUser() throws Exception{
        mockMvc.perform(patch("/person/update"))
                .andExpect(status().is(403));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void shouldNotDeletePersonByUser() throws Exception{
        mockMvc.perform(delete("/person/delete"))
                .andExpect(status().is(403));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void shouldAdminGetResponseForGetAllPersons() throws Exception{
        mockMvc.perform(get("/person/persons"))
                .andExpect(status().is(200));
    }

}
