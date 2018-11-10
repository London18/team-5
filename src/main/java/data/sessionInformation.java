package data;

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

	public String getStartDateTime() {
		return startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
