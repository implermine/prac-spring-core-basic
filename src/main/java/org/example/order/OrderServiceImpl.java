package org.example.order;

import org.example.discount.DiscountPolicy;
import org.example.discount.FixDiscountPolicy;
import org.example.member.Member;
import org.example.member.repository.MemberRepository;
import org.example.member.repository.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{


    // 문제점1 : 할인 정책을 변경하려면 클라이언트인 OrderServiceImpl의 코드를 고쳐야 한다.
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 따라서, OCP와 DIP를 지키기 위해, 다음과같이 구현체가 아닌 인터페이스에 의존
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
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
