package org.example.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 싱글턴에서 상태를 유지할 때 발생하는 문제점
 */
public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        // 조회 1
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);

        // 조회 2
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // Thread A: A사용자 10000원 주문
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                statefulService1.order("userA", 10000);
            }
        };

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
            }
        }
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
