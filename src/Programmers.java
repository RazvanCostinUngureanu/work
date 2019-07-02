import org.json.simple.JSONObject;

public interface Programmers {
	
	public int calcTotalSalary();
	public void increaseDuration();
	public int getWorkedDays();
	public int getTotalDays();
	public JSONObject generateJson(); 
	public void setSalary(boolean isFullTime);
	public String getRaport();

}
