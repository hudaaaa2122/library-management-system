package com.example.library_management_system.service;


import com.example.library_management_system.entity.Member;
import com.example.library_management_system.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class MemberService {


    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }


    public Member getMemberById(@PathVariable UUID id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found with id: " + id));
    }

    public ResponseEntity<String> createMember(@RequestBody Member member) {
        member.setId(UUID.randomUUID());
        memberRepository.save(member);
        return ResponseEntity.status(HttpStatus.CREATED).body("Member created");
    }
}


