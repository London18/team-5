package db;

import data.sessionInformation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

public class sessionFetcher {

	private DatabaseHelper db;

	public sessionFetcher(){
		try {
			this.db = DatabaseHelper.instance();
		} catch (Exception e) {}
	}


	public sessionInformation getSessionInfo(String user){
		LocalDateTime localDateTime = LocalDateTime.now();

		PreparedStatement preparedStatement = null;

		ResultSet results;

		// Do they have overdue safe place request?
		try {
			preparedStatement = this.db.getConnection().prepareStatement("SELECT STAFF.NAME, SIT.STARTTD, SIT.ENDTD"
					+ " FROM STAFF, SESSIONS SIT, SESSIONSASS ASS, SES"
					+ "SIONENDSTATE END, HOMESTATUS HOME, PASSWORDS PASS WHERE SIT.ENDTD = ASS.ENDTD AND STAFF.PAYROLLN"
					+ "UM =ASS.PAYROLLNUM AND HOME.PAYROLLNUM = STAFF.PAYROLLNUM AND ASS.ENDTD > NOW() AND END.PARTNERI"
					+ "D =ASS.PARTNERID AND HOME.HOME = 0 AND PASS.USERNAME = ? AND PASS.PAYROLLNUM = STAFF.PAYROLLNU"
					+ "M ORDER BY SIT.STARTTD ASC;");
			preparedStatement.setString(0, user);
			results = preparedStatement.executeQuery();
			if(results.next()){
				return new sessionInformation(1, results.getString("STARTTD"),
						results.getString("ENDTD"),results.getString("NAME"));
			}else{
				// No overdue safe place requests, so any overdue left patient requests?

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return new sessionInformation(0,null,null,"Liam");

	}


}
