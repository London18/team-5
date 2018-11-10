package db;

import com.mysql.cj.protocol.Resultset;

import java.sql.PreparedStatement;
import java.util.Date;

public class sessionFetcher {

	private DatabaseHelper db;

	public sessionFetcher(){
		try {
			this.db = DatabaseHelper.instance();
		} catch (Exception e) {}
	}


	public String[] getSessionInfo(String user){
		String[] toReturn = new String[4];
		Date date = new Date();

		PreparedStatement preparedStatement = null;

		Resultset results;


		return toReturn;

	}


}
