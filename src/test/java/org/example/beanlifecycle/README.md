# 정리

1. @PostConstruct, @PreDestroy를 사용하자.
2. 코드를 고칠 수 없는 외부 라이브러리를 초기화, 종료해야 하면 `@Bean`의 `initMethod`, `destoryMethod`를 사용하자.