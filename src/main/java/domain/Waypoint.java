package domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor(force = true)
public class Waypoint {

    private final Date timestamp;
    private final Position position;
    private final Speed speed;
    @JsonProperty("speed_limit")
    private final Speed speedLimit;

    public Waypoint(Date timestamp, Position position, Speed speed, Speed speedLimit) {
        this.timestamp = timestamp;
        this.position = position;
        this.speed = speed;
        this.speedLimit = speedLimit;
    }

    public static OrganizedResult organizeWaypointsData(List<Waypoint> waypoints) {

        OrganizedResult result = new OrganizedResult();

        double totalDistance = distanceBetweenTwoWaypoints(waypoints.get(0), waypoints.get(waypoints.size() - 1));
        double totalDuration = durationBetweenTwoWaypoints(waypoints.get(0), waypoints.get(waypoints.size() - 1));
        double distanceSpeeding = distanceSpeeding(waypoints);
        double durationSpeeding = durationSpeeding(waypoints);

        return result
                .withTotalDistance(totalDistance)
                .withTotalDuration(totalDuration)
                .withDistanceSpeeding(distanceSpeeding)
                .withDurationSpeeding(durationSpeeding);
    }

    private static double distanceSpeeding(List<Waypoint> waypoints) {

        List<List<Waypoint>> waypointListNonFiltered = extractSequentialWaypointPairs(waypoints);

        ArrayList<List<Waypoint>> waypointList = waypointListNonFiltered.stream().filter(list -> list.size() != 0)
                .collect(Collectors.toCollection(ArrayList::new));

        return waypointList.stream().mapToDouble(e -> distanceBetweenTwoWaypoints(e.get(0), e.get(e.size() - 1))).sum();

    }

    private static double durationSpeeding(List<Waypoint> waypoints) {

        List<List<Waypoint>> waypointListNonFiltered = extractSequentialWaypointPairs(waypoints);

        ArrayList<List<Waypoint>> waypointList = waypointListNonFiltered.stream().filter(list -> list.size() != 0)
                .collect(Collectors.toCollection(ArrayList::new));

        return waypointList.stream().mapToDouble(e -> durationBetweenTwoWaypoints(e.get(0), e.get(e.size() - 1))).sum();

    }

    private static double distanceBetweenTwoWaypoints(Waypoint waypointOne, Waypoint waypointTwo) {

        double startLongitude = waypointOne.position.getLongitude().getValue();
        double endLongitude = waypointTwo.position.getLongitude().getValue();
        double startLatitude = waypointOne.position.getLatitude().getValue();
        double endLatitude = waypointTwo.position.getLatitude().getValue();

        double theta = startLongitude - endLongitude;

        double distance = Math.sin(deg2rad(startLatitude)) * Math.sin(deg2rad(endLatitude)) +
                Math.cos(deg2rad(startLatitude)) * Math.cos(deg2rad(endLatitude)) * Math.cos(deg2rad(theta));
        distance = Math.acos(distance);
        distance = rad2deg(distance);
        distance = distance * 60 * 1.1515 * 1.609344; // Distance in kilometer
        return distance;
    }

    private static double durationBetweenTwoWaypoints(Waypoint firstWaypoint, Waypoint lastWaypoint) {
        return (lastWaypoint.timestamp.getTime() - firstWaypoint.timestamp.getTime()) / 1000;
    }

    /**
     * This function extracts the sublist of sequential waypoints in those
     * speed has been higher than the limit.
     * @param waypoints list of all waypoints
     * @return list of sequential waypoints in those speed has exceeded the limit
     */
    private static List<List<Waypoint>> extractSequentialWaypointPairs(List<Waypoint> waypoints) {

        List<List<Waypoint>> results = new ArrayList<>();
        int previous = 0;

        for (int current = 0; current < waypoints.size(); current++) {
            if (waypoints.get(current).speed.getValue() <= waypoints.get(current).speedLimit.getValue()) {
                results.add(waypoints.subList(previous, current));
                previous = current + 1;
            }
        }
        results.add(waypoints.subList(previous, waypoints.size()));
        return results;
    }

    private static double deg2rad(double deg) {
        return deg * Math.PI / 180.0;
    }

    private static double rad2deg(double rad) {
        return rad * 180 / Math.PI;
    }


}
