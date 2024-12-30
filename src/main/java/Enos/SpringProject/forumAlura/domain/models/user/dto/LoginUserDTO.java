package Enos.SpringProject.forumAlura.domain.models.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginUserDTO(
        @NotNull
                @NotBlank
        String username,
        @NotNull
                @NotBlank
        String password
) {
}
