package mydiaryweb.module.behaviour.logic;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import mydiaryweb.entity.inferences.Main;
import mydiaryweb.module.behaviour.persistence.ActionsInput;
import mydiaryweb.module.behaviour.persistence.FinalOutput;
import mydiaryweb.module.behaviour.persistence.InputObject;
import mydiaryweb.module.behaviour.persistence.OutputDayObject;



public class Utils {

	public final static String HABIT = "habit";
	public final static String GENERALLY_DONE = "generally done";
	public final static String WEAK_PATTERN = "weak pattern";
	public final static String IRRELEVANT = "irrelevant";
	public final static String[] DAYS_OF_WEEK = { "sunday", "monday",
			"tuesday", "wednesday", "thursday", "friday", "saturday" };

	public static long countDays(Date createdDate) {
		int result = 0;

		Calendar start = Calendar.getInstance();
		start.setTime(createdDate);

		Date currentDate = new Date();
		Calendar end = Calendar.getInstance();
		end.setTime(currentDate);
		int today = end.get(Calendar.DAY_OF_WEEK);

		while (start.before(end)) {
			if (start.get(Calendar.DAY_OF_WEEK) == today) {
				result++;
				start.add(Calendar.DATE, 7);
			} else {
				start.add(Calendar.DATE, 1);
			}
		}

		return result - 1;
	}

	public static ArrayList<FinalOutput> applyAlgorithm(
			ArrayList<InputObject> input, Date createdDate,
			ArrayList<OutputDayObject> output, ArrayList<String> friends) {
		
			processInput(input, output);
		return processOutput(createdDate, output, friends);
	}

	private static ArrayList<FinalOutput> processOutput(Date createdDate,
			ArrayList<OutputDayObject> output, ArrayList<String> friends) {
		ArrayList<FinalOutput> result = new ArrayList<FinalOutput>();
		long days = countDays(createdDate);
		Map<String, ArrayList<OutputDayObject>> map = new HashMap<String, ArrayList<OutputDayObject>>();
		for (OutputDayObject obj : output) {
			if (map.containsKey(obj.getActionID())) {
				map.get(obj.getActionID()).add(obj);
			} else {
				if (obj.getTimes() > 2) {
					ArrayList<OutputDayObject> newList = new ArrayList<OutputDayObject>();
					newList.add(obj);
					map.put(obj.getActionID(), newList);
				}
			}
		}

		for (String key : map.keySet()) {
			for (String day : DAYS_OF_WEEK) {
				result.add(buildFinalOutput(map.get(key), days, day, friends));
			}
		}

		return result;
	}

	private static FinalOutput buildFinalOutput(
			ArrayList<OutputDayObject> output, long days, String day,
			ArrayList<String> friends) {
		FinalOutput result = new FinalOutput();
		result.setActionID(output.get(0).getActionID());
		result.setDay(day);
		ArrayList<OutputDayObject> outputByDay = new ArrayList<OutputDayObject>();
		for (OutputDayObject outputDayObject : output) {
			try {
				if (outputDayObject.getDay().equals(day)) {
					outputByDay.add(outputDayObject);
				}
			} catch (java.lang.NullPointerException e) {
				// TODO Auto-generated catch block
				
			}
		}

		setWhoPercent(result, outputByDay, days, friends);

		double dayPercent = calculateDayPercent(outputByDay, days,
				result.getDay());
		result.setHowRecurringByDay(getFrequency(dayPercent));
		setLocationPercent(result, outputByDay, days);

		return result;
	}

