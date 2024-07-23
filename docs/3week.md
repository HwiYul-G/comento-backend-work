##  스프링 부트 환경 셋팅 및 환경 셋팅 테스트
### 스프링 부트 환경 셋팅
1. [spring initializr](https://start.spring.io/ )에 들어가서 Maven프로젝트 생성
    - GROUP: 프로젝트 그룹의 id
    - Artifcat, Name: 애플리케이션 이름
    - Description: 애플리케이션 설명
    - packageName: 그룹 + artifact id의 조합
    - packaging: Jar
    - Java: 17
    - dependencies
        - lombok: 자바의 보일러 플레이트 코드를 어노테이션 형태로 줄여준다.
        - spring web: RESTful web services와 웹 앱을 만들기 위해 필요한 도구들을 장착
        - spring boot devTools: 파일에 변화가 생길 때 애필리케이션의 재시작을 자동으로 유발한다.
2. 1의 결과로 생성된 프로젝트를 인텔리제이, 이클립스같은 IDE로 오픈한다.
3. 프로젝트 구조 정리
    - `resources` 경로 아래: `mapper`, `static`, `templates` 디렉토리 + `application.properties`
    - `com.demo.comentoStatistic` 경로 아래: `config`, `controller`, `dao`, `dto` 패키지 생성
4. application.properties 설정
```application.properteis
server.port = 8031
server.servlet.contextPath=/  # root 경로 설정 (없으면 기본값이 /)

logging.level.org.springframework.web=DEBUG # DEBUG 레벨 이상의 로그만 보여줌 (TRACE > DEBUG > INFO > WARN > ERROR> FATAL)
logging.level.org.hibernate=ERROR # ERROR 이상의 로그레벨만 보여줌

devtools.livereload.enabled=true # 파일 변화시 reload가 될 수 있도록 설정
```
### 환경 셋팅 테스트
1. `PingController` 만들어서 테스트하기
```java
@Controller
public class PingController {

    @RequestMapping(value="/ping", produces = "application/json")
    @ResponseBody
    public Object healthCheck(){
        Map<String, Object> map = new HashMap<>();
        map.put("today", ZonedDateTime.now().getMonth() + ":" + ZonedDateTime.now().getDayOfMonth());
        return map;
    }

}
```
2. 스프링 구동 후 `http://localhost:8031/`에서 데이터 확인
<img src='' width='200' height='200' />

## DB와 Table 생성하기
```sql

```

## 스프링 부트, Mybatis, mariadb 연동

## API sql
