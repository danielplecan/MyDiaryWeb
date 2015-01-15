package mydiaryweb.module.mdqa.nlp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;


import edu.stanford.nlp.util.CoreMap;

public class StanfordNLP {

    public StanfordCoreNLP pipeline;


    public StanfordNLP() {
        init();
    }

    public HashSet<String> stopWords = new HashSet<String>();

    public void init() {
        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner");
        pipeline = new StanfordCoreNLP(props);
    }


    public List<String> getSentWords(String text) {
        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
        List<String> words = new ArrayList<String>();

        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
                String word = token.get(TextAnnotation.class);
                words.add(word);
            }
        }
        return words;
    }

    public List<String> getNERTags(String text) {
        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
        List<String> ner = new ArrayList<String>();


        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
                String nerT = token.get(NamedEntityTagAnnotation.class);
                ner.add(nerT);
            }
        }

        return ner;
    }

    public List<String> getPosTags(String text) {
        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
        List<String> pos = new ArrayList<String>();

        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
                String posT = token.get(PartOfSpeechAnnotation.class);
                pos.add(posT);
            }
        }

        return pos;
    }

    public List<String> getStemmedWords(String text) {
        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
        List<String> stemmed = new ArrayList<String>();

        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
                stemmed.add(lemma);
            }
        }

        return stemmed;
    }

    public static String stem(String word) {
        ArrayList<String> stemTypes = new ArrayList<String>(
                Arrays.asList("ied$", "ed$", "ing$", "ies$", "ise$", "ize$", "est$", "ality$",
                        "s$"));

        for (String stemType : stemTypes) {
            word = word.replaceAll(stemType, "*");
        }

        return word;
    }
}
