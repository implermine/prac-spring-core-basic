package org.example;

import org.example.member.Grade;
import org.example.member.Member;
import org.example.member.service.MemberService;
import org.example.member.service.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        NoSpring_But_OCP_Logic();
    }

    /**
     * DIP와 OCP를 위반한 로직
     */
    private static void NoSpring_Logic(){
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);

        Member foundMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("foundMember = " + foundMember.getName());
    }

    /**
     * Spring을 사용하진 않지만, DIP와 OCP를 위반하지 않은 로직
     */
    private static void NoSpring_But_OCP_Logic(){
        AppConfig appConfig = new AppConfig(); // -> 추후에 스프링 컨테이너가 된다.
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member foundMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("foundMember = " + foundMember);
    }
}
