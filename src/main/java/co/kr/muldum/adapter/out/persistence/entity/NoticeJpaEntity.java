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
    name = "notices",
    indexes = {
        @Index(name = "idx_author_user_id", columnList = "author_user_id")
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeJpaEntity {

    @Id
    private Long id; // Snowflake ID

    @Column(name = "author_user_id", nullable = false)
    private Long authorUserId;

    @Column(nullable = false, length = 100)
    private String title;

    @Type(JsonBinaryType.class)
    @Column(name = "content_data", columnDefinition = "jsonb")
    private Map<String, Object> contentData;

    @Type(JsonBinaryType.class)
    @Column(name = "notification_config", columnDefinition = "jsonb")
    private Map<String, Object> notificationConfig;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Column(name = "deadline_date")
    private OffsetDateTime deadlineDate;

    public NoticeJpaEntity(Long id, Long authorUserId, String title,
                          Map<String, Object> contentData, Map<String, Object> notificationConfig,
                          OffsetDateTime deadlineDate) {
        this.id = id;
        this.authorUserId = authorUserId;
        this.title = title;
        this.contentData = contentData;
        this.notificationConfig = notificationConfig;
        this.deadlineDate = deadlineDate;
    }
}
