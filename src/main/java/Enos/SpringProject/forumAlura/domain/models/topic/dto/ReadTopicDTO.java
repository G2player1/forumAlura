package Enos.SpringProject.forumAlura.domain.models.topic.dto;

import Enos.SpringProject.forumAlura.domain.models.course.dto.ReadCourseDTO;
import Enos.SpringProject.forumAlura.domain.models.topic.Topic;
import Enos.SpringProject.forumAlura.domain.models.topic.TopicStatus;
import Enos.SpringProject.forumAlura.domain.models.user.dto.ReadUserDTO;

import java.time.LocalDate;

public record ReadTopicDTO(
        Long id,
        String title,
        String message,
        LocalDate date,
        TopicStatus status,
        ReadCourseDTO course,
        ReadUserDTO user
) {
    public ReadTopicDTO(Topic topic){
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus(), new ReadCourseDTO(topic.getCourse()), new ReadUserDTO(topic.getUser()));
    }
}
