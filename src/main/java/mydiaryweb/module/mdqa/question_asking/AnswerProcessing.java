package mydiaryweb.module.mdqa.question_asking;

import mydiaryweb.module.mdqa.question_asking.bot_engine.ChatterBot;
import mydiaryweb.module.mdqa.question_asking.bot_engine.ChatterBotFactory;
import mydiaryweb.module.mdqa.question_asking.bot_engine.ChatterBotSession;
import mydiaryweb.module.mdqa.question_asking.bot_engine.ChatterBotType;

public class AnswerProcessing {
    // default bot api set to CLEVERBOT
    // it can be changed to PANDORABOTS or JABBERWACKY
    private String botMode = "C";
    private String userInputContainer;
    private String userInputReturned;
    ChatterBotSession bot1session;
    ChatterBotSession bot2session;
    ChatterBotSession bot3session;

    public String processAnswer(String userInput, QuestionObject questionObj) {

        this.userInputContainer = userInput;
        try {

            switch (botMode) {
                case "C":
                    userInputReturned = bot1session.think(userInputContainer);
                    break;
                case "P":
                    userInputReturned = bot2session.think(userInputContainer);
                    break;
                case "J":
                    userInputReturned = bot3session.think(userInputContainer);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userInputReturned;

    }

    public String getBotMode() {
        return botMode;
    }

    public void setBotMode(String botModeNew) {
        if (botModeNew.equals("C") || botModeNew.equals("P") || botModeNew.equals("J"))
            botMode = botModeNew;

        try {
            ChatterBotFactory factory = new ChatterBotFactory();

            ChatterBot bot1 = factory.create(ChatterBotType.CLEVERBOT);
            bot1session = bot1.createSession();

            ChatterBot bot2 = factory.create(ChatterBotType.PANDORABOTS, "b0dafd24ee35a477");
            bot2session = bot2.createSession();

            ChatterBot bot3 = factory.create(ChatterBotType.JABBERWACKY);
            bot3session = bot3.createSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}