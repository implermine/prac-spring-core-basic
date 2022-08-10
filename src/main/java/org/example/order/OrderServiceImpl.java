package org.example.order;

import lombok.Setter;
import org.example.annotationForQualifier.MainDiscountPolicy;
import org.example.discount.DiscountPolicy;
import org.example.discount.FixDiscountPolicy;
import org.example.member.Member;
import org.example.member.repository.MemberRepository;
import org.example.member.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
@Setter // xml에 Setter로 설정해보려고 추가함
public class OrderServiceImpl implements OrderService{


    // 문제점1 : 할인 정책을 변경하려면 클라이언트인 OrderServiceImpl의 코드를 고쳐야 한다.
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 따라서, OCP와 DIP를 지키기 위해, 다음과같이 구현체가 아닌 인터페이스에 의존
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;
    private Integer someConstant; // <- 마찬가지로 xml 설정

    /**
     * DiscountPolicy의 두 구현체(Fix와 Rate) 모두 Component로 올라와 있더라도, 변수명을 지정해서 구현체를 고를 수 있다.
     */

    /**
     * @Qualifier에 대해...
     *
     * 변수명보다 Qualifier가 우선한다.
     *
     * 우선권은 Qualifier -> Primary -> 변수명이다.
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy fixDiscountPolicy) {
        System.out.println("call AppConfig.orderService");
        this.memberRepository = memberRepository;
        this.discountPolicy = fixDiscountPolicy;
    }

    /**
     * DIP와 OCP 위반 코드
     */
    public OrderServiceImpl() {
        this.memberRepository = new MemoryMemberRepository();
        this.discountPolicy = new FixDiscountPolicy();
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
