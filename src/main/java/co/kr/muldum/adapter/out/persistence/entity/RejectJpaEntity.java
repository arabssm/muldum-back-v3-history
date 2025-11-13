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
@Table(
    name = "rejects",
    indexes = {
        @Index(name = "idx_reviewer_user", columnList = "reviewer_user_type, reviewer_user_id")
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RejectJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reviewer_user_type", nullable = false, length = 50)
    private String reviewerUserType;

    @Column(name = "reviewer_user_id", nullable = false)
    private Long reviewerUserId;

    @Type(JsonBinaryType.class)
    @Column(name = "reject_data", columnDefinition = "jsonb")
    private Map<String, Object> rejectData;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public RejectJpaEntity(String reviewerUserType, Long reviewerUserId, Map<String, Object> rejectData) {
        this.reviewerUserType = reviewerUserType;
        this.reviewerUserId = reviewerUserId;
        this.rejectData = rejectData;
    }
}
