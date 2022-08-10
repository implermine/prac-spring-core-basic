package org.example.cglib;

import org.example.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 바이트 코드 조작 테스트
 */
public class CGLIBTest {

    @Test
    void configurationDeep(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // AppConfig도 스프링 빈으로 등록된다.
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean);
        // 출력: bean = class org.example.AppConfig$$EnhancerBySpringCGLIB$$bd479d70


        /**
         * 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 '상속' 받은 임의의 다른 클래스를 만들고,
         * 그 다른 클래스를 스프링 빈을 등록한다.
         */

        /**
         * @Configuration을 붙이면 바이트코드를 조작하는 CGLIB 기술을 사용해서 싱글톤을 보장하지만, 만약 @Bean만 적용하면
         * 싱글톤을 보장하지 않는다.
         */
    }
}
