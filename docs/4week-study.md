## custom validator 관련

## Srping에서 RestControllerAdvice와 ExceptionHandler로 예외 핸들링
### Basic exception handling
사용자가 서버에 접근하는 환경에 따라서 오류 헨들링이 된다.
웹 페이지에서 접근했다면 Wiltelab error page를 받는다.
스프링 부트는 BasicErrorController가 구현되어 있고, 기본적으로 스프링 부트는 error 요청을 /error로 전달하는 WAS setup을 가진다.
#### Requestflow
`WAS -> Filter -> DispatcherServlet -> Interceptor -> Controller`
만약 컨트롤러(service, db.. )에서 예외가 발생하면 그 예외는 /error를 한번 이상 호출한다.
`WAS -> Filter -> DispatcherServlet -> Interceptor -> Controller -> (throw Excpeiton) -> Interceptor -> DispatcherServlet -> Filter -> Was -> Filter -> DispatcherServlet -> Interceptor -> Controller(/error)`

restapi에서 json같은 메시지를 받는다면 클라이언트 관점에서 유용하지 않을 것이다.

이것을 해결하기 위해서, Exception을 우리 스스로 만들어서 리턴하도록 구현할 수 있다.
많은 방법이 있지만 ExceptionHandler와 RestControllerAdvice를 말할 것이다.

### ExceptionHandler
- `Controller`, `RestControllerAdvice`에서 `ExceptionHandler`를 사용할 수 있다.
- `ExceptionHandler`가 던진 exceptions은 `ExceptionHandlerExceptionResolver`가 다룬다.
- ExceptionHandler를 사용해서 `Controller`에서 발생하는 errors를 쉽게 다룰 수 있다.
#### REstControllerAdvice
- restcontrollerAdvice는 스프링 빈으로 등록된다.
- exceptionhandler는 globally하게 적용될 수 있다.

```java
@Slf4j
@RestControllerAdvice
public class ServiceExceptionController{
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException exception){
        log.warn("exception test");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("exception test");
    }
}
```




### 참고자료
- https://medium.com/@seonggil/handling-exceptions-with-restcontrolleradvice-exceptionhandler-e7c95216da8d

## AOP(Aspect Oriented Programming)
AOP는 cross-cutting 관점을 분리함으로써 모듈화를 증가시키는 목적을 가진 프로그래밍 파라다임이다.
코드 자체를 수정하지 않고 기존의 코드에 추가적인 행동을 추가함으로써 AOP를 할 수 있다.
대신에, 우리는 새로운 코드와 새로운 행동을 분리해서 선언해야 한다.
스프링 AOP 프레임워크는 우리가 이러한 cross-cutting concerns를 구현하게 도와준다.

### 용어
1. Aspect
    - 여러 클래스 전체에서 자른 concern의 모듈화 (ex. 통합된 로깅)
    - 전체에서 잘린 관점을 캡슐화하는 모듈
    - advice와 pointcuts를 가진다. 
2. JoinPoint
    - 프로그램 실행 동안의 포인트. (ex. method 예외, exception handling)
    - 메소드 호출, 생성자 자극, 필드 접근 등처럼 프로그램의 실행에서 특정한 시점
3. PointCut
    - 특정한 joinpoint에 aspect가 advice를 매칭시키는 '술어'부분.
    - aspect의 advice가 적용되어야하는 장소를 정의하는 표현
    - pointcut은 특정한 join point를 선택한다. 
4. Advice
    - 특정 pointcut이 매칭되었을 때 실행되는 코드
    - advice의 여러 종류는 before, after, around, after-throwing을 포함한다.

**나의 결론 : point cut으로 join point를 지정하고 join point에서 aspect의 advice가 실행된다.**

### AOP Test
유닛 테스트 관점에서, spring application context를 포함한 다른 의존성 없이 aspect 안에서만 로직을 테스팅한다.
이를 위해서 `joinPoint`를 mock하기 위해서 Mockito를 사용하고 testing aspect에 mock을 주입한다.
```java
// Mockito를 가능하게 하는 어노테이션: 자동으로 mock을 초기화 + 각 mocks을 @InjectMock 어노테이션된 테스팅 유닛에 삽입
@ExtendWith(MockitoExtension.class)
class DaoAspectTest {
    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @InjectMocks
    private DaoAspect daoAspect;

    @DisplayName("RuntimeException 발생시 DaoAccessException이 제대로 던져 지는지 확인")
    @Test
    void whenExecuteJoinPoint_thenDaoAccessExceptionIsThrown() throws Throwable {
        when(proceedingJoinPoint.proceed()).thenThrow(new RuntimeException());

        assertThrows(DaoAccessException.class, ()-> daoAspect.daoBefore(proceedingJoinPoint));
        verify(proceedingJoinPoint, times(1)).proceed();
    }

}
```


