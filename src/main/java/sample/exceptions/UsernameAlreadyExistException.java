package sample.exceptions;

public class UsernameAlreadyExistException extends Exception {

    private String username;

    public UsernameAlreadyExistException(String username) {
        super(String.format("An account with the username %s already exists!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}