package org.example.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 싱글턴에서 상태를 유지할 때 발생하는 문제점
 */
public class StatefulServiceTest {

    void statefulServiceSingletonHard() throws InterruptedException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        // 조회 1
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);

        // 조회 2
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // Thread A: A사용자 10000원 주문
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                statefulService1.order("userA", 10000);
            }
        });
        thread1.start();

        Thread.sleep(100);


        // Thread B: B사용자 20000원 주문
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                statefulService2.order("userB", 20000);
            }
        });
        thread2.start();

        while (thread1.isAlive() || thread2.isAlive()) {
            Thread.sleep(100);
        }

        // ThreadA: 사용자 A 주문 금액 조회

        int price = statefulService1.getPrice();

        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new
                AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService",
                StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService",
                StatefulService.class);
        //ThreadA: A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB: B사용자 20000원 주문
        statefulService2.order("userB", 20000);
        //ThreadA: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        //ThreadA: 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
        System.out.println("price = " + price);
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
