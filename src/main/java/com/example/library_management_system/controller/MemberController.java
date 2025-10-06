package com.example.library_management_system.controller;

import com.example.library_management_system.entity.Member;
import com.example.library_management_system.repository.MemberRepository;
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
    private MemberRepository memberRepository;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    @GetMapping ("/{id}")
    public Member getMemberById(@PathVariable UUID id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found with id: " + id));
    }
    @PostMapping
    public ResponseEntity <String> createMember(@RequestBody Member member) {
        member.setId(UUID.randomUUID());
        memberRepository.save(member);
        return ResponseEntity.status(HttpStatus.CREATED).body("Member created");
    }
}

