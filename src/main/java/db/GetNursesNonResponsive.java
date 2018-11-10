package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetNursesNonResponsive {

    public static List<String> getNonResponses(){
        List<String> toReturn = new ArrayList<>();
        try(PreparedStatement statement = DatabaseHelper.instance().getConnection().prepareStatement("SELECT STAFF.NAME, " +
                "(NOW() - SESSIONS.ENDTD) AS 'Time Elapsed' FROM SESSIONSASS, STAFF, SESSIONS WHERE SESSIONSA" +
                "SS.LEFTHOME = 1 AND SESSIONSASS.HOME = 0 AND SESSIONSASS.PAYROLLNUM = STAFF.PAYROLLNUM AND S" +
                "ESSIONS.SESSIONID = SESSIONSASS.SESSIONID AND SESSIONS.ENDTD < NOW() ORDER BY 'Time Elapsed' DES" +
                "C;")) {
            ResultSet results = statement.executeQuery();
            while(results.next()){
                toReturn.add(results.getString("NAME"));
                toReturn.add(results.getString("Time Elapsed"));
            }
        } catch (Exception e) {
            return null;
        }
        return toReturn;
    }

}
