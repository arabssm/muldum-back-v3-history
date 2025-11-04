package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.HistoryLog;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoryLogMapper {

    public static HistoryLog toDomain(HistoryLogJpaEntity entity) {
        return new HistoryLog(
                entity.getId(),
                entity.getType(),
                entity.getStatus(),
                entity.getLoggedBy(),
                entity.getLoggedAt(),
                entity.getTitle(),
                entity.getContent()
        );
    }
}