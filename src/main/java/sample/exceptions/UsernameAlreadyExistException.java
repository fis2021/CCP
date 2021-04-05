package sample.exceptions;

public class UsernameAlreadyExistException extends Exception {

    private String username;

    public UsernameAlreadyExistException(String username) {
        super(String.format(username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}