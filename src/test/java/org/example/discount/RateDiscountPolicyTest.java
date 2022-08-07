package org.example.discount;

import org.assertj.core.api.Assertions;
import org.example.member.Grade;
import org.example.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void should_discount_when_vip() {
        // given
        Member memberVIP = new Member(1L, "memberVIP", Grade.VIP);
        int givenPrice = 1000;

        // when
        int discountPrice = discountPolicy.discount(memberVIP,givenPrice);

        // then
        Assertions.assertThat(discountPrice).isEqualTo((int)(givenPrice * (0.1)));
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void should_not_discount_when_not_vip(){
        // given
        Member memberNotVIP = new Member(1L, "memberNotVIP", Grade.BASIC);
        int givenPrice = 1000;

        // when
        int discountPrice = discountPolicy.discount(memberNotVIP,givenPrice);

        // then
        Assertions.assertThat(discountPrice).isEqualTo(0);
    }
}