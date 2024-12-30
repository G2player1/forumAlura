package Enos.SpringProject.forumAlura.domain.models.user.dto;

import Enos.SpringProject.forumAlura.domain.models.user.User;
import Enos.SpringProject.forumAlura.domain.models.user.UserRoles;

public record ReadDetailedUserDTO(
        Long id,
        String username,
        String email,
        String password,
        UserRoles role
) {
    public ReadDetailedUserDTO(User user){
        this(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