	private static void setLocationPercent(FinalOutput finalOutput,
			ArrayList<OutputDayObject> output, long days) {
		ArrayList<String> locations = new ArrayList<String>();
		ArrayList<String> allLocations = new ArrayList<String>();
		for (OutputDayObject item : output) {
			allLocations.add(item.getWhere());
		}
		for (String location : allLocations) {
			int index = locations.indexOf(location);
			if (index < 0) {
				locations.add(location);
			}
		}
		int[] totals = new int[locations.size()+10];
		for (String location : locations) {
			for (OutputDayObject outputDayObject : output) {
				if (outputDayObject.getWhere().equals(location)) {
					totals[locations.indexOf(location)]++;
				}
			}
		}
		int max = 0;
		int index = 0;
		for (int i : totals) {
			if (max < totals[i]) {
				max = totals[i];
				index = i;
			}
		}
		double percent = (double) max / days * 100;
		if (percent > 33) {
			finalOutput.setLocationID(locations.get(index));
			finalOutput.setHowRecurringByLocation(getFrequency(percent));
		}
	}

	private static void setWhoPercent(FinalOutput finalOutput,
			ArrayList<OutputDayObject> output, long days,
			ArrayList<String> friends) {
		int[] totals = new int[friends.size()];
		for (String friend : friends) {
			for (OutputDayObject outputDayObject : output) {
				if (outputDayObject.getWho().equals(friend)) {
					totals[friends.indexOf(friend)]++;
				}
			}
		}
		int max = 0;
		int index = 0;
		for (int i : totals) {
			if (max < totals[i]) {
				max = totals[i];
				index = i;
			}
		}
		double percent = (double) max / days * 100;
		if (percent > 33) {
			finalOutput.setFacesID(friends.get(index));
			finalOutput.setHowRecurringByPerson(getFrequency(percent));
		}
	}

	private static double calculateDayPercent(
			ArrayList<OutputDayObject> output, long days, String day) {
		double result = (double) output.size() / days * 100;
		return result;
	}

	private static void processInput(ArrayList<InputObject> input,
			ArrayList<OutputDayObject> existingOutput) {
		for (InputObject item : input) {
			OutputDayObject outputObject = new OutputDayObject();
			try {
				outputObject.setActionID(item.getActionID());
			
			outputObject.setDay(getDayOfWeek(item.getDate()));
			outputObject.setWhere(item.getLocationID());
			outputObject.setWho(item.getFacesID());
			} catch (java.lang.NullPointerException e) {
				// TODO Auto-generated catch block
				
			}
			int index = existingOutput.indexOf(outputObject);
			if (index < 0) {
				existingOutput.add(outputObject);
			} else {
				OutputDayObject obj = existingOutput.get(index);
				obj.setTimes(obj.getTimes() + 1);
			}
		}
	}

	private static String getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		return getStringDate(day);
	}

	private static String getStringDate(int index) {
		switch (index) {
		case 0: {
			return "sunday";
		}
		case 1: {
			return "monday";
		}
		case 2: {
			return "tuesday";
		}
		case 3: {
			return "wednesday";
		}
		case 4: {
			return "thursday";
		}
		case 5: {
			return "friday";
		}
		case 6: {
			return "saturday";
		}
		default: {
			return null;
		}

		}
	}

	private static String getFrequency(double percent) {
		if (percent >= 80) {
			return HABIT;
		}
		if (percent >= 65) {
			return GENERALLY_DONE;
		}
		if (percent >= 50) {
			return WEAK_PATTERN;
		}
		return IRRELEVANT;
	}
	public static void getInfoForBehavior(ArrayList<Main> objects) {
		ArrayList<InputObject> inputObject = new ArrayList<>();
		ArrayList<ActionsInput> actions = new ArrayList<>();
		for (Main object : objects) {
			String actionID = ""+object.getAction().getId();
			Date date = object.getDate();
			String actionName = object.getAction().getName();
			String locationName = object.getAction().getLocation()
					.getAddress()
					+ ";" + object.getAction().getLocation().getProximity();
			String personName = "";
			if (object.getAction().getFace()!= null)
				personName = object.getAction().getFace().getFaceName();
			if (object.getAction().getSound() != null)
				personName = object.getAction().getSound().getSoundName();
			inputObject.add(new InputObject(actionID, locationName, personName, date));
			actions.add(new ActionsInput(actionName, locationName, personName, date));
//                        Percent percent = new Percent(actions, )
		}
	}
}
