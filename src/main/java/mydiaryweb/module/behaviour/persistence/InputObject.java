package mydiaryweb.module.behaviour.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputObject {

	private String actionID;
	private String locationID;
	private String facesID;
	private Date date;

	
	public InputObject(String actionID, String locationID,
			String facesID, Date date) {
		super();
		this.actionID = actionID;
		this.locationID = locationID;
		this.facesID = facesID;
		this.date = date;
	}


	public String getActionID() {
		return actionID;
	}

	public void setActionID(String actionID) {
		this.actionID = actionID;
	}

	public String getLocationID() {
		return locationID;
	}

	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}

	public String getFacesID() {
		return facesID;
	}

	public void setFacesID(String faceID) {
		this.facesID = faceID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

	public static InputObject[] LoadObjects() throws FileNotFoundException,
			IOException, ParseException {

		LineNumberReader lnr = new LineNumberReader(new FileReader(new File(
				"noiembrie.txt")));
		lnr.skip(Long.MAX_VALUE);

		int length = lnr.getLineNumber();

		lnr.close();

		BufferedReader in = new BufferedReader(new FileReader("noiembrie.txt"));
		String action = "", location = "", face = "", data = "";
		;
		Date data2 = new Date();

		InputObject[] obiecte = new InputObject[1000];
		String currentLine;
		String split[] = new String[3];
		int i = 0;

		for (i = 0; i < length; i++) {
			String linie = in.readLine();
			split = linie.split(" ");
			System.out.println(split[3]);
			action = split[0];
			location = split[1];
			face = split[2];
			data = split[3];
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			data2 = sdf.parse(data);
			obiecte[i] = new InputObject(action, location, face, data2);

			// TODO code application logic here
		}

		try {

			FileOutputStream fout = new FileOutputStream("address.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			for (i = 1; i <= length; i++) {
				oos.writeObject(obiecte[i]);
				try {
					System.out.println(obiecte[i].actionID + " "
							+ obiecte[i].date + " " + obiecte[i].facesID + " "
							+ obiecte[i].locationID);
				} catch (java.lang.NullPointerException e) {
					// TODO Auto-generated catch block
					System.out.println("Am terminat de importat!");
				}
			}
			oos.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return obiecte;
	}

}
