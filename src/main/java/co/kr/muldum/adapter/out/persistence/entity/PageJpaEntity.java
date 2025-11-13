package co.kr.muldum.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pages")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private TeamJpaEntity team;

    @Column(columnDefinition = "TEXT")
    private String banner;

    @Column(columnDefinition = "TEXT")
    private String icon;

    public PageJpaEntity(String content, TeamJpaEntity team, String banner, String icon) {
        this.content = content;
        this.team = team;
        this.banner = banner;
        this.icon = icon;
    }
}
