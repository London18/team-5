package auth;

public class AuthenticationResult {

    private AuthenticationResultState state;
    private String username;
    private int payRollId;

    public AuthenticationResult(AuthenticationResultState state, String username, int payRollId) {
        this.state = state;
        this.username = username;
        this.payRollId = payRollId;
    }

    public AuthenticationResultState getState() {
        return state;
    }

    public int getPayRollId() {
        return payRollId;
    }

    public String getUsername() {
        return username;
    }
}
