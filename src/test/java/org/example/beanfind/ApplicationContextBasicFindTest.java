package org.example.beanfind;

import org.assertj.core.api.Assertions;
import org.example.AppConfig;
import org.example.member.service.MemberService;
import org.example.member.service.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 빈 SELECT 조회 (not all)
 * 가장 기본적이므로  동일한 타입이 둘 이상인 경우는 고려하지 않는다.
 */
public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름과 parent 타입으로 조회")
    void should_find_bean_by_name_and_parent_type(){
        MemberService foundBean = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(foundBean).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름 없이 parent 타입만으로 조회 -> SameBeanFind와 비교하면, 하나의 빈만 조회되면 괜찮음")
    void should_find_bean_by_no_name_and_parent_type(){

        MemberService foundBean = ac.getBean(MemberService.class);
        Assertions.assertThat(foundBean).isInstanceOf(MemberService.class);

    }

    @Test
    @DisplayName("빈 이름 없이 구체 타입만으로 조회")
    void should_find_bean_by_no_name_and_specific_type(){
        MemberService foundBean = ac.getBean(MemberServiceImpl.class);
        Assertions.assertThat(foundBean).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회하는데 없는 이름으로 조회할 시 실패 케이스")
    void should_fail_finding_when_select_with_nonexistent_bean_name(){
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
            ac.getBean("karma");
        });
    }
}
