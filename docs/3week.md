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
    - [코드 작성 커밋 내역](https://github.com/HwiYul-G/comento-backend-work/commit/8440ac623d3fd9ea067ebc87ed6a6f47759bbea0)
2. 스프링 구동 후 `http://localhost:8031/ping`에서 데이터 확인

    <img src='./images/3week/pingTest.png' width='200' height='150' />

## DB와 Table 생성 및 DB 데이터 초기화
### DB와 Table 생성
1. DBeaver 설치 및 connection
    - [1주차 내역의 DBeaver 설치, Connection 파트 참고]('./1week.md')
2. 새 databse 생성
    |1|2|
    |:--:|:--:|
    |<img src='./images/3week/create-db1.png' width='200' height='150'/>|<img src='./images/3week/create-db2.png' width='200' height='150'/>|
### DB 데이터 초기화
**`spring-boot/sql/create-table.sql`에서 모든 sql 관리**하므로 해당 부분 코드를 참고
1. erd 따라서 sql console로 table 생성

    <img src='./images/3week/erd.png/' width='400' height='200'>
 
    - [관련 테이블 생성 sql 바로가기](../spring-boot/sql/create-table.sql)
    ```sql
        CREATE TABLE statistic9.requestInfo (
            requestID numeric NOT NULL PRIMARY KEY,
            requestCode varchar(5) NOT NULL,
            userID varchar(5),
            createDate varchar(10)
        );

        CREATE TABLE statistic9.requestCode(
            requestCode varchar(5) NOT NULL PRIMARY KEY,
            code_explain varchar(5) NOT NULL
        );

        CREATE TABLE statistic9.user(
            userID varchar(5) NOT NULL PRIMARY KEY,
            HR_ORGAN varchar(5) NOT NULL,
            USERNAME varchar(5) NOT NULL
        );

        ALTER TABLE statistic9.requestInfo
            ADD FOREIGN KEY(requestCode) REFERENCES statistic9.requestCode(requestCode);
        ALTER TABLE statistic9.requestInfo
            ADD FOREIGN KEY(userID) REFERENCES statistic9.user(userID);
    ```
4. `requestCode` table 데이터 초기화
    - [requestCode 데이터 삽입 sql](../spring-boot/sql/init-requestCode.sql)
    ```sql
        INSERT INTO statistic9.requestCode(requestCode, code_explain)
            VALUES('L', '로그인'),
            ('O', '로그아웃'),
            ('WB', '게시글작성'),
            ('DB', '게시글삭제');
    ```
5. `user` table 데이터 초기화
    - [user 데이터 삽입 sql](../spring-boot/sql/init-user.sql)
    ```sql
        INSERT INTO statistic9.user(userID, HR_ORGAN, USERNAME)
            VALUES(1, 'AAA', '신짱구'),
            (2, 'AAA', '신형만'),
            (3, 'AAA', '봉미선'),
            (4, 'AAA', '신짱아'),
            (5, 'AAA', '흰둥이'),
            (6, 'AAA', '김철수'),
            (7, 'AAA', '한유리'),
            (8, 'AAA', '이훈이'),
            (9, 'AAA', '맹구'),
            (10, 'AAA', '한수지');
        INSERT INTO statistic9.user(userID, HR_ORGAN, USERNAME)
            VALUES(11, 'BBB', '루피'),
            (12, 'BBB', '크롱'),
            (13, 'BBB', '포비'),
            (14, 'BBB', '패티'),
            (15, 'BBB', '뽀로로'),
            (16, 'BBB', '크롱'),
            (17, 'BBB', '페티'),
            (18, 'BBB', '에디');
        INSERT INTO statistic9.user(userID, HR_ORGAN, USERNAME)
            VALUES(19, 'CCC', '도라에몽'),
            (20, 'CCC', '노진구'),
            (21, 'CCC', '신이슬'),
            (22, 'CCC', '만퉁퉁'),
            (23, 'CCC', '왕비실'),
            (24, 'CCC', '도라미');
        INSERT INTO statistic9.user(userID, HR_ORGAN, USERNAME)
            VALUES(25, 'DDD', '코난'),
            (26, 'DDD', '유미란'),
            (27, 'DDD', '홍장미'),
            (28, 'DDD', '유명한'),
            (29, 'DDD', '세모'),
            (30, 'DDD', '아름이'),
            (31, 'DDD', '뭉치'),
            (32, 'DDD', '유미란');
    ```
6. `requestInfo` table 데이터 초기화
    **많은 데이터 생성을 위해서 파이썬으로 자동화 코드를 작성**
    - [requestInfo 관련 랜덤 데이터 생성 파이썬 코드](../requestInfo-generator.py)
        - 아래의 코드는 2022년 1월 1일 부터 24년 7월 23일 까지의 데이터를 3000개 생성함
    - [해당 코드로 생성된 초기화 데이터 sql 파일](../spring-boot/sql/init-requestInfo.sql) 
    ```python
    import random;
    import datetime;

    request_codes = ['L', 'O', 'WB', 'DB']
    max_user_id = 32 # 현재 모든 인원 수에 따라서 변경

    # 시작 날짜를 변경하고 싶다면 변경
    start_date = datetime.datetime(2022, 1, 1, 0, 0)
    end_data = datetime.datetime.now()

    def generate_random_date(start, end):
        return start + (end - start) * random.random()

    insert_queries = []

    for i in range(1, 3001):
        request_id = i
        request_code = random.choice(request_codes)
        user_id = random.randint(1, max_user_id)
        random_date = generate_random_date(start_date, end_data)
        create_date = random_date.strftime('%y%m%d%H%M')

        insert_queries.append(f"({request_id}, '{request_code}', '{user_id}', '{create_date}')")

    # 전체 쿼리 생성
    insert_query = f"INSERT INTO statistic9.requestInfo(requestID, requestCode, userID, createDate) VALUES\n" + ",\n".join(insert_queries) + ";"

    # 파일로 저장
    with open("insert_queries.txt", "w") as file:
        file.write(insert_query)

    print("쿼리가 insert_queries.txt 파일에 저장되었습니다.")
    ```

## 스프링 부트, Mybatis, mariadb 연동
1. mybatis, mariaDB, junit test dependencies 추가
    - [의존성 추가 관련 커밋](https://github.com/HwiYul-G/comento-backend-work/commit/e2cff2f63b196bf605048dd80c3c3e9aa09ab3fb)
2. application.properites 추가
    - [db 설정 및 mybatis-xml 연결](https://github.com/HwiYul-G/comento-backend-work/commit/2cf2806fab6192a7eb946cf24b2b47da38e17c15)
3. mapper 작성
    - [dto, dao 작성 및 mapper와 연결](https://github.com/HwiYul-G/comento-backend-work/commit/f1ca683e151b703abfd962416fabb57fb07f7821)
4. service, controller 작성
    - [서비스](https://github.com/HwiYul-G/comento-backend-work/commit/7bb3324fb8bb62d68115b972ca807e958c415dc1)
    - [controller](https://github.com/HwiYul-G/comento-backend-work/commit/55b6e90011d07f1bebd4d94429974e8ef079bae1)
5. 실행 결과

    <img src='./images/3week/db-connection-test.png' width='200' height='150'>

## API sql
- **쿼리를 통해 필요한 totCnt, average 등만 찾고, 나머지 response는 service에서 추가**
1. 년월별 사용자 접속자 수(로그인 수)
    ```sql
    select count(*) as totCnt
    from statistic9.requestInfo ri
    where left(ri.createDate, 6) = #{yearMonth} and requestLog='L';
    ```
    - 평균 하루 로그인 수
       - `lastDay`: 특정 년월의 마지막 일 (service에서 자바 코드를 이용해 데이터를 넘겨준다.)
        ```sql
        select count(*) as totCnt, count(*) / #{lastDay} as average 
        from statistic9.requestInfo ri
        where left(ri.createDate, 6) = #{yearMonth} and requestLog='L';
        ```
    - 휴일 제외 로그인 수
        - `holidayList`: '1, 2, 3' 같이 컴마(,)로 구분된 문자열
        - 혹은 holiday관련 테이블이 존재하는 경우 `not in (select holiday_date from holidays)`같은 문을 이용
        ```sql
        select count(*) as totCnt
        from statistic9.requestInfo ri
        where left(ri.createDate,6)=#{yearMonth} and requestLog='L' and right(ri.createDate,2) not in (#{holidayList});
        ```
    - 휴일 제외한 평균 하루 로그인 수
        - `lastDay`: 특정 년월의 마지막 일
        - `holidayList`: '1, 2, 3' 같이 컴마(,)로 구분된 문자열
        ```sql
        select count(*) as totCnt, count(*) / #{lastDay} as average
        from statistic9.requestInfo ri
        where left(ri.createDate, 6)=#{yearMonth} and requestLog='L' and right(ri.createDate,2) not in (#{holidayList})
        ```
2. 년월별 부서별 사용자 접속자 수(로그인 수)
    ```sql
    select count(*) as totCnt
    from statistic9.requestInfo ri inner join statistic9.user as u on userID
    where left(ri.createDate, 6) = #{yearMonth} and requestLog='L' and u.HR_ORGAN=#{team};
    ```
3. 년월별 게시글 작성 수
    ```sql
    select count(*) as totCnt
    from statistic9.requestInfo ri
    where left(ri.createDate, 6)=#{yearMonth} and requestLog='WB';
    ```
4. 년월별 부서별 게시글 작성 수
    ```sql
    select count(*) as totCnt
    from statistic9.requestInfo ri left outer join statistic9.user as u on userID
    where left(ri.createDate, 6)=#{yearMonth} and requestLog='WB' and u.HR_ORGAN=#{team};
    ```