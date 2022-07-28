package org.example.member.service;

import org.assertj.core.api.Assertions;
import org.example.member.Grade;
import org.example.member.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member foundMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(member);
    }

    @Test
    void findMember() {
    }
}