# 학습 내용
## Servlets
- 동적 웹 페이지(요청에 따라 콘텐츠 생성, 시간에 따른 변화 등)을 생성할 수 있음
- Java Servlet은 java 지원 웹/앱 서버에서 실행되는 java 프로그램
    - 서버에서 얻은 복잡한 요청 처리
      <img src="https://media.geeksforgeeks.org/wp-content/uploads/20240305120707/Servlets_architecture-768.png" width="200" height="200" />
- 매 요청마다 프로세스를 생성하고 파괴하는 CGI(Common Gateway Interface)의 한계를 해결함

## JSP(Jakarta Server Pages)
- 웹 애플리케이션을 만드는 서버 측 기술
- 동적 웹 '콘텐츠' 제작
- HTML 태그와 JSP 태그로 구성
    - JSP 태그를 이용해 Java 코드를 HTML에 삽입

## Apache Tomcat와 Eclipse Jetty
- java로 applications을 실행하기 위해 사용하는 server

## JDBC
database와 통신하기 위해 사용
<img src="https://media.geeksforgeeks.org/wp-content/uploads/20200229213833/Architecture-of-JDBC2.jpg" width="300" height="200"/>

## MyBatis
![image](https://terasolunaorg.github.io/guideline/5.1.1.RELEASE/en/_images/DataAccessMyBatis3Scope.png)


## 참고자료
- [Java Servlet](https://www.geeksforgeeks.org/introduction-java-servlets/)
- [JSP](https://www.geeksforgeeks.org/introduction-to-jsp/?ref=lbp)
- [Apache Tomcat vs. Eclipse Jetty](https://www.geeksforgeeks.org/apache-tomcat-vs-eclipse-jetty/)
- [JDBC](https://www.geeksforgeeks.org/introduction-to-jdbc/)
- [MyBatis 자료1](https://mybatis.org/mybatis-3/)
- [MyBatis 자료2](https://terasolunaorg.github.io/guideline/5.1.1.RELEASE/en/ArchitectureInDetail/DataAccessMyBatis3.html)

# 개발자 로드맵에 따른 학습 내역
## 데이터베이스 선택 기준
1. 빅데이터 처리 > Postgres
2. 복잡한 로직을 다루거나 트러블이나 장애 다루기 쉬운 것 + 돈 여유: Oracle(금융권)
3. 보편적으론 MySQL 추천

## REST API

### 쿠키와 세션 vs. 토큰

### 캐싱
1. 클라이언트 측: 브라우저 캐시를 이용해 get method를 빠르게
2. 서버측: middleware 사용
- r-db 호출 없이 in-memory db인 redis를 사용해 빠르게 처리한다.
- redis에는 잘 변화지 않는 설정 정보를 주로 넣어놓난다.
- r-db 호출이 적어저 DB가 락에 걸릴 확률이 줄어든다.

### HTTPS = HTTP + SSL/TLS
SSL/TLS는 데이터를 주고 받을 때 암호화하는 것을 의미한다.
### CRS: 도메인이 다르면 에러다! same-origin(같은 곳에서 같은 것을 요청한다.)
daum.net은 daum.net의 요청만 받는다. 만약 naver.com에서 daum.net을 호출시 호출받는 daum.net에서 naver.com이 호출 가능하도록 들곡하거나 해야한다.


### ORM으로인해 N+1 Problems가 발생한다.

### 모놀로식 아키텍처 vs MSA
MSA는 운영 복잡도가 높아지고, 트렌젝션 처리가 복잡하고 로그 볼 때도 안 좋다.
모놀로식은 한 번만 올리면 되서 편할 수 있지만 올려야할게 많으면 느려진다.
모놀로식은 부분이 고장일 때 전체를 멈춰야 한ㄷ.

### 메시지 큐
A가 B를 매번 호출하지 않고 queue에 쌓아서 한번에 B를 호출하게 된다.
이를 통해서, 덜 호출(부하 감소)하고 B에 트레픽이 몰려도 처리할 수 잇다.
B가 죽어서 처리할 수 없을 때 큐에 호출 내용이 남아있어 복구가능하다.
rabbit MQ는 메모리 위에
kafka는 dis(HDD) 위에서 동작한다. 즉, kafka는 죽어도 데이터를 살릴 수 있다.
