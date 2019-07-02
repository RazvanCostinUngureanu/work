import org.json.simple.JSONObject;

public class ActiveProgrammers implements Programmers {

	private String firstName;
	private String lastName;
	private String activity;
	private String startDate;
	private String endDate;
	private int workedDays;
	private int totalDays;
	private int salary;
	private int totalSalary;

	
	
	
	
	public ActiveProgrammers(String firstName, String lastName, String activity, String startDate, String endDate,
			int workedDays, int totalDays, int salary) {
	
		this.firstName = firstName;
		this.lastName = lastName;
		this.activity = activity;
		this.startDate = startDate;
		this.endDate = endDate;
		this.workedDays = workedDays;
		this.totalDays = totalDays;
		this.salary = salary;

	}
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getWorkedDays() {
		return workedDays;
	}

	public void setWorkedDays(int workedDays) {
		this.workedDays = workedDays;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(int totalSalary) {
		this.totalSalary = totalSalary;
	}

	@Override
	public String toString() {
		return "ActiveProgrammers [firstName=" + firstName + ", lastName=" + lastName + ", activity=" + activity
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", workedDays=" + workedDays + ", totalDays="
				+ totalDays + ", salary=" + salary + ", totalSalary=" + totalSalary + "]";
	}

	@Override
	public int calcTotalSalary() {
		int totalSalary = this.salary * this.workedDays;
		return totalSalary;

	}

	@Override
	public void increaseDuration() {
		this.workedDays++;

	}

	@Override
	public JSONObject generateJson() {
		JSONObject p1Obj = new JSONObject();
		p1Obj.put("programmerFirstName", this.getFirstName());
		p1Obj.put("programmerLastName", this.getLastName());
		p1Obj.put("programmerActivity", this.getActivity());
		p1Obj.put("programmerStartDate", this.getStartDate());
		p1Obj.put("programmerEndDate", this.getEndDate());
		p1Obj.put("programmerWorkedDays", this.getWorkedDays());
		p1Obj.put("programmerTotalDays", this.getTotalDays());
		p1Obj.put("programmerSalary", this.getSalary());
		p1Obj.put("programmerTotalSalary", this.getTotalSalary());
		 return p1Obj;
	}

	@Override
	public void setSalary(boolean isFullTime) {
		if(isFullTime){
			this.totalSalary = calcTotalSalary();
		}else{
			this.totalSalary = calcTotalSalary()/2;
		}
		
	}

	@Override
	public String getRaport() {
		return this.getLastName() + ", " + this.getFirstName() + ", " + "in charge of "
				+ this.getActivity() + " from " + this.getStartDate() + " to " + this.getEndDate() + " (duration "
				+ this.getTotalDays() + "), this month : " + this.getWorkedDays() + " days (total cost = "
				+ this.getTotalSalary() + ")\n\r\n\r\n\r";
	}

}
