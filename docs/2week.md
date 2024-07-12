# SW 활용 현환 분석 API 가이드 문서

개정이력

| 버전  |    변경일     | 변경사유 | 변경내역 |
|:---:|:----------:|:----:|:----:|
| 1.0 | 2024-07-13 | 최초작성 | 최초작성 |
||            ||
||            ||

## 데이터 공통사항

### 부서 목록
- EX) A팀, B팀, C팀, D팀
### request log 정보
| request log |   의미   |
|:-----------:|:------:|
|      L      |  로그인   |
|      O      |  로그아웃  |
|     WB      | 게시글 작성 |
|     DB      | 게시글 삭제 |

### Response
응답은 아래와 같은 규칙을 따른다.
- JSON 포맷의 데이터
- 필드명은 소문자로 시작하는 `camel notation`을 사용
- 다음 json object type 으로 응답
```json
{
  "isSuccess": true,
  "total": 1,
  "data": [
    {}, ..., {}
  ]
}
```
|   Name    | Description |    Type    |     Note      |
|:---------:|:-----------:|:----------:|:-------------:|
| isSuccess |    성공 유무    |  boolean   |               |
|   total   |  전체 데이터 수   |   number   |               |
|   data    |   요청 컨텐츠    | json array | total 수 만큼 응답 |

## API 목록
- 년월별 사용자 접속자 수(로그인 수)
- 년월별 부서별 사용자 접속자 수(로그인 수)
- 년월별 평균 하루 로그인 수
- 년월별 휴일을 제외한 로그인 수
- 년월별 게시글 작성 수
- 년월별 부서별 게시글 작성 수

### 년월별 사용자 접속자 수
#### 요청url
`rest/logins/{yearMonth}`

#### Request Parameters
|Name|Type| Description | Mandatory |  Note  |
|:--:|:--:|:-----------:|:---------:|:------:|
|yearMonth|string|             |     O     | 202008 |

#### Response Body
```json
{
  "isSuccess": true,
  "total": 1,
  "data": [
    {
      "totCnt": 3,
      "yearMonth": "202008",
      "requestLog": "L"
    }
  ]
}
```

### 년월별 부서별 사용자 접속자 수
#### 요청url
`rest/logins/{org}/{yearMonth}`

#### Request Parameters
|     Name     |  Type  | Description | Mandatory |  Note  |
|:------------:|:------:|:-----------:|:---------:|:------:|
|  yearMonth   | string |             |     O     | 202008 |
| organization | string |     조직명     |     O     |   A    |

#### Response Body
```json
{
  "isSuccess": true,
  "total": 2,
  "data": [
    {
      "yearMonth": "202008",
      "requestLog": "L",
      "team": "A"
    },
    {
      "yearMonth": "202008",
      "requestLog": "L",
      "team": "A"
    }
  ]
}
```

### 년월별 평균 하루 로그인 수
#### 요청url
`rest/average-login/{yearMonth}`
#### Request Parameters
|     Name     |  Type  | Description | Mandatory |  Note  |
|:------------:|:------:|:-----------:|:---------:|:------:|
|yearMonth|string|             |     O     | 202008 |
#### Response Body
```json

```

### 년월별 휴일을 제외한 로그인 수
#### 요청url
`rest/exclude-holiday-login/{yearMonth}`
#### Request Parameters
|     Name     |  Type  | Description | Mandatory |  Note  |
|:------------:|:------:|:-----------:|:---------:|:------:|
|yearMonth|string|             |     O     | 202008 |
#### Response Body
```json

```

### 년월별 게시글 작성 수
#### 요청url
`rest/posts/{yearMonth}`
#### Request Parameters
|     Name     |  Type  | Description | Mandatory |  Note  |
|:------------:|:------:|:-----------:|:---------:|:------:|
|yearMonth|string|             |     O     | 202008 |
#### Response Body
```json

```

### 년월별 부서별 게시글 작성 수
#### 요청url
`rest/posts/{org}/{yearMonth}`
#### Request Parameters
|     Name     |  Type  | Description | Mandatory |  Note  |
|:------------:|:------:|:-----------:|:---------:|:------:|
|yearMonth|string|             |     O     | 202008 |
#### Response Body
```json

```