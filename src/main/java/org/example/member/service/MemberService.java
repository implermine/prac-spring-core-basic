package org.example.member.service;

import org.example.member.Member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
