package org.example.inject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class InjectionTest {

    @Test
    @DisplayName("각각의 옵션이 어떻게 처리되는지 확인해보자")
    void check_injection(){
//        ApplicationContext ac = new AnnotationConfigApplicationContext(InjectionAppConfig.class);

        /**
         * 클래스를 직접 스프링 컨테이너에 등록할 수 있다.
         */

        ApplicationContext ac = new AnnotationConfigApplicationContext(InjectionTestClass.class);
    }



    @Configuration
    public static class InjectionAppConfig{

        @Bean
        public InjectionTestClass injectionTestClass(){
            return new InjectionTestClass();
        }

    }
}
