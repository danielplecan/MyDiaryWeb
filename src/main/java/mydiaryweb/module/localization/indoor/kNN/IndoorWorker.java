package mydiaryweb.module.localization.indoor.kNN;


import mydiaryweb.entity.localization.input.indoor.IndoorLocation;
import mydiaryweb.entity.localization.input.indoor.Measure;
import mydiaryweb.entity.localization.output.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AugustinUser on 1/14/2015.
 */
public class IndoorWorker {
    public static int K = 3;

    public static List<Location> processIndoorData(List<IndoorLocation> indoorLocationsList, List<Measure> measureList) {
        kNN knn = new kNN(indoorLocationsList, K);
        IndoorResultFilter indoorResultFilter = new IndoorResultFilter(knn);
        for (Measure measure : measureList) {
            indoorResultFilter.storeMeasure(measure);
        }
        List<IndoorResult> indoorResultList = indoorResultFilter.processMeasuresQuery();

        List<Location> indoorFinalOutput = new ArrayList<>();
        for(IndoorResult result : indoorResultList) {
            Location location = new Location();
            location.setTimestamp(result.getMeasure().getTimestamp());
            location.setType("indoor");
            location.setAddress(result.getRoom().getLocationAddress());
            location.setProximity(result.getRoom().getLocationName());
            location.setDetails(result.getRoom().getRoomName());

        }
        return indoorFinalOutput;
    }

}
