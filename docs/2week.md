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
- 응답은 아래의 형식을 취한다. 
  - `isSuccess`, `yearMonth`, `requestLog`는 필수적으로 응답에 들어가고, API에 따라 추가적인 게 있다.
```json
{
  "isSuccess": true,
  "yearMonth": "202008",
  "requestLog": "L",
  ...
}
```

## API 목록
- 년월별 사용자 접속자 수(로그인 수)
- 년월별 부서별 사용자 접속자 수(로그인 수)
- 년월별 평균 하루 로그인 수
- 년월별 휴일을 제외한 로그인 수
- 년월별 게시글 작성 수
- 년월별 부서별 게시글 작성 수

### 년월별 사용자 접속자 수(로그인 수)
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
  "yearMonth": "202008",
  "totCnt": 3,
  "requestLog": "L"
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
  "yearMonth": "202008",
  "totCnt": 3,
  "requestLog": "L",
  "team": "A"
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
{
  "isSuccess": true,
  "yearMonth": "202008",
  "average": 1.2,
  "requestLog": "L"
}
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
{
  "isSuccess": true,
  "yearMonth": "202008",
  "average": 2,
  "requestLog": "L"
}
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
{
  "isSuccess": true,
  "yearMonth": "202008",
  "average": 2,
  "requestLog": "WB"
}
```

### 년월별 부서별 게시글 작성 수
#### 요청url
`rest/posts/{org}/{yearMonth}`
#### Request Parameters
|   Name    |  Type  | Description | Mandatory |  Note  |
|:---------:|:------:|:-----------:|:---------:|:------:|
| yearMonth |string|             |     O     | 202008 |
|    org    |string|     조직명     |     O     |   A    |
#### Response Body
```json
{
  "isSuccess": true,
  "yearMonth": "202008",
  "average": 2,
  "requestLog": "WB",
  "team": "A"
}
```