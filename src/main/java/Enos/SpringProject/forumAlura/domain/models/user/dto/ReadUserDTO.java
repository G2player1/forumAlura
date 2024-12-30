package Enos.SpringProject.forumAlura.domain.models.user.dto;

import Enos.SpringProject.forumAlura.domain.models.user.User;

public record ReadUserDTO(
        Long id,
        String username,
        String email
) {
    public ReadUserDTO(User user){
        this(user.getId(), user.getUsername(), user.getEmail());
    }
}
