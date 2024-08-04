# 목차
1. [SW 활용 현황 분석 API 가이드 문서](#sw-활용-현황-분석-api-가이드-문서)
2. [작업 내역 요약](#작업-내역-요약)

</br>

# SW 활용 현황 분석 API 가이드 문서

개정이력

| 버전  |    변경일     |     변경사유     |                                                      변경내역                                                      |
|:---:|:----------:|:------------:|:--------------------------------------------------------------------------------------------------------------|
| 1.0 | 2024-07-13 |     최초작성     |                                                      최초작성                                                      |
| 1.1 | 2024-07-22 | 통일성과 단순화를 위함 | - `{org}/{yearMonth}` 순서 변경 `{yearMonth}/{org}` </br> - 로그인 수 평균과 휴일 제외 로그인 수를 로그인 api 하나로 합치고 query param으로 변경 |
| 1.2 |  2024-08-02| url 주소 변경과 검증         |- url 주소 변경 및 예시 url 추가</br> - http 파라미터 타입 추가 </br> - parameter 검증 관련 추가                                                                                                                    |

## 데이터 공통사항
### 부서 목록(HR_ORGAN)
|HR_ORGAN| 의미|
|:--:|:--:|
|AAA|짱구는 못말려|
|BBB|뽀로로와 친구들|
|CCC|도라에몽|
|DDD|명탐정 코난|

### request log 정보
| request log |   의미   |
|:-----------:|:------:|
|      L      |  로그인   |
|      O      |  로그아웃  |
|     WB      | 게시글 작성 |
|     DB      | 게시글 삭제 |
### Request
http parameter는 아래와 같은 검증 규칙을 따른다.
- `yearMonth`는 `202201`이 최소이고, `202407`이 최대이다.
- `org`는 부서 목록(HR_ORGAN)에 있는 부서만 받을 수 있다.

### Response
응답은 아래와 같은 규칙을 따른다.
- JSON 포맷의 데이터
- 필드명은 소문자로 시작하는 `camel notation`을 사용
- 응답은 아래의 형식을 취한다. 
  - `isSuccess`, `yearMonth`, `requestLog`는 필수적으로 응답에 들어가고, API에 따라 추가적인 게 있다.
```json
{
  "isSuccess": true,
  "yearMonth": "202401",
  "requestLog": "L",
  ...
}
```

## API 목록
- [년월별 사용자 접속자 수(로그인 수)](#년월별-사용자-접속자-수로그인-수)
  - 평균 하루 로그인 수
  - 휴일 제외 로그인 수
- [년월별 부서별 사용자 접속자 수(로그인 수)](#년월별-부서별-사용자-접속자-수)
- [년월별 게시글 작성 수](#년월별-게시글-작성-수)
- [년월별 부서별 게시글 작성 수](#년월별-부서별-게시글-작성-수)
- [특정 년도의 휴일 목록 업데이트](#특정-년도의-휴일-업데이트)

### 년월별 사용자 접속자 수(로그인 수)
#### 요청url
- `api/v1/stats/logins/{yearMonth}`
- 예시 : `api/v1/stats/logins/202401?is-average=true&`
#### HTTP Parameter
|http parameter type|      Name       |  Type   | Description | Mandatory |  Note  |
|:--:|:---------------:|:-------:|:-----------:|:---------:|:------:|
|path |    yearMonth    | string  |    특정 년월         |     O     | 202401 |
|query(request) |   is-average    | boolean |     평균값 여부(default=false)     |     X     |  true  |
|query(request)| exclude-holiday | boolean |  휴일 제외 여부(default=false)   |     X     | false  |
- 평균값만 true인 경우는 해당 년 월의 하루 로그인 수
- 휴일 제외 여부만 true인 경우는 해당 년월의 휴일을 제외한 카운트 수
- 휴일 제외 여부와 평균값 둘다 true인 경우는 해당 년월의 휴일을 제외한 평균 로그인 수
    - 이때 평균은 그 달의 일 수를 기준으로 계산됨
#### Response Body
```json
{
  "isSuccess": true,
  "yearMonth": "202401",
  "totCnt": 3,
  "average": 1.2,
  "requestLog": "L"
}
```

### 년월별 부서별 사용자 접속자 수
#### 요청url
- `api/v1/stats/logins/{yearMonth}/{org}`
- 예시: `api/v1/stats/logins/202401/AAA` 

#### HTTP Parameters
|http parameter type|     Name     |  Type  | Description | Mandatory |  Note  |
|:--:|:------------:|:------:|:-----------:|:---------:|:------:|
|path|  yearMonth   | string |             |     O     | 202401 |
|path| organization | string |     조직명     |     O     |   A    |

#### Response Body
```json
{
  "isSuccess": true,
  "yearMonth": "202401",
  "totCnt": 3,
  "requestLog": "L",
  "team": "A"
}
```

### 년월별 게시글 작성 수
#### 요청url
- `api/v1/stats/posts/{yearMonth}`
- 예시 : `api/v1/stats/posts/202401`
#### Http Parameters
|http parameter type|     Name     |  Type  | Description | Mandatory |  Note  |
|:--:|:------------:|:------:|:-----------:|:---------:|:------:|
|path|yearMonth|string|             |     O     | 202401 |
#### Response Body
```json
{
  "isSuccess": true,
  "yearMonth": "202401",
  "totCnt": 2,
  "requestLog": "WB"
}
```

### 년월별 부서별 게시글 작성 수
#### 요청url
- `api/v1/stats/posts/202401/{org}`
- 예시: `api/v1/stats/posts/202401/AAA`
#### Http Parameters
| http paramteter |   Name    |  Type  | Description | Mandatory |  Note  |
|:--:|:---------:|:------:|:-----------:|:---------:|:------:|
|path| yearMonth |string|    특정년월         |     O     | 202401 |
|path|    org    |string|     조직명     |     O     |   A    |
#### Response Body
```json
{
  "isSuccess": true,
  "yearMonth": "202401",
  "totCnt": 2,
  "requestLog": "WB",
  "team": "AAA"
}
```

### 특정 년도의 휴일 업데이트
- 함부로 insert하지 못하도록 담당자만 코드를 통해 확인 요망

</br>
</br>

# 작업 내역 요약
- 제출 마지막 일에 몰아서 작성함으로 **자세한 것은 코드 참조**
- 추후 학습 내역 관련 docs에 차차 업데이트...
## 할일 목록(작업 목록)
- [x] 공휴일 목록 추가
  - [공공 api](https://www.data.go.kr/data/15012690/openapi.do) 이용하기: restTemplate이나 webClient를 이용
  - 혹은 공공 api 데이터를 참고한 sql 쿼리문
- [x] mybatis query문 작성 및 dao, service, controller 연결
  - `isSuccess`관련해서 항상 db에서 true를 꺼내오거나 중간에 변경하는 것이 탐탁지 않아서 내부와 외부 dto를 나눔
- [x] exception handling(AOP 이용)
- [x] client가 요청하는 parameter validate 로직 추가
  - 2022년 01월에 설립되어 2024년 07월까지 운영된 회사라고 가정하므로 해당 기간 외에는 파라미터 오류안내
  - org도 AAA, BBB, CCC, DDD 외에는 오류 안내
- [x] favicon warning 관련 처리(문제는 없지만 warning 뜨는 게 기분 나쁨)

## 공휴일 테이블 관련 sql문
### holiday table 생성
- `localDate`의 값은 `20241225` 형태로 들어감에 주의
- 기존 테이블의 date관련 형식이 `2412250707`형태로 2024년 12월 25일 7월 7일을 나타낸다. 
- 따라서 mybatis query 작성시 테이블 join 및 값 비교시 문제를 일으킬 수 있음에 주의하고 작성해야 한다. 
```sql
  CREATE TABLE statistic9.holiday(
    localDate varchar(10) NOT NULL PRIMARY KEY,
    description varchar(15)
  );
```
### holiday data insert
- 한국천문연구원_특일 정보 공공 api : https://www.data.go.kr/data/15012690/openapi.do
- 공공 api를 이용해서 데이터를 집어 넣지 못한 경우 이를 이용
- 공공 api에서 주는 데이터를 이용해 만든 쿼리문
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