package mydiaryweb.module.mdqa.nlp;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.time.*;
import edu.stanford.nlp.util.CoreMap;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeNLP {

	AnnotationPipeline pipeline;

	public DateTimeNLP() {
		pipeline = new AnnotationPipeline();
		Properties props = new Properties();

        //
        props.setProperty("sutime.markTimeRanges", "true");
        props.setProperty("sutime.includeRange", "true");

		pipeline.addAnnotator(new TokenizerAnnotator(false));
		pipeline.addAnnotator(new WordsToSentencesAnnotator(false));
		pipeline.addAnnotator(new POSTaggerAnnotator(false));
		pipeline.addAnnotator(new TimeAnnotator("sutime", props));

	}

	public HashMap<String, SUTime.Temporal> extractTemporalDataWithTodayReference(String text){
        DateTime date = new DateTime();
		return extractTemporalData(text, date);
	}

	public HashMap<String, SUTime.Temporal> extractTemporalData(String text, DateTime rdt) {

		HashMap<String, SUTime.Temporal> tmp = new HashMap<String, SUTime.Temporal>();

		Annotation annotation = new Annotation(text);
		//If present, then the string is interpreted as a date/time and used as the reference document date with respect to which other temporal expressions are resolved
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        String modifiedDate = formatter.print(rdt);
		annotation.set(CoreAnnotations.DocDateAnnotation.class, modifiedDate);

		pipeline.annotate(annotation);

		List<CoreMap> timexAnnsAll = annotation.get(TimeAnnotations.TimexAnnotations.class);
	      for (CoreMap cm : timexAnnsAll) {
	    	//Required either at the entire annotation level or per sentence level.
	        List<CoreLabel> tokens = cm.get(CoreAnnotations.TokensAnnotation.class);

	        String label = cm.get(CoreAnnotations.TextAnnotation.class);
	        SUTime.Temporal tst = cm.get(TimeExpression.Annotation.class).getTemporal();

            /*SUTime.Range range = tst.getRange();
            SUTime.IsoDate isoDate = new SUTime.IsoDate(2014, 12, 26);
            //range.resolve(isoDate);
            System.out.println("===========================");
	        System.out.println(label + " " + tst);
	        System.out.println(range.getJodaTimeInterval().getStart());
	        System.out.println(range.beginTime().getJodaTimeInstant().toDateTime());
	        System.out.println(range.beginTime().getJodaTimePartial().toDateTime(rdt));
	        System.out.println(tst.getDuration());
	        System.out.println(tst.getTime());
	        System.out.println("===========================");*/


	        tmp.put(label, tst);
	      }

	     return tmp;
	}
}
