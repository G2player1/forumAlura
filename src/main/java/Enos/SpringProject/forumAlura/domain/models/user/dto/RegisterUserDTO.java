package Enos.SpringProject.forumAlura.domain.models.user.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserDTO(
        @NotNull
                @NotBlank
        String username,
        @NotNull
                @NotBlank
                @Email
        String email,
        @NotNull
                @NotBlank
                @JsonAlias("senha")
        String password,
        @NotNull
                @NotBlank
        String role
) {
}
