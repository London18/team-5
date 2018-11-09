package auth;

public class UserSession {

    private String name;
    private int payrollId;
    private long creationTime;

    private static final int EXPIRE_TIME = 7 * 24 * 60 * 60;

    public UserSession(String name, int payrollId) {
        this.name = name;
        this.payrollId = payrollId;
        this.creationTime = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public int getPayrollId() {
        return payrollId;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public boolean hasExpired() {
        return System.currentTimeMillis() - creationTime > EXPIRE_TIME * 1000L;
    }
}