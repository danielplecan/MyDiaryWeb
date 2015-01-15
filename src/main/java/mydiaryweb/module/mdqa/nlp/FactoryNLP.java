package mydiaryweb.module.mdqa.nlp;

public class FactoryNLP {

    DateTimeNLP dtNLP;
    StanfordNLP langNLP;
    StopWords stopWords;

    private static FactoryNLP instance = null;

    protected FactoryNLP() {
        // Exists only to defeat instantiation.
    }

    public static FactoryNLP inst() {
        if(instance == null) {
            instance = new FactoryNLP();
        }
        return instance;
    }

    public DateTimeNLP getDateTimeNLP(){
        if (dtNLP == null){
            dtNLP = new DateTimeNLP();
        }

        return dtNLP;
    }

    public StanfordNLP getStanfordNLP(){
        if (langNLP == null){
            langNLP = new StanfordNLP();
        }

        return langNLP;
    }

    public StopWords getStopWords(){
        if (stopWords == null){
            stopWords = new StopWords();
        }

        return stopWords;
    }

}
