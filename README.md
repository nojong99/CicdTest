# Spring Boot + React 게시판 애플리케이션

Spring Boot와 React를 사용하여 만든 게시판 애플리케이션입니다.

## 기능

- **사용자 관리**: 회원가입, 로그인, 로그아웃
- **게시글 관리**: 게시글 작성, 조회, 수정, 삭제 (CRUD)
- **게시글 검색**: 제목과 내용으로 게시글 검색
- **페이지네이션**: 게시글 목록 페이지네이션
- **JWT 인증**: 토큰 기반 인증 시스템

## 기술 스택

### Backend
- Spring Boot 3.5.3
- Spring Security
- Spring Data JPA
- H2 Database
- JWT (JSON Web Token)
- Gradle

### Frontend
- React 18
- TypeScript
- React Router
- Axios
- CSS3

## 실행 방법

### 1. 로컬 개발 환경

#### Backend 실행
```bash
./gradlew bootRun
```

#### Frontend 실행
```bash
cd frontend
npm install
npm start
```

### 2. Docker를 사용한 실행

```bash
# 전체 애플리케이션 실행
docker-compose up --build

# 백그라운드 실행
docker-compose up -d --build
```

## API 엔드포인트

### 인증
- `POST /api/auth/signup` - 회원가입
- `POST /api/auth/login` - 로그인
- `GET /api/auth/me` - 현재 사용자 정보

### 게시글
- `GET /api/posts` - 게시글 목록 조회
- `GET /api/posts/search` - 게시글 검색
- `GET /api/posts/{id}` - 게시글 상세 조회
- `POST /api/posts` - 게시글 작성
- `PUT /api/posts/{id}` - 게시글 수정
- `DELETE /api/posts/{id}` - 게시글 삭제

## 데이터베이스

- H2 인메모리 데이터베이스 사용
- H2 콘솔: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

## 접속 정보

- Frontend: http://localhost:3000
- Backend API: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console

## 프로젝트 구조

```
project/
├── src/main/java/com/example/demo/
│   ├── config/          # 설정 클래스
│   ├── controller/      # REST API 컨트롤러
│   ├── dto/            # 데이터 전송 객체
│   ├── entity/         # JPA 엔티티
│   ├── repository/     # 데이터 접근 계층
│   ├── security/       # 보안 관련 클래스
│   └── service/        # 비즈니스 로직
├── frontend/
│   ├── src/
│   │   ├── components/ # React 컴포넌트
│   │   ├── contexts/   # React Context
│   │   └── pages/      # 페이지 컴포넌트
│   └── public/         # 정적 파일
├── Dockerfile          # Backend Docker 설정
├── docker-compose.yml  # Docker Compose 설정
└── build.gradle        # Gradle 설정
```

## 개발 환경 설정

1. Java 17 이상 설치
2. Node.js 18 이상 설치
3. Docker (선택사항)

## 배포

### GitHub Actions CI/CD

이 프로젝트는 GitHub Actions를 통한 CI/CD 파이프라인이 설정되어 있습니다.

1. GitHub 저장소에 코드 푸시
2. 자동으로 테스트 및 빌드 실행
3. Docker 이미지 생성 및 배포

## 라이선스

MIT License 