package Enos.SpringProject.forumAlura.domain.models.topic.dto;

import Enos.SpringProject.forumAlura.domain.models.course.dto.ReadCourseDTO;
import Enos.SpringProject.forumAlura.domain.models.response.Response;
import Enos.SpringProject.forumAlura.domain.models.response.dto.ReadResponseDTO;
import Enos.SpringProject.forumAlura.domain.models.topic.Topic;
import Enos.SpringProject.forumAlura.domain.models.topic.TopicStatus;
import Enos.SpringProject.forumAlura.domain.models.user.dto.ReadUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;

public record ReadDetailedTopicDTO(
        Long id,
        String title,
        String message,
        LocalDate date,
        TopicStatus status,
        ReadCourseDTO course,
        ReadUserDTO user,
        Page<ReadResponseDTO> responses
) {
    public ReadDetailedTopicDTO(Topic topic){
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus(), new ReadCourseDTO(topic.getCourse()), new ReadUserDTO(topic.getUser()), new PageImpl<Response>(topic.getResponseList()).map(ReadResponseDTO::new));
    }
}
