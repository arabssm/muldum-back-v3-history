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
@Table(name = "file_books")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileBookJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "notice_id")
    private Long noticeId;

    @Type(JsonBinaryType.class)
    @Column(name = "book_config", columnDefinition = "jsonb")
    private Map<String, Object> bookConfig;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public FileBookJpaEntity(Long fileId, Long noticeId, Map<String, Object> bookConfig) {
        this.fileId = fileId;
        this.noticeId = noticeId;
        this.bookConfig = bookConfig;
    }
}