### 참고자료
- https://www.baeldung.com/spring-aop
- https://naveen-metta.medium.com/deep-dive-into-aspect-oriented-programming-aop-in-spring-and-spring-boot-afcb29141cbd
- https://www.baeldung.com/spring-aop-test-aspect

# 작업 내역
- 제출 마지막 일에 몰아서 작성함으로 **자세한 것은 코드 참조**
- 추후 학습 내역 관련 docs에 차차 업데이트...
## 할일 목록
- [x] 공휴일 목록 추가
- [ ] mybatis query문 작성 및 dao, service, controller 연결
- [ ] exception handling
- [ ] favicon warning 관련 처리

## 공휴일 목록 추가(트러블 슈팅 포함)
### holiday table 생성
- `localDate`의 값은 `20241225` 형태로 들어감에 주의
- 기존 테이블의 date관련 형식이 `2412250707`형태로 2024년 12월 25일 7월 7일을 나타내므로 테이블 join 및 값 비교시 문제를 일으킬 수 
```sql
  CREATE TABLE statistic9.holiday(
    localDate varchar(10) NOT NULL PRIMARY KEY,
    description varchar(15)
  );
```
### 공휴일api를 사용해서 holiday table에 데이터 집어넣기
현재 2022년 01월 ~ 2024년 07월까지 운영된 회사라고 가정됨. 
즉, requestInfo 자체가 2022년 01월 ~ 2024년 07월까지 만 존재함.
#### 공휴일 API 링크
- https://www.data.go.kr/data/15012690/openapi.do
#### restTemplate과 webclient 중 webClient 사용 이유
- 스프링에는 다른 서비스에 http 요청을 하기 위해서 restTemplate과 webClient가 존재한다.
- external api in spring을 검색한 후 restTemplate 자료만 나와 초기에 이를 사용해 시도
- try-catch로 지속적으로 오류를 핸들링해야하는 부분에서 코드의 불편감을 느낌
- 또한, 서버와 200으로 연결되지만 내가 받으려고 한 형태의 올바른 응답이 아닌 경우 에러 핸들링이 어려운 점 > 이로 인해 내 스프링 서버가 끊기는 문제
- 다른 방안 탐색 중 비동기, 논 블로킹으로 더 현대적이고 오류 핸들링도 간편한 webClient 찾음

#### service Key error
공공API에서 제공해준 encoding, decoding 키를 둘다 사용해서 `UriComponentsBuilder`로 `toURIString()`을 하거나 URI 객체로 빌드했을 때 공공api 서버에서 200 신호를 받아도 `SERVICE_KEY_IS_NOT_REGISTERED_ERROR`를 받음
```xml
<OpenAPI_ServiceResponse>
  <cmmMsgHeader>
    <errMsg>SERVICE ERROR</errMsg>
    <returnAuthMsg>SERVICE_KEY_IS_NOT_REGISTERED_ERROR</returnAuthMsg>
    <returnReasoNCode>30</returnReasoNCode>
  </cmmMsgHeader>
</OpenAPI_ServiceResponse>
```
`URI`객체를 이용해 URI를 직접 만드는 방식인 `new URI("주소")`으로 해결할 수 있다.

#### exception handling
- 하지만 위의 service key 관련 오류를 해결해도 브라우저에서 직접 uri를 쳤을 땐 발생하지 않은 문제(http routing error)가 있다.
- 공공 api의 오류 핸들링은 http Status를 200으로 보내준 상태에서 다뤄야하므로 불편하다.
- 200이 제대로 나왔을 때, 기대한 값이 올 거라고 예상해서 만들어둔 ResponseDto로 받지만 그 안에는 null이 들어 있기 때문에 왜 문제인지 바로 파악하기 어렵기 때문이다.
- restTemplate을 이용할 때는 이 부분에서 계속 연결한 uri 를 직접 들어가서 확인해야 했고 browser에서는 잘못된 부분이 없는 것처럼 (성공한 것처럼) 보이는 문제가 있었다.
- 즉 서버 로그를 확인하고 uri를 직접 들어가 보지 않으면 올바른지 아닌지 확인할 수 없는 것이다. 

