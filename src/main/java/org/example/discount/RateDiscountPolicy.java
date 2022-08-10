package org.example.discount;

import org.example.member.Grade;
import org.example.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy{

    private static final int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP){
            return (int)(price * (discountPercent / 100d));
        }else{
            return 0;
        }
    }
}
