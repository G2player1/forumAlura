package Enos.SpringProject.forumAlura.domain.repositories;

import Enos.SpringProject.forumAlura.domain.models.course.Course;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<Course,Long> {
    Course findByIdAndActive(@NotNull Long id, Integer active);
}
