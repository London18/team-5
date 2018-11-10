package data;

import java.util.Date;

public class sessionInformation {

	private int status;
	private String startDateTime;
	private String endDateTime;
	private String name;

	public sessionInformation(int status, String startDateTime, String endDateTime, String name) {
		this.status = status;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.name = name;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getStartDateTime() {
		return startDateTime;
	}


	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}


	public String getEndDateTime() {
		return endDateTime;
	}


	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
