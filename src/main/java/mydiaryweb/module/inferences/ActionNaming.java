package mydiaryweb.module.inferences;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ActionNaming {

	public static void Init(){
		
		DefaultActions = new ArrayList<>();
		
		parseXmlFile();
	}
	
	public static String GetActionName(String loc, String  mov, String sv, String face){
				
		Init();
		
		DefaultAction candidate = null;
		for(DefaultAction da : DefaultActions){
			 
			boolean canBeCandidate = loc.toLowerCase().startsWith(da.loc.toLowerCase())
	        		&& da.mov.toLowerCase().equals(mov.toLowerCase());
			
			 if(canBeCandidate){
				 
				 // min required values
				 if (candidate == null){
					 candidate = da;
				 }
			 }
		        	
			 if (canBeCandidate && da.sv.toLowerCase().equals(sv.toLowerCase())
		        		&& da.face.toLowerCase().equals(face.toLowerCase())){
				 // is the exactly searched action
				 return da.actionName;
			 }
		}
		
		// if found at least one cadidate and no exactly action should return candidate action
		if (candidate != null){
			return candidate.actionName;
		}
		
		//not even min action found
		return null;
	}
	
	private static ArrayList<DefaultAction> DefaultActions;
	private static Document dom;
	
	private static void parseXmlFile(){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
                        
			dom = db.parse(System.getProperty("catalina.home") + File.separator + "files" + File.separator + "defaultNames.xml");
			
			parseDocument();

		}catch(ParserConfigurationException | SAXException | IOException pce) {
			pce.printStackTrace();
		}
	}

	private static void parseDocument(){
		//get the root element
		Element docEle = dom.getDocumentElement();

		//getting locations
		NodeList locations = docEle.getElementsByTagName("location");
		if(locations != null && locations.getLength() > 0) {
			for(int i = 0 ; i < locations.getLength();i++) {
				Element locationElement = (Element)locations.item(i);
				
				//getting movements
				NodeList movements = locationElement.getElementsByTagName("movement");

				if(movements != null && movements.getLength() > 0) {
					for(int j = 0 ; j < movements.getLength();j++) {
						Element movementElement = (Element)movements.item(j);
						
						//getting sounds
						NodeList sounds = locationElement.getElementsByTagName("sounds");

						if(sounds != null && sounds.getLength() > 0) {
							for(int k = 0 ; k < sounds.getLength();k++) {
								Element soundsElement = (Element)sounds.item(k);
								
								//getting faces
								NodeList faces = locationElement.getElementsByTagName("faces");

								if(faces != null && faces.getLength() > 0) {
									for(int l = 0 ; l < faces.getLength();l++) {
										Element facesElement = (Element)faces.item(l);
										
										Element action = (Element)(facesElement.getElementsByTagName("action").item(0));
										
										//adding the action to default Action
										DefaultActions.add(getAction(locationElement,movementElement,soundsElement,facesElement,action));
									}
								}
							}
						}
					}
				}
				
				
			}
		}
	}
	
	private static DefaultAction getAction(Element location, Element movement, Element sounds, Element faces, Element action) {

		String loc = location.getAttribute("value");
		String face =  faces.getAttribute("value");
		String sv =  sounds.getAttribute("value");
		String mov =   movement.getAttribute("value");
		String actionName =  action.getAttribute("value");

		return new DefaultAction(actionName, loc, sv, face,  mov);
	}
}
