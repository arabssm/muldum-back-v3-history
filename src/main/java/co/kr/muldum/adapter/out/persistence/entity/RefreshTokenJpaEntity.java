package co.kr.muldum.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "refresh_tokens")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshTokenJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, columnDefinition = "TEXT")
    private String email;

    @Column(name = "refresh_token", columnDefinition = "TEXT")
    private String refreshToken;

    public RefreshTokenJpaEntity(String email, String refreshToken) {
        this.email = email;
        this.refreshToken = refreshToken;
    }
}
