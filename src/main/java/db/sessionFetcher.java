package db;

import data.sessionInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

public class sessionFetcher {

	public sessionInformation getSessionInfo(String user) {
		LocalDateTime localDateTime = LocalDateTime.now();

		PreparedStatement preparedStatement = null;

		ResultSet results;

		// Do they have overdue safe place request?
		try {
			Connection connection = DatabaseHelper.instance().getConnection();
			preparedStatement = connection.prepareStatement("SELECT STAFF.NAME, STARTTD, ENDTD FR" +
					"OM STAFF, SESSIONS, SESSIONSASS, PASSWORDS PASS WHERE STAFF.PAYROLLNUM = PASS.PAYROLLNUM AND " +
					"SESSIONSASS.PAYROLLNUM = STAFF.PAYROLLNUM AND SESSIONS.SESSIONID = SESSIONSASS.SESSIONID AND " +
					"SESSIONSASS.LEFTHOME = 1 AND SESSIONSASS.HOME = 0 AND PASS.USERNAME = ? ORDER BY STARTTD ASC;");
			preparedStatement.setString('0',user);
			results = preparedStatement.executeQuery();
			if (results.next()) {
				return new sessionInformation(1, results.getDate("STARTTD"),
						results.getDate("ENDTD"), results.getString("NAME"));
			} else {
				// No overdue safe place requests, so any overdue left patient requests?
				preparedStatement = connection.prepareStatement("SELECT STAFF.NAME, STARTTD, E" +
						"NDTD FROM STAFF, SESSIONS, SESSIONSASS, PASSWORDS PASS WHERE STAFF.PAYROLLNUM = PASS.PA" +
						"YROLLNUM AND SESSIONSASS.PAYROLLNUM = STAFF.PAYROLLNUM AND SESSIONS.SESSIONID = SESSIONS" +
						"ASS.SESSIONID AND SESSIONSASS.LEFTHOME = 0 AND SESSIONSASS.HOME = 0 AND SESSIONS.ENDTD <" +
						" NOW() AND PASS.USERNAME = ? ORDER BY STARTTD ASC;");
				preparedStatement.setString('0',user);
				results = preparedStatement.executeQuery();
				if (results.next()) {
					return new sessionInformation(1, results.getDate("STARTTD"),
							results.getDate("ENDTD"), results.getString("NAME"));
				} else {
					// No overdue left home requests, so any current requests?
					preparedStatement = connection.prepareStatement("SELECT STAFF.NAME, STARTTD, E" +
							"NDTD FROM STAFF, SESSIONS, SESSIONSASS, PASSWORDS PASS WHERE STAFF.PAYROLLNUM = PASS.PA" +
							"YROLLNUM AND SESSIONSASS.PAYROLLNUM = STAFF.PAYROLLNUM AND SESSIONS.SESSIONID = SESSIONS" +
							"ASS.SESSIONID AND SESSIONSASS.LEFTHOME = 0 AND SESSIONSASS.HOME = 0 AND SESSIONS.STARTTD" +
							" < NOW() AND PASS.USERNAME = ? ORDER BY STARTTD ASC;");
					preparedStatement.setString('0',user);
					results = preparedStatement.executeQuery();
					if (results.next()) {
						return new sessionInformation(1, results.getDate("STARTTD"),
								results.getDate("ENDTD"), results.getString("NAME"));
					} else {
						//No current requests, any future requests
						preparedStatement = connection.prepareStatement("SELECT STAFF.NAME, STARTTD" +
								", ENDTD FROM STAFF, SESSIONS, SESSIONSASS, PASSWORDS PASS WHERE STAFF.PAYROLLNUM = " +
								"PASS.PAYROLLNUM AND SESSIONSASS.PAYROLLNUM = STAFF.PAYROLLNUM AND SESSIONS.SESSIONID " +
								"= SESSIONSASS.SESSIONID AND SESSIONSASS.LEFTHOME = 0 AND SESSIONSASS.HOME = 0 AND" +
								" SESSIONS.STARTTD > NOW() AND PASS.USERNAME = ? ORDER BY STARTTD ASC;");
						preparedStatement.setString('0',user);
						results = preparedStatement.executeQuery();
						if (results.next()) {
							return new sessionInformation(1, results.getDate("STARTTD"),
									results.getDate("ENDTD"), results.getString("NAME"));
						} else {
							//No future requests
							preparedStatement = connection.prepareStatement("SELECT STAFF.NAME FRO" +
									"M PASSWORDS, STAFF WHERE PASSWORDS.PAYROLLNUM = STAFF.PAYROLLNUM AND USERNAME " +
									"= ?;");
							preparedStatement.setString('0',user);
							results = preparedStatement.executeQuery();
							results.next();
							return new sessionInformation(2,null,null,
									results.getString("NAME"));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new sessionInformation(2,null,null,"N/A");
	}


}
