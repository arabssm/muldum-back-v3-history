package co.kr.muldum.adapter.out.persistence.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.Map;

@Entity
@Table(
    name = "item_requests",
    indexes = {
        @Index(name = "idx_requester_user", columnList = "requester_user_type, requester_user_id"),
        @Index(name = "idx_team_id", columnList = "team_id"),
        @Index(name = "idx_status", columnList = "status")
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemRequestJpaEntity {

    @Id
    private Long id; // Snowflake ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private TeamJpaEntity team;

    @Column(name = "requester_user_type", nullable = false, length = 50)
    private String requesterUserType;

    @Column(name = "requester_user_id", nullable = false)
    private Long requesterUserId;

    @Type(JsonBinaryType.class)
    @Column(name = "product_info", nullable = false, columnDefinition = "jsonb")
    private Map<String, Object> productInfo;

    @Column(name = "item_source_key", length = 20)
    private String itemSourceKey;

    @Column(length = 20)
    private String status = "PENDING";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reject_id")
    private RejectJpaEntity reject;

    @Type(JsonBinaryType.class)
    @Column(name = "request_details", columnDefinition = "jsonb")
    private Map<String, Object> requestDetails;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public ItemRequestJpaEntity(Long id, TeamJpaEntity team, String requesterUserType, Long requesterUserId,
                               Map<String, Object> productInfo, String itemSourceKey, String status,
                               RejectJpaEntity reject, Map<String, Object> requestDetails) {
        this.id = id;
        this.team = team;
        this.requesterUserType = requesterUserType;
        this.requesterUserId = requesterUserId;
        this.productInfo = productInfo;
        this.itemSourceKey = itemSourceKey;
        this.status = status;
        this.reject = reject;
        this.requestDetails = requestDetails;
    }
}
