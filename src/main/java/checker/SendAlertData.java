package checker;

import db.Session;

import java.util.HashSet;
import java.util.Set;

public class SendAlertData {

    private Session session;
    private Set<String> leavingReminders = new HashSet<>();
    private Set<String> homeReminders = new HashSet<>();
    private Set<String> globalLeavingAlerts = new HashSet<>();
    private Set<String> globalHomeAlerts = new HashSet<>();

    public SendAlertData(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public Set<String> getLeavingReminders() {
        return leavingReminders;
    }

    public Set<String> getHomeReminders() {
        return homeReminders;
    }

    public Set<String> getGlobalHomeAlerts() {
        return globalHomeAlerts;
    }

    public Set<String> getGlobalLeavingAlerts() {
        return globalLeavingAlerts;
    }
}
