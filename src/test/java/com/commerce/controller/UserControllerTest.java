package com.commerce.controller;

import com.commerce.entity.User;
import com.commerce.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by jinwoo on 2017. 6. 25..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @MockBean
    UserController userController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        //mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void join() throws Exception {


        User testUser = new User();

        testUser.setName("jinwoo");
        testUser.setEmail("superlucky84@gmail.com");
        testUser.setMobile("01092838674");
        testUser.setPassword("1234");
        testUser.setUsername("jinwoo kim");

        //given(this.userController.join(testUser,vava)).willReturn(userRepository.findOne(1L));

        ObjectMapper mapper = new ObjectMapper();
        String testUserSt = mapper.writeValueAsString(testUser);

        System.out.println(testUserSt);


        mockMvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testUserSt))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$['id']", containsString("1")))
                .andDo(print());

    }

}