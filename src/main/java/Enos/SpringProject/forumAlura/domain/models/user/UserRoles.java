package Enos.SpringProject.forumAlura.domain.models.user;

import Enos.SpringProject.forumAlura.domain.exceptions.CantGetEnumException;

public enum UserRoles {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRoles(String role){
        this.role = role;
    }

    public static UserRoles fromString(String value){
        for (UserRoles userRole : UserRoles.values()){
            if (userRole.role.equalsIgnoreCase(value)){
                return userRole;
            }
        }
        throw new CantGetEnumException("Role not found");
    }
}
