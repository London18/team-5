package db;

public class SessionNurse {

    private Nurse nurse;
    private boolean exited;
    private boolean homeSafe;
    private String comment;

    public SessionNurse(Nurse nurse, boolean exited, boolean homeSafe, String comment) {
        this.nurse = nurse;
        this.exited = exited;
        this.homeSafe = homeSafe;
        this.comment = comment;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public boolean hasExited() {
        return exited;
    }

    public boolean isHomeSafe() {
        return homeSafe;
    }

    public String getPayrollId() {
        return nurse.getPayrollId();
    }

    public String getName() {
        return nurse.getUsername();
    }

    public String getComment() {
        return comment;
    }
}
