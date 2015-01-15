package mydiaryweb.module.mdqa.verbalize;

import mydiaryweb.module.mdqa.model.Action;
import mydiaryweb.module.mdqa.question_answering.Question;
import simplenlg.features.Feature;
import simplenlg.features.Tense;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

import java.util.List;

public class Verbalize {
    NLGFactory nlgFactory;
    Realiser realiser;

    public Verbalize() {
        Lexicon lexicon = Lexicon.getDefaultLexicon();
        nlgFactory = new NLGFactory(lexicon);
        realiser = new Realiser(lexicon);
    }

    public String verbalizeAction(Action action, Question question) {
        //Should call text generation module API
        SPhraseSpec p = nlgFactory.createClause();
        p.setSubject("You");

        String verb = "";
        p.setFeature(Feature.TENSE, Tense.PAST);
        switch (question.getQuestionType()) {
            case HUM:
                verb = "meet";
                p.setObject("with " + action.getFace().getName());
                break;
            case TIME:
                verb = "were";
                p.setObject("at " + action.getActionDate().stringify());
                break;
            case LOC:
                verb = "were";

                if (action.getLocation().getAddress() != null && !action.getLocation().getAddress().isEmpty()) {
                    p.setObject("at " + action.getLocation().getAddress());
                    break;
                }

                if (action.getLocation().getLocation() != null && !action.getLocation().getLocation().isEmpty()) {
                    p.setObject("at " + action.getLocation().getLocation());
                    break;
                }

                if (action.getLocation().getLocation() != null && !action.getLocation().getProximity().isEmpty()) {
                    p.setObject("near " + action.getLocation().getLocation());
                    break;
                }

                if (action.getLocation().getVehicleName() != null && !action.getLocation().getVehicleName().isEmpty()) {
                    p.setObject("in the " + action.getLocation().getVehicleName());
                    break;
                }
                break;
        }
        List<String> verbs = question.getWordsByPosType("VB");
        if (!verbs.isEmpty()) {
            verb = verbs.get(0);
        }
        p.setVerb(verb);
        return realiser.realiseSentence(p);
    }
}
