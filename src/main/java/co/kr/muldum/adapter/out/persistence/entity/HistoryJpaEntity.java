package co.kr.muldum.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "histories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HistoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(nullable = false)
    private Integer generation;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "logo_url", columnDefinition = "TEXT")
    private String logoUrl;

    @Column(columnDefinition = "TEXT")
    private String slogan;

    @Column(name = "detail_background", columnDefinition = "TEXT")
    private String detailBackground;

    @Column(name = "detail_features", columnDefinition = "TEXT")
    private String detailFeatures;

    @Column(name = "detail_research", columnDefinition = "TEXT")
    private String detailResearch;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<HistoryAwardJpaEntity> awards = new ArrayList<>();

    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<HistoryContributorJpaEntity> contributors = new ArrayList<>();

    public HistoryJpaEntity(String name, Integer generation, String description,
                           String logoUrl, String slogan, String detailBackground,
                           String detailFeatures, String detailResearch) {
        this.name = name;
        this.generation = generation;
        this.description = description;
        this.logoUrl = logoUrl;
        this.slogan = slogan;
        this.detailBackground = detailBackground;
        this.detailFeatures = detailFeatures;
        this.detailResearch = detailResearch;
    }
}