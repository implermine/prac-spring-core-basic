package org.example.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class ObjectProviderTest {

    @Test
    void providerTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeProviderTest.SingletonBean.class, PrototypeProviderTest.PrototypeBean.class); //-> ApplicationContext에 정보를 등록한다. not 생성해서 넣는다.

        PrototypeProviderTest.SingletonBean bean1 = ac.getBean(PrototypeProviderTest.SingletonBean.class);
        Assertions.assertThat(bean1.logic()).isEqualTo(1);

        PrototypeProviderTest.SingletonBean bean2 = ac.getBean(PrototypeProviderTest.SingletonBean.class);
        Assertions.assertThat(bean2.logic()).isEqualTo(1); // not 2


    }

    @Scope("singleton")
    static class SingletonBean{

        private final ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;

        public SingletonBean(ObjectProvider<PrototypeBean> prototypeBeanObjectProvider) {
            this.prototypeBeanObjectProvider = prototypeBeanObjectProvider;
        }

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject(); // 마치 ThreadLocal 지역변수처럼 사용.
            // ObjectProvider의 getObject()를 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환한다. (DL)
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
