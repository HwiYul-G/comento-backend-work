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