1. 그냥 MyLogger를 final field로 LogDemoController와 LogDemoService에 두면, 스프링 컨테이너를 띄울 때, 자동으로 주입할 수 없다.
request context 속에서만 살아있는 빈이고, prototype과 같은 이유로 싱글턴이므로 Provider가 필요하다.

2. ObjectProvider.getObject()를 `LogDemoController`, `LogDemoService` 에서 각각 한번씩 따로 호출해도 같은 HTTP 요청이면 같은 스프링 빈이 반환된다!