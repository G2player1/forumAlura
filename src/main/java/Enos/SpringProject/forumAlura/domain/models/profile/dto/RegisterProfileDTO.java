package Enos.SpringProject.forumAlura.domain.models.profile.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterProfileDTO(
        @NotNull
                @JsonAlias("usuario_id")
        Long user_id,
        @NotNull
                @NotBlank
                @JsonAlias("nome")
        String name
) {
}
