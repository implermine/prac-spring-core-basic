package org.example;

import org.example.member.Grade;
import org.example.member.Member;
import org.example.member.service.MemberService;
import org.example.member.service.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);

        Member foundMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("foundMember = " + foundMember.getName());
    }
}
