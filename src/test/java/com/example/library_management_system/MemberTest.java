package com.example.library_management_system;

import com.example.library_management_system.entity.Member;
import com.example.library_management_system.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberTest {


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        memberRepository.deleteAll();
    }
    @Test
    void testCreateMember() throws Exception {
        Member member = new Member(null, "John Doe", "alic@example.com", LocalDate.now());
        mockMvc.perform(get("/members/" + member.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("bob@example.com"));
    }
    @Test
    void testGetAllMembers() throws Exception {
        mockMvc.perform(get("/members/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
    @Test
    void testGetMemberById() throws Exception {
        Member member = memberRepository.save(new Member(null , "Jane Doe", "bob@exampple.com", LocalDate.now()));
        mockMvc.perform(get("/members/" + member.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.email").value("bob@example.com"));}
    @Test
    void testGetMemberByIdNotFound() throws Exception {
        mockMvc.perform(get("/members/" + UUID.randomUUID()))
                .andExpect(status().isInternalServerError());
    }
}
