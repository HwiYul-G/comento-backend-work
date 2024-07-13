## API(Application Programming Interface)
API는 다양한 프로그램들이 서로 커뮤니케이션하기 위해 사용하는 커뮤니케이션 규칙과 서브루틴의 집합이다.
### 동작 방법
API는 사용자로부터 request를 받고 서비스 제공처로 request를 보내서 결과로 나오는 response를 요청한 사용자에게 전달한다.
- client는 APIs URL을 통해서 request를 초기화한다.
- request를 받은 후 API는 서버로 호출을 보낸다.
- 서버는 API에게 정보를 담아서 response를 보낸다.
- API는 데이터를 client에게 보낸다.
### API의 아키텍처

#### REST(Representational State Transfer) - 이걸 사용한다.
- 웹 서비스에서 일부 정보를 가져오거나 제공하는 데 사용된다.
- REST API를 통해 수행되는 모든 통신은 HTTP 요청만 사용한다.
   - 웹 url 형식으로 Get, Post, Put, Delete같은 request를 서버에 보낸다.
   - 서버는 HTML, XML, JSON, Image 등의 자원을 응답으로 보낸다.
#### SOAP(Simple Object Access Protocol)
- 메시지를 전달하기 위해서 XML 포멧을 사용한다.
- HTTP나 SMTP같은 프로토콜 위에서 동작한다.


### API 문서화로 개발자간 커뮤니케이션
1. 업체간의 소통방법
   - 예시: [라인 페이](https://pay.line.me/file/guidebook/technicallinking/LINE_Pay_Technical_Linking_Guide_for_Normal_Merchant_v1.0.1_ko.pdf)
2. 한 가지 기능에 대한 API 가이드 문서
   - 예시: [카카오 API - 검색 키워드 분석](https://file.notion.so/f/f/d05b93ff-ccfc-4cfa-b06c-24e3ff5413c9/8380ec34-0930-4617-841a-160c724cc56d/kakaoAPI_____(1).pdf?id=539595d9-ef31-4194-a74e-3ebae64e1534&table=block&spaceId=d05b93ff-ccfc-4cfa-b06c-24e3ff5413c9&expirationTimestamp=1720857600000&signature=acrs0r05wiOuqJAYYoaHEGru7rC0uaPAHotiZlE_roU&downloadName=kakaoAPI_%EC%B0%B8%EA%B3%A0%EB%AC%B8%EC%84%9C_%EA%B4%80%EC%8B%AC+%ED%82%A4%EC%9B%8C%EB%93%9C+%EB%B6%84%EC%84%9D+%281%29.pdf)
3. 한 가지 주제에 대한 API 가이드 문서
   - 예시: [카카오 API 방문자 데이터셋](https://file.notion.so/f/f/d05b93ff-ccfc-4cfa-b06c-24e3ff5413c9/2aa0bc9c-f5cd-412a-b04b-3d28231b12a9/kakaoAPI____API_(1).pdf?id=f8d60ea1-0e44-4090-ae8a-71a812573378&table=block&spaceId=d05b93ff-ccfc-4cfa-b06c-24e3ff5413c9&expirationTimestamp=1720864800000&signature=67wX8ISdNxZidpeAJRELWKXZtlQ7ZO3SEpY7_Rmv0NQ&downloadName=kakaoAPI_%EC%B0%B8%EA%B3%A0%EB%AC%B8%EC%84%9C_%EB%B0%A9%EB%AC%B8%EC%9E%90+%EB%8D%B0%EC%9D%B4%ED%84%B0+API+%281%29.pdf)

### 참고자료
- https://www.geeksforgeeks.org/what-is-an-api/
- https://www.geeksforgeeks.org/basics-of-soap-simple-object-access-protocol/
- https://curity.io/resources/learn/api-security-best-practices/

## HTTPS = HTTP + SSL/TLS
### HTTP(HyperText Transfer Protocol)
- HTTP는 웹 브라우저와 웹 서버가 커뮤니케이션하는 표준을 제공한다.
- HTTP는 컴퓨터 사이의 데이터를 전달하는 규칙의 집합이다.
- HTTP는 IP기반의 커뮤니케이션 프로토콜이다.

### HTTPS(Hyper Text Transfer Protocol Secure)
- 암호화된 통신을 제공하고, 구성된 웹 서버의 식별가능한 proof를 보호한다.
- SSL(Secure Socket Layer)이 자격증명을 진행한다.
- HTTP headers와 request/response data를 포맣매서 모든 메시지 내용물을 암호화 한다. 
- HTTPS의 검증은 서버측의 디지털 자격증명을 서명할 신뢰할만한 3자가 필요하다.

### 참고자료
- https://www.geeksforgeeks.org/difference-between-http-and-https/