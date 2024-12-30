package Enos.SpringProject.forumAlura.domain.models.course;

import Enos.SpringProject.forumAlura.domain.exceptions.CantGetEnumException;

public enum Category {
    MANAGEMENT("administracao"),
    TECHNOLOGY("technologia"),
    HEALTH("saude"),
    HUMANITY("humanas"),
    ENGINEERING("engenharia");

    private String category;

    Category(String category){
        this.category = category;
    }

    public static Category fromString(String value){
        for (Category category : Category.values()){
            if (category.category.equalsIgnoreCase(value)){
                return category;
            }
        }
        throw new CantGetEnumException("Invalid Category");
    }
}
