package mydiaryweb.module.mdqa.question_asking.bot_engine;

public class ChatterBotThought {
private String[] emotions;
private String text;

public String[] getEmotions() {
    return emotions;
}

public void setEmotions(String[] emotions) {
    this.emotions = emotions;
}

public String getText() {
    return text;
}

public void setText(String text) {
    this.text = text;
}
}