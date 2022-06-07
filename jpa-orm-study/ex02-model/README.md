# ex02 엔티티 매핑 & 연관관계 매핑 기초
## 개념
- 객체와 테이블 연관관계의 차이를 이해
    - 객체를 테이블에 맞추어 데이터 중심으로 모델링하면, 협력 관계를 만들 수 없다.
    - 객체는 참조를 사용해 연관된 객체를 찾는다.
- 객체의 참조와 테이블의 외래 키를 매핑
- 용어 이해
    - 방향(Direction): 단방향, 양방향
    - 다중성(Multiplicity): 다대일(N:1), 일대다(1:N), 일대일(1:1), 다대다(N:M) 이해
    - 연관관계의 주인(Owner): 객체 양방향 연관관계는 관리 주인 이 필요

## 시나리오
- 회원과 팀이 있다.
- 회원은 하나의 팀에만 소속될 수 있다.
- 회원과 팀은 다대일 관계다.

## 테이블에 맞추어 모델링
```text
member
    id (PK)
    team_id (FK)
    username

team
    id (PK)
    name
```

## 객체에 맞추어 모델링
```text
Member
    long id
    Team team - @ManyToOne @JoinColumn(name = "team_id")
    String username

Team
    long id
    String name
    List<Member> members - @OneToMany(mappedBy = "team") // Member.team - mappedBy 읽기만 가능
```

## 연관관계의 주인 (Owner)
- 양방향 매핑 규칙
    - 객체의 두 관계중 하나를 연관관계의 주인으로 지정
    - 연관관계의 주인만이 외래 키를 관리 (등록, 수정)
    - 주인이 아닌 쪽은 읽기만 가능
    - 주인은 mappedBy 속성을 사용하지 않음
    - 주인이 아니면 mappedBy 속성으로 주인을 지정해 준다.
- 누구를 주인으로 ?
    - 외래 키가 있는 곳을 주인으로 지정한다.
    - 예제에서는 Member.team이 연관관계의 주인이다.

## 양방향 매핑 시 주의점
- 예제에서 Team, Member 양쪽에 모두 값을 주입해야하는데 하나에만 주입하는 경우
- 순수 객체 상태를 고려하여 항상 양쪽에 값을 설정한다.
- 연관관계 편의 메소드를 생성한다. 예제에서는 changeMember
- 양방향 매핑 시에 무한 루프를 조심하자.
    - toString(), lombok, JSON 생성 라이브러리
  
## 양방향 매핑 정리
- 단방향 매핑만으로도 이미 연관관계 매핑은 완료
- 양방향 매핑은 반대 방향으로 조회(객체 그래프 탐색) 기능이 추가된 것 뿐이다.
- JPQL에서 역방향으로 탐색할 일이 많다.
- 단방향 매핑을 잘 하고 양방향 매핑은 필요할 때만 추가한다. (테이블에 양향을 주지 않기 때문)
    