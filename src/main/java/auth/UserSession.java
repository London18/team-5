package auth;

import db.Nurse;
import db.Role;

public class UserSession {

    private Nurse nurse;
    private long creationTime;

    private static final int EXPIRE_TIME = 7 * 24 * 60 * 60;

    public UserSession(Nurse nurse) {
        this.nurse = nurse;
        this.creationTime = System.currentTimeMillis();
    }

    public String getName() {
        return nurse.getUsername();
    }

    public String getPayrollId() {
        return nurse.getPayrollId();
    }

    public String getEmail() {
        return nurse.getEmail();
    }

    public Role getRole() {
        return nurse.getRole();
    }

    public long getCreationTime() {
        return creationTime;
    }

    public boolean hasExpired() {
        return System.currentTimeMillis() - creationTime > EXPIRE_TIME * 1000L;
    }
}
