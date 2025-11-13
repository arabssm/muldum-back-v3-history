package co.kr.muldum.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "history_contributors")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HistoryContributorJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "history_id", nullable = false)
    private HistoryJpaEntity history;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "github_url", columnDefinition = "TEXT")
    private String githubUrl;

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    public HistoryContributorJpaEntity(HistoryJpaEntity history, String name, String githubUrl, Integer sortOrder) {
        this.history = history;
        this.name = name;
        this.githubUrl = githubUrl;
        this.sortOrder = sortOrder;
    }

    public void setHistory(HistoryJpaEntity history) {
        this.history = history;
    }
}