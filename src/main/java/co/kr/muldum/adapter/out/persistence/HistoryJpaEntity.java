package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.ClubType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HistoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer generation;

    @Enumerated(EnumType.STRING)
    @Column(name = "club_type", nullable = false)
    private ClubType clubType;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(columnDefinition = "TEXT")
    private String slogan;

    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AwardJpaEntity> awards = new ArrayList<>();

    @OneToOne(mappedBy = "history", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DetailJpaEntity detail;

    public HistoryJpaEntity(Long id, String name, Integer generation, ClubType clubType,
                             String description, String logoUrl, String slogan) {
        this.id = id;
        this.name = name;
        this.generation = generation;
        this.clubType = clubType;
        this.description = description;
        this.logoUrl = logoUrl;
        this.slogan = slogan;
    }
}