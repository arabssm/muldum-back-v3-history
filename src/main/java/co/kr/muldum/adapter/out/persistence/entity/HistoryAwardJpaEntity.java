package co.kr.muldum.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "history_awards")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HistoryAwardJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "history_id", nullable = false)
    private HistoryJpaEntity history;

    @Column(name = "award_type", nullable = false, columnDefinition = "TEXT")
    private String awardType;

    public HistoryAwardJpaEntity(HistoryJpaEntity history, String awardType) {
        this.history = history;
        this.awardType = awardType;
    }

    public void setHistory(HistoryJpaEntity history) {
        this.history = history;
    }
}