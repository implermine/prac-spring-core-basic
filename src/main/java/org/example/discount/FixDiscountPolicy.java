package org.example.discount;

import org.example.member.Grade;
import org.example.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private final static int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade()== Grade.VIP){ // VIP면 fix된 discountAmt를 주면 될듯
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
