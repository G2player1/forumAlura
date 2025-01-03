package Enos.SpringProject.forumAlura.domain.models.course.dto;

import Enos.SpringProject.forumAlura.domain.models.course.Category;
import Enos.SpringProject.forumAlura.domain.models.course.Course;

public record ReadCourseDTO(
        Long id,
        String name,
        Category category
) {
    public ReadCourseDTO(Course course){
        this(course.getId(), course.getName(), course.getCategory());
    }
}
