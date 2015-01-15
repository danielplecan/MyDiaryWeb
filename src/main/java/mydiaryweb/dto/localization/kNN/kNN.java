package mydiaryweb.dto.localization.kNN;

import mydiaryweb.entity.localization.input.indoor.*;

import java.util.*;


public class kNN {
	int maxK;
	List<IndoorLocation> listIndoorLocations;
	Map<IndoorLocation, List<Voter>> voters;

	public kNN(List<IndoorLocation> listIndoorLocations, int maxK) {
		super();
		this.maxK = maxK;
		this.listIndoorLocations = listIndoorLocations;
		voters = new HashMap<IndoorLocation, List<Voter>>();
		for (IndoorLocation il : listIndoorLocations) {
			voters.put(il, new ArrayList<Voter>());
			for (Room r : il.getRooms()) {
				for (Calibration c : r.getCalibrations()) {
					voters.get(il).add(new Voter(r, c));
				}
			}
		}
	}
	
	public IndoorLocation findLocation(Measure m) {
		IndoorLocation location = null;
		int n = 0, n1 = 0;
		Set<String> existentMacs = new HashSet<String>();
		for (MeasureValue mv : m.getMeasures()) {
			existentMacs.add(mv.getMac()); 
		}
		for (IndoorLocation currentLocation : listIndoorLocations) {
			n1 = 0;
			for (Room r : currentLocation.getRooms()) {
				for (Calibration c : r.getCalibrations()) {
					for (CalibrationValue cv : c.getCalibrationValues()) {
						if (existentMacs.contains(cv.getMac())) {
							n1++;
						}
					}
				}
			}
			if (n1 > n) {
				n = n1;
				location = currentLocation;
			}
		}
		return location;
	}
	
	double calculateDistance(Calibration c, Measure m) {
		double d = 0.0, dd = 0.0;
		Map<String, Double> macCalibration = new HashMap<String, Double>();
		for (CalibrationValue cv : c.getCalibrationValues()) {
			macCalibration.put(cv.getMac(), (double)cv.getValue());
		}
		for (MeasureValue mv : m.getMeasures()) {
			if (macCalibration.containsKey(mv.getMac())) {
				dd = (double)mv.getValue() - macCalibration.get(mv.getMac());
				d += dd * dd;
			}
		}
		return Math.sqrt(d);
	}
	
	public Room findRoom(IndoorLocation il, Measure m) {
        if (il == null) {
            return null;
        }
		List<Voter> locationVoters = voters.get(il);
		List<Vote> voteList = new ArrayList<Vote>();
		for (Voter v : locationVoters) {
			voteList.add(new Vote(v, calculateDistance(v.getCalibration(), m)));
		}
		Collections.sort(voteList);
		int k = 0;
		Map<Room, Integer> roomVotes = new HashMap<Room, Integer>();
		for (Vote v : voteList) {
			++k;
			if (k > maxK) {
				break;
			}
			//System.out.println(v.getVoter().getRoom().roomName + " " + v.getDistance());
			if (roomVotes.containsKey(v.getVoter().getRoom())) {
				roomVotes.put(v.getVoter().getRoom(), roomVotes.get(v.getVoter().getRoom()) + 1);
			} else {
				roomVotes.put(v.getVoter().getRoom(), 1);
			}
		}
		Room selectedRoom = null;
		int n = 0;
		for (Room r : roomVotes.keySet()) {
			if (roomVotes.get(r) > n) {
				n = roomVotes.get(r);
				selectedRoom = r;
			}
		}
        return selectedRoom;
	}

	public Room classifyMeasure(Measure m) {
		IndoorLocation il = findLocation(m);
		/*
		for (Voter v : voters.get(il)) {
			System.out.println(v.getRoom().getRoomName() + " " + v.getCalibrationValue().getMac());
		}
		*/
		Room r = findRoom(il, m);
		r.setLocationName(il.getLocationName());
		r.setLocationAddress(il.getAddress());
//		System.out.println("[Info] Location name = " + il.getLocationName());
		return r;
	}
	
}

