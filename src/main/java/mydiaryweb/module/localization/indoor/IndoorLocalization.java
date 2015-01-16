package mydiaryweb.module.localization.indoor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import mydiaryweb.entity.localization.output.Location;

/**
 *
 * @author Daniel
 */
public class IndoorLocalization {
    public static List<Location> mergeIndoorWithOudoor(
            List<Location> indoorList,
            List<Location> outdoorList
    ) {
        List<Location> filteredOutdoorList = new ArrayList<>();

        for (Location indoor : indoorList) {
            for (Location outdoor : outdoorList) {
                if(Math.abs(
                        indoor.getTimestamp().getTime()
                                - outdoor.getTimestamp().getTime())
                        > 5 * 1000) {
                    filteredOutdoorList.add(outdoor);
                }
            }
        }

        List<Location> finalList = new ArrayList<>();
        finalList.addAll(indoorList);
        finalList.addAll(filteredOutdoorList);
        Collections.sort(finalList, new Comparator<Location>() {

            @Override
            public int compare(Location o1, Location o2) {
                if (o1.getTimestamp().getTime() > o2.getTimestamp().getTime())
                    return 1;
                else if (o1.getTimestamp().getTime() < o2.getTimestamp().getTime())
                    return -1;
                return 0;
            }

        });
        return finalList;
    }
}
