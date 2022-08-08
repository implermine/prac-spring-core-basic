package org.example.beandefinition;

import org.example.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 스프링 빈 설정 메타 정보 - BeanDefinition
 *
 * 스프링은 어떻게 이런 다양한 설정 형식을 지원하는 것일까? 그 중심에서 BeanDefinition 이라는 추상화가 있다.
 * 쉽게 이야기해서 '역할'과 '구현'을 개념적으로 나눈 것이다
 * * XML을 읽어서 BeanDefinition을 만들면 된다.
 * * 자바 코드를 읽어서 BeanDefinition을 만들면 된다.
 * * 스프링 컨테이너는 자바 코드인지, XML인지 몰라도 된다. 오직 BeanDefinition만 알면 된다.
 * BeanDefinition을 빈 설정 메타정보라 한다.
 * * @Bean, <bean>당 각각 하나씩 메타 정보가 생성된다.
 * 스프링 컨테이너는 이 메타정보를 기반으로 스프링 빈을 생성한다.
 *
 * BeanDefinition 살펴보기
 *  BeanDefinition 정보
 *  BeanClassName: 생성할 빈의 클래스 명(자바 (어노테이션?) 설정 처럼 팩토리 역할의 빈을 사용하면 없음)
 *  factoryBeanName: 팩토리 역할의 빈을 사용할 경우 이름, 예) appConfig
 *  factoryMethodName: 빈을 생성할 팩토리 메서드 지정, 예) memberService
 *  Scope: 싱글톤(기본값
 *  lazyInit: 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아니라, 실제 빈을 사용할 때 까지 최대한
 *   생성을 지연처리 하는지 여부
 *   InitMethodName: 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드 명
 *   DestroyMethodName: 빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드 명
 *   Constructor arguments, Properties: 의존관계 주입에서 사용한다. (자바 설정 처럼 팩토리 역할의
 *   빈을 사용하면 없음
 *
 */
public class BeanDefinitionTest {


    @Test
    @DisplayName("어노테이션 빈 설정 메타정보 확인")
    void check_beanDefinitions_when_annotation(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {

                String beanClassName = beanDefinition.getBeanClassName();
                String factoryBeanName = beanDefinition.getFactoryBeanName();
                String factoryMethodName = beanDefinition.getFactoryMethodName();
                String scope = beanDefinition.getScope();
                boolean lazyInit = beanDefinition.isLazyInit();
                String initMethodName = beanDefinition.getInitMethodName();
                String destroyMethodName = beanDefinition.getDestroyMethodName();
                ConstructorArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();
                MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();


                System.out.println("============================================================================================================================");
                System.out.println("============================================================================================================================");
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        " /// beanDefinition = " + beanDefinition);
                System.out.println("beanClassName = " + beanClassName);
                System.out.println("factoryBeanName = " + factoryBeanName);
                System.out.println("factoryMethodName = " + factoryMethodName);
                System.out.println("scope = " + scope);
                System.out.println("lazyInit = " + lazyInit);
                System.out.println("initMethodName = " + initMethodName);
                System.out.println("destroyMethodName = " + destroyMethodName);
                System.out.println("constructorArgumentValues = " + constructorArgumentValues);
                System.out.println("propertyValues = " + propertyValues);
                System.out.println("============================================================================================================================");
                System.out.println("============================================================================================================================");

            }


        }
    }

    @Test
    @DisplayName("XML 빈 설정 메타정보 확인")
    void check_beanDefinitions_when_xml(){
        GenericXmlApplicationContext xmlApplicationContext = new GenericXmlApplicationContext("appConfig.xml");
        String[] beanDefinitionNames = xmlApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = xmlApplicationContext.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {

                String beanClassName = beanDefinition.getBeanClassName();
                String factoryBeanName = beanDefinition.getFactoryBeanName();
                String factoryMethodName = beanDefinition.getFactoryMethodName();
                String scope = beanDefinition.getScope();
                boolean lazyInit = beanDefinition.isLazyInit();
                String initMethodName = beanDefinition.getInitMethodName();
                String destroyMethodName = beanDefinition.getDestroyMethodName();
                ConstructorArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();
                MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();


                System.out.println("============================================================================================================================");
                System.out.println("============================================================================================================================");
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        " /// beanDefinition = " + beanDefinition);
                System.out.println("beanClassName = " + beanClassName);
                System.out.println("factoryBeanName = " + factoryBeanName);
                System.out.println("factoryMethodName = " + factoryMethodName);
                System.out.println("scope = " + scope);
                System.out.println("lazyInit = " + lazyInit);
                System.out.println("initMethodName = " + initMethodName);
                System.out.println("destroyMethodName = " + destroyMethodName);
                System.out.println("constructorArgumentValues = " + constructorArgumentValues);
                System.out.println("propertyValues = " + propertyValues);
                System.out.println("============================================================================================================================");
                System.out.println("============================================================================================================================");

            }


        }
    }
}
