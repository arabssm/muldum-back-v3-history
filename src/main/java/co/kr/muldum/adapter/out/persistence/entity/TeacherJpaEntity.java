package co.kr.muldum.adapter.out.persistence.entity;

import co.kr.muldum.domain.model.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TEACHER")
public class TeacherJpaEntity extends UserJpaEntity {

    protected TeacherJpaEntity() {
    }

    public TeacherJpaEntity(Long userId, String email, String name) {
        super(userId, email, name, Role.TEACHER);
    }
}
