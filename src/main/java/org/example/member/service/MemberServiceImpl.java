package org.example.member.service;

import org.example.member.Member;
import org.example.member.repository.MemberRepository;
import org.example.member.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl() {
        this.memberRepository = new MemoryMemberRepository();
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
