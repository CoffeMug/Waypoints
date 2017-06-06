package application;

import domain.OrganizedResult;
import domain.Waypoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.WaypointsParser;

/**
 * Created by amin on 2017-05-31.
 */
class WaypointsApplicationMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(Waypoint.class);

    public static void main (String[] args) {

        if(args.length != 1) {
            LOGGER.error("Wrong number of arguments, please provide the path to json file as argument.");
            System.exit(0);
        }

        OrganizedResult organizedResult = new Waypoint().organizeWaypointsData(WaypointsParser.parsedWaypoints(args[0]));

        LOGGER.info("Distance speeding {} (km).", organizedResult.getDistanceSpeeding());
        LOGGER.info("Duration speeding {} (seconds).", organizedResult.getDurationSpeeding());
        LOGGER.info("Total distance traveled {} (km).", organizedResult.getTotalDistance());
        LOGGER.info("Total duration traveled {} (seconds).", organizedResult.getTotalDuration());

    }
}
