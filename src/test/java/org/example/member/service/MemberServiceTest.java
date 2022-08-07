package org.example.member.service;

import org.assertj.core.api.Assertions;
import org.example.member.Grade;
import org.example.member.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();
    @Test
    @DisplayName("회원가입")
    void should_join_member() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member foundMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(foundMember).isEqualTo(member);
    }
}