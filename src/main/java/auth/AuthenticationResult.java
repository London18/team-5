package auth;

import db.Nurse;

public class AuthenticationResult {

    private AuthenticationResultState state;
    private Nurse nurse;

    public AuthenticationResult(AuthenticationResultState state, Nurse nurse) {
        this.state = state;
        this.nurse = nurse;
    }

    public AuthenticationResultState getState() {
        return state;
    }

    public Nurse getNurse() {
        return nurse;
    }
}
