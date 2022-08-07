package org.example;

import org.example.discount.DiscountPolicy;
import org.example.discount.FixDiscountPolicy;
import org.example.discount.RateDiscountPolicy;
import org.example.member.repository.MemberRepository;
import org.example.member.repository.MemoryMemberRepository;
import org.example.member.service.MemberService;
import org.example.member.service.MemberServiceImpl;
import org.example.order.OrderService;
import org.example.order.OrderServiceImpl;

/**
 * DIP 와 OCP를 지키기 위해, '누군가'가 인터페이스에 구현체를 주입해줘야 한다.
 *
 * 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스를 만들자.
 */
public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(this.memberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(this.memberRepository(),this.fixDiscountPolicy());
    }

    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    public DiscountPolicy fixDiscountPolicy(){
        return new FixDiscountPolicy();
    }

    public DiscountPolicy rateDiscountPolicy(){
        return new RateDiscountPolicy();
    }
}
