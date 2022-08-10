package org.example.member.repository;

import org.example.member.Member;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{

    public MemoryMemberRepository() {
        System.out.println("call AppConfig.memberRepository");
    }

    // key: memberId , value:member
    private static Map<Long, Member> db = new HashMap<>();

    @Override
    public void save(Member member) {
        db.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return db.get(memberId);
    }
}
