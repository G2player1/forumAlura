package Enos.SpringProject.forumAlura.domain.exceptions;

public class CreationTokenException extends RuntimeException {

    private final String message;

    public CreationTokenException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
