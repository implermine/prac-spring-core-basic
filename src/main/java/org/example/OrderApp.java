package org.example;

import org.example.member.Grade;
import org.example.member.Member;
import org.example.member.service.MemberService;
import org.example.member.service.MemberServiceImpl;
import org.example.order.Order;
import org.example.order.OrderService;
import org.example.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        NoSpring_But_OCP_Logic();
    }


    private static void NoSpring_Logic(){
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        long memberId = 1L;

        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"itemA",10000);

        System.out.println("order = " + order);
    }

    private static void NoSpring_But_OCP_Logic(){
        AppConfig appConfig = new AppConfig(); // 추후에 스프링 컨테이너가 된다.
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        long memberId = 1L;

        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"itemA",10000);

        System.out.println("order = " + order);
    }
}
