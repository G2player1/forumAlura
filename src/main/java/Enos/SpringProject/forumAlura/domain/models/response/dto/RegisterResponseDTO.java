package Enos.SpringProject.forumAlura.domain.models.response.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterResponseDTO(
        @NotNull
        @NotBlank
        String message,
        @NotNull
        Long topic_id,
        @NotNull
        LocalDate date,
        @NotNull
        Long user_id,
        @NotNull
        @NotBlank
        String solution
) {
}
