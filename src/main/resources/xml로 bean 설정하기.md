## 예제

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="memberService" class="org.example.member.service.MemberServiceImpl">
        <constructor-arg name="memberRepository" ref="memberRepository"></constructor-arg>
    </bean>
</beans>
```

## 템플릿

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="등록할 빈 이름" class="패키지명.클래스 (그런데 실제 구현체)">
        <constructor-arg name="패키지명.클래스로 지정된 클래스의 생성자에 주입 할 빈에 해당하는 파라미터 명" ref="주입 할 빈 이름(여기 xml에서 띄워놔야겠죵)"></constructor-arg>
    </bean>
</beans>
```