package org.example.member.service;

import org.example.member.Member;
import org.example.member.repository.MemberRepository;
import org.example.member.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "memberServiceImpl") // -> 자동으로 클래스명의 앞 대문자를 소문자명으로 바꿔 빈으로 등록한다.
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    /**
     * 구현체에 의존하는 DIP 위반 코드
     */
    public MemberServiceImpl() {
        this.memberRepository = new MemoryMemberRepository();
    }

    /**
     * 생성자가 하나만 존재할 때, 생성자 주입이 동작하므로 이 클래스는 @Autowired가 반드시 붙어줘야 한다.
     */
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        System.out.println("call AppConfig.memberService");
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
