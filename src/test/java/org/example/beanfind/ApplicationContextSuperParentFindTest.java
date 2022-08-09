package org.example.beanfind;

import org.example.discount.DiscountPolicy;
import org.example.discount.FixDiscountPolicy;
import org.example.discount.RateDiscountPolicy;
import org.example.member.repository.MemberRepository;
import org.example.member.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 부모 타입으로 조회하면 모든 자식 타입 ( a.k.a 해당 부모 타입으로 업캐스팅 가능한) 을 조회한다.
 */
public class ApplicationContextSuperParentFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SuperParentFindTestAppConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 존재하면, 중복 오류가 발생한다.")
    void should_fail_finding_bean_when_select_with_parent_type_but_bind_with_single_beanType() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> {
            ac.getBean(DiscountPolicy.class);
        });
    }

    /**
     * 국룰
     */
    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void should_success_finding_bean_when_select_by_parent_type_with_specific_bean_name_and_child_are_plural() {
        DiscountPolicy foundBean = ac.getBean("fixDiscountPolicy", DiscountPolicy.class);
        assertThat(foundBean).isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void should_success_finding_bean_when_select_by_subType_aka_specific() {
        DiscountPolicy foundBean = ac.getBean(FixDiscountPolicy.class);

        assertThat(foundBean).isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void should_success_finding_beans_when_select_by_parent_type_but_result_may_collection() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String s : beansOfType.keySet()) {
            System.out.println(" beanName:" + s + " bean:" + beansOfType.get(s));
        }

        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Object로 조회")
    void should_success_finding_all_beans_when_select_with_java_Object_type() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String s : beansOfType.keySet()) {
//            System.out.println(" beanName:" + s + " bean:" + beansOfType.get(s));
        }

        assertThat(beansOfType.get("fixDiscountPolicy")).isNotNull();
        assertThat(beansOfType.get("rateDiscountPolicy")).isNotNull();
        assertThat(beansOfType.get("memberRepository")).isNotNull();

    }


    @Configuration
    static class SuperParentFindTestAppConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }

        @Bean
        public MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }
    }
}
