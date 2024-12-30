package Enos.SpringProject.forumAlura.domain.models.topic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterTopicDTO(
        @NotNull
        @NotBlank
        String title,
        @NotNull
        @NotBlank
        String message,
        @NotNull
        LocalDate date,
        @NotNull
        Long user_id,
        @NotNull
        Long course_id
) {
}
