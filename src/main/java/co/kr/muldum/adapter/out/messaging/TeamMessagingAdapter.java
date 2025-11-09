package co.kr.muldum.adapter.out.messaging;

import co.kr.muldum.adapter.out.messaging.dto.TeamRequestMessage;
import co.kr.muldum.adapter.out.messaging.dto.TeamResponseMessage;
import co.kr.muldum.application.port.out.TeamQueryPort;
import co.kr.muldum.domain.exception.TeamServiceCommunicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * RabbitMQ를 통해 팀 서비스에 사용자 정보를 질의하는 어댑터
 */
@Service
public class TeamMessagingAdapter implements TeamQueryPort {

    private static final Logger log = LoggerFactory.getLogger(TeamMessagingAdapter.class);

    private final RabbitTemplate rabbitTemplate;
    private final ConcurrentHashMap<String, CompletableFuture<Long>> pendingRequests = new ConcurrentHashMap<>();

    public TeamMessagingAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Long getTeamIdByUserId(Long userId) {
        String correlationId = UUID.randomUUID().toString();
        CompletableFuture<Long> future = new CompletableFuture<>();
        pendingRequests.put(correlationId, future);

        try {
            TeamRequestMessage request = new TeamRequestMessage(userId, correlationId);
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.TEAM_EXCHANGE,
                    RabbitMQConfig.TEAM_ROUTING_KEY,
                    request
            );

            return future.get(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new TeamServiceCommunicationException("팀 서비스에 사용자 정보를 요청하는 중 오류가 발생했습니다.", e);
        } finally {
            pendingRequests.remove(correlationId);
        }
    }

    @RabbitListener(queues = RabbitMQConfig.TEAM_RESPONSE_QUEUE)
    public void handleTeamResponse(TeamResponseMessage response) {
        CompletableFuture<Long> future = pendingRequests.remove(response.getCorrelationId());
        if (future != null) {
            future.complete(response.getTeamId());
        } else {
            log.warn("[RabbitMQ] 응답을 처리할 Future를 찾을 수 없습니다. correlationId={}", response.getCorrelationId());
        }
    }
}