이는 아래와 같은 방식으로 보다 간편하게 해결할 수 있다.
```java
webClient.get().uri("주소").retriev()
  .onStatus(HttpStautsCode::is4xxClientError, res -> {
    ...
  })
  .onStauts(HttpStatusCode::is5xxClientError, res -> {
    ...
  })
  .bodyToMono(String.class).block();
```
위에서 모든 받은 데이터를 String으로 변형시켜준다. 이는 예생한 response 값이 아닌 error 데이터를 받았을 때를 다루기 위함이다.
```java
String response = 위의함수(){
  try{
      CorrectResponseDto dto = objectMapper.readValue(response, CorrectResponseDto.class)
      .stream().map(매핑 처리).toList();
  }catch(Exception e){
    // 매핑에 실패하면 exception이 자연스럽게 발생하면서 오류 처리 가능
    throw new Exception();
  }
}
```
결국 try-catch를 사용하는 한계가 있다.

#### XML, JSON 둘다 받을 수 있어야 해
- 공공 api 서버에 json 데이터로 달라고 요청해도 이는 200 status에 올바른 데이터가 날라올 때 가능한 이야기이다.
- 앞에 발생한 오류들같이 200으로 연결은 OK지만 error가 던져지는 경우는 json이 아니라 xml만 보내준다.
- restTemplate으로 받는 경우 restTemplate의 converter 목록에 xmlMapper와 objectMapper를 둘다 추가해주는 방식으로 해결할 수 있다. (현재 코드는 지움..)
- 위를 찾아보다가 xml도 지원가능하게 아래를 설정하게 됨.
```java
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    ... 다른 내용 ...
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(new ObjectMapper()));
        converters.add(new MappingJackson2XmlHttpMessageConverter(new XmlMapper()));
    }
}
```

#### 결국,,, spring에서 실행시 http routing error로 계속 실패해서 직접 데이터를 넣어줌
<details>
  <summary>2022년 to 2024년 공휴일 데이터 넣는 sql문</summary>
  
  ```sql

    # 2022년
    INSERT INTO statistic9.holiday (localDate, description) VALUES
        ('20220101', '1월1일'),
        ('20220131', '설날'),
        ('20220201', '설날'),
        ('20220202', '설날'),
        ('20220301', '삼일절'),
        ('20220309', '대통령선거일'),
        ('20220505', '어린이날'),
        ('20220508', '부처님오신날'),
        ('20220601', '전국동시지방선거'),
        ('20220606', '현충일'),
        ('20220815', '광복절'),
        ('20220909', '추석'),
        ('20220910', '추석'),
        ('20220911', '추석'),
        ('20220912', '대체공휴일'),
        ('20221003', '개천절'),
        ('20221009', '한글날'),
        ('20221010', '대체공휴일'),
        ('20221225', '기독탄신일');


    # 2023년
    INSERT INTO statistic9.holiday (localDate, description) VALUES
        ('20230101', '1월1일'),
        ('20230121', '설날'),
        ('20230122', '설날'),
        ('20230123', '설날'),
        ('20230124', '대체공휴일'),
        ('20230301', '삼일절'),
        ('20230505', '어린이날'),
        ('20230527', '부처님오신날'),
        ('20230529', '대체공휴일'),
        ('20230606', '현충일'),
        ('20230815', '광복절'),
        ('20230928', '추석'),
        ('20230929', '추석'),
        ('20230930', '추석'),
        ('20231002', '임시공휴일'),
        ('20231003', '개천절'),
        ('20231009', '한글날'),
        ('20231225', '기독탄신일');


    # 2024년
    INSERT INTO statistic9.holiday (localDate, description) VALUES
        ('20240101', '1월1일'),
        ('20240209', '설날'),
        ('20240210', '설날'),
        ('20240211', '설날'),
        ('20240212', '대체공휴일(설날)'),
        ('20240301', '삼일절'),
        ('20240410', '국회의원선거'),
        ('20240505', '어린이날'),
        ('20240506', '대체공휴일(어린이날)'),
        ('20240515', '부처님오신날'),
        ('20240606', '현충일'),
        ('20240815', '광복절'),
        ('20240916', '추석'),
        ('20240917', '추석'),
        ('20240918', '추석'),
        ('20241003', '개천절'),
        ('20241009', '한글날'),
        ('20241225', '기독탄신일');
  ```

</details>

## mybatis query문 작성 및 dao, service, controller 연결
## Exception Handling
## favicon warning 관련 처리
완성 후 지속적으로 데이터 요청시 아래와 같은 warning이 발생함
```
.m.m.a.ExceptionHandlerExceptionResolver :
        Resolved [org.springframework.web.servlet.resource.NoResourceFoundException: No static resource favicon.ico.]
```