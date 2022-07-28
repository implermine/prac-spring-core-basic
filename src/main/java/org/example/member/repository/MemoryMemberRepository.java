package org.example.member.repository;

import org.example.member.Member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

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
