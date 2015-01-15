package mydiaryweb.dto.localization.kNN;


import mydiaryweb.entity.localization.input.indoor.Measure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 1/9/2015.
 */
public class IndoorResultFilter {
    kNN knn;
    List<IndoorResult> indoorResultList;

    public IndoorResultFilter(kNN knn) {
        this.knn = knn;
        this.indoorResultList = new ArrayList<IndoorResult>();
    }

    void storeMeasure(Measure measure) {
        IndoorResult indoorResult = new IndoorResult(knn.classifyMeasure(measure), measure);
        if (indoorResult != null && !indoorResult.getRoom().getRoomName().toLowerCase().equals("outside")) {
            indoorResultList.add(indoorResult);
        }
    }

    List<IndoorResult> processMeasuresQuery() {
//        List<IndoorResult> filteredIndoorResult = new ArrayList<>();
//        IndoorResult lastIndoorResult = null;
//        int occurrences = 0;
//        for (IndoorResult indoorResult : indoorResultList) {
//            if (lastIndoorResult == null) {
//                lastIndoorResult = indoorResult;
//                occurrences = 1;
//            } else {
//                if (indoorResult.getRoom().getRoomName() == lastIndoorResult.getRoom().getRoomName()) {
//                    occurrences += 1;
//                } else {
//                    if (occurrences > 2) {
//                        if (filteredIndoorResult.size() == 0 || (filteredIndoorResult.size() > 0 &&
//                                filteredIndoorResult.get(filteredIndoorResult.size() - 1).getRoom().getRoomName() != lastIndoorResult.getRoom().getRoomName())) {
//                            filteredIndoorResult.add(lastIndoorResult);
//                        }
//                    }
//                    lastIndoorResult = indoorResult;
//                    occurrences = 1;
//                }
//            }
//        }
//        if (lastIndoorResult != null && occurrences > 2) {
//            if (filteredIndoorResult.size() == 0 || (filteredIndoorResult.size() > 0 &&
//                    filteredIndoorResult.get(filteredIndoorResult.size() - 1).getRoom().getRoomName() != lastIndoorResult.getRoom().getRoomName())) {
//                filteredIndoorResult.add(lastIndoorResult);
//            }
//        }
//        return filteredIndoorResult;
        return indoorResultList;
    }
}
