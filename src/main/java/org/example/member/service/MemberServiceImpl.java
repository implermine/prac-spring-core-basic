package org.example.member.service;

import org.example.member.Member;
import org.example.member.repository.MemberRepository;
import org.example.member.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    /**
     * 구현체에 의존하는 DIP 위반 코드
     */
    public MemberServiceImpl() {
        this.memberRepository = new MemoryMemberRepository();
    }

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
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
