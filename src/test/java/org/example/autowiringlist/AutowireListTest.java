package org.example.autowiringlist;

import org.assertj.core.api.Assertions;
import org.example.AutoAppConfig;
import org.example.discount.DiscountPolicy;
import org.example.member.Grade;
import org.example.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * 의도적으로 정말 해당 타입의 스프링 빈이 다 필요한 경우도 있다.
 * 예를 들어서 할인 서비스를 제공하는데, 클라이언트가 할인의 종류(rate,fix)를 선택할 수 있다고 가정해보자.
 * 스프링을 사용하면 소위 말하는 전략 패턴을 매우 간단하게 구현할 수 있다.
 */
public class AutowireListTest {

    @Test
    void findListOfBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);// 여러개 가능

        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);

        /**
         * discount Map은 key가 Bean의 이름이다.
         */
        int discountPrice = discountService.discount(member,10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);
    }

    static class DiscountService{

        /**
         * discount Map은 key가 Bean의 이름이다.
         */
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
        }

        public int discount(Member member,int price, String discountCode){
            DiscountPolicy discountPolicy = this.policyMap.get(discountCode);

            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member,price);

        }
    }
}
