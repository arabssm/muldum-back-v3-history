package co.kr.muldum.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(
    name = "members",
    indexes = {
        @Index(name = "idx_team_student", columnList = "team_id, student_id", unique = true),
        @Index(name = "idx_team_id", columnList = "team_id"),
        @Index(name = "idx_student_id", columnList = "student_id")
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private TeamJpaEntity team;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String role;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public MemberJpaEntity(TeamJpaEntity team, Long studentId, String role) {
        this.team = team;
        this.studentId = studentId;
        this.role = role;
    }
}
