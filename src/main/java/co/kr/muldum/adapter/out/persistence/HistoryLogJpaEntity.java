package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.LogStatus;
import co.kr.muldum.domain.model.LogType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "history_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HistoryLogJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LogType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LogStatus status;

    @Column(name = "logged_by", nullable = false)
    private Long loggedBy;

    @Column(name = "logged_at", nullable = false)
    private LocalDateTime loggedAt;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    public HistoryLogJpaEntity(LogType type, LogStatus status, Long loggedBy,
                                LocalDateTime loggedAt, String title, String content) {
        this.type = type;
        this.status = status;
        this.loggedBy = loggedBy;
        this.loggedAt = loggedAt;
        this.title = title;
        this.content = content;
    }
}