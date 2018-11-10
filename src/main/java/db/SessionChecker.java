package db;

import util.Pair;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionChecker {

    public static List<Pair<SessionNurse, Session>> getNotRespondedNurses() throws Exception {
        String sql = "SELECT * FROM `sessions`,`sessionsass`,`staff` WHERE (`sessionsass`.`LEFTHOME`=0 OR `sessionsass`.`HOME`=0) AND `staff`.`PAYROLLNUM` = `sessionsass`.`SESSIONID` AND `sessions`.`STARTTD` >= NOW()";

        List<Pair<SessionNurse, Session>> notResponded = new ArrayList<>();

        try(ResultSet set = DatabaseHelper.instance().getConnection().prepareStatement(sql).executeQuery()) {
            while(set.next()) {
                Nurse nurse = new Nurse(set.getString("PAYROLLNUM"), set.getString("NAME"), set.getString("EMAIL_ADDRESS"), Role.valueOf(set.getString("ROLE")));
                SessionNurse sessionNurse = new SessionNurse(nurse, set.getBoolean("LEFTHOME"), set.getBoolean("HOME"), set.getString("COMMENT"));

                Session session = new Session(set.getString("PATIENTID"), new Date(set.getDate("STARTTD").getTime()), new Date(set.getDate("ENDTD").getTime()));
                notResponded.add(new Pair<>(sessionNurse, session));
            }
        }
        return notResponded;
    }

    public static List<Nurse> getAdmins() throws Exception {
        String sql = "SELECT * FROM `staff` WHERE `role`='ADMIN'";

        try(PreparedStatement statement = DatabaseHelper.instance().getConnection().prepareStatement(sql)) {
            ResultSet set = statement.executeQuery();
            List<Nurse> admins = new ArrayList<>();
            while(set.next()) {
                admins.add(new Nurse(set.getString("PAYROLLNUM"), set.getString("NAME"), set.getString("EMAIL_ADDRESS"), Role.valueOf(set.getString("ROLE"))));
            }
            return admins;
        }

    }
}
