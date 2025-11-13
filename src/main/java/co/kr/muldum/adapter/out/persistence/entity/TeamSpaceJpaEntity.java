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
@Table(name = "team_spaces")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamSpaceJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_id", nullable = false)
    private Long teamId;

    @Column(name = "filebook_id")
    private Long filebookId;

    @Type(JsonBinaryType.class)
    @Column(name = "space_config", columnDefinition = "jsonb")
    private Map<String, Object> spaceConfig;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public TeamSpaceJpaEntity(Long teamId, Long filebookId, Map<String, Object> spaceConfig) {
        this.teamId = teamId;
        this.filebookId = filebookId;
        this.spaceConfig = spaceConfig;
    }
}
