package Enos.SpringProject.forumAlura.domain.models.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterCourseDTO(
        @NotNull
                @NotBlank
        String name,
        @NotNull
                @NotBlank
        String category
) {
}
