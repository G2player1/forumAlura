package Enos.SpringProject.forumAlura.domain.services.course;

import Enos.SpringProject.forumAlura.domain.models.course.Course;
import Enos.SpringProject.forumAlura.domain.models.course.dto.ReadCourseDTO;
import Enos.SpringProject.forumAlura.domain.models.course.dto.RegisterCourseDTO;
import Enos.SpringProject.forumAlura.domain.repositories.ICourseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private ICourseRepository courseRepository;

    public ReadCourseDTO registerCourse(@Valid RegisterCourseDTO registerCourseDTO) {
        Course course = new Course(registerCourseDTO);
        course = courseRepository.save(course);
        return new ReadCourseDTO(course);
    }
}
