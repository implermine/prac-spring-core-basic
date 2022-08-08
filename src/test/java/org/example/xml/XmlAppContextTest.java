package org.example.xml;

import org.assertj.core.api.Assertions;
import org.example.member.service.MemberService;
import org.example.member.service.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Xml 기반 DI 컨테이너 구성 연습
 */
public class XmlAppContextTest {

    /**
     * beanFactory == applicationContext == DI Container == IOC Container
     */
    @Test
    void should_load_bean_factory_with_xml_property_settings(){
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

        MemberService foundBean = ac.getBean("memberService", MemberService.class);

        Assertions.assertThat(foundBean).isInstanceOf(MemberServiceImpl.class);
    }
}
