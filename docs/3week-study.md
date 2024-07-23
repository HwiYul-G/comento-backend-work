## RestController란?
- spring 4.0에서 RESTful web services 생성을 간단하게 하려고 `@RestController`가 등장했다.
    - `@RestController` = `@Controller` + `@ResponseBody`
    - `@ResponseBody` 어노테이션은 반환하는 object를 자동적으로 HttpResponse로 직렬화 한다.
    - `@Controller`: `@Component` 클래스에 역할을 구체화한 것이고, classpath scanning을 통해 자동으로 감지한다.

### Response 보기
```
```

### 참고자료
- https://doublesprogramming.tistory.com/204
- https://www.baeldung.com/spring-controller-vs-restcontroller
- https://www.geeksforgeeks.org/spring-rest-controller/

## Maven과 Gradle-Groovy, Gradle-Kotlin
메이븐과 그레이들은 자바 프로젝트의 빌드 자동화 툴이다.
||Maven|Gradle|
|:--:|:--|:--|
|빌드 방식|clean, compile, test, package 등 미리 정의된 라이프 사이클|의존성을 가진 task의 DAG(Directed Acyclic Graph) 사용 > 빌드 과정 유연화 및 커스텀화|
|성능|적은 변화에도 task 재실행 필요 > 큰 프로젝트일 수록 느림|build, build cache, 병렬 실행 등 > 더 빠름|
|의존성 관리|내장된 의존성 scope의 제한된 셋 제공 > 나중에 복잡한 모듈 구조|커스텀 의존성 스코프 > 유연함, 조직화 유리, 더 빠른 빌드|
|러닝 커브|단순 구조, XML 설정 > 쉬움| groovy dsl로 배우기 어려움 > 배우면 편함|

### maven과 gradle 중 무엇을 선택할까?
- maven: 프로젝트가 이미 메이븐에 강한 의존성을 가진 경우, 설정에 대한 컨벤션 접근이 있는 경우
- gradle: 빌드 과정에서 맞춤화 및 유연화가 필요한 경우, 더 빠르길 원하는 경우

### 참고자료
- https://medium.com/@jagritisrvstv/maven-and-gradle-what-to-choose-b63f9584425e 
- https://hackernoon.com/maven-vs-gradle-how-to-choose-the-right-build-tool
- https://stackify.com/gradle-vs-maven/
- https://www.linkedin.com/pulse/comparing-maven-gradle-choosing-right-build-tool-your-nikhil-sharma/

## JAR vs. WAS
### JAR(Java Archive)
- `.jar` 확장자를 가진다.
- 라이브러리, 리소스, 메타데이터 파일을 가진다.
- 본질적으로 `.class` 파일과 컴파일된 자바 라이브러리 자원과 애플리케이션 압축 버전을 포함하는 zip파일
- maven이나 jar command로 생성
- 기본적으로 아래의 구조를 가진다.
    ```plain
    META-INF/
        MANIFEST.MF
    com/
        example/
            MyApplication.cass
    ```
### WAS(Web Application Archive, Web Application Resource)
- `.war` 확장자를 가진다.
- web applications를 패키징하는 데 사용된다.
    - 웹 애플리케이션은 servlet/jsp container로 배포할 수 있다.
- jar와 마찬가지로 maven이나 command로 생성한다.
- 기본 구조는 아래의 골격을 가진다.
    ```
    META-INF/ > private하고 외부에서 손댈 수 없음
        MANIFEST.MF      
    WEB-INF/
        web.xml
        jsp/
            helloWorld.jsp
        classes/
            static/
            templates/
            application.properties
        lib/
            // *.jar files as libs
    ```
### JAR vs.WAS
- jar는 라이브러리, 플러그인, 다른 종류의 앱으로 사용하기 위해서 여러 파을들을 패킹징한다. 반면, war files은 오직 web application만에 사용된다.
- archives 구조: jar는 desired strucutre를 가질 뿐이지만, wars는 사전 정의된 web-inf와 meta-inf를 명확히 가진다.
- jar를 실행하가능한 jar로 빌드하면 추가적인 소프트웨어 없이 실행 가능하다. 반면, 서버가 있어야만 war를 실행할 수 있다.

### 참고자료
- https://dev.to/martygo/what-is-the-difference-between-a-jar-and-a-war-file-402a
- https://www.baeldung.com/java-jar-war-packaging