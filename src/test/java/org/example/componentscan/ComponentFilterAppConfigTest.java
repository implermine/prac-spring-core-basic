package org.example.componentscan;

import org.assertj.core.api.Assertions;
import org.example.componentscan.filter.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 설정 정보와 전체 테스트 코드
 */
public class ComponentFilterAppConfigTest {

    @Test
    @DisplayName("어노테이션으로 ComponentScan으로 Scan될 대상 Include하기")
    void should_include_certain_component(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanToInclude beanToInclude = ac.getBean("beanToInclude", BeanToInclude.class);
        Assertions.assertThat(beanToInclude).isNotNull();
    }

    @Test
    @DisplayName("어노테이션으로 ComponentScan으로 Scan될 대상 Exclude하기")
    void should_exclude_certain_component(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
            ac.getBean("beanToExclude", BeanToExclude.class);
        });
    }

    /**
     * 그 조건은
     * @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, classes = BeanToIncludeButExcludedByOtherCondition.class)
     */
    @Test
    @DisplayName("어노테이션으로 Include 되야하지만 다른 조건에 의해 Exclude하기")
    void should_exclude_by_other_condition(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
            ac.getBean("beanToIncludeButExcludedByOtherCondition", BeanToIncludeButExcludedByOtherCondition.class);
        });

    }





    @Configuration
    @ComponentScan(
            basePackages = "org.example.componentscan.filter",
            excludeFilters = {
                    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class),
                    @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, classes = BeanToIncludeButExcludedByOtherCondition.class)
            },
            includeFilters = {
                    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class)
            }
    )
    public static class ComponentFilterAppConfig{

    }
}
