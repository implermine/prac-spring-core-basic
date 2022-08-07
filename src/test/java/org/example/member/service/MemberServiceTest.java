package org.example.member.service;

import org.assertj.core.api.Assertions;
import org.example.AppConfigWithNoSpring;
import org.example.member.Grade;
import org.example.member.Member;
import org.junit.jupiter.api.*;

class MemberServiceTest {


    @Nested
    @DisplayName("OCP나 DIP를 지키지 않은 테스트")
    class NoSpring{

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

    @Nested
    @DisplayName("OCP와 DIP를 지키지만 Spring을 사용치 않은 테스트")
    class NoSpringButOCP{

        MemberService memberService;

        @BeforeEach
        public void beforeEach(){
            AppConfigWithNoSpring appConfigWithNoSpring = new AppConfigWithNoSpring();
            memberService = appConfigWithNoSpring.memberService();
        }

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



}