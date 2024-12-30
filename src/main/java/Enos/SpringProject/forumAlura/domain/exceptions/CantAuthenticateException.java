package Enos.SpringProject.forumAlura.domain.exceptions;

public class CantAuthenticateException extends RuntimeException {
    public CantAuthenticateException(String message) {
        super(message);
    }
}
