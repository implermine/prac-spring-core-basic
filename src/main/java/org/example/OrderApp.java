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
        NoSpring_Logic();
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

    private static void Spring_Logic(){

    }
}
