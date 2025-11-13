package co.kr.muldum.adapter.out.persistence.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.time.OffsetDateTime;
import java.util.Map;

@Entity
@Table(
    name = "team_budget_histories",
    indexes = {
        @Index(name = "idx_changed_by_user", columnList = "changed_by_user_type, changed_by_user_id"),
        @Index(name = "idx_team_id", columnList = "team_id")
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamBudgetHistoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_id", nullable = false)
    private Long teamId;

    @Column(name = "changed_by_user_type", nullable = false, length = 50)
    private String changedByUserType;

    @Column(name = "changed_by_user_id", nullable = false)
    private Long changedByUserId;

    @Type(JsonBinaryType.class)
    @Column(name = "change_data", nullable = false, columnDefinition = "jsonb")
    private Map<String, Object> changeData;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    public TeamBudgetHistoryJpaEntity(Long teamId, String changedByUserType, Long changedByUserId,
                                     Map<String, Object> changeData) {
        this.teamId = teamId;
        this.changedByUserType = changedByUserType;
        this.changedByUserId = changedByUserId;
        this.changeData = changeData;
    }
}
