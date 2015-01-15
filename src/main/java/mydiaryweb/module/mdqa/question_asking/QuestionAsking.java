package mydiaryweb.module.mdqa.question_asking;

import java.util.ArrayList;
import java.util.Iterator;

public class QuestionAsking {
	private ArrayList<QuestionObject> questionObjects;
	private QuestionGenerator questionGenerator;

	public QuestionAsking() {
		questionObjects = new ArrayList<QuestionObject>();
		questionGenerator = new QuestionGenerator();
	}

	public QuestionObject askQuestion() {
		cleanList();
		if (questionObjects.size() > 0) {
			QuestionObject questionObj = questionObjects.get(0);
			String question = questionGenerator.generateQuestion(questionObj);
			questionObjects.remove(0);
			// questtion asking
			return questionObj;

		} else {
			return null;
		}
	}

	/** Remove elements that are obsolete */
	public void cleanList() {

		Iterator<QuestionObject> it = questionObjects.iterator();

		while (it.hasNext()) {
			QuestionObject obj = it.next();
			/*
			 * if(){// check if element is obsolete or not and if it is remove
			 * it it.remove(); }
			 */

		}
	}
}