package com.example.library_management_system.Controller;

import com.example.library_management_system.Entity.Member;
import com.example.library_management_system.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping ("/members/all")
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    @GetMapping ("/members/{id}")
    public Member getMemberById(@PathVariable UUID id) {
        return memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
    }
    @PostMapping ("/members/create" )
    public String createMember() {
        return "Member created";
    }
}

