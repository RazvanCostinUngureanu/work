import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class ProjectTeam {
	
	private String teamName;
	private String teamProject;
	private boolean fullTime;
	private List<Programmers> team;
	
	public ProjectTeam(String teamName, String teamProject, boolean fullTime) {

		this.teamName = teamName;
		this.teamProject = teamProject;
		this.fullTime = fullTime;
		this.team = new ArrayList<>();
	}
		
	

	public void addProgrammers(Programmers p){
		this.team.add(p);
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamProject() {
		return teamProject;
	}

	public void setTeamProject(String teamProject) {
		this.teamProject = teamProject;
	}

	public boolean isFullTime() {
		return fullTime;
	}

	public void setFullTime(boolean fullTime) {
		this.fullTime = fullTime;
	}

	public List<Programmers> getTeam() {
		return team;
	}

	public void setTeam(List<Programmers> team) {
		this.team = team;
	}
	
	public void update(){
		for(Programmers p : this.team){
			p.setSalary(this.isFullTime());
		}
	}
	
	

}
