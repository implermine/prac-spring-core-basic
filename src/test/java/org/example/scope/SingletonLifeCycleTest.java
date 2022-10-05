package org.example.scope;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

public class SingletonLifeCycleTest {

    @Test
    public void singletonBeanFind(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean bean = ac.getBean(SingletonBean.class);
        SingletonBean bean2 = ac.getBean(SingletonBean.class);

        Assertions.assertThat(bean).isSameAs(bean2);
        ac.close(); // 종료

    }

    @Scope(scopeName = SCOPE_SINGLETON)
    static class SingletonBean{
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }
    }
}
