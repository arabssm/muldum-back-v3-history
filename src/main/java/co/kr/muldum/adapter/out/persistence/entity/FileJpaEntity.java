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
    name = "files",
    indexes = {
        @Index(name = "idx_owner_user", columnList = "owner_user_type, owner_user_id")
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileJpaEntity {

    @Id
    private Long id; // Snowflake ID

    @Column(nullable = false, columnDefinition = "TEXT")
    private String path;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> metadata;

    @Column(name = "owner_user_type", nullable = false, length = 50)
    private String ownerUserType;

    @Column(name = "owner_user_id", nullable = false)
    private Long ownerUserId;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public FileJpaEntity(Long id, String path, Map<String, Object> metadata,
                        String ownerUserType, Long ownerUserId) {
        this.id = id;
        this.path = path;
        this.metadata = metadata;
        this.ownerUserType = ownerUserType;
        this.ownerUserId = ownerUserId;
    }
}
