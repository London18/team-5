package db;

import java.sql.Date;

public class HomeInformation {

    private boolean home;
    private Date lastSubmission;

    public HomeInformation(boolean home, Date lastSubmission) {
        this.home = home;
        this.lastSubmission = lastSubmission;
    }

    public boolean isHome() {
        return home;
    }

    public Date getLastSubmission() {
        return lastSubmission;
    }
}
