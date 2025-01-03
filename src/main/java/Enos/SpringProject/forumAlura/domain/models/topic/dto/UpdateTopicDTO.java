package Enos.SpringProject.forumAlura.domain.models.topic.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateTopicDTO(
        @NotNull
        Long id,
        String title,
        String message
) {
}
