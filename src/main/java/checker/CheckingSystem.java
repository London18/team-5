package checker;

import db.Nurse;
import db.Session;
import db.SessionChecker;
import db.SessionNurse;
import mail.MailHandler;
import util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckingSystem {

    /**
     * 30 minutes
     */
    private static int ALERT_AFTER_END = 30;

    /**
     * 2 hours
     */
    private static int MAX_HOME_TIME = 120;

    private static Map<Session, SendAlertData> sentAlerts = new HashMap<>();

    public static void updateNurses() {
        try {
            List<Pair<SessionNurse, Session>> list = SessionChecker.getNotRespondedNurses();

            for(Pair<SessionNurse, Session> pair : list) {
                SessionNurse nurse = pair.getValue1();
                Session session = pair.getValue2();
                SendAlertData data = getAlertData(session);

                if(!nurse.hasExited()) {
                    long timeAfter = System.currentTimeMillis() - session.getEndDate().getTime();
                    if(timeAfter > 0) {
                        if(!data.getLeavingReminders().contains(nurse.getPayrollId())) {
                            sendReminder(nurse, "Reminder to indicate you finished your last sit", "You haven't marked you finished your latest sit");
                            data.getLeavingReminders().add(nurse.getPayrollId());
                        } else if(timeAfter >= ALERT_AFTER_END * 60L * 1000L && !data.getGlobalLeavingAlerts().contains(nurse.getPayrollId())) {
                            raiseAlert(nurse, false);
                            data.getGlobalLeavingAlerts().add(nurse.getPayrollId());
                        }
                    }
                }
                if(!nurse.isHomeSafe()) {
                    long timeAfter = System.currentTimeMillis() - session.getEndDate().getTime();
                    if(timeAfter > 0) {
                        if(!data.getHomeReminders().contains(nurse.getPayrollId())) {
                            sendReminder(nurse, "Reminder to indicate you are home safe from your sit", "You haven't marked you are home safely from your last sit.");
                            data.getHomeReminders().add(nurse.getPayrollId());
                        } else if(timeAfter >= (ALERT_AFTER_END + MAX_HOME_TIME) * 60L * 1000L && !data.getGlobalHomeAlerts().contains(nurse.getPayrollId())) {
                            raiseAlert(nurse, true);
                            data.getGlobalHomeAlerts().add(nurse.getPayrollId());
                        }
                    }
                }
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void raiseAlert(SessionNurse problemNurse, boolean isHomeAlert) throws Exception {
        System.out.println("PROBLEM ALERT FOR " + problemNurse.getName());

        List<Nurse> admins = SessionChecker.getAdmins();
        MailHandler handler = new MailHandler();
        for(Nurse admin : admins) {
            if(isHomeAlert) {
//                handler.sendEmail(admin.getEmail(), "ALERT: No home safe report", problemNurse.getName() + " has not marked that they were back home safely. You should probably call them to check in on them");
            } else {
//                handler.sendEmail(admin.getEmail(), "Alert: No sit finish report", problemNurse.getName() + " has not marked that they finished their sit. You may want to follow up on this");
            }
            System.out.println("SENDING EMAIL TO " + admin.getEmail() + " AS " + problemNurse.getName() + " ISNT BACK: " + isHomeAlert);
        }
    }

    private static void sendReminder(SessionNurse nurse, String subject, String message) {
        try {
            String actualMessage = "<html><body><p> Hi " + nurse.getName() + "</p><p>" + message + "</p><br />";
            actualMessage += "<a>Please click the </p><a href = 'localhost:8080'>following link</a><p> to confirm</p>";
            actualMessage += "<p>Thanks!</p></body></html>";

//            new MailHandler().sendEmail(nurse.getNurse().getEmail(), subject, actualMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("SENDING REMINDER TO " + nurse.getName() + ": " + subject + ", " + message);
    }

    private static SendAlertData getAlertData(Session session) {
        SendAlertData data = sentAlerts.get(session);
        if(data != null) return data;
        data = new SendAlertData(session);
        sentAlerts.put(session, data);
        return data;
    }
}
