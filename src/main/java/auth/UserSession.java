package auth;

public class UserSession {

    private String name;
    private long creationTime;

    private static final int EXPIRE_TIME = 7 * 24 * 60 * 60;

    public String getName() {
        return name;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public boolean hasExpired() {
        return System.currentTimeMillis() - creationTime > EXPIRE_TIME * 1000L;
    }
}
