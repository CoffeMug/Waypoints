package domain;

public class OrganizedResult {

    private double distanceSpeeding;
    private double totalDistance;
    private double durationSpeeding;
    private double totalDuration;

    public OrganizedResult() {
        // Empty constructor
    }

    public double getDistanceSpeeding() {
        return distanceSpeeding;
    }

    public OrganizedResult withDistanceSpeeding(double distanceSpeeding) {
        this.distanceSpeeding = distanceSpeeding;
        return this;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public OrganizedResult withTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
        return this;
    }

    public double getDurationSpeeding() {
        return durationSpeeding;
    }

    public OrganizedResult withDurationSpeeding(double durationSpeeding) {
        this.durationSpeeding = durationSpeeding;
        return this;
    }

    public double getTotalDuration() {
        return totalDuration;
    }

    public OrganizedResult withTotalDuration(double totalDuration) {
        this.totalDuration = totalDuration;
        return this;
    }
}
