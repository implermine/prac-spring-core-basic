package org.example.discount;

import org.example.member.Member;

public interface DiscountPolicy {


    int discount(Member member, int price);
}
