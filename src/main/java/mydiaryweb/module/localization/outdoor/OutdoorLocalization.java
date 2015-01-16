package mydiaryweb.module.localization.outdoor;

import mydiaryweb.entity.localization.input.outdoor.Device;
import mydiaryweb.entity.localization.input.outdoor.RegisteredOutdoorLocation;
import mydiaryweb.entity.localization.input.outdoor.ScannedDevice;
import mydiaryweb.entity.localization.input.outdoor.VisitedOutdoorLocation;
import mydiaryweb.entity.localization.output.Location;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.ws.ProtocolException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dplecan
 */
public class OutdoorLocalization {

    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371; //kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = (double) (earthRadius * c);

        return dist;
    }

    public static String reverseGeocoding(double latitude, double longitude) {

        try {
            String locationString;
            String line;

            URL url = new URL("http://maps.google.com/maps/api/geocode/json?"
                    + "latlng=" + latitude + "," + longitude + "&sensor=false");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            JSONObject jsonObject;
            jsonObject = new JSONObject(stringBuilder.toString());
            JSONObject location;
            location = jsonObject.getJSONArray("results").getJSONObject(0);
            locationString = location.getString("formatted_address");

            return locationString;
        } catch (MalformedURLException ex) {
            Logger.getLogger(OutdoorLocalization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(OutdoorLocalization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(OutdoorLocalization.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Map<String, String[]> getNearbyPlaces(double latitude, double longitude) {
        BufferedReader bufferedReader = null;

        try {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            bufferedReader = new BufferedReader(new FileReader(System.getProperty("catalina.home")
                     + File.separator + "files" + File.separator +"PlacesTypes.txt"));

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("|");
            }

            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

            String requestUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
                    + String.valueOf(latitude) + "," + String.valueOf(longitude) + "&rankby=distance&types="
                    + stringBuilder.toString() + "&key=AIzaSyBsNf0273ba0yg9Zm8qoNBtOrs2JEnj_S0";

            URL url = new URL(requestUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray results = jsonObject.getJSONArray("results");
            Map<String, String[]> places = new HashMap<>();

            JSONObject place;
            int nrOfPlaces;

            if (results.length() > 10) {
                nrOfPlaces = 10;
            } else {
                nrOfPlaces = results.length();
            }

            for (int i = 0; i < nrOfPlaces; i++) {
                place = results.getJSONObject(i);
                String placeName = place.getString("name");
                JSONArray placeTypes = place.getJSONArray("types");
                String[] types = new String[placeTypes.length()];

                for (int j = 0; j < placeTypes.length(); j++) {
                    types[j] = placeTypes.getString(j);
                }

                places.put(placeName, types);
            }
            return places;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(OutdoorLocalization.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException | JSONException ex) {
            Logger.getLogger(OutdoorLocalization.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(OutdoorLocalization.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static boolean verifyDevice(List<Device> deviceList, List<ScannedDevice> scannedDeviceList) {

        for (int i = 0; i < deviceList.size(); i++) {
            for (int j = 0; j < scannedDeviceList.size(); j++) {
                if (deviceList.get(i).getMac().equals(scannedDeviceList.get(j).getMacDevice())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Date convertStringToDate(String dateString) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date date = format.parse(dateString);
            System.out.println(date);
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(OutdoorLocalization.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<Location> locate(List<RegisteredOutdoorLocation> registeredLocations, List<VisitedOutdoorLocation> visitedLocations) {
        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < visitedLocations.size(); i++) {
            double min = 0.101;
            String proximity = null;
            Location location = new Location();
            for (int j = 0; j < registeredLocations.size(); j++) {
                double distanceBetweenLocations = distFrom(visitedLocations.get(i).getLatitude(), visitedLocations.get(i).getLongitude(),
                        registeredLocations.get(j).getLatitude(), registeredLocations.get(j).getLongitude());
                if (distanceBetweenLocations < min) {
                    min = distanceBetweenLocations;
                    proximity = registeredLocations.get(j).getLocationName();
                }
            }
            location.setProximity(proximity);
            HashMap<String, String[]> places = (HashMap<String, String[]>) getNearbyPlaces(visitedLocations.get(i).getLatitude(),
                    visitedLocations.get(i).getLongitude());
            StringBuilder stringBuilder = new StringBuilder();
            for (Entry entry : places.entrySet()) {
                stringBuilder.append(entry.getKey());
                String[] types = (String[]) entry.getValue();
                if (types.length > 0) {
                    stringBuilder.append("[");
                    for (int j = 0; j < types.length - 1; j++) {
                        stringBuilder.append(types[j]).append(",");
                    }
                    stringBuilder.append(types[types.length - 1]).append("];");
                }
            }
            location.setDetails(stringBuilder.toString());
            location.setAddress(reverseGeocoding(visitedLocations.get(i).getLatitude(),
                    visitedLocations.get(i).getLongitude()));
            location.setTimestamp(convertStringToDate(visitedLocations.get(i).getTimestamp()));
            location.setType("outdoor");
            //vehicle name setat de fetele de la Bluetooth
            locations.add(location);
        }

//        for (int i = 0 ; i < locations.size(); i++) {
//            System.out.println(locations.get(i).getAddress() + " " + locations.get(i).getDetails() + 
//                    " " + locations.get(i).getProximity() + " " + locations.get(i).getType());
//        }
        return locations;
    }
}
