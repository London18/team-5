package db;

import java.sql.Date;

public class Session {

    private Date startDate;
    private Date endDate;

    public Session(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
