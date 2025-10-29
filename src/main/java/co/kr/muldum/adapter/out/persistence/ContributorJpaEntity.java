package co.kr.muldum.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contributors")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContributorJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contributor_id")
    private Long contributorId;

    @Column(nullable = false)
    private String name;

    @Column(name = "github_url")
    private String githubUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_id", nullable = false)
    private DetailJpaEntity detail;

    public ContributorJpaEntity(Long contributorId, String name, String githubUrl, DetailJpaEntity detail) {
        this.contributorId = contributorId;
        this.name = name;
        this.githubUrl = githubUrl;
        this.detail = detail;
    }
}