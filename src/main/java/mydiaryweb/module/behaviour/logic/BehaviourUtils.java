package mydiaryweb.module.behaviour.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mydiaryweb.entity.behaviour.output.Behaviour;
import mydiaryweb.entity.behaviour.output.RecurringBehaviour;
import mydiaryweb.entity.inferences.Action;
import mydiaryweb.entity.inferences.Main;
import mydiaryweb.module.behaviour.persistence.ActionsInput;
import mydiaryweb.module.behaviour.persistence.FinalOutput;
import mydiaryweb.module.behaviour.persistence.InputObject;
import mydiaryweb.module.behaviour.persistence.OutputDayObject;
import mydiaryweb.service.BehaviourService;
import mydiaryweb.service.InferenceService;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class BehaviourUtils {
    private static InferenceService inferenceService;

    private static BehaviourService behaviourService;

    public final static String HABIT = "habit";
    public final static String GENERALLY_DONE = "generally done";
    public final static String WEAK_PATTERN = "weak pattern";
    public final static String IRRELEVANT = "irrelevant";
    public final static String[] DAYS_OF_WEEK = {"sunday", "monday",
        "tuesday", "wednesday", "thursday", "friday", "saturday"};

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

    public static List<FinalOutput> applyAlgorithm(
            ArrayList<InputObject> input, Date createdDate,
            ArrayList<OutputDayObject> output, List<String> friends) {

        processInput(input, output);
        return processOutput(createdDate, output, friends);
    }

    private static List<FinalOutput> processOutput(Date createdDate,
            ArrayList<OutputDayObject> output, List<String> friends) {
        List<FinalOutput> result = new ArrayList<FinalOutput>();
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
            List<String> friends) {
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
        int[] totals = new int[locations.size() + 10];
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
            List<String> friends) {
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

    private static void createPatternsBehaviour(List<Main> objects) {
        ArrayList<ActionsInput> actions = new ArrayList<>();
        for (Main object : objects) {
            Action action = object.getAction();
            String locationName = action.getLocation()
                    .getAddress()
                    + ";" + action.getLocation().getProximity();
            String personName = "";
            if (action.getFace() != null) {
                personName = action.getFace().getFaceName();
            }
            if (action.getSound() != null) {
                personName = action.getSound().getSoundName();
            }
            actions.add(new ActionsInput(action.getName(), locationName, personName, object.getDate(), action.getBehaviour(), action.getId()));
        }
        Collections.sort(actions);
        String tmpLocation = "";
        String tmpWho = "";
        Date tmpDate = null;
        long tmpDifferenceDate = 0;
        int tmpCount = 0;
        for (int i = 0; i < actions.size(); i++) {
            ActionsInput action = actions.get(i);
            if (tmpCount == 0) {
                tmpLocation = action.getLocation();
                tmpWho = action.getWithWho();
                tmpDate = action.getDate();
                if (i != actions.size() - 1) {
                    DateTime dt1 = new DateTime(tmpDate);
                    DateTime dt2 = new DateTime(actions.get(i + 1).getDate());
                    tmpDifferenceDate = Days.daysBetween(dt1, dt2).getDays();
                }
                tmpCount++;
            } else {
                DateTime dt1 = new DateTime(tmpDate);
                DateTime dt2 = new DateTime(action.getDate());
                if (action.getLocation().equals(tmpLocation)
                        && action.getWithWho().equals(tmpWho)
                        && dt1.getHourOfDay() == dt2.getHourOfDay()
                        && (Days.daysBetween(dt1, dt2).getDays() == tmpDifferenceDate
                        || Days.daysBetween(dt1, dt2).getDays() == tmpDifferenceDate - 1 || Days
                        .daysBetween(dt1, dt2).getDays() == tmpDifferenceDate + 1)) {
                    tmpCount++;
                    tmpDate = action.getDate();
                } else {
                    if (tmpCount >= 3) {
                        Date dateAfter = DateUtils.addDays(tmpDate,
                                (int) tmpDifferenceDate);
                        if (actions.get(i - tmpCount).getBehavior() != null) {
                            Behaviour b = actions.get(i - tmpCount).getBehavior();
                            b.setCounter(tmpCount);
                            b.setFlag(false);
                            b.setNextTime(dateAfter);
                            behaviourService.updateBehaviour(b);
                        } else {
                            Behaviour b = new Behaviour(dateAfter, tmpCount, false);
                            //create entry
                            behaviourService.addBehaviour(b);
                            for (int j = i - 1; j >= i - tmpCount; j--) {
                                actions.get(j).setBehavior(b);
                                //update action
                                inferenceService.setBehaviourForAction(actions.get(j).getActionId(), b);
                                //actions.get(j).getActionId();
                            }
                        }
                    }
                    tmpCount = 0;
                    i--;
                }
            }
        }
    }

    public static void processBehaviour(List<Main> objects) {

        ArrayList<InputObject> inputObject = new ArrayList<>();
        for (Main object : objects) {
            Action action = object.getAction();
            String actionID = "" + action.getId();
            Date date = object.getDate();
            String actionName = action.getName();
            String locationName = action.getLocation()
                    .getAddress()
                    + ";" + action.getLocation().getProximity();
            String personName = "";
            if (action.getFace() != null) {
                personName = action.getFace().getFaceName();
            }
            if (action.getSound() != null) {
                personName = action.getSound().getSoundName();
            }
            inputObject.add(new InputObject(actionID, locationName, personName, date));
            createPatternsBehaviour(inferenceService.getActivitiesByActionName(actionName));
        }

        ArrayList<OutputDayObject> output = new ArrayList<>();
        ArrayList<String> friends = new ArrayList<>(); //TODO un query la BD care ia toate persoanele  de la Faces and Sounds si umple lista asta
        List<FinalOutput> finalOutputs = applyAlgorithm(inputObject, new Date(), output, friends);
        for(FinalOutput finalOutput: finalOutputs){
            //TODO ii trebuie o metoda care dupa actionId sa returneze obiectul Action
            Action a = inferenceService.getActionById(Long.parseLong(finalOutput.getActionID()));
            //in Obiectul ala RecurringBehavior campul day sa-l faci String nu Date
            RecurringBehaviour recurringBehavior = new RecurringBehaviour();
            recurringBehavior.setAction(a);//aici pui obiectul a returnat mai sus intr-o linie comentata
            recurringBehavior.setDay(finalOutput.getDay());
            recurringBehavior.setHowRecurringByDay(finalOutput.getHowRecurringByDay());
            recurringBehavior.setHowRecurringByLocation(finalOutput.getHowRecurringByLocation());
            recurringBehavior.setHowRecurringByPerson(finalOutput.getHowRecurringByPerson());
            recurringBehavior.setLocationName(finalOutput.getLocationID());
            recurringBehavior.setPersonName(finalOutput.getFacesID());
            //sterge din RecurringBehavior campul 'type' ca nu imi trebuie
            
            //TODO o metoda care persista obiectul recurringBehavior
            behaviourService.addRecurringBehaviour(recurringBehavior);
        }
    }
    
    public static void setServices(InferenceService newInferenceService, 
            BehaviourService newBehaviourService) {
        inferenceService = newInferenceService;
        behaviourService = newBehaviourService;
    }
}
