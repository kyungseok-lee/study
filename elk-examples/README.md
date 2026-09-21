# ELK Stack Example Project

[![GitHub](https://img.shields.io/badge/GitHub-kyungseok--lee%2Fstudy-blue)](https://github.com/kyungseok-lee/study/tree/main/elk-examples)
[![Docker](https://img.shields.io/badge/Docker-Compose-blue)](https://docs.docker.com/compose/)
[![Go](https://img.shields.io/badge/Go-1.21-blue)](https://golang.org/)
[![Next.js](https://img.shields.io/badge/Next.js-14.0.4-black)](https://nextjs.org/)

ELK Stack (Elasticsearch, Logstash, Kibana) + Filebeat를 활용한 로그 수집 및 분석 시스템 예제입니다.

**GitHub**: https://github.com/kyungseok-lee/study/tree/main/elk-examples

## 🏗️ 아키텍처

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Next.js       │    │   Go Fiber      │    │   Logstash      │    │  Elasticsearch  │
│   Client        │◄──►│   Server        │───►│   Pipeline      │───►│   Database      │
│   (Port 3000)   │    │   (Port 8080)   │    │ TCP 5000/Beats 5044│    │   (Port 9200)   │
└─────────────────┘    └─────────────────┘    └─────────────────┘    └─────────────────┘
                                │                      ▲                      │
                                │                      │                      │
                                ▼                      │                      │
                       ┌─────────────────┐             │                      │
                       │    Filebeat     │─────────────┘                      │
                       │   (Log Agent)   │                                    │
                       └─────────────────┘                                    │
                                │                                             │
                                │                                             │
                                ▼                                             │
                       ┌─────────────────┐                                    │
                       │   Log Files     │                                    │
                       │   ./logs/       │                                    │
                       └─────────────────┘                                    │
                                                                              │
                                                                              ▼
                                                                     ┌─────────────────┐
                                                                     │     Kibana      │
                                                                     │   Dashboard     │
                                                                     │   (Port 5601)   │
                                                                     └─────────────────┘
```

## 🚀 기술 스택

### Backend
- **Go 1.21** - 메인 서버 언어
- **Fiber v2.52.0** - 고성능 웹 프레임워크
- **Logrus** - 구조화된 로깅

### Frontend
- **Next.js 14.0.4** - React 프레임워크
- **TypeScript 5.3.3** - 타입 안전성
- **Tailwind CSS** - 스타일링

### ELK Stack + Filebeat
- **Elasticsearch 8.11.0** - 검색 및 분석 엔진
- **Logstash 8.11.0** - 로그 수집 및 변환
- **Kibana 8.11.0** - 데이터 시각화
- **Filebeat 8.11.0** - 로그 파일 수집 에이전트

## 📋 사전 요구사항

- Docker & Docker Compose
- Go 1.21+ (로컬 개발용)
- Node.js 18+ (로컬 개발용)

## 🛠️ 설치 및 실행

### 1. 프로젝트 클론
```bash
git clone https://github.com/kyungseok-lee/study.git
cd study/elk-examples
```

### 2. 원클릭 실행 (권장)
```bash
# 실행 권한 부여
chmod +x scripts/run.sh

# 프로젝트 실행
./scripts/run.sh
```

### 3. Make 명령어 사용
```bash
# 프로젝트 초기 설정
make -f scripts/Makefile setup

# 모든 서비스 시작
make -f scripts/Makefile up

# 서비스 상태 확인
make -f scripts/Makefile status

# 로그 확인
make -f scripts/Makefile logs
```

### 4. Docker Compose 직접 사용
```bash
# 모든 서비스 시작
docker compose up -d

# 로그 확인
docker compose logs -f
```

### 5. 서비스 접속
- **Next.js 클라이언트**: http://localhost:3000
- **Go 서버 API**: http://localhost:8080
- **Kibana 대시보드**: http://localhost:5601
- **Elasticsearch**: http://localhost:9200

## 🔧 로컬 개발

### Go 서버 개발
```bash
cd server
go mod tidy
go run main.go
```

### Next.js 클라이언트 개발
```bash
cd client
npm install
npm run dev
```

## 검증

프로젝트 루트에서 `make -f scripts/Makefile test`는 Go 패키지를 컴파일 검증합니다 (현재 테스트 파일 없음). 클라이언트에는 자동화 테스트 스크립트가 없어 해당 단계는 명시적으로 건너뜁니다.

## 📊 주요 기능

### 1. 로그 생성 및 전송
- Go Fiber 서버에서 다양한 레벨의 로그 생성
- Logstash를 통한 실시간 로그 수집
- Elasticsearch에 구조화된 로그 저장

### 2. 파일 로그 수집 (Filebeat)
- Go 서버의 파일 로그 자동 수집
- 실시간 로그 파일 모니터링
- Docker 컨테이너 로그 수집

### 3. 로그 조회 및 필터링
- Next.js 클라이언트에서 `/logs`의 샘플 응답을 주기적으로 조회
- 샘플 로그의 레벨별 필터링 (info, warn, error, debug)
- 로그 상세 정보 및 추가 필드 표시

### 4. 커스텀 로그 생성
- 웹 인터페이스를 통한 커스텀 로그 생성
- 추가 필드와 함께 구조화된 로그 전송
- 실시간 로그 업데이트

### 5. Kibana 시각화
- Elasticsearch 데이터의 고급 분석
- 대시보드 및 차트 생성
- 로그 패턴 분석 및 모니터링

## 🎯 API 엔드포인트

### Go Server API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | 서버 상태 확인 |
| GET | `/logs` | 매 요청마다 생성하는 샘플 로그 3개 조회 (Elasticsearch 조회 아님) |
| POST | `/logs` | 커스텀 로그 전송 |
| GET | `/generate-logs` | 샘플 로그 10개 생성 |

### 로그 데이터 구조
```json
{
  "timestamp": "2024-01-01T12:00:00Z",
  "level": "info",
  "message": "User logged in successfully",
  "service": "go-server",
  "fields": {
    "user_id": 123,
    "action": "login",
    "ip_address": "192.168.1.1"
  }
}
```

## 🔍 Kibana 설정

### 1. 인덱스 패턴 생성
1. Kibana 접속 (http://localhost:5601)
2. Stack Management → Index Patterns
3. `elk-example-logs-*` 패턴 생성
4. Time field: `timestamp`

### 2. 대시보드 생성
1. Discover에서 로그 데이터 확인
2. Visualize에서 차트 생성
3. Dashboard에 시각화 추가

## 🐛 문제 해결

### Elasticsearch 메모리 부족
```bash
# docker-compose.yml에서 메모리 설정 조정
environment:
  - "ES_JAVA_OPTS=-Xms1g -Xmx1g"
```

### Logstash 연결 실패
```bash
# Logstash 컨테이너 상태 확인
docker compose logs logstash

# 포트 충돌 확인
netstat -tulpn | grep :5044
```

### Next.js 빌드 오류
```bash
# 의존성 재설치
cd client
rm -rf node_modules package-lock.json
npm install
```

## 📈 성능 최적화

### 1. Elasticsearch 튜닝
- 힙 메모리: 최소 1GB, 최대 4GB
- 인덱스 템플릿 설정
- 로그 보존 정책 구성

### 2. Logstash 최적화
- 배치 크기 조정
- 필터 최적화
- 출력 버퍼링 설정

### 3. Go 서버 최적화
- 연결 풀링
- 로그 비동기 전송
- 메트릭 수집

## 🔒 보안 고려사항

- Elasticsearch 보안 설정 (X-Pack)
- 네트워크 격리
- 로그 데이터 암호화
- 접근 권한 관리

## 📚 추가 리소스

- [Elasticsearch 공식 문서](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html)
- [Logstash 공식 문서](https://www.elastic.co/guide/en/logstash/current/index.html)
- [Kibana 공식 문서](https://www.elastic.co/guide/en/kibana/current/index.html)
- [Go Fiber 문서](https://docs.gofiber.io/)
- [Next.js 문서](https://nextjs.org/docs)

## 🤝 기여하기

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 라이선스

이 하위 프로젝트에는 별도 LICENSE 파일이 포함되어 있지 않습니다.
