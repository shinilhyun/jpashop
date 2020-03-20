# 커머스 API Project

### Spring Data JPA, Querydsl을 활용하여 커머스 API를 만들어보자

---

#### 최대한 객체지향적으로 생각하여 짜보자
- 객체의 역할, 책임, 협력을 생각하여 설계
- 트랜젝션 스크립트 방식이 아닌 도메인 개발 모델 활용
- 변경에 유연하게 대처할 수 있도록 설계

#### 테스트 주고 개발 진행

- TDD 적극 활용하자 (Junit5 사용)
- 통합 테스트 뿐 아니라, Slice Test, POJO Test 가능하면 진행

#### 쉽게 짜보자

- 클린코드를 지향하여 짜보자
- 들여쓰기 2개이상 금지
- 한 메서드에서는 한가지 일만, 한 클래스에서는 한가지 책임만!!
- 직관적이고 누구나 이해할 수 있는 매서드, 변수, 클랙스명으로 주석을 대체

#### 시스템이 커질 것을 염두해두고 설계

- 패키지의 의존 사이클이 생기지 않도록 구현해보자


---

Spring Data JPA

Spring Security(+ JWT)

Querydsl

