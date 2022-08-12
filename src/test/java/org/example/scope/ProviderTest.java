package org.example.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;


/**
 * javax provider JSR-330
 */
public class ProviderTest {

    @Test
    @DisplayName("JSR-330의 prototype 쓰기")
    void should_return_prototype_bean_when_using_provider(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class, PrototypeBean.class);
        var bean1 = ac.getBean(SingletonBean.class);
        Assertions.assertThat(bean1.logic()).isEqualTo(1);

        var bean2 = ac.getBean(SingletonBean.class);
        Assertions.assertThat(bean2.logic()).isEqualTo(1); // not 1
    }



    @Scope("singleton")
    static class SingletonBean{

        private final Provider<PrototypeBean> provider;

        public SingletonBean(Provider<PrototypeBean> provider) {
            this.provider = provider;
        }

        public int logic(){
            PrototypeBean prototypeBean = provider.get();
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
