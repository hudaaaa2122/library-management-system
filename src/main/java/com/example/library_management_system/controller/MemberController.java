package com.example.library_management_system.controller;

import com.example.library_management_system.entity.Member;
import com.example.library_management_system.repository.MemberRepository;
import com.example.library_management_system.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }
    @GetMapping ("/{id}")
    public Member getMemberById(@PathVariable UUID id) {
        return memberService.getMemberById(id);
    }
    @PostMapping
    public ResponseEntity <String> createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }
}

