package co.kr.muldum.adapter.out.persistence;

import co.kr.muldum.domain.model.Student;
import co.kr.muldum.domain.model.Teacher;
import co.kr.muldum.domain.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toDomain(UserJpaEntity entity) {
        if (entity instanceof StudentJpaEntity studentEntity) {
            return Student.of(
                    studentEntity.getUserId(),
                    studentEntity.getEmail(),
                    studentEntity.getName(),
                    studentEntity.getTeamId()
            );
        } else if (entity instanceof TeacherJpaEntity teacherEntity) {
            return Teacher.of(
                    teacherEntity.getUserId(),
                    teacherEntity.getEmail(),
                    teacherEntity.getName()
            );
        }

        throw new IllegalArgumentException("Unknown entity type: " + entity.getClass().getName());
    }

    public static UserJpaEntity toEntity(User user) {
        if (user instanceof Student student) {
            return new StudentJpaEntity(
                    student.getUserId(),
                    student.getEmail(),
                    student.getName(),
                    student.getTeamId()
            );
        } else if (user instanceof Teacher teacher) {
            return new TeacherJpaEntity(
                    teacher.getUserId(),
                    teacher.getEmail(),
                    teacher.getName()
            );
        }

        throw new IllegalArgumentException("Unknown user type: " + user.getClass().getName());
    }
}