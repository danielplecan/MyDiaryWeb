package mydiaryweb.controller;

import mydiaryweb.entity.faces.output.Face;
import mydiaryweb.entity.inferences.Action;
import mydiaryweb.entity.inferences.Main;
import mydiaryweb.entity.localization.output.Location;
import mydiaryweb.entity.movement.output.Move;
import mydiaryweb.entity.sound.output.Sound;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import simplenlg.features.Feature;
import simplenlg.features.Tense;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import mydiaryweb.service.InferenceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dplecan
 */

@Controller
public class TextGenerationController {
    @Autowired
    private InferenceService inferenceService;

    @RequestMapping(method = RequestMethod.GET, value = "/api/text-generation/get-journal")
    @ResponseBody
    public Map<String, Object> getJournal() {
        Map<String, Object> responseMap = new HashMap<>();


        responseMap.put("journal", generateText(inferenceService.getCurrentDayActivities()));

        return responseMap;
    }

    public ArrayList<String> generateText(List<Main> mainTable) {
        ArrayList<String> finalText = new ArrayList<>();
        String rooms = "Kitchen,Bedroom,Bathroom,Garage,Hall";
        String prepositions = "After that,After <n>,<n> later";
        Random rand = new Random();
        String previousTime = null;

        for (Main main : mainTable) {

            String[] splittedPrepositions = prepositions.split(",");
            String currentPreposition = null;
            if (!finalText.isEmpty())
                currentPreposition = splittedPrepositions[rand.nextInt(splittedPrepositions.length)];

            Lexicon lexicon = Lexicon.getDefaultLexicon();
            NLGFactory nlgFactory = new NLGFactory(lexicon);
            Realiser realiser = new Realiser(lexicon);
            SPhraseSpec q = nlgFactory.createClause();
            SPhraseSpec p = nlgFactory.createClause();
            p.setSubject("I");
            p.setFeature(Feature.TENSE, Tense.PAST);

            Action action = main.getAction();

            Move movementRs = action.getMovement();
            Location locationRs = action.getLocation();
            Face faceRs = action.getFace();
            Sound soundRs = action.getSound();

            if (movementRs != null)
                p.setVerb(movementRs.getMovementType().toLowerCase());
            else p.setVerb("is");

            if (locationRs != null) {
                if (rooms.contains(locationRs.getDetails())) {
                    p.setObject("in the " + locationRs.getDetails().toLowerCase());
                } else
                    p.setObject("to " + locationRs.getDetails());
            }
            if (faceRs != null) {
                p.addComplement("with " + faceRs.getFaceName());
            }

            if (!finalText.isEmpty())
                if (!currentPreposition.contains("<n>"))
                    p.addComplement("at " + locationRs.getTimestamp().getHours());

            String result = realiser.realiseSentence(p);

            if (finalText.isEmpty() == true) {
                finalText.add("This morning, at " + locationRs.getTimestamp().getHours() + ", " + result);
            } else {
                String minutes = computeHoursAndMinutes(Integer.toString(locationRs.getTimestamp().getHours()), previousTime);
                if (currentPreposition.contains("<n>")) {
                    currentPreposition = currentPreposition.replace("<n>", minutes);
                    finalText.add(currentPreposition + ",at " + locationRs.getTimestamp().getHours() + ", " + result);
                } else
                    finalText.add(currentPreposition + ", " + result);
            }
            previousTime = Integer.toString(locationRs.getTimestamp().getHours());
        }
        return finalText;
    }

    private String computeHoursAndMinutes(String currentTime, String previousTime) {
        String result = "";

        String format = "hh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date obj1 = sdf.parse(previousTime);
            Date obj2 = sdf.parse(currentTime);

            long diff = obj2.getTime() - obj1.getTime();
            double diffInHours = diff / ((double) 1000 * 60 * 60);
            if ((int) diffInHours != 0)
                result = (int) diffInHours + " hours and " + (int) ((diffInHours - (int) diffInHours) * 60) + " minutes";
            else
                result = (int) ((diffInHours - (int) diffInHours) * 60) + " minutes";
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.valueOf(result);
    }
}

