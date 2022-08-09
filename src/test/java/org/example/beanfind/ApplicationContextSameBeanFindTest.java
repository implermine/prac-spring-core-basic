package org.example.beanfind;


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

/**
 * 같은 타입에 대해서 조회하는 방법, @Configuration 사용 시, AutoWired에선 BeanName으로 구체화 가능
 */
public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanAppConfig.class);

    @Test
    @DisplayName("타입'만'으로 조회시 같은 타입이 둘 이상 있으면, 이름이 다르더라도, 중복 오류가 발생한다.")
    void should_fail_finding_bean_when_same_type_of_bean_are_exist_more_than_two(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> {
            ac.getBean(MemberRepository.class);
        });
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기 -> 오류 없이 -> rem Joinable")
    void should_not_fail_finding_bean_when_selected_bean_are_called_with_list_binding(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String s : beansOfType.keySet()) {
            MemberRepository memberRepository = beansOfType.get(s);
            System.out.println("key(beanName) = " + s + " // memberRepository = " + memberRepository);
        }
        System.out.println(beansOfType);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }


    @Configuration
    static class SameBeanAppConfig{

        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
