package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by amin on 2017-05-31.
 */
public class Waypoint {

    private Date timestamp;
    private Position position;
    private Speed speed;
    @JsonProperty("speed_limit")
    private Speed speedLimit;

    public Waypoint() {
        // Suppress Json mapping exception
    }

    public Waypoint(Date timestamp, Position position, Speed speed, Speed speedLimit) {
        this.timestamp = timestamp;
        this.position = position;
        this.speed = speed;
        this.speedLimit = speedLimit;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Speed getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(Speed speedLimit) {
        this.speedLimit = speedLimit;
    }

    public OrganizedResult organizeWaypointsData(List<Waypoint> waypoints) {

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

    private double distanceSpeeding(List<Waypoint> waypoints) {

        List<List<Waypoint>> waypointListNonFiltered = extractSequentialWaypointPairs(waypoints);

        ArrayList<List<Waypoint>> waypointList = waypointListNonFiltered.stream().filter(list -> list.size() != 0)
                .collect(Collectors.toCollection(ArrayList::new));

        return waypointList.stream().mapToDouble(e -> distanceBetweenTwoWaypoints(e.get(0), e.get(e.size() - 1))).sum();

    }

    private double durationSpeeding(List<Waypoint> waypoints) {

        List<List<Waypoint>> waypointListNonFiltered = extractSequentialWaypointPairs(waypoints);

        ArrayList<List<Waypoint>> waypointList = waypointListNonFiltered.stream().filter(list -> list.size() != 0)
                .collect(Collectors.toCollection(ArrayList::new));

        return waypointList.stream().mapToDouble(e -> durationBetweenTwoWaypoints(e.get(0), e.get(e.size() - 1))).sum();

    }

    private double distanceBetweenTwoWaypoints(Waypoint waypointOne, Waypoint waypointTwo) {

        double startLongitude = waypointOne.getPosition().getLongitude().getValue();
        double endLongitude = waypointTwo.getPosition().getLongitude().getValue();
        double startLatitude = waypointOne.getPosition().getLatitude().getValue();
        double endLatitude = waypointTwo.getPosition().getLatitude().getValue();

        double theta = startLongitude - endLongitude;

        double distance = Math.sin(deg2rad(startLatitude)) * Math.sin(deg2rad(endLatitude)) +
                Math.cos(deg2rad(startLatitude)) * Math.cos(deg2rad(endLatitude)) * Math.cos(deg2rad(theta));
        distance = Math.acos(distance);
        distance = rad2deg(distance);
        distance = distance * 60 * 1.1515 * 1.609344; // Distance in kilometer
        return distance;
    }

    private double durationBetweenTwoWaypoints(Waypoint firstWaypoint, Waypoint lastWaypoint) {
        return (lastWaypoint.getTimestamp().getTime() - firstWaypoint.getTimestamp().getTime()) / 1000;
    }

    /**
     * This function extracts the sublist of sequential waypoints in those
     * speed has been higher than the limit.
     * @param waypoints list of all waypoints
     * @return list of sequential waypoints
     */
    private List<List<Waypoint>> extractSequentialWaypointPairs(List<Waypoint> waypoints) {

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

    private double deg2rad(double deg) {
        return deg * Math.PI / 180.0;
    }

    private double rad2deg(double rad) {
        return rad * 180 / Math.PI;
    }


}
