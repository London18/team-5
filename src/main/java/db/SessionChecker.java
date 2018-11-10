package db;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SessionChecker {

    /**
     * @return Gets all sessions currently or in the future
     */
    public static List<Session> getSessionInformation() {
        return Arrays.asList(
          new Session(new Date(System.currentTimeMillis() - 50_000L), new Date(System.currentTimeMillis() + 100_000L))
        );
    }

    public static Session getCurrentSession(List<Session> sessions) {
        long time = System.currentTimeMillis();
        for(Session session : sessions) {
            if(session.getStartDate().getTime() >= time && session.getEndDate().getTime() <= time) return session;
        }
        return null;
    }

    public static boolean isInSession(List<Session> sessions) {
        return getCurrentSession(sessions) != null;
    }
}
