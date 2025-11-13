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
    name = "tokens",
    indexes = {
        @Index(name = "idx_user", columnList = "user_type, user_id"),
        @Index(name = "idx_expires_at", columnList = "expires_at")
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenJpaEntity {

    @Id
    private Long id; // Snowflake ID

    @Column(name = "user_type", nullable = false, length = 50)
    private String userType;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Type(JsonBinaryType.class)
    @Column(name = "token_data", nullable = false, columnDefinition = "jsonb")
    private Map<String, Object> tokenData;

    @Column(name = "expires_at", nullable = false)
    private OffsetDateTime expiresAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public TokenJpaEntity(Long id, String userType, Long userId, Map<String, Object> tokenData,
                         OffsetDateTime expiresAt) {
        this.id = id;
        this.userType = userType;
        this.userId = userId;
        this.tokenData = tokenData;
        this.expiresAt = expiresAt;
    }
}
