package org.example.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 싱글톤 빈과 프로토타입 빈을 같이 쓸때 문제가 생긴다.
 *
 * 프로토타입 빈을 쓰지만 마치 싱글턴처럼 사용되는 경우
 *
 * singletonBean 이 내부에 가지고 있는 프로토타입 빈은 이미 과거에 주입이 끝난 빈이다. 주입 시점에 스프링 컨테이너에 요청해서 프로토타입 빈이 새로 생성이 된 것이지,
 * 사용 할 때마다 새로 생성되는 것이 아니다.
 *
 * 스프링은 일반적으로 싱글톤 빈을 사용하므로, 싱글톤 빈이 프로토타입 빈을 사용하게 된다. 그런데 싱글톤빈은 생성 시점에만 의존관계 주입을 받기 때문에,
 * 프로토타입 빈이 `새로 생성되기는 하지만`, 싱글톤 빈과 함께 계속 유지되는 것이 문제다.
 *
 * 아마 원하는 것이 이런 것은 아닐 것이다. 프로토타입 빈을 주입 시점에만 새로 생성하는게 아니라, 사용할 대 마다 새로 생성해서 사용하는 것을 원할 것이다.
 *
 * 참고: 여러 빈에서 같은 프로토타입 빈을 주입 받으면, `주입 받는 시점에 각각 새로운프로토타입 빈이 생성`된다. 예를 들어서 clientA, clientB가 각각 의존관계 주입을 받으면
 * 각각 다른 인스턴스의 프로토타입 빈을 주입 받는다.
 *
 * clientA -> prototypeBean@x01
 * clientB -> prototypeBean@x02
 *
 * 물론 사용할 때 마다 새로 생성되는 것은 아니다.
 */
public class SingletonWithPrototypeProblemTest {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac  = new AnnotationConfigApplicationContext(SingletonBean.class,PrototypeBean.class);
        SingletonBean bean1 = ac.getBean(SingletonBean.class);


        SingletonBean bean2 = ac.getBean(SingletonBean.class);

        bean1.logic();
        Assertions.assertThat(bean2.getPrototypeBeanCount()).isEqualTo(2L);


        /**
         * 프로토타입 빈을 사용하였지만 count가 올라간다.
         */

        bean2.logic();
        Assertions.assertThat(bean2.getPrototypeBeanCount()).isEqualTo(2L);

    }

    @Scope("singleton")
    static class SingletonBean{

        private final PrototypeBean prototypeBean;

        @Autowired
        public SingletonBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public void logic(){
            prototypeBean.addCount();
        }

        public int getPrototypeBeanCount(){
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count += 1;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
