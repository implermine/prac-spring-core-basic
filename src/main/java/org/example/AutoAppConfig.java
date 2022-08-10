package org.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION , classes = Configuration.class),
        basePackages = "org.example"
//        basePackageClasses = AutoAppConfig.class // 지정한 클래스의 '패키지'를 탐색 시작 위치로 지정한다.
//        basePackages = {"org.example", "org.notExample"} // 여러 시작 위치를 지정

        // 만약 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
)
public class AutoAppConfig {
}
