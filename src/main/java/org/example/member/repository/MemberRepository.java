package org.example.member.repository;

import org.example.member.Member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
