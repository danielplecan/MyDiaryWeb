package mydiaryweb.module.inferences;

import java.math.BigInteger;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import mydiaryweb.entity.faces.output.Face;
import mydiaryweb.entity.inferences.Action;
import mydiaryweb.entity.localization.output.Location;
import mydiaryweb.entity.movement.output.Move;
import mydiaryweb.entity.sound.output.Sound;
import mydiaryweb.service.InferenceService;

public class InferencesHandler {
    private static InferenceService inferenceService;

    public static void StartInferencing(List<Location> locations, List<Move> movements, List<Face> faces, List<Sound> sounds, int interval) {

        for (Location loc : locations) {

            Date maxTime = new Date(loc.getTimestamp().getTime() + (1000 * interval));
            Date minTime = new Date(loc.getTimestamp().getTime() - (1000 * interval));

            for (Move move : movements) {

                if (move.getStartMoment().after(minTime) && move.getStartMoment().before(maxTime)) {
                    //valid stuff here - should find any faces & sounds
                    boolean foundAnyDetails = false;

                    for (Sound sound : sounds) {
                        if (sound.getTimestamp().after(minTime) && sound.getTimestamp().before(maxTime)) {
                            foundAnyDetails = true;

                            boolean foundAnyFaces = false;

                            for (Face face : faces) {
                                if (face.getTimestamp().after(minTime) && face.getTimestamp().before(maxTime)) {
                                    foundAnyFaces = true;

                                    updateDB(loc, move, sound, face);
                                }
                            }

                            if (!foundAnyFaces) {
                                updateDB(loc, move, sound, null);
                            }
                        }
                    }

                    if (!foundAnyDetails) {
                        updateDB(loc, move, null, null);
                    }
                }
            }
        }
    }

    private static void updateDB(Location loc, Move move, Sound sound, Face face) {
        Action action = insertOrGet(loc, move, sound, face);

        Date averageTime = getAverageTime(loc, move, sound, face);

        insertMain(action, averageTime);
    }

    private static Action insertOrGet(Location loc, Move move, Sound sound, Face face) {

        Action ac = tryGetAction(loc, move, sound, face);

        if (ac == null) {
            String name = ActionNaming.GetActionName(loc.getProximity(), move.getMovementType(), sound.getSoundName(), face.getFaceName());

            return insertAction(loc, move, sound, face, name);
        }

        return ac;

		//actually should insert the action into DB if it isn't there or should retrieve the existing action
        //in both cases, the output should be a valid one
        // NOTE -> if insert than should call NAMING
    }

    private static Action insertAction(Location loc, Move move, Sound sound, Face face, String name) {
        return inferenceService.addAction(loc, move, sound, face, name);
    }

    private static Action tryGetAction(Location loc, Move move, Sound sound, Face face) {
        return inferenceService.getAction(loc, move, sound, face);
    }

    private static Date getAverageTime(Location loc, Move move, Sound sound, Face face) {

        ArrayList<Date> dates = new ArrayList<>();

        dates.add(loc.getTimestamp());
        dates.add(move.getStartMoment());

        if (face != null) {
            dates.add(face.getTimestamp());
        }

        if (sound != null) {
            dates.add(sound.getTimestamp());
        }

        return getAverageTime(dates);
    }

    private static Date getAverageTime(ArrayList<Date> dates) {
        BigInteger total = BigInteger.ZERO;
        for (Date date : dates) {
            total = total.add(BigInteger.valueOf(date.getTime()));
        }
        BigInteger averageMillis = total.divide(BigInteger.valueOf(dates.size()));
        Date averageDate = new Date(averageMillis.longValue());

        return averageDate;
    }

    private static void insertMain(Action action, Date moment) {
        inferenceService.addMain(action, moment);
    }
    
    public static void setService(InferenceService newInferenceService) {
        inferenceService = newInferenceService;
    }
}
