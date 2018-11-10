package db;


import java.util.Date;

public class Session {

    private String patientId;

    private Date startDate;
    private Date endDate;

    public Session(String patientId, Date startDate, Date endDate) {
        this.patientId = patientId;

        this.startDate = startDate;
        this.endDate = endDate;
    }


    public String getPatientId() {
        return patientId;
    }


    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj.getClass() != Session.class) return false;
        Session session = (Session) obj;
        return session.patientId.equals(patientId) && session.startDate.equals(startDate) && session.endDate.equals(endDate);
    }

    @Override
    public int hashCode() {
        return patientId.hashCode() * startDate.hashCode() & endDate.hashCode();
    }

}
