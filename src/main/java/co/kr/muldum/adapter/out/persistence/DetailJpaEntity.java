package co.kr.muldum.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "details")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long detailId;

    @Column(columnDefinition = "TEXT")
    private String background;

    @Column(columnDefinition = "TEXT")
    private String features;

    @Column(columnDefinition = "TEXT")
    private String research;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "history_id")
    private HistoryJpaEntity history;

    @OneToMany(mappedBy = "detail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ContributorJpaEntity> contributors = new ArrayList<>();

    public DetailJpaEntity(Long detailId, String background, String features, String research, HistoryJpaEntity history) {
        this.detailId = detailId;
        this.background = background;
        this.features = features;
        this.research = research;
        this.history = history;
    }
}