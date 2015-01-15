package mydiaryweb.module.mdqa.question_asking.bot_engine;

public class ChatterBotFactory {

public ChatterBot create(ChatterBotType type) throws Exception {
    return create(type, null);
}

public ChatterBot create(ChatterBotType type, Object arg) throws Exception {
    switch (type) {
        case CLEVERBOT:
            return new Cleverbot("http://www.cleverbot.com/webservicemin", 35);
        case JABBERWACKY:
            return new Cleverbot("http://jabberwacky.com/webservicemin", 29);
        case PANDORABOTS:
            if (arg == null) {
                throw new Exception("PANDORABOTS needs a botid arg");
            }
            return new Pandorabots(arg.toString());
    }
    return null;
}
}