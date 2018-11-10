package db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HomeChecker {

    public static HomeInformation getHomeInformation(int payrollId) throws Exception {
        String sql = "SELECT * FROM `" + TableNames.HOME_STATUS + "` WHERE `PAYROLLNUM` = " + payrollId;

        try(PreparedStatement statement = DatabaseHelper.instance().getConnection().prepareStatement(sql)) {
            ResultSet set = statement.executeQuery();
            if(!set.first()) return null;

            return new HomeInformation(set.getBoolean("HOME"), set.getDate("LASTSUBMISSION"));
        }
    }

    public static void changeHomeState(int payrollId, boolean home) throws Exception {
        HomeInformation information = getHomeInformation(payrollId);
        if(information == null) {
            //Insert
            String sql = "INSERT INTO `" + TableNames.HOME_STATUS + "` VALUES(?,?,?)";
            try(PreparedStatement statement = DatabaseHelper.instance().getConnection().prepareStatement(sql)) {
                statement.setInt(1, payrollId);
                statement.setBoolean(2, home);
                statement.setDate(3, new Date(System.currentTimeMillis()));
                statement.execute();
            }
        } else {
            //Update
            String sql = "UPDATE `" + TableNames.HOME_STATUS + "` SET `HOME`=? AND `LASTSUBMISSION`=? WHERE `PAYROLLNUM`=?";
            try(PreparedStatement statement = DatabaseHelper.instance().getConnection().prepareStatement(sql)) {
                statement.setBoolean(1, home);
                statement.setDate(2, new Date(System.currentTimeMillis()));
                statement.setInt(3, payrollId);
            }
        }
    }
}
