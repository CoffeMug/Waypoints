package util;

import domain.Latitude;
import domain.Longitude;
import domain.Position;
import domain.Speed;

/**
 * Created by amin on 2017-06-03.
 */
public class TestData {

    public static final String dateOne ="2016-04-26 08:34:55.000";
    public static final String dateTwo ="2016-04-26 08:35:00.000";
    public static final String dateThree ="2016-04-26 08:35:05.000";
    public static final String dateFour ="2016-04-26 08:35:10.000";
    public static final String dateFive ="2016-04-26 08:35:15.000";
    public static final String dateSix ="2016-04-26 08:35:20.000";

    public static final Position positionOne = new Position(new Latitude(59.334), new Longitude(18.0667));
    public static final Position positionTwo = new Position(new Latitude(59.3337), new Longitude(18.0662));
    public static final Position positionThree = new Position(new Latitude(59.3331), new Longitude(18.0664));
    public static final Position positionFour = new Position(new Latitude(59.3327), new Longitude(18.0665));
    public static final Position positionFive = new Position(new Latitude(59.3323), new Longitude(18.0666));
    public static final Position positionSix = new Position(new Latitude(59.3345), new Longitude(18.0679));


    public static final Speed speedThirty = new Speed(30);
    public static final Speed speedLimitTwenty = new Speed(20);
    public static final Speed speedLimitThirtyFive= new Speed(35);

}
