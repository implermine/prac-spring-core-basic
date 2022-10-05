package org.example.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

public class PrototypeLifeCycleTest {

    @Test
    public void prototypeBeanFind(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find Prototype bean");
        PrototypeBean bean = ac.getBean(PrototypeBean.class);
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        Assertions.assertThat(bean).isNotSameAs(bean2);

        /**
         * find Prototype bean
         * PrototypeBean.init
         * PrototypeBean.init
         */


        /**
         * Init만 2번 일어나고 매번 새로운 빈을 만들어주며,
         *
         * destroy는 관리하지 않는다.
         *
         * 또한, AppicationContext를 생성할 때 (스프링 컨테이너를 만들 때) 빈을 생성하는것이 아닌,
         *
         * 빈은 조회할 때, 빈을 생성한다.
         *
         * `그러나` 싱글턴 빈과 의존관계가 존재하는 프로토타입 빈은 싱글턴 생성시 생성된다. 싱글턴 빈을 조회시 생성되는것이 아니라.
         */

        ac.close();

    }

    @Scope(scopeName = SCOPE_PROTOTYPE)
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
