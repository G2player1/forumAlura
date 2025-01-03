package Enos.SpringProject.forumAlura.domain.models.response.dto;

import Enos.SpringProject.forumAlura.domain.models.response.Response;
import Enos.SpringProject.forumAlura.domain.models.topic.dto.ReadTopicDTO;
import Enos.SpringProject.forumAlura.domain.models.user.dto.ReadUserDTO;

import java.time.LocalDate;

public record ReadResponseDTO(
        Long id,
        String message,
        LocalDate date,
        ReadUserDTO user,
        String solution,
        ReadTopicDTO topic
) {
    public ReadResponseDTO(Response response){
        this(response.getId(), response.getMessage(), response.getCreationDate(), new ReadUserDTO(response.getUser()), response.getSolution(), new ReadTopicDTO(response.getTopic()));
    }
}
