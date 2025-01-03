package Enos.SpringProject.forumAlura.domain.models.course.dto;

import Enos.SpringProject.forumAlura.domain.models.course.Category;
import Enos.SpringProject.forumAlura.domain.models.course.Course;
import Enos.SpringProject.forumAlura.domain.models.topic.dto.ReadTopicDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public record ReadDetailedCourseDTO(
        Long id,
        String name,
        Category category,
        Page<ReadTopicDTO> topics
) {
    public ReadDetailedCourseDTO(Course course){
        this(course.getId(), course.getName(), course.getCategory(), new PageImpl<>(course.getTopicList()).map(ReadTopicDTO::new));
    }
}
