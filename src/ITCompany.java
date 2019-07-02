
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ITCompany {

	static File jsonFileName = new File("file.json");

	public static void main(String[] args) {

		if (jsonFileName.exists()) {
			System.out.println("The file already exists");
		} else {
			createJson();
		}
		loadJson();
	}

	public static void createJson() {

		// Create programmers
		Programmers p1 = new ActiveProgrammers("John", "Snow", "Java", "03.06.2019", "19.06.2019", 13, 17, 45);
		Programmers p2 = new ActiveProgrammers("Arya", "Stark", "Java", "05.08.2019", "30.08.2019", 20, 26, 50);
		Programmers p3 = new ActiveProgrammers("Rose", "Bolton", "Java", "01.03.2018", "20.03.2018", 14, 20, 35);
		Programmers p4 = new ActiveProgrammers("Tommen", "Baratheon", "C++", "02.07.2018", "21.07.2018", 15, 21, 35);
		Programmers p5 = new ActiveProgrammers("Gregor", "Clegane", "C++", "11.09.2017", "30.09.2017", 15, 20, 40);
		Programmers p6 = new ActiveProgrammers("Tyrion", "Lanister", "C++", "03.11.2017", "17.11.2017", 11, 15, 38);

		// Define project teams
		ProjectTeam team1 = new ProjectTeam("team 1", "Java", true);
		ProjectTeam team2 = new ProjectTeam("team 2", "C++", false);
		team1.addProgrammers(p1);
		team1.addProgrammers(p2);
		team1.addProgrammers(p3);
		team2.addProgrammers(p4);
		team2.addProgrammers(p5);
		team2.addProgrammers(p6);

		long daysConsumed = p1.getWorkedDays() + p2.getWorkedDays() + p3.getWorkedDays() + p4.getWorkedDays()
				+ p5.getWorkedDays() + p6.getWorkedDays();
		long daysInCharge = p1.getTotalDays() + p2.getTotalDays() + p3.getTotalDays() + p4.getTotalDays()
				+ p5.getTotalDays() + p6.getTotalDays();

		saveInJson(p1, p2, p3, p4, p5, p6, team1, team2, daysConsumed, daysInCharge);
	}

	@SuppressWarnings("unchecked")
	public static void saveInJson(Programmers p1, Programmers p2, Programmers p3, Programmers p4, Programmers p5,
			Programmers p6, ProjectTeam team1, ProjectTeam team2, long daysConsumed, long daysInCharge) {

		JSONObject obj = new JSONObject();
		obj.put("name", "IT");
		obj.put("numberOfTeams", 2);
		obj.put("numberOfProgrammers", 6);
		obj.put("daysConsumed", daysConsumed);
		obj.put("daysInCharge", daysInCharge);

		JSONObject p1Obj = p1.generateJson();

		JSONObject p2Obj = p2.generateJson();

		JSONObject p3Obj = p3.generateJson();
		JSONObject p4Obj = p4.generateJson();

		JSONObject p5Obj = p5.generateJson();

		JSONObject p6Obj = p6.generateJson();

		/* Encoding teams within JSONArray */

		JSONArray team1Obj = new JSONArray();
		JSONArray team2Obj = new JSONArray();

		team1Obj.add(p1Obj);
		team1Obj.add(p2Obj);
		team1Obj.add(p3Obj);
		team2Obj.add(p4Obj);
		team2Obj.add(p5Obj);
		team2Obj.add(p6Obj);

		obj.put(team1.getTeamName(), team1Obj);
		obj.put(team2.getTeamName(), team2Obj);

		/* Write JSON Object to JSON File */
		FileWriter writer;

		try {
			System.out.println("Start writing JSON File...");
			writer = new FileWriter(jsonFileName);
			writer.write(obj.toJSONString());
			System.out.println("Successfully! JSON File is DONE!");
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadJson() {

		JSONParser jsonParser = new JSONParser();
		Object obj = null;
		try {
			obj = jsonParser.parse(new FileReader(jsonFileName));
			JSONObject jsonObject = (JSONObject) obj;

			String nameCompany = (String) jsonObject.get("name");
			long numberOfTeams = (long) jsonObject.get("numberOfTeams");
			long numberOfProgrammers = (long) jsonObject.get("numberOfProgrammers");
			long daysConsumed = (long) jsonObject.get("daysConsumed");
			long daysInCharge = (long) jsonObject.get("daysInCharge");

			// Create Team 1 and programmers
			JSONArray jTeam1 = (JSONArray) jsonObject.get("team 1");

			// Read from Team 1 - programmer 1
			Object prog1 = jTeam1.get(0);
			String firstProjectName = (String) ((JSONObject) prog1).get("programmerActivity");

			String prog1FirstName = (String) ((JSONObject) prog1).get("programmerFirstName");
			String prog1LastName = (String) ((JSONObject) prog1).get("programmerLastName");
			String prog1Activity = (String) ((JSONObject) prog1).get("programmerActivity");
			String prog1StartDate = (String) ((JSONObject) prog1).get("programmerStartDate");
			String prog1EndDate = (String) ((JSONObject) prog1).get("programmerEndDate");
			
			int prog1WorkedDays = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerWorkedDays")));
			int prog1TotalDays = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerTotalDays")));
			int prog1Salary = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerSalary")));
			

			Programmers p1 = new ActiveProgrammers(prog1FirstName, prog1LastName, prog1Activity, prog1StartDate,
					prog1EndDate, prog1WorkedDays, prog1TotalDays, prog1Salary);
			p1.calcTotalSalary();

			// Read from Team 1 - programmer 2
			Object prog2 = jTeam1.get(1);
			String prog2FirstName = (String) ((JSONObject) prog2).get("programmerFirstName");
			String prog2LastName = (String) ((JSONObject) prog2).get("programmerLastName");
			String prog2Activity = (String) ((JSONObject) prog2).get("programmerActivity");
			String prog2StartDate = (String) ((JSONObject) prog2).get("programmerStartDate");
			String prog2EndDate = (String) ((JSONObject) prog2).get("programmerEndDate");
		
			int prog2WorkedDays = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerWorkedDays")));
			int prog2TotalDays = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerTotalDays")));
			int prog2Salary = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerSalary")));
		

			Programmers p2 = new ActiveProgrammers(prog2FirstName, prog2LastName, prog2Activity, prog2StartDate,
					prog2EndDate, prog2WorkedDays, prog2TotalDays, prog2Salary);
			p2.calcTotalSalary();

			// Read from Team 1 - programmer 3
			Object prog3 = jTeam1.get(2);
			String prog3FirstName = (String) ((JSONObject) prog3).get("programmerFirstName");
			String prog3LastName = (String) ((JSONObject) prog3).get("programmerLastName");
			String prog3Activity = (String) ((JSONObject) prog3).get("programmerActivity");
			String prog3StartDate = (String) ((JSONObject) prog3).get("programmerStartDate");
			String prog3EndDate = (String) ((JSONObject) prog3).get("programmerEndDate");
			
			int prog3WorkedDays = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerWorkedDays")));
			int prog3TotalDays = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerTotalDays")));
			int prog3Salary = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerSalary")));
		

			Programmers p3 = new ActiveProgrammers(prog3FirstName, prog3LastName, prog3Activity, prog3StartDate,
					prog3EndDate, prog3WorkedDays, prog3TotalDays, prog3Salary);
			p3.calcTotalSalary();

			// Create Team 1 and programmers
			JSONArray jTeam2 = (JSONArray) jsonObject.get("team 2");

			// Read from Team 2 - programmer 4
			Object prog4 = jTeam2.get(0);
			String secondProjectName = (String) ((JSONObject) prog4).get("programmerActivity");
			String prog4FirstName = (String) ((JSONObject) prog4).get("programmerFirstName");
			String prog4LastName = (String) ((JSONObject) prog4).get("programmerLastName");
			String prog4Activity = (String) ((JSONObject) prog4).get("programmerActivity");
			String prog4StartDate = (String) ((JSONObject) prog4).get("programmerStartDate");
			String prog4EndDate = (String) ((JSONObject) prog4).get("programmerEndDate");
			
			int prog4WorkedDays = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerWorkedDays")));
			int prog4TotalDays = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerTotalDays")));
			int prog4Salary = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerSalary")));
		

			Programmers p4 = new ActiveProgrammers(prog4FirstName, prog4LastName, prog4Activity, prog4StartDate,
					prog4EndDate, prog4WorkedDays, prog4TotalDays, prog4Salary);
			p4.calcTotalSalary();

			// Read from Team 2 - programmer 5
			Object prog5 = jTeam2.get(1);
			String prog5FirstName = (String) ((JSONObject) prog5).get("programmerFirstName");
			String prog5LastName = (String) ((JSONObject) prog5).get("programmerLastName");
			String prog5Activity = (String) ((JSONObject) prog5).get("programmerActivity");
			String prog5StartDate = (String) ((JSONObject) prog5).get("programmerStartDate");
			String prog5EndDate = (String) ((JSONObject) prog5).get("programmerEndDate");
			
			int prog5WorkedDays = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerWorkedDays")));
			int prog5TotalDays = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerTotalDays")));
			int prog5Salary = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerSalary")));
			

			Programmers p5 = new ActiveProgrammers(prog5FirstName, prog5LastName, prog5Activity, prog5StartDate,
					prog5EndDate, prog5WorkedDays, prog5TotalDays, prog5Salary);
			p5.calcTotalSalary();

			// Read from Team 2 - programmer 6
			Object prog6 = jTeam2.get(2);
			String prog6FirstName = (String) ((JSONObject) prog6).get("programmerFirstName");
			String prog6LastName = (String) ((JSONObject) prog6).get("programmerLastName");
			String prog6Activity = (String) ((JSONObject) prog6).get("programmerActivity");
			String prog6StartDate = (String) ((JSONObject) prog6).get("programmerStartDate");
			String prog6EndDate = (String) ((JSONObject) prog6).get("programmerEndDate");
			int prog6WorkedDays = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerWorkedDays")));
			int prog6TotalDays = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerTotalDays")));
			int prog6Salary = Integer.valueOf(String.valueOf(((JSONObject) prog1).get("programmerSalary")));
			

			Programmers p6 = new ActiveProgrammers(prog6FirstName, prog6LastName, prog6Activity, prog6StartDate,
					prog6EndDate, prog6WorkedDays, prog6TotalDays, prog6Salary);
			p6.calcTotalSalary();

			ProjectTeam team1 = new ProjectTeam("Team 1", "Java", true);
			team1.addProgrammers(p1);
			team1.addProgrammers(p2);
			team1.addProgrammers(p3);
			ProjectTeam team2 = new ProjectTeam("Team 2", "C#", false);
			team2.addProgrammers(p4);
			team2.addProgrammers(p5);
			team2.addProgrammers(p6);

			update(team1, team2);

			try {

				File report = new File("report.txt");
				if (!report.exists()) {
					report.createNewFile();
				} else {
					System.out.println("Report file already exists");
				}

				FileWriter fwReport = new FileWriter(report);
				BufferedWriter bwReport = new BufferedWriter(fwReport);
				StringBuffer contentReport = new StringBuffer();

				contentReport.append(nameCompany + "\r\n\r\n" + nameCompany + " is actually composed of "
						+ numberOfTeams + " project teams, and " + numberOfProgrammers + " programmers.\r\n"
						+ "This month, " + daysConsumed + " days have been consummed by " + numberOfProgrammers
						+ " programmers, and " + daysInCharge + " days still in charge.\n\r\n\r\n\r\n\r"
						+ "Project teams details: \n\r\n\r\n\r\n\r");

				contentReport.append("Project team: " + firstProjectName + "\n\r\n\r\n\r");

				for (Programmers p : team1.getTeam()) {
					contentReport.append(p.getRaport());
				}

				contentReport.append("\n\rProject team: " + secondProjectName + "\n\r\n\r\n\r");

				for (Programmers p : team2.getTeam()) {
					contentReport.append(p.getRaport());
				}

				bwReport.write(contentReport.toString());
				bwReport.close();
				fwReport.close();

				System.out.println("Report File is DONE!");
				System.out.println(contentReport);

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public static void update(ProjectTeam t1, ProjectTeam t2) {

		t1.update();
		t2.update();
	}
}
