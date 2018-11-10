package data;

import java.util.Date;

public class sessionInformation {

	private int status;
	private Date startDateTime;
	private Date endDateTime;
	private String name;

	public sessionInformation(int status, Date startDateTime, Date endDateTime, String name) {
		this.status = status;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
