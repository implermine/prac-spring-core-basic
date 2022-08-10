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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DIP 와 OCP를 지키기 위해, '누군가'가 인터페이스에 구현체를 주입해줘야 한다.
 *
 * 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스를 만들자.
 *
 *
 * ###
 * AppConfig처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을
 * 'IOC 컨테이너' 또는 'DI 컨테이너'라 한다.
 * ###
 *
 * ###
 * 스프링은 빈을 생성하고, 의존관계를 주입하는 단계가 나누어져 있다. 그런데 이렇게 자바 코드로 스프링 빈을 등록하면 생성자를 호출하면서
 * 의존관계 주입도 한번에 처리된다.
 * ###
 */
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){ // <- 요게 이름으로 들어감.
        /**
         * 싱글턴 CGLIB 테스트 용도
         *
         * 스프링 컨테이너가 각각 @Bean을 호출해서 스프링 빈을 생성한다. 그래서 memberRepository()는
         * 다음과 같이 총 3번이 호출되어야 하는 것 아닌가?
         * 1. 스프링 컨테이너가 스프링 빈에 등록하기 위헤 @Bean이 붙어있는 memberRepository() 호출
         * 2. memberService() 로직에서 memberRepository() 호출
         * 3. orderService() 로직에서 memberRepository() 호출
         */



        // 1번
        return new MemberServiceImpl(this.memberRepository());
    }

    /**
     * 빈 이름 직접 명시
     *
     * 주의, 빈 이름은 항상 다른 이름을 부여해야 한다.
     */
    @Bean(name = "orderService")
    public OrderService orderService(){
        // 1번
        return new OrderServiceImpl(this.memberRepository(),this.discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository(){
        // 2번? 3번?
        return new MemoryMemberRepository();
    }

    /**
     * fix로 결정
     */
    @Bean
    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
    }

    //TODO: fix랑 rate 둘다 등록 안되나 bean name으로 조회하면 되자나
}
