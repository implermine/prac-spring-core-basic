# Ref

---
[김영한 : 스프링-핵심-원리-기본편](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%95%B5%EC%8B%AC-%EC%9B%90%EB%A6%AC-%EA%B8%B0%EB%B3%B8%ED%8E%B8/dashboard)


# Miscellaneous

---

## 프레임워크 vs 라이브러리
> 프레임워크가 내가 작성한 코드를 제어하고, 대신 실행하면 그것은 프레임워크가 맞다. (JUnit)
반면에 내가 작성한 코드가 직접 제어의 흐름을 담당한다면 그것은 프레임워크가 아니라 라이브러리다.

## 스프링 컨테이너, DI 컨테이너, IOC 컨테이너
> ApplicationContext를 스프링 컨테이너라 한다.
> AppConfig가 DI 컨테이너였으니, 스프링으로 전환된 AppConfig의 '정보'를 ApplicationContext에 올리면 그때부터 ApplicationContext가 DI 컨테이너이자, 스프링 컨테이너이다.

## BeanDefinitionName
> 빈의 이름이자 ID이다.

## BeanDefinition
> Annotation, XML등으로 작성한 빈 정보를 추상화 한 데이터, 스프링 컨테이너가 빈을 등록하는것에 필요하다.