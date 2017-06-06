package domain;

import exception.ValidationFailedException;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.TestData;
import util.WaypointBuilder;

import static org.junit.Assert.assertEquals;

/**
 * Created by amin on 2017-06-03.
 */
public class WaypointTest {

    @Test
    public void organizeWaypointsDataTotalDurationDifferentDates() throws ParseException {

        Date dateOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateOne);
        Date dateTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateTwo);

        Waypoint waypointOne = new WaypointBuilder()
                .withTimestamp(dateOne)
                .build();
        Waypoint waypointTwo = new WaypointBuilder()
                .withTimestamp(dateTwo)
                .build();

        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(waypointOne);
        waypoints.add(waypointTwo);

        OrganizedResult result = new Waypoint().organizeWaypointsData(waypoints);

        assertEquals(5, result.getTotalDuration(), 0.001);
    }

    @Test
    public void organizeWaypointsDataTotalDurationSameDates() {


        Waypoint waypointOne = new WaypointBuilder()
                .build();
        Waypoint waypointTwo = new WaypointBuilder()
                .build();

        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(waypointOne);
        waypoints.add(waypointTwo);

        OrganizedResult result = new Waypoint().organizeWaypointsData(waypoints);

        assertEquals(0, result.getTotalDuration(), 0.001);
    }


    @Test
    public void organizeWaypointsDataTotalDistanceDifferentPositions() {

        Waypoint waypointOne = new WaypointBuilder()
                .withPosition(TestData.positionOne)
                .build();
        Waypoint waypointTwo = new WaypointBuilder()
                .withPosition(TestData.positionFive)
                .build();

        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(waypointOne);
        waypoints.add(waypointTwo);

        OrganizedResult result = new Waypoint().organizeWaypointsData(waypoints);

        assertEquals(0.189, result.getTotalDistance(), 0.001);

    }

    @Test
    public void organizeWaypointsDataTotalDistanceSamePositions() {

        Waypoint waypointOne = new WaypointBuilder()
                .withPosition(TestData.positionOne)
                .build();
        Waypoint waypointTwo = new WaypointBuilder()
                .withPosition(TestData.positionOne)
                .build();

        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(waypointOne);
        waypoints.add(waypointTwo);

        OrganizedResult result = new Waypoint().organizeWaypointsData(waypoints);

        assertEquals(0, result.getTotalDistance(), 0.001);

    }

    @Test
    public void organizeWaypointsDataExceedSpeedLimitTwoOfTwoPoints() throws ParseException {

        Date dateOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateOne);
        Date dateTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateTwo);

        Waypoint waypointOne = new WaypointBuilder()
                .withTimestamp(dateOne)
                .withPosition(TestData.positionOne)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitTwenty)
                .build();
        Waypoint waypointTwo = new WaypointBuilder()
                .withTimestamp(dateTwo)
                .withPosition(TestData.positionTwo)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitTwenty)
                .build();

        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(waypointOne);
        waypoints.add(waypointTwo);

        OrganizedResult result = new Waypoint().organizeWaypointsData(waypoints);

        assertEquals(0.044, result.getDistanceSpeeding(), 0.001);
        assertEquals(5, result.getDurationSpeeding(), 0.001);
    }


    @Test
    public void organizeWaypointsDataExceedSpeedLimitFourOfFivePoints() throws ParseException {

        Date dateOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateOne);
        Date dateTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateTwo);
        Date dateThree = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateThree);
        Date dateFour = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateFour);
        Date dateFive = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateFive);

        Waypoint waypointOne = new WaypointBuilder()
                .withTimestamp(dateOne)
                .withPosition(TestData.positionOne)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitTwenty)
                .build();
        Waypoint waypointTwo = new WaypointBuilder()
                .withTimestamp(dateTwo)
                .withPosition(TestData.positionTwo)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitTwenty)
                .build();
        Waypoint waypointThree = new WaypointBuilder()
                .withTimestamp(dateThree)
                .withPosition(TestData.positionThree)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitThirtyFive)
                .build();
        Waypoint waypointFour = new WaypointBuilder()
                .withTimestamp(dateFour)
                .withPosition(TestData.positionFour)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitTwenty)
                .build();
        Waypoint waypointFive = new WaypointBuilder()
                .withTimestamp(dateFive)
                .withPosition(TestData.positionFive)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitTwenty)
                .build();


        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(waypointOne);
        waypoints.add(waypointTwo);
        waypoints.add(waypointThree);
        waypoints.add(waypointFour);
        waypoints.add(waypointFive);

        OrganizedResult result = new Waypoint().organizeWaypointsData(waypoints);

        assertEquals(0.089, result.getDistanceSpeeding(), 0.001);
        assertEquals(10, result.getDurationSpeeding(), 0.001);
    }


    @Test
    public void organizeWaypointsDataExceedSpeedLimitFourOfSixPoints() throws ParseException {

        Date dateOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateOne);
        Date dateTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateTwo);
        Date dateThree = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateThree);
        Date dateFour = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateFour);
        Date dateFive = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateFive);
        Date dateSix = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateSix);

        Waypoint waypointOne = new WaypointBuilder()
                .withTimestamp(dateOne)
                .withPosition(TestData.positionOne)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitTwenty)
                .build();
        Waypoint waypointTwo = new WaypointBuilder()
                .withTimestamp(dateTwo)
                .withPosition(TestData.positionTwo)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitTwenty)
                .build();
        Waypoint waypointThree = new WaypointBuilder()
                .withTimestamp(dateThree)
                .withPosition(TestData.positionThree)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitThirtyFive)
                .build();
        Waypoint waypointFour = new WaypointBuilder()
                .withTimestamp(dateFour)
                .withPosition(TestData.positionFour)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitThirtyFive)
                .build();
        Waypoint waypointFive = new WaypointBuilder()
                .withTimestamp(dateFive)
                .withPosition(TestData.positionFive)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitTwenty)
                .build();

        Waypoint waypointSix = new WaypointBuilder()
                .withTimestamp(dateSix)
                .withPosition(TestData.positionSix)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitTwenty)
                .build();

        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(waypointOne);
        waypoints.add(waypointTwo);
        waypoints.add(waypointThree);
        waypoints.add(waypointFour);
        waypoints.add(waypointFive);
        waypoints.add(waypointSix);

        OrganizedResult result = new Waypoint().organizeWaypointsData(waypoints);

        assertEquals(0.3, result.getDistanceSpeeding(), 0.001);
        assertEquals(10, result.getDurationSpeeding(), 0.001);
    }


    @Test
    public void organizeWaypointsDataExceedSpeedLimitTwoOfThreePoints() throws ParseException {

        Date dateOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateOne);
        Date dateTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateTwo);
        Date dateThree = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateThree);

        Waypoint waypointOne = new WaypointBuilder()
                .withTimestamp(dateOne)
                .withPosition(TestData.positionOne)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitThirtyFive)
                .build();
        Waypoint waypointTwo = new WaypointBuilder()
                .withTimestamp(dateTwo)
                .withPosition(TestData.positionTwo)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitTwenty)
                .build();
        Waypoint waypointThree = new WaypointBuilder()
                .withTimestamp(dateThree)
                .withPosition(TestData.positionThree)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitTwenty)
                .build();

        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(waypointOne);
        waypoints.add(waypointTwo);
        waypoints.add(waypointThree);

        OrganizedResult result = new Waypoint().organizeWaypointsData(waypoints);

        assertEquals(0.068, result.getDistanceSpeeding(), 0.001);
        assertEquals(5, result.getDurationSpeeding(), 0.001);
    }

    @Test
    public void organizeWaypointsDataDistanceSpeedingNormalSpeed() throws ParseException {

        Date dateOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateOne);
        Date dateTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateTwo);

        Waypoint waypointOne = new WaypointBuilder()
                .withTimestamp(dateOne)
                .withPosition(TestData.positionOne)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitThirtyFive)
                .build();
        Waypoint waypointTwo = new WaypointBuilder()
                .withTimestamp(dateTwo)
                .withPosition(TestData.positionFour)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitThirtyFive)
                .build();

        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(waypointOne);
        waypoints.add(waypointTwo);

        OrganizedResult result = new Waypoint().organizeWaypointsData(waypoints);

        assertEquals(0, result.getDistanceSpeeding(), 0.001);

    }


    @Test
    public void organizeWaypointsDataDurationSpeedingNormalSpeed() throws ParseException {

        Date dateOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateOne);
        Date dateTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(TestData.dateTwo);

        Waypoint waypointOne = new WaypointBuilder()
                .withTimestamp(dateOne)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitThirtyFive)
                .build();
        Waypoint waypointTwo = new WaypointBuilder()
                .withTimestamp(dateTwo)
                .withSpeed(TestData.speedThirty)
                .withSpeedLimit(TestData.speedLimitThirtyFive)
                .build();

        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(waypointOne);
        waypoints.add(waypointTwo);

        OrganizedResult result = new Waypoint().organizeWaypointsData(waypoints);

        assertEquals(0, result.getDurationSpeeding(), 0.001);

    }


    @Test(expected = ValidationFailedException.class)
    public void waypointInvalidLatitude() {

        double invalidLatitude = -185.99;
        double validLongitude = 23.89;

        new WaypointBuilder()
                .withPosition(new Position(new Latitude(invalidLatitude), new Longitude(validLongitude)))
                .build();

    }

    @Test(expected = ValidationFailedException.class)
    public void waypointInvalidLongitude() {

        double validLatitude = -23.99;
        double invalidLongitude = 223.89;

        new WaypointBuilder()
                .withPosition(new Position(new Latitude(validLatitude), new Longitude(invalidLongitude)))
                .build();
    }

    @Test(expected = ValidationFailedException.class)
    public void waypointInvalidSpeed() {

        double invalidSpeed = -10.09;

        new WaypointBuilder()
                .withSpeed(new Speed(invalidSpeed))
                .build();
    }

    @Test(expected = ValidationFailedException.class)
    public void waypointInvalidSpeedLimit() {

        double invalidSpeedLimit = -10.09;

        new WaypointBuilder()
                .withSpeedLimit(new Speed(invalidSpeedLimit))
                .build();
    }
}