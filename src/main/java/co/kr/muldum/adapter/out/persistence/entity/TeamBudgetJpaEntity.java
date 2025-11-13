package co.kr.muldum.adapter.out.persistence.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.Map;

@Entity
@Table(name = "team_budgets")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamBudgetJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_id", nullable = false, unique = true)
    private Long teamId;

    @Type(JsonBinaryType.class)
    @Column(name = "budget_info", nullable = false, columnDefinition = "jsonb")
    private Map<String, Object> budgetInfo;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public TeamBudgetJpaEntity(Long teamId, Map<String, Object> budgetInfo) {
        this.teamId = teamId;
        this.budgetInfo = budgetInfo;
    }
}
