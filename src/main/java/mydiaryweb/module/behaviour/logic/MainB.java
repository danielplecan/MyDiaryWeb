package mydiaryweb.module.behaviour.logic;
import mydiaryweb.module.behaviour.persistence.FinalOutput;
import mydiaryweb.module.behaviour.persistence.OutputDayObject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import mydiaryweb.module.behaviour.persistence.InputObject;


public class MainB {

	public static void main(String[] args) throws ParseException {
//		Utils.getInfoForBehavior(objects);
		ArrayList<OutputDayObject> output = new ArrayList<>();
		
		InputObject[] vector = new InputObject[200];
		try {
			vector = InputObject.LoadObjects();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<InputObject> input = new ArrayList<InputObject>(Arrays.asList(vector));
		
		ArrayList<String> friends = new ArrayList<String>();
		friends.add("Maria");
		friends.add("Ion");
		friends.add("George");
		friends.add("Remus");
		ArrayList<FinalOutput> myFinalOutput = null;
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = fmt.parse("2014-11-01");
			myFinalOutput = Utils.applyAlgorithm(input,
					new Date(), output, friends);
		
		for (FinalOutput finalOutput : myFinalOutput) {
			System.out.println(finalOutput);
		}
		for (OutputDayObject finalOutput : output) {
			System.out.println(finalOutput);
		}

	}

}
