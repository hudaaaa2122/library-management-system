package com.example.library_management_system.Repository;

import com.example.library_management_system.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
}


