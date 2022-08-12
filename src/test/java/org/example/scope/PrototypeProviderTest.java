package org.example.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 싱글턴 빈과 LifeCycle을 함께하는 Prototype이 아닌,
 *
 * 어떻게 하면 사용할 때 마다 항상 새로운 프로토타입 빈을 생성할 수 있을까 ?
 *
 * 가장 간단한 방법은 싱글턴 빈이 프로토타입을 사용할 때 마다 스프링 컨테이너에 새로 요청하는 것이다.
 *
 */
public class PrototypeProviderTest {

    @Test
    void providerTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class, PrototypeBean.class); //-> ApplicationContext에 정보를 등록한다. not 생성해서 넣는다.

        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        Assertions.assertThat(bean1.logic()).isEqualTo(1);

        SingletonBean bean2 = ac.getBean(SingletonBean.class);
        Assertions.assertThat(bean2.logic()).isEqualTo(1); // not 2


    }

    @Scope("singleton")
    static class SingletonBean{

        ApplicationContext ac;

        public SingletonBean(ApplicationContext ac) {
            this.ac = ac;
        }

        public int logic(){
            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class); // 마치 ThreadLocal 지역변수처럼 사용.
            prototypeBean.addCount();
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
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }

}
