package org.example.order;

import org.assertj.core.api.Assertions;
import org.example.AppConfig;
import org.example.member.Grade;
import org.example.member.Member;
import org.example.member.service.MemberService;
import org.example.member.service.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class OrderServiceIntegrationTest {


    @Nested
    @DisplayName("OCP나 DIP를 지키지 않은 테스트")
    class NoSpring{

        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();


        @Test
        @DisplayName("주문 생성과 그에따른 할인 로직 테스트")
        void should_create_order_via_proper_discount_logic(){
            long memberId = 1L;
            Member member = new Member(memberId, "memberA", Grade.VIP);
            memberService.join(member);

            Order order = orderService.createOrder(memberId,"itemA",10000);
            Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        }
    }

    @Nested
    @DisplayName("OCP와 DIP를 지키지만 Spring을 사용치 않은 테스트")
    class NoSpringButOCP{

        MemberService memberService;
        OrderService orderService;

        @BeforeEach
        public void beforeEach(){
            AppConfig appConfig = new AppConfig();
            memberService = appConfig.memberService();
            orderService = appConfig.orderService();
        }

        @Test
        @DisplayName("주문 생성과 그에따른 할인 로직 테스트")
        void should_create_order_via_proper_discount_logic(){
            long memberId = 1L;
            Member member = new Member(memberId, "memberA", Grade.VIP);
            memberService.join(member);

            Order order = orderService.createOrder(memberId,"itemA",10000);
            Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        }
    }


}