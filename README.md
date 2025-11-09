# 물듬 (history)

History 서비스는 ARA 프로젝트에서 전공 동아리의 연혁과 로그를 제공하는 전용 백엔드입니다. 
세대별 히스토리와 Super Admin 로그를 조회할 수 있으며, 사용자 기준 조회 시 RabbitMQ를 통해 팀 서비스와 통신해 소속 팀을 확인합니다.

## 제공 API
- `GET /ara/history?generation=` 최신 또는 특정 세대의 히스토리 목록을 조회합니다.
- `GET /ara/history/{team-id}` 팀 ID로 전공 동아리 상세 정보를 조회합니다.
- `GET /ara/history/users/{user-id}` 사용자 ID로 자신의 팀 히스토리를 조회합니다. (RabbitMQ로 팀 서비스에 질의)
- `GET /sup/history/logs?type=` Super Admin이 히스토리 관련 로그를 조회합니다.

## RabbitMQ 통신 구조
| 용도 | Exchange | Queue | Routing Key |
|---|---|---|---|
| 팀 ID 요청 | `team.exchange` | `team.queue` | `team.get` |
| 팀 ID 응답 | `team.exchange` | `team.response.queue` | `team.response` |

- 요청 메시지(`TeamRequestMessage`): `{ "userId": Long, "correlationId": UUID }`
- 응답 메시지(`TeamResponseMessage`): `{ "teamId": Long, "correlationId": UUID }`
- History 서비스는 응답을 기다리는 동안 `correlationId` 기반으로 Future를 관리하며, 5초 내 응답이 없거나 오류가 발생하면 `502 Bad Gateway`를 반환합니다.

## 실행 방법
1. `.env` 파일을 복사해 환경 변수를 채우거나 업데이트합니다. (PostgreSQL, RabbitMQ)
2. 필요한 인프라를 실행합니다.
   ```bash
   docker compose up -d postgres rabbitmq
   ```
3. 애플리케이션을 빌드/실행합니다.
   ```bash
   ./gradlew bootRun
   ```
4. Swagger UI는 `http://localhost:8080/swagger-ui.html` 에서 확인할 수 있습니다.

## 환경 변수
| 이름 | 설명 | 기본값 |
|---|---|---|
| `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD` | PostgreSQL 접속 정보 | `localhost`, `5432`, `muldum_db`, ... |
| `RABBITMQ_HOST`, `RABBITMQ_PORT` | RabbitMQ 브로커 호스트/포트 | `localhost`, `5672` |
| `RABBITMQ_USERNAME`, `RABBITMQ_PASSWORD` | 브로커 인증 정보 | `guest`, `guest` |
| `RABBITMQ_MANAGEMENT_PORT` | 관리 콘솔 포트 | `15672` |

자세한 컨벤션은 `CONVENTION.md`를 참고하세요.
