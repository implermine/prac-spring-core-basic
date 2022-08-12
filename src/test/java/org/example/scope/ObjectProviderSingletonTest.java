package org.example.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ResolvableType;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * ObjectProvider가 제공하는 DL(Dependecy Lookup)은 싱글턴에도 적용되는가?
 */
public class ObjectProviderSingletonTest {

    @Test
    @DisplayName("ObjectProvider로 DL을 수행하더라도, 싱글턴은 유지되어야 한다.")
    void should_return_singleton_bean_despite_using_object_provider(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean1.class, SingletonBean2.class);
        SingletonBean1 bean1 = ac.getBean(SingletonBean1.class);
        Assertions.assertThat(bean1.logic()).isEqualTo(1);

        SingletonBean1 bean2 = ac.getBean(SingletonBean1.class);
        Assertions.assertThat(bean2.logic()).isEqualTo(2); // not 1
    }



    @Scope("singleton")
    static class SingletonBean1{

        private final ObjectProvider<SingletonBean2> singletonBean2ObjectProvider;

        public SingletonBean1(ObjectProvider<SingletonBean2> singletonBean2ObjectProvider) {
            this.singletonBean2ObjectProvider = singletonBean2ObjectProvider;
        }

        public int logic(){
            SingletonBean2 object = singletonBean2ObjectProvider.getObject();
            object.addCount();
            return object.getCount();
        }
    }

    @Scope("singleton")
    static class SingletonBean2{

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
