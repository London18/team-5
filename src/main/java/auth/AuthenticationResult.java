package auth;

public class AuthenticationResult {

    private AuthenticationResultState state;
    private String username;
    private String payRollId;

    public AuthenticationResult(AuthenticationResultState state, String username, String payRollId) {
        this.state = state;
        this.username = username;
        this.payRollId = payRollId;
    }

    public AuthenticationResultState getState() {
        return state;
    }

    public String getPayRollId() {
        return payRollId;
    }

    public String getUsername() {
        return username;
    }
}
