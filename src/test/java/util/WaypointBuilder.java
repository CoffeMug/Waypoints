package util;

import domain.*;

import java.util.Date;

public class WaypointBuilder {

    private Date timestamp = new Date();
    private Position position = new Position(new Latitude(90.00), new Longitude(180.00));
    private Speed speed = new Speed(10);
    private Speed speedLimit = new Speed(10);

    public WaypointBuilder() {
    }

    public WaypointBuilder withTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public WaypointBuilder withPosition(Position position) {
        this.position = position;
        return this;
    }

    public WaypointBuilder withSpeed(Speed speed) {
        this.speed = speed;
        return this;
    }

    public WaypointBuilder withSpeedLimit(Speed speedLimit) {
        this.speedLimit = speedLimit;
        return this;
    }

    public Waypoint build() {
        return new Waypoint(timestamp, position, speed, speedLimit);
    }
}
