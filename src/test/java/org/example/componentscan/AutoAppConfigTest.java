package org.example.componentscan;

import org.assertj.core.api.Assertions;
import org.example.AutoAppConfig;
import org.example.member.service.MemberService;
import org.example.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan(){
        /**
         * 스프링 DI 컨테이너에 AutoAppConfig에 수록된 정보를 BeanDefiinition으로 추상화하여 등록한다.
         * AutoAppConfig는 @ComponentScan으로 빈 데이터를 추출하며,
         * @ComponentScan의 excludeFilter는 본인을 제외한 @Configuration을 exclude한다.
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
//        MemberService memberService = ac.getBean("memberService", MemberService.class); // -> 빈 이름이 맞지 않으므로 에러
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        OrderService orderService = ac.getBean(OrderService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderService.class);

    }
}
